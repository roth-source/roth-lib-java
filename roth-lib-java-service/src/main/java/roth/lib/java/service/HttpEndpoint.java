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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.net.http.HttpMethod;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.service.annotation.Service;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.service.reflector.ServiceReflector;
import roth.lib.java.type.MimeType;

@SuppressWarnings("serial")
public abstract class HttpEndpoint extends HttpServlet
{
	protected static String ORIGIN 								= "Origin";
	protected static String ANY 								= "*";
	protected static String ACCESS_CONTROL_ALLOW_ORIGIN 		= "Access-Control-Allow-Origin";
	protected static String ACCESS_CONTROL_ALLOW_CREDENTIALS 	= "Access-Control-Allow-Credentials";
	protected static String ACCESS_CONTROL_ALLOW_METHODS 		= "Access-Control-Allow-Methods";
	protected static String CONTENT_TYPE 						= "Content-Type";
	protected static String ACCEPT		 						= "Accept";
	protected static String ALLOWED_METHODS 					= "GET, POST";
	protected static List<HttpMethod> SUPPORTED_METHODS			= Arrays.asList(HttpMethod.GET, HttpMethod.POST);
	protected static List<String> LOCALHOSTS 					= Arrays.asList("localhost", "127.0.0.1");
	protected static String ENDPOINT 							= "_endpoint";
	protected static String SERVICE 							= "service";
	protected static String METHOD 								= "method";
	protected static Pattern SERVICE_METHOD_PATTERN 			= Pattern.compile("(?:^|/)(?<" + SERVICE + ">\\w+)/(?<" + METHOD + ">\\w+)(?:/|$)");
	
	protected static LinkedHashMap<String, ServiceReflector> serviceReflectorMap = new LinkedHashMap<String, ServiceReflector>();
	
	protected MapperReflector mapperReflector = MapperReflector.get();
	protected MapperConfig mapperConfig = MapperConfig.get();
	protected MapperConfig debugMapperConfig = MapperConfig.debug();
	
	public static void register(Class<? extends HttpService> serviceClass)
	{
		Service service = serviceClass.getDeclaredAnnotation(Service.class);
		if(service != null)
		{
			String serviceName = service.name();
			if(serviceName != null && !serviceName.isEmpty())
			{
				serviceReflectorMap.put(serviceName, new ServiceReflector(serviceClass, serviceName));
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
		boolean dev = isDev(request, response);
		try
		{
			if(dev || isOriginAllowed(request, response))
			{
				String origin = request.getHeader(ORIGIN);
				if(origin != null)
				{
					response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
					response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
				}
				else
				{
					response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ANY);
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
												Mapper requestMapper = null;
												Parameter parameter = methodReflector.getParameter();
												if(parameter != null)
												{
													InputStream input = methodReflector.isGzippedInput() ? new GZIPInputStream(request.getInputStream()) : request.getInputStream();
													Type methodParameterType = parameter.getParameterizedType();
													service.setRequestContentType(requestContentType);
													requestMapper = getRequestMapper(request, response, requestContentType);
													//service.setRequestMapper(requestMapper);
													methodRequest = requestMapper.setContext(methodReflector.getContext()).deserialize(input, methodParameterType);
												}
												if(dev)
												{
													debugRequest(request, methodRequest, requestMapper);
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
															//service.setResponseMapper(responseMapper);
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
			exception(request, response, e);
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
		if(dev)
		{
			debugResponse(response, methodResponse, responseMapper);
		}
	}
	
	public abstract void exception(HttpServletRequest request, HttpServletResponse response, Throwable e);
	
	protected Object endpoint(HttpServletRequest request, HttpServletResponse response, String methodName)
	{
		return null;
	}

	protected boolean isDev(HttpServletRequest request, HttpServletResponse response)
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
			service = serviceReflector.service();
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
			methodReflector = serviceReflector.getMethodReflectorMap().get(methodName);
		}
		return methodReflector;
	}
	
	protected MapperReflector getMapperReflector()
	{
		return mapperReflector;
	}
	
	protected MapperConfig getMapperConfig()
	{
		return mapperConfig;
	}
	
	protected MapperConfig getDebugMapperConfig()
	{
		return debugMapperConfig;
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
				mapper = getMapperReflector().getMapper(MapperType.XML, getMapperConfig());
				break;
			}
			case APPLICATION_X_WWW_FORM_URLENCODED:
			case MULTIPART_FORM_DATA:
			{
				mapper = getMapperReflector().getMapper(MapperType.FORM, getMapperConfig());
				break;
			}
			default:
			{
				mapper = getMapperReflector().getMapper(MapperType.JSON, getMapperConfig());
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
				mapper = getMapperReflector().getMapper(MapperType.XML, getMapperConfig());
				break;
			}
			default:
			{
				mapper = getMapperReflector().getMapper(MapperType.JSON, getMapperConfig());
				break;
			}
		}
		return mapper;
	}
	
	protected void debugRequest(HttpServletRequest request, Object methodRequest, Mapper mapper)
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
		if(methodRequest != null && mapper != null)
		{
			mapper.setMapperConfig(getDebugMapperConfig()).serialize(methodRequest);
			System.out.println();
		}
	}
	
	protected void debugResponse(HttpServletResponse response, Object methodResponse, Mapper mapper)
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
		if(methodResponse != null && mapper != null)
		{
			mapper.setMapperConfig(getDebugMapperConfig()).serialize(methodResponse);
			System.out.println();
		}
	}
	
}
