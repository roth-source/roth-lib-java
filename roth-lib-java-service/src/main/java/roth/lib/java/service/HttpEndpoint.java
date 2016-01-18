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
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roth.lib.java.Characters;
import roth.lib.java.http.HttpMethod;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertyReflector;
import roth.lib.java.service.annotation.Service;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.service.reflector.ServiceReflector;
import roth.lib.java.type.MimeType;
import roth.lib.java.util.EnumUtil;
import roth.lib.java.util.ReflectionUtil;
import roth.lib.java.validate.Filterer;
import roth.lib.java.validate.Validator;
import roth.lib.java.validate.ValidatorException;

@SuppressWarnings("serial")
public abstract class HttpEndpoint extends HttpServlet implements Characters
{
	protected static String ORIGIN 								= "Origin";
	protected static String ANY 								= "*";
	protected static String X_CSRF_TOKEN						= "X-Csrf-Token";
	protected static String ACCESS_CONTROL_ALLOW_ORIGIN 		= "Access-Control-Allow-Origin";
	protected static String ACCESS_CONTROL_ALLOW_CREDENTIALS 	= "Access-Control-Allow-Credentials";
	protected static String ACCESS_CONTROL_ALLOW_METHODS 		= "Access-Control-Allow-Methods";
	protected static String ACCESS_CONTROL_EXPOSE_HEADERS 		= "Access-Control-Expose-Headers";
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
	protected static LinkedHashMap<String, Filterer> filtererMap = new LinkedHashMap<String, Filterer>();
	protected static LinkedHashMap<String, Validator> validatorMap = new LinkedHashMap<String, Validator>();
	
	protected MapperReflector mapperReflector = MapperReflector.get();
	protected MapperConfig mapperConfig = MapperConfig.get();
	
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
	
	public static void filterer(String name, Filterer filterer)
	{
		filtererMap.put(name, filterer);
	}
	
	public static void validator(String name, Validator validator)
	{
		validatorMap.put(name, validator);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		Object methodResponse = null;
		String debugRequest = "";
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
				response.setHeader(ACCESS_CONTROL_EXPOSE_HEADERS, X_CSRF_TOKEN);
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
													service.setRequestMapper(requestMapper);
													methodRequest = requestMapper.setContext(methodReflector.getContext()).deserialize(input, methodParameterType);
													errors.addAll(validate(request, response, methodRequest, requestMapper.getMapperType()));
												}
												if(dev)
												{
													debugRequest = getDebugRequest(request, methodRequest, requestMapper);
												}
												boolean validCsrf = !(ajaxAuthenticated && methodReflector.isAuthenticated() && !service.isValidCsrfToken(methodRequest, dev));
												if(validCsrf)
												{
													boolean authorized = service.isAuthorized(methodReflector, methodRequest);
													if(authorized)
													{
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
			System.out.println(debugRequest + getDebugResponse(response, methodResponse, responseMapper));
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
	
	protected LinkedList<HttpError> validate(HttpServletRequest request, HttpServletResponse response, Object value, MapperType mapperType)
	{
		return validate(request, response, value, mapperType, BLANK);
	}
	
	protected LinkedList<HttpError> validate(HttpServletRequest request, HttpServletResponse response, Object value, MapperType mapperType, String path)
	{
		LinkedList<HttpError> errors = new LinkedList<HttpError>();
		if(value != null)
		{
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
			if(entityReflector != null)
			{
				for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(mapperType))
				{
					try
					{
						Type propertyType = propertyReflector.getFieldType();
						String propertyName = propertyReflector.getPropertyName(mapperType);
						Object propertyValue = propertyReflector.getField().get(value);
						if(ReflectionUtil.isArray(propertyType) || ReflectionUtil.isCollection(propertyType))
						{
							if(propertyValue != null)
							{
								int i = 0;
								for(Object element : ReflectionUtil.asCollection(propertyValue))
								{
									errors.addAll(validate(request, response, element, mapperType, path + propertyName + LEFT_BRACE + i + RIGHT_BRACE + DOT));
									i++;
								}
							}
						}
						else if(ReflectionUtil.isMap(propertyType))
						{
							if(propertyValue != null)
							{
								for(Entry<?, ?> elementEntry : ReflectionUtil.asMap(propertyValue).entrySet())
								{
									String elementName = null;
									Object elementKey = elementEntry.getKey();
									if(elementKey != null)
									{
										if(elementKey instanceof Enum)
										{
											elementName = EnumUtil.toString((Enum<?>) elementKey);
										}
										else
										{
											elementName = elementKey.toString();
										}
									}
									if(elementName != null)
									{
										errors.addAll(validate(request, response, elementEntry.getValue(), mapperType, path + elementName + DOT));
									}
								}
							}
						}
						else
						{
							if(propertyValue != null)
							{
								for(String filter : propertyReflector.getFilters())
								{
									Filterer filterer = filtererMap.get(filter);
									if(filterer != null)
									{
										propertyValue = filterer.filter(propertyValue);
									}
								}
							}
							boolean blank = propertyValue == null;
							if(!blank && propertyValue instanceof String)
							{
								blank = ((String) propertyValue).isEmpty();
							}
							if(!blank)
							{
								for(String validate : propertyReflector.getValidates())
								{
									Validator validator = validatorMap.get(validate);
									if(validator != null)
									{
										try
										{
											validator.validate(propertyValue, value, entityReflector);
										}
										catch(ValidatorException e)
										{
											errors.add(HttpErrorType.REQUEST_FIELD_INVALID.error().setContext(path + propertyName).setMessage(e.getMessage()));
											break;
										}
									}
								}
							}
							else if(blank && propertyReflector.isRequired())
							{
								errors.add(HttpErrorType.REQUEST_FIELD_REQUIRED.error().setContext(path + propertyName));
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return errors;
	}
	
	protected String getDebugRequest(HttpServletRequest request, Object methodRequest, Mapper mapper)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
		builder.append("REQUEST");
		builder.append(NEW_LINE);
		builder.append("----------------------------------");
		builder.append(NEW_LINE);
		builder.append(request.getMethod());
		builder.append(SPACE);
		builder.append(request.getRequestURI());
		builder.append(NEW_LINE);
		Enumeration<String> names = request.getHeaderNames();
		while(names.hasMoreElements())
		{
			String name = names.nextElement();
			builder.append(name);
			builder.append(": ");
			builder.append(request.getHeader(name));
			builder.append(NEW_LINE);
		}
		builder.append(NEW_LINE);
		if(methodRequest != null && mapper != null)
		{
			builder.append(mapper.setPrettyPrint(true).serialize(methodRequest));
			builder.append(NEW_LINE);
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}
	
	protected String getDebugResponse(HttpServletResponse response, Object methodResponse, Mapper mapper)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
		builder.append("RESPONSE");
		builder.append(NEW_LINE);
		builder.append("----------------------------------");
		builder.append(NEW_LINE);
		builder.append(response.getStatus());
		builder.append(NEW_LINE);
		for(String name : response.getHeaderNames())
		{
			builder.append(name);
			builder.append(": ");
			builder.append(response.getHeader(name));
			builder.append(NEW_LINE);
		}
		builder.append(NEW_LINE);
		if(methodResponse != null && mapper != null)
		{
			builder.append(mapper.setPrettyPrint(true).serialize(methodResponse));
			builder.append(NEW_LINE);
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}
	
}
