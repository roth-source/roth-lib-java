package roth.lib.net.http.endpoint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roth.lib.map.Config;
import roth.lib.map.SerialMapper;
import roth.lib.net.http.HttpHeaders;
import roth.lib.net.http.HttpMethod;
import roth.lib.net.http.annotation.Ajax;
import roth.lib.net.http.annotation.Api;
import roth.lib.net.http.annotation.Delete;
import roth.lib.net.http.annotation.Get;
import roth.lib.net.http.annotation.Post;
import roth.lib.net.http.annotation.Put;
import roth.lib.net.http.header.type.MimeType;
import roth.lib.net.http.service.HttpService;
import roth.lib.net.http.service.HttpServiceResponse;
import roth.lib.net.http.service.HttpServiceType;
import roth.lib.net.http.task.HttpTaskService;
import roth.lib.net.http.util.ServiceUtil;

@SuppressWarnings("serial")
public abstract class HttpEndpoint<RequestConfig extends Config, ResponseConfig extends Config> extends HttpServlet implements HttpHeaders
{
	protected static List<String> LOCALHOSTS 			= Arrays.asList("localhost", "127.0.0.1");
	protected static String SERVICE 					= "service";
	protected static String METHOD 						= "method";
	protected static Pattern SERVICE_METHOD_PATTERN 	= Pattern.compile("(?:^|/)(?<" + SERVICE + ">\\w+)/(?<" + METHOD + ">\\w+)(?:/|$)");
	protected static String ENDPOINT 					= "endpoint";
	protected static String ALLOWED_METHODS 			= "GET, POST, PUT, DELETE";
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			boolean dev = isDev(request, response);
			ServiceMethod serviceMethod = getServiceMethod(request, response);
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
								if(ServiceUtil.hasAnnotation(service, method, httpMethodAnnotationClass))
								{
									if(isOriginAllowed(request, response))
									{
										response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, !dev ? request.getHeader(ORIGIN) : "*");
										response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
										response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
										Ajax ajax = ServiceUtil.getAnnotation(service, method, Ajax.class);
										Api api = ServiceUtil.getAnnotation(service, method, Api.class);
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
												Parameter parameter = parameters[0];
												Type methodParameterType = parameter.getParameterizedType();
												methodRequest = getRequestMapper(request, response).deserialize(request.getInputStream(), methodParameterType, getRequestConfig(request, response));
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
																getResponseMapper(request, response).serialize(methodResponse, response.getOutputStream(), getResponseConfig(request, response));
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
	}
	
	protected abstract void errors(HttpServletRequest request, HttpServletResponse response, LinkedList<HttpError> errors);
	protected abstract void exception(HttpServletRequest request, HttpServletResponse response, Exception e);
	protected abstract HttpService getService(HttpServletRequest request, HttpServletResponse response, String serviceName);
	protected abstract boolean isOriginAllowed(HttpServletRequest request, HttpServletResponse response);
	protected abstract SerialMapper<RequestConfig> getRequestMapper(HttpServletRequest request, HttpServletResponse response);
	protected abstract RequestConfig getRequestConfig(HttpServletRequest request, HttpServletResponse response);
	protected abstract SerialMapper<ResponseConfig> getResponseMapper(HttpServletRequest request, HttpServletResponse response);
	protected abstract ResponseConfig getResponseConfig(HttpServletRequest request, HttpServletResponse response);
	
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
	
	protected ServiceMethod getServiceMethod(HttpServletRequest request, HttpServletResponse response)
	{
		ServiceMethod serviceMethod = null;
		Matcher matcher = SERVICE_METHOD_PATTERN.matcher(request.getPathInfo());
		if(matcher.find())
		{
			serviceMethod = new ServiceMethod(matcher.group(SERVICE), matcher.group(METHOD));
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
		LinkedList<Method> methods = ServiceUtil.getMethods(service.getClass());
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
		switch(httpMethod)
		{
			case GET: 
			{
				return Get.class;
			}
			case POST: 
			{
				return Post.class;
			}
			case PUT: 
			{
				return Put.class;
			}
			case DELETE: 
			{
				return Delete.class;
			}
			default:
			{
				return null;
			}
		}
	}
	
	protected String getResponseContentType(HttpServletRequest request, HttpServletResponse response)
	{
		return MimeType.APPLICATION_JSON.getValue();
	}
	
	public static class ServiceMethod
	{
		protected String serviceName;
		protected String methodName;
		
		public ServiceMethod(String serviceName, String methodName)
		{
			this.serviceName = serviceName;
			this.methodName = methodName;
		}
		
		public String getServiceName()
		{
			return serviceName;
		}
		
		public String getMethodName()
		{
			return methodName;
		}
		
	}
	
}
