package roth.lib.service.endpoint;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected static String ALLOWED_METHODS 					= "GET, POST, PUT, DELETE";
	protected static List<String> LOCALHOSTS 					= Arrays.asList("localhost", "127.0.0.1");
	protected static String ENDPOINT 							= "endpoint";
	protected static String SERVICE 							= "service";
	protected static String METHOD 								= "method";
	protected static Pattern SERVICE_METHOD_PATTERN 			= Pattern.compile("(?:^|/)(?<" + SERVICE + ">\\w+)/(?<" + METHOD + ">\\w+)(?:/|$)");
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		File file = new File(config.getServletContext().getRealPath("WEB-INF/classes"));
		
	}
	
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		/*
		try
		{
			boolean dev = isDev(request, response);
			HttpServiceMethod serviceMethod = getServiceMethod(request, response);
			if(serviceMethod != null)
			{
				HttpService service = getHttpService(request, response, serviceMethod.getServiceName());
				if(service != null)
				{
					service.setHttpServletRequest(request).setHttpServletResponse(response);
					service.setService(serviceMethod.getServiceName()).setMethod(serviceMethod.getMethodName());
					Method method = getMethod(request, response, service, serviceMethod.getMethodName());
					if(method != null)
					{
						HttpMethod httpMethod = HttpMethod.get(request.getMethod());
						if(httpMethod != null)
						{
							Class<? extends Annotation> httpMethodAnnotationClass = getHttpMethodAnnotationClass(request, response, httpMethod);
							if(httpMethodAnnotationClass != null)
							{
								if(ReflectionUtil.hasAnnotation(service.getClass(), method, httpMethodAnnotationClass))
								{
									if(isOriginAllowed(request, response))
									{
										response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, !dev ? request.getHeader(ORIGIN) : ANY_ORIGIN);
										response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
										response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
										Ajax ajax = ReflectionUtil.getAnnotation(service.getClass(), method, Ajax.class);
										Api api = ReflectionUtil.getAnnotation(service.getClass(), method, Api.class);
										boolean hasAjax = ajax != null;
										boolean hasApi = api != null;
										boolean ajaxAuthenticated = hasAjax && (!ajax.authenticated() || service.isAjaxAuthenticated(ajax));
										boolean apiAuthenticated = hasApi && (!api.authenticated() || service.isApiAuthenticated(api));
										if(ajaxAuthenticated || apiAuthenticated)
										{
											Object methodRequest = null;
											Parameter[] parameters = method.getParameters();
											if(parameters != null && parameters.length == 1)
											{
												boolean gzipped = false;
												GzippedInput gzip = ReflectionUtil.getAnnotation(service.getClass(), method, GzippedInput.class);
												if(gzip != null)
												{
													if(gzip.optional())
													{
														String gzipHeader = request.getHeader(gzip.header());
														gzipped = gzipHeader != null;
													}
													else
													{
														gzipped = true;
													}
												}
												InputStream input = gzipped ? new GZIPInputStream(request.getInputStream()) : request.getInputStream();
												Type methodParameterType = parameters[0].getParameterizedType();
												methodRequest = getRequestMapper(request, response).deserialize(input, methodParameterType);
											}
											boolean validCsrf = !(ajaxAuthenticated && ajax.authenticated() && !service.isValidCsrfToken(methodRequest, dev));
											if(validCsrf)
											{
												boolean authorized = service.isAuthorized(methodRequest);
												if(authorized)
												{
													LinkedList<HttpError> serviceErrors = service.validate(methodRequest);
													if(serviceErrors.isEmpty())
													{
														Object methodResponse = null;
														try
														{
															if(methodRequest != null)
															{
																methodResponse = method.invoke(service, methodRequest);
															}
															else
															{
																methodResponse = method.invoke(service);
															}
															if(methodResponse != null)
															{
																response.setHeader(CONTENT_TYPE, getResponseContentType(request, response));
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
																getResponseMapper(request, response).serialize(methodResponse, response.getOutputStream());
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
										error(request, response, HttpErrorType.HTTP_ORIGIN_UNSUPPORTED);
									}
								}
								else
								{
									error(request, response, HttpErrorType.HTTP_METHOD_NOT_IMPLEMENTED);
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
		catch(Exception e)
		{
			exception(request, response, e);
		}
		*/
	}
	
	/*
	protected abstract void errors(HttpServletRequest request, HttpServletResponse response, LinkedList<HttpError> errors);
	protected abstract void exception(HttpServletRequest request, HttpServletResponse response, Exception e);
	protected abstract HttpService getService(HttpServletRequest request, HttpServletResponse response, String serviceName);
	protected abstract boolean isOriginAllowed(HttpServletRequest request, HttpServletResponse response);
	protected abstract Mapper getRequestMapper(HttpServletRequest request, HttpServletResponse response);
	protected abstract Mapper getResponseMapper(HttpServletRequest request, HttpServletResponse response);
	
	protected boolean isDev(HttpServletRequest request, HttpServletResponse response)
	{
		return getLocalHosts(request, response).contains(request.getServerName());
	}
	
	protected List<String> getLocalHosts(HttpServletRequest request, HttpServletResponse response)
	{
		return LOCALHOSTS;
	}
	
	protected void error(HttpServletRequest request, HttpServletResponse response, HttpErrorType error)
	{
		LinkedList<HttpError> errors = new LinkedList<HttpError>();
		errors.add(new HttpError(error));
		errors(request, response, errors);
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
	
	protected HttpService getHttpService(HttpServletRequest request, HttpServletResponse response, String serviceName)
	{
		HttpService service = null;
		HttpServiceType serviceType = HttpServiceType.get(serviceName);
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
				service = getService(request, response, serviceName);
				break;
			}
		}
		return service;
	}
	
	protected Method getMethod(HttpServletRequest request, HttpServletResponse response, HttpService service, String methodName)
	{
		LinkedList<Method> methods = ReflectionUtil.getMethods(service.getClass(), HttpService.class);
		for(Method method : methods)
		{
			if(method.getName().equalsIgnoreCase(methodName))
			{
				method.setAccessible(true);
				return method;
			}
		}
		return null;
	}
	
	protected Class<? extends Annotation> getHttpMethodAnnotationClass(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod)
	{
		
		return null;
	}
	
	protected String getResponseContentType(HttpServletRequest request, HttpServletResponse response)
	{
		return MimeType.APPLICATION_JSON.toString();
	}
	*/
}
