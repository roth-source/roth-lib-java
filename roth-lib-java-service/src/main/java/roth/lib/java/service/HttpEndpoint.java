package roth.lib.java.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roth.lib.java.map.Mapper;
import roth.lib.java.map.MapperConfig;
import roth.lib.java.map.form.FormReflector;
import roth.lib.java.map.json.JsonReflector;
import roth.lib.java.map.xml.XmlReflector;
import roth.lib.java.net.http.HttpMethod;
import roth.lib.java.service.annotation.Service;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.service.reflector.ServiceReflector;
import roth.lib.java.type.MimeType;
import roth.lib.java.util.ClassLoaderUtil;

@SuppressWarnings("serial")
public class HttpEndpoint extends HttpServlet
{
	protected static String ORIGIN 								= "Origin";
	protected static String ANY 								= "*";
	protected static String ACCESS_CONTROL_ALLOW_ORIGIN 		= "Access-Control-Allow-Origin";
	protected static String ACCESS_CONTROL_ALLOW_CREDENTIALS 	= "Access-Control-Allow-Credentials";
	protected static String ACCESS_CONTROL_ALLOW_METHODS 		= "Access-Control-Allow-Methods";
	protected static String ACCESS_CONTROL_ALLOW_METHOD 		= "Access-Control-Allow-Method";
	protected static String ACCESS_CONTROL_ALLOW_HEADERS 		= "Access-Control-Allow-Headers";
	protected static String ACCESS_CONTROL_EXPOSE_HEADERS 		= "Access-Control-Expose-Headers";
	protected static String ACCESS_CONTROL_MAX_AGE 				= "Access-Control-Max-Age";
	protected static String CONTENT_TYPE 						= "Content-Type";
	protected static String ACCEPT		 						= "Accept";
	protected static String ALLOWED_METHODS 					= "GET, POST";
	protected static List<HttpMethod> SUPPORTED_METHODS			= Arrays.asList(HttpMethod.GET, HttpMethod.POST);
	protected static List<String> LOCALHOSTS 					= Arrays.asList("localhost", "127.0.0.1");
	protected static String ENDPOINT 							= "_endpoint";
	protected static String SERVICE 							= "service";
	protected static String METHOD 								= "method";
	protected static Pattern SERVICE_METHOD_PATTERN 			= Pattern.compile("(?:^|/)(?<" + SERVICE + ">\\w+)/(?<" + METHOD + ">\\w+)(?:/|$)");
	
	protected LinkedHashMap<String, ServiceReflector> serviceReflectorMap = new LinkedHashMap<String, ServiceReflector>();
	
	protected FormReflector requestFormReflector = new FormReflector();
	protected JsonReflector requestJsonReflector = new JsonReflector();
	protected XmlReflector requestXmlReflector = new XmlReflector();
	protected JsonReflector responseJsonReflector = new JsonReflector();
	protected XmlReflector responseXmlReflector = new XmlReflector();
	
