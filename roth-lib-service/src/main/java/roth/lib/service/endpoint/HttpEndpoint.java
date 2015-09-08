package roth.lib.service.endpoint;

import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
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

import roth.lib.map.Mapper;
import roth.lib.map.MapperConfig;
import roth.lib.map.form.FormReflector;
import roth.lib.map.json.JsonReflector;
import roth.lib.map.xml.XmlReflector;
import roth.lib.net.http.HttpMethod;
import roth.lib.net.http.type.MimeType;
import roth.lib.service.HttpService;
import roth.lib.service.HttpServiceMethod;
import roth.lib.service.HttpServiceResponse;
import roth.lib.service.HttpServiceType;
import roth.lib.service.reflector.MethodReflector;
import roth.lib.service.reflector.ServiceReflector;
import roth.lib.service.task.HttpTaskService;

@SuppressWarnings("serial")
public class HttpEndpoint extends HttpServlet
{
	protected static String ORIGIN 								= "Origin";
	protected static String ANY_ORIGIN 							= "*";
	protected static String ACCESS_CONTROL_ALLOW_ORIGIN 		= "Access-Control-Allow-Origin";
	protected static String ACCESS_CONTROL_ALLOW_CREDENTIALS 	= "Access-Control-Allow-Credentials";
	protected static String ACCESS_CONTROL_ALLOW_METHODS 		= "Access-Control-Allow-Methods";
	protected static String ACCESS_CONTROL_ALLOW_METHOD 		= "Access-Control-Allow-Method";
	protected static String ACCESS_CONTROL_ALLOW_HEADERS 		= "Access-Control-Allow-Headers";
	protected static String ACCESS_CONTROL_EXPOSE_HEADERS 		= "Access-Control-Expose-Headers";
	protected static String ACCESS_CONTROL_MAX_AGE 				= "Access-Control-Max-Age";
	protected static String CONTENT_TYPE 						= "Content-Type";
	protected static String ACCEPT		 						= "Accept";
	protected static String ALLOWED_METHODS 					= "GET, POST, PUT, DELETE";
	protected static List<HttpMethod> SUPPORTED_METHODS			= Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
	protected static List<String> LOCALHOSTS 					= Arrays.asList("localhost", "127.0.0.1");
	protected static String ENDPOINT 							= "endpoint";
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
	public void init(ServletConfig config) throws ServletException
	{
		//File file = new File(config.getServletContext().getRealPath("WEB-INF/classes"));
		// TODO: implement annotated @Service discovery
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			boolean dev = isDev(request, response);
			if(dev || isOriginAllowed(request, response))
			{
				HttpMethod httpMethod = HttpMethod.fromString(request.getMethod());
				if(httpMethod != null)
				{
					if(SUPPORTED_METHODS.contains(httpMethod))
					{
						HttpServiceMethod serviceMethod = getServiceMethod(request, response);
						if(serviceMethod != null)
						{
							HttpService service = this.getHttpService(request, response, serviceMethod.getServiceName());
							if(service != null)
							{
								service.setHttpServletRequest(request).setHttpServletResponse(response);
								service.setService(serviceMethod.getServiceName()).setMethod(serviceMethod.getMethodName());
								MethodReflector methodReflector = getMethodReflector(request, response, service.getClass(), serviceMethod.getServiceName(), serviceMethod.getMethodName());
								if(methodReflector != null)
								{
									if(methodReflector.isHttpMethodImplemented(httpMethod))
									{
										response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, !dev ? request.getHeader(ORIGIN) : ANY_ORIGIN);
										response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
										response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
										boolean hasAjax = methodReflector.isAjax();
										boolean hasApi = methodReflector.isApi();
										boolean ajaxAuthenticated = hasAjax && (!methodReflector.isAuthenticated() || service.isAjaxAuthenticated(methodReflector.getContext()));
										boolean apiAuthenticated = hasApi && (!methodReflector.isAuthenticated() || service.isApiAuthenticated(methodReflector.getContext()));
										if(ajaxAuthenticated || apiAuthenticated)
										{
											Object methodRequest = null;
											Parameter parameter = methodReflector.getParameter();
											if(parameter != null)
											{
												boolean gzipped = false;
												InputStream input = gzipped ? new GZIPInputStream(request.getInputStream()) : request.getInputStream();
												Type methodParameterType = parameter.getParameterizedType();
												methodRequest = getRequestMapper(request, response).setContext(methodReflector.getContext()).deserialize(input, methodParameterType);
											}
											boolean validCsrf = !(ajaxAuthenticated && methodReflector.isAuthenticated() && !service.isValidCsrfToken(methodRequest, dev));
											if(validCsrf)
											{
												boolean authorized = service.isAuthorized(methodReflector.getContext(), methodRequest);
												if(authorized)
												{
													LinkedList<HttpError> serviceErrors = service.validate(methodRequest);
													if(serviceErrors.isEmpty())
													{
														try
														{
															Object methodResponse = methodReflector.invoke(service, methodRequest);
															if(methodResponse != null)
															{
																
																response.setHeader(CONTENT_TYPE, getResponseContentType(request, response).toString());
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
																getResponseMapper(request, response).setContext(methodReflector.getContext()).serialize(methodResponse, response.getOutputStream());
															}
														}
														catch(Exception e)
														{
															exception(request, response, e);
														}
													}
													else
													{
														errors(request, response, serviceErrors);
													}
												}
												else
												{
													error(request, response, HttpErrorType.SERVICE_NOT_AUTHORIZED);
												}
											}
											else
											{
												error(request, response, HttpErrorType.SERVICE_CSRF_TOKEN_INVALID);
											}
										}
										else
										{
											if(hasAjax && !ajaxAuthenticated)
											{
												error(request, response, HttpErrorType.SERVICE_AJAX_NOT_AUTHENTICATED);
											}
											else if(hasApi && !apiAuthenticated)
											{
												error(request, response, HttpErrorType.SERVICE_API_NOT_AUTHENTICATED);
											}
											else
											{
												error(request, response, HttpErrorType.SERVICE_CHANNEL_NOT_IMPLEMENTED);
											}
										}
									}
									else
									{
										error(request, response, HttpErrorType.HTTP_METHOD_NOT_IMPLEMENTED);
									}
								}
								else
								{
									error(request, response, HttpErrorType.SERVICE_METHOD_MISSING);
								}
							}
							else
							{
								error(request, response, HttpErrorType.SERVICE_NOT_IMPLEMENTED);
							}
						}
						else
						{
							error(request, response, HttpErrorType.SERVICE_METHOD_NAME_MISSING);
						}
					}
					else
					{
						error(request, response, HttpErrorType.HTTP_METHOD_NOT_SUPPORTED);
					}
				}	
				else
				{
					error(request, response, HttpErrorType.HTTP_METHOD_MISSING);
				}
			}
			else
			{
				error(request, response, HttpErrorType.HTTP_ORIGIN_UNSUPPORTED);
			}
		}
		catch(Exception e)
		{
			exception(request, response, e);
		}
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
	
	protected void error(HttpServletRequest request, HttpServletResponse response, HttpErrorType error)
	{
		errors(request, response, new HttpError(error));
	}
	
	protected void errors(HttpServletRequest request, HttpServletResponse response, HttpError... errors)
	{
		errors(request, response, Arrays.asList(errors));
	}
	
	protected void errors(HttpServletRequest request, HttpServletResponse response, Collection<HttpError> errors)
	{
		
	}
	
	protected void exception(HttpServletRequest request, HttpServletResponse response, Exception e)
	{
		
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
	
	private HttpService getHttpService(HttpServletRequest request, HttpServletResponse response, String serviceName)
	{
		HttpService service = null;
		HttpServiceType serviceType = HttpServiceType.fromString(serviceName);
		switch(serviceType)
		{
			case ENDPOINT:
			{
				service = new HttpEndpointService();
				break;
			}
			case TASK:
			{
				service = new HttpTaskService();
				break;
			}
			case NOT_FOUND:
			{
				service = getAnnotatedService(request, response, serviceName);
				if(service == null)
				{
					service = getService(request, response, serviceName);
				}
				break;
			}
		}
		return service;
	}
	
	protected HttpService getAnnotatedService(HttpServletRequest request, HttpServletResponse response, String serviceName)
	{
		return null;
	}
	
	protected HttpService getService(HttpServletRequest request, HttpServletResponse response, String serviceName)
	{
		return null;
	}
	
	protected ServiceReflector getServiceReflector(HttpServletRequest request, HttpServletResponse response, Class<?> serviceClass, String serviceName)
	{
		ServiceReflector serviceReflector = serviceReflectorMap.get(serviceName);
		if(serviceReflector == null)
		{
			serviceReflector = new ServiceReflector(serviceClass, serviceName);
			serviceReflectorMap.put(serviceName, serviceReflector);
		}
		return serviceReflector;
	}
	
	protected MethodReflector getMethodReflector(HttpServletRequest request, HttpServletResponse response, Class<?> serviceClass, String serviceName, String methodName)
	{
		MethodReflector methodReflector = null;
		ServiceReflector serviceReflector = getServiceReflector(request, response, serviceClass, serviceName);
		if(serviceReflector != null)
		{
			methodReflector = serviceReflector.getMethodReflectorMap().get(methodName);
		}
		return methodReflector;
	}
	
	protected MimeType getRequestContentType(HttpServletRequest request, HttpServletResponse response)
	{
		MimeType contentType = MimeType.fromString(request.getHeader(CONTENT_TYPE));
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
				default:
				{
					contentType = null;
					break;
				}
			}
		}
		return contentType != null ? contentType : MimeType.APPLICATION_JSON;
	}
	
	protected MimeType getResponseContentType(HttpServletRequest request, HttpServletResponse response)
	{
		MimeType contentType = MimeType.fromString(request.getHeader(ACCEPT));
		if(contentType != null)
		{
			switch(contentType)
			{
				case APPLICATION_JSON:
				case APPLICATION_XML:
				{
					break;
				}
				default:
				{
					contentType = null;
					break;
				}
			}
		}
		return contentType != null ? contentType : MimeType.APPLICATION_JSON;
	}
	
	protected Mapper getRequestMapper(HttpServletRequest request, HttpServletResponse response)
	{
		Mapper mapper = null;
		MimeType contentType = getRequestContentType(request, response);
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
	
	protected Mapper getResponseMapper(HttpServletRequest request, HttpServletResponse response)
	{
		Mapper mapper = null;
		MimeType contentType = getResponseContentType(request, response);
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
	
}