	protected MapperConfig requestFormConfig = new MapperConfig();
	protected MapperConfig requestJsonConfig = new MapperConfig();
	protected MapperConfig requestXmlConfig = new MapperConfig();
	protected MapperConfig responseJsonConfig = new MapperConfig();
	protected MapperConfig responseXmlConfig = new MapperConfig();
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException
	{
		LinkedList<Class<?>> classes = ClassLoaderUtil.getClasses();
		for(Class<?> klass : classes)
		{
			if(HttpService.class.isAssignableFrom(klass))
			{
				Class<? extends HttpService> serviceClass = (Class<HttpService>) klass;
				Service service = klass.getDeclaredAnnotation(Service.class);
				if(service != null)
				{
					String serviceName = service.name();
					if(serviceName != null && !serviceName.isEmpty())
					{
						serviceReflectorMap.put(serviceName, new ServiceReflector(serviceClass, serviceName));
					}
				}
			}
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		Object methodResponse = null;
		LinkedList<HttpError> errors = new LinkedList<HttpError>();
		MimeType requestContentType = getRequestContentType(request, response);
		MimeType responseContentType = getResponseContentType(request, response);
		Mapper responseMapper = getResponseMapper(request, response, responseContentType);
		response.setHeader(CONTENT_TYPE, responseContentType.toString());
		String origin = request.getHeader(ORIGIN);
		boolean mock = origin == null;
		boolean local = isLocal(request, response);
		boolean dev = mock || local;
		try
		{
			if(mock || local || isOriginAllowed(request, response))
			{
				if(local)
				{
					response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ANY);
				}
				else
				{
					response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
					response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
				}
				response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
				HttpMethod httpMethod = HttpMethod.fromString(request.getMethod());
				if(httpMethod != null)
				{
					if(SUPPORTED_METHODS.contains(httpMethod))
					{
						HttpServiceMethod serviceMethod = getServiceMethod(request, response);
						if(serviceMethod != null)
						{
							if(!ENDPOINT.equalsIgnoreCase(serviceMethod.getServiceName()))
							{
								HttpService service = getAnnotatedService(request, response, serviceMethod.getServiceName());
								if(service != null)
								{
									service.setServletContext(request.getServletContext()).setHttpServletRequest(request).setHttpServletResponse(response);
									service.setService(serviceMethod.getServiceName()).setMethod(serviceMethod.getMethodName());
									MethodReflector methodReflector = getMethodReflector(request, response, service.getClass(), serviceMethod.getServiceName(), serviceMethod.getMethodName());
									if(methodReflector != null)
									{
										responseMapper.setContext(methodReflector.getContext());
										if(methodReflector.isHttpMethodImplemented(httpMethod))
										{
											boolean hasAjax = methodReflector.isAjax();
											boolean hasApi = methodReflector.isApi();
											boolean ajaxAuthenticated = hasAjax && (!methodReflector.isAuthenticated() || service.isAjaxAuthenticated(methodReflector));
											boolean apiAuthenticated = hasApi && (!methodReflector.isAuthenticated() || service.isApiAuthenticated(methodReflector));
											if(ajaxAuthenticated || apiAuthenticated)
											{
												Object methodRequest = null;
												Parameter parameter = methodReflector.getParameter();
												if(parameter != null)
												{
													InputStream input = methodReflector.isGzippedInput() ? new GZIPInputStream(request.getInputStream()) : request.getInputStream();
													Type methodParameterType = parameter.getParameterizedType();
													service.setRequestContentType(requestContentType);
													Mapper requestMapper = getRequestMapper(request, response, requestContentType);
													service.setRequestMapper(requestMapper);
													methodRequest = requestMapper.setContext(methodReflector.getContext()).deserialize(input, methodParameterType);
												}
												if(dev)
												{
													debugRequest(request, methodRequest);
												}
												boolean validCsrf = !(ajaxAuthenticated && methodReflector.isAuthenticated() && !service.isValidCsrfToken(methodRequest, dev));
												if(validCsrf)
												{
													boolean authorized = service.isAuthorized(methodReflector, methodRequest);
													if(authorized)
													{
														//errors.addAll(service.validate(methodRequest));
														if(errors.isEmpty())
														{
															service.setResponseContentType(responseContentType);
															service.setResponseMapper(responseMapper);
															try
															{
																methodResponse = methodReflector.invoke(service, methodRequest);
															}
															catch(InvocationTargetException e)
															{
																if(e.getCause() != null)
																{
																	errors.add(service.exception(HttpErrorType.SERVICE_EXCEPTION.error(), e.getCause()));
																}
															}
														}
													}
													else
													{
														errors.add(HttpErrorType.SERVICE_NOT_AUTHORIZED.error());
													}
												}
												else
												{
													errors.add(HttpErrorType.SERVICE_CSRF_TOKEN_INVALID.error());
												}
											}
											else
											{
												if(hasAjax && !ajaxAuthenticated)
												{
													errors.add(HttpErrorType.SERVICE_AJAX_NOT_AUTHENTICATED.error());
												}
												else if(hasApi && !apiAuthenticated)
												{
													errors.add(HttpErrorType.SERVICE_API_NOT_AUTHENTICATED.error());
												}
												else
												{
													errors.add(HttpErrorType.SERVICE_CHANNEL_NOT_IMPLEMENTED.error());
												}
											}
										}
										else
										{
											errors.add(HttpErrorType.HTTP_METHOD_NOT_IMPLEMENTED.error());
										}
									}
									else
									{
										errors.add(HttpErrorType.SERVICE_METHOD_MISSING.error());
									}
								}
								else
								{
									errors.add(HttpErrorType.SERVICE_NOT_IMPLEMENTED.error());
								}
							}
							else
							{
								methodResponse = endpoint(request, response, serviceMethod.getMethodName());
							}
						}
						else
						{
							errors.add(HttpErrorType.SERVICE_METHOD_NAME_MISSING.error());
						}
					}
					else
					{
						errors.add(HttpErrorType.HTTP_METHOD_NOT_SUPPORTED.error());
					}
				}	
				else
				{
					errors.add(HttpErrorType.HTTP_METHOD_MISSING.error());
				}
			}
			else
			{
				errors.add(HttpErrorType.HTTP_ORIGIN_UNSUPPORTED.error());
			}
		}
		catch(Throwable e)
		{
			if(dev)
			{
				e.printStackTrace();
			}
		}
		
		if(!errors.isEmpty())
		{
			if(methodResponse == null)
			{
				methodResponse = new HttpServiceResponse().setErrors(errors);
			}
			else if(methodResponse instanceof HttpServiceResponse)
			{
				((HttpServiceResponse) methodResponse).setErrors(errors);
			}
		}
		
		if(methodResponse != null)
		{
			if(dev && methodResponse instanceof HttpServiceResponse)
			{
				HttpServiceResponse serviceResponse = (HttpServiceResponse) methodResponse;
				HttpSession session = request.getSession(false);
				if(session != null)
				{
					serviceResponse.setJsessionId(session.getId());
					Object csrfTokenObject = session.getAttribute(HttpService.CSRF_TOKEN);
					if(csrfTokenObject != null && csrfTokenObject instanceof String)
					{
						serviceResponse.setCsrfToken((String) csrfTokenObject);
					}
				}
			}
			try(OutputStream output = response.getOutputStream())
			{
				responseMapper.serialize(methodResponse, output);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		debugResponse(response, methodResponse);
	}
	
	protected Object endpoint(HttpServletRequest request, HttpServletResponse response, String methodName)
	{
		return null;
	}

	protected boolean isLocal(HttpServletRequest request, HttpServletResponse response)
	{
		return getLocalHosts(request, response).contains(request.getServerName());
	}
	
	protected List<String> getLocalHosts(HttpServletRequest request, HttpServletResponse response)
	{
		return LOCALHOSTS;
	}
	
	protected boolean isOriginAllowed(HttpServletRequest request, HttpServletResponse response)
	{
		return true;
	}
	
	protected HttpServiceMethod getServiceMethod(HttpServletRequest request, HttpServletResponse response)
	{
		HttpServiceMethod serviceMethod = null;
		Matcher matcher = SERVICE_METHOD_PATTERN.matcher(request.getPathInfo());
		if(matcher.find())
		{
			serviceMethod = new HttpServiceMethod(matcher.group(SERVICE), matcher.group(METHOD));
		}
		return serviceMethod;
	}
	
	protected final HttpService getAnnotatedService(HttpServletRequest request, HttpServletResponse response, String serviceName)
	{
		HttpService service = null;
		ServiceReflector serviceReflector = serviceReflectorMap.get(serviceName);
		if(serviceReflector != null)
		{
			service = serviceReflector.getService();
		}
		if(service == null)
		{
			service = getService(request, response, serviceName);
		}
		return service;
	}
	
	protected HttpService getService(HttpServletRequest request, HttpServletResponse response, String serviceName)
	{
		return null;
	}
	
	protected ServiceReflector getServiceReflector(HttpServletRequest request, HttpServletResponse response, Class<? extends HttpService> serviceClass, String serviceName)
	{
		ServiceReflector serviceReflector = serviceReflectorMap.get(serviceName);
		if(serviceReflector == null)
		{
			serviceReflector = new ServiceReflector(serviceClass, serviceName);
			serviceReflectorMap.put(serviceName, serviceReflector);
		}
		return serviceReflector;
	}
	
	protected MethodReflector getMethodReflector(HttpServletRequest request, HttpServletResponse response, Class<? extends HttpService> serviceClass, String serviceName, String methodName)
	{
		MethodReflector methodReflector = null;
		ServiceReflector serviceReflector = getServiceReflector(request, response, serviceClass, serviceName);
		if(serviceReflector != null)
		{
			methodReflector = serviceReflector.getServiceMethodReflectorMap().get(methodName);
		}
		return methodReflector;
	}
	
	protected MimeType getRequestContentType(HttpServletRequest request, HttpServletResponse response)
	{
		MimeType contentType = null;
		String contentTypeHeader = request.getHeader(CONTENT_TYPE);
		if(contentTypeHeader != null)
		{
			int index = contentTypeHeader.indexOf(";");
			if(index > -1)
			{
				contentTypeHeader = contentTypeHeader.substring(0, index);
			}
			contentType = MimeType.fromString(contentTypeHeader);
			if(contentType != null)
			{
				switch(contentType)
				{
					case APPLICATION_JSON:
					case APPLICATION_XML:
					case APPLICATION_X_WWW_FORM_URLENCODED:
					{
						break;
					}
					case TEXT_PLAIN:
					default:
					{
						contentType = null;
						break;
					}
				}
			}
		}
		return contentType != null ? contentType : MimeType.APPLICATION_JSON;
	}
	
	protected MimeType getResponseContentType(HttpServletRequest request, HttpServletResponse response)
	{
		MimeType contentType = null;
		String acceptHeader = request.getHeader(ACCEPT);
		if(acceptHeader != null)
		{
			int index = acceptHeader.indexOf(";");
			if(index > -1)
			{
				acceptHeader = acceptHeader.substring(0, index);
			}
			List<String> acceptTypes = Arrays.asList(acceptHeader.split(","));
			accepts: for(String acceptType : acceptTypes)
			{
				contentType = MimeType.fromString(acceptType);
				if(contentType != null)
				{
					switch(contentType)
					{
						case APPLICATION_JSON:
						case APPLICATION_XML:
						{
							break accepts;
						}
						default:
						{
							contentType = null;
							break;
						}
					}
				}
			}
		}
		return contentType != null ? contentType : MimeType.APPLICATION_JSON;
	}
	
	protected Mapper getRequestMapper(HttpServletRequest request, HttpServletResponse response, MimeType contentType)
	{
		Mapper mapper = null;
		switch(contentType)
		{
			case APPLICATION_XML:
			{
				mapper = getRequestXmlReflector().getMapper(getRequestXmlConfig());
				break;
			}
			case APPLICATION_X_WWW_FORM_URLENCODED:
			{
				mapper = getRequestFormReflector().getMapper(getRequestFormConfig());
				break;
			}
			default:
			{
				mapper = getRequestJsonReflector().getMapper(getRequestJsonConfig());
				break;
			}
		}
		return mapper;
	}
	
	protected Mapper getResponseMapper(HttpServletRequest request, HttpServletResponse response, MimeType contentType)
	{
		Mapper mapper = null;
		switch(contentType)
		{
			case APPLICATION_XML:
			{
				mapper = getResponseXmlReflector().getMapper(getResponseXmlConfig());
				break;
			}
			default:
			{
				mapper = getResponseJsonReflector().getMapper(getResponseJsonConfig());
				break;
			}
		}
		return mapper;
	}
	
	public FormReflector getRequestFormReflector()
	{
		return requestFormReflector;
	}

	public JsonReflector getRequestJsonReflector()
	{
		return requestJsonReflector;
	}
	
	public XmlReflector getRequestXmlReflector()
	{
		return requestXmlReflector;
	}
	
	public JsonReflector getResponseJsonReflector()
	{
		return responseJsonReflector;
	}
	
	public XmlReflector getResponseXmlReflector()
	{
		return responseXmlReflector;
	}
	
	public MapperConfig getRequestFormConfig()
	{
		return requestFormConfig;
	}
	
	public MapperConfig getRequestJsonConfig()
	{
		return requestJsonConfig;
	}
	
	public MapperConfig getRequestXmlConfig()
	{
		return requestXmlConfig;
	}
	
	public MapperConfig getResponseJsonConfig()
	{
		return responseJsonConfig;
	}
	
	public MapperConfig getResponseXmlConfig()
	{
		return responseXmlConfig;
	}
	
	protected void debugRequest(HttpServletRequest request, Object methodRequest)
	{
		System.out.println();
		System.out.println();
		System.out.println("REQUEST");
		System.out.println("----------------------------------");
		System.out.println(request.getMethod() + " " + request.getRequestURI());
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements())
		{
			String name = names.nextElement();
			String value = request.getHeader(name);
			System.out.println(name + ": " + value);
		}
		System.out.println();
		if(methodRequest != null)
		{
			getRequestJsonReflector().getMapper(new MapperConfig(true)).serialize(methodRequest, System.out);
			System.out.println();
		}
	}
	
	protected void debugResponse(HttpServletResponse response, Object methodResponse)
	{
		System.out.println();
		System.out.println();
		System.out.println("RESPONSE");
		System.out.println("----------------------------------");
		System.out.println(response.getStatus());
		for(String name : response.getHeaderNames())
		{
			String value = response.getHeader(name);
			System.out.println(name + ": " + value);
		}
		System.out.println();
		if(methodResponse != null)
		{
			getResponseJsonReflector().getMapper(new MapperConfig(true)).serialize(methodResponse, System.out);
			System.out.println();
		}
	}
	
}
