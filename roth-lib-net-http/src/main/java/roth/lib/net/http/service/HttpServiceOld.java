package roth.lib.net.http.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import roth.lib.annotation.Filter;
import roth.lib.annotation.Required;
import roth.lib.annotation.Validate;
import roth.lib.map.Config;
import roth.lib.map.SerialMapper;
import roth.lib.map.util.MapperUtil;
import roth.lib.net.http.HttpHeaders;
import roth.lib.net.http.HttpMethod;
import roth.lib.net.http.annotation.Ajax;
import roth.lib.net.http.annotation.Api;
import roth.lib.net.http.annotation.Delete;
import roth.lib.net.http.annotation.Get;
import roth.lib.net.http.annotation.Post;
import roth.lib.net.http.annotation.Put;
import roth.lib.net.http.endpoint.HttpError;
import roth.lib.net.http.endpoint.HttpErrorType;
import roth.lib.net.http.header.type.MimeType;
import roth.lib.util.IdUtil;

@SuppressWarnings("serial")
public abstract class HttpServiceOld<RequestConfig extends Config, ResponseConfig extends Config> extends HttpServlet implements HttpHeaders
{
	protected static String ALLOWED_METHODS 		= "GET, POST, PUT, DELETE";
	protected static String SET_COOKIE				= "Set-Cookie";
	protected static String JSESSIONID				= "JSESSIONID";
	protected static Pattern JSESSIONID_PATTERN 	= Pattern.compile(JSESSIONID + "=(?<" + JSESSIONID + ">\\w+);");
	protected static String CSRF_TOKEN				= "csrfToken";
	protected static String METHOD 					= "method";
	protected static String NUMBER 					= "number";
	protected static String PHONE 					= "phone";
	protected static String EMAIL 					= "email";
	protected static Pattern METHOD_PATTERN 		= Pattern.compile("^(?:/)?(?<" + METHOD + ">\\w+)(?:/|$)");
	protected static String NUMBER_FORMAT 			= "[^0-9]";
	protected static String PHONE_FORMAT 			= "^[0-9]{10}$";
	protected static String EMAIL_FORMAT 			= "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
	
	protected static HttpFieldFilterer NUMBER_FILTERER;
	protected static HttpFieldValidator PHONE_VALIDATOR;
	protected static HttpFieldValidator EMAIL_VALIDATOR;
	
	protected static LinkedHashMap<String, HttpFieldFilterer> FIELD_FILTERERS = new LinkedHashMap<String, HttpFieldFilterer>();
	protected static LinkedHashMap<String, HttpFieldValidator> FIELD_VALIDATORS = new LinkedHashMap<String, HttpFieldValidator>();
	
	static
	{
		NUMBER_FILTERER = new HttpFieldFilterer()
		{
			@Override
			public Object filter(Object value)
			{
				if(value != null && value instanceof String)
				{
					return ((String) value).replaceAll(NUMBER_FORMAT, "");
				}
				else
				{
					return value;
				}
			}
		};
		PHONE_VALIDATOR = new HttpFieldValidator()
		{
			@Override
			public boolean validate(Object value)
			{
				if(value != null && value instanceof String)
				{
					return ((String) value).matches(PHONE_FORMAT);
				}
				return true;
			}
		};
		EMAIL_VALIDATOR = new HttpFieldValidator()
		{
			@Override
			public boolean validate(Object value)
			{
				if(value != null && value instanceof String)
				{
					return ((String) value).matches(EMAIL_FORMAT);
				}
				return true;
			}
		};
		FIELD_FILTERERS.put(NUMBER, NUMBER_FILTERER);
		FIELD_VALIDATORS.put(PHONE, PHONE_VALIDATOR);
		FIELD_VALIDATORS.put(EMAIL, EMAIL_VALIDATOR);
	}
	
	protected boolean dev;
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	
	protected HttpServiceOld()
	{
		this.dev = false;
	}
	
	protected HttpServiceOld(boolean dev)
	{
		this.dev = dev;
	}
	
	public HttpServletRequest getHttpServletRequest()
	{
		return httpServletRequest;
	}
	
	public HttpServletResponse getHttpServletResponse()
	{
		return httpServletResponse;
	}
	
	@Override
	protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
		try
		{
			String methodName = getMethodName();
			if(methodName != null)
			{
				Method method = getMethod(methodName);
				if(method != null)
				{
					HttpMethod httpMethod = HttpMethod.get(httpServletRequest.getMethod());
					if(httpMethod != null)
					{
						Class<? extends Annotation> httpMethodAnnotationClass = getHttpMethodAnnotationClass(httpMethod);
						if(httpMethodAnnotationClass != null)
						{
							if(hasAnnotation(method, httpMethodAnnotationClass))
							{
								if(isOriginAllowed())
								{
									httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, !dev ? httpServletRequest.getHeader(ORIGIN) : "*");
									httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
									httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
									Ajax ajax = getAnnotation(method, Ajax.class);
									Api api = getAnnotation(method, Api.class);
									boolean hasAjax = ajax != null;
									boolean hasApi = api != null;
									boolean validAjax = hasAjax && isValidAjaxRequest(ajax);
									boolean validApi = hasApi && isValidApiRequest(api);
									if(validAjax || validApi)
									{
										Object methodRequest = null;
										Parameter[] parameters = method.getParameters();
										if(parameters != null && parameters.length == 1)
										{
											Parameter parameter = parameters[0];
											Type methodParameterType = parameter.getParameterizedType();
											methodRequest = getRequestMapper().deserialize(httpServletRequest.getInputStream(), methodParameterType, getRequestConfig());
										}
										boolean allowed = isAllowed(methodRequest);
										if(!(validAjax && ajax.authenticated() && !isValidCsrfToken()) && allowed)
										{
											LinkedList<HttpError> serviceErrors = validate(methodRequest);
											if(serviceErrors.isEmpty())
											{
												Object methodResponse = null;
												try
												{
													if(methodRequest != null)
													{
														methodResponse = method.invoke(this, methodRequest);
													}
													else
													{
														methodResponse = method.invoke(this);
													}
													if(methodResponse != null)
													{
														httpServletResponse.setHeader(CONTENT_TYPE, getResponseContentType());
														if(dev && methodResponse instanceof HttpServiceResponse)
														{
															String jsessionId = null;
															Collection<String> setCookies = httpServletResponse.getHeaders(SET_COOKIE);
															for(String setCookie : setCookies)
															{
																Matcher matcher = JSESSIONID_PATTERN.matcher(setCookie);
																if(matcher.find())
																{
																	jsessionId = matcher.group(JSESSIONID);
																	break;
																}
															}
															if(jsessionId != null)
															{
																HttpServiceDev httpServiceDev = ((HttpServiceResponse) methodResponse).getDev();
																if(httpServiceDev == null)
																{
																	httpServiceDev = new HttpServiceDev();
																}
																httpServiceDev.setJsessionId(jsessionId);
																((HttpServiceResponse) methodResponse).setDev(httpServiceDev);
															}
														}
														getResponseMapper().serialize(methodResponse, httpServletResponse.getOutputStream(), getResponseConfig());
													}
												}
												catch(Exception e)
												{
													exception(e);
												}
											}
											else
											{
												errors(serviceErrors);
											}
										}
										else
										{
											error(HttpErrorType.SERVICE_CSRF_TOKEN_INVALID);
										}
									}
									else
									{
										if(hasAjax && !validAjax)
										{
											error(HttpErrorType.SERVICE_AJAX_NOT_AUTHENTICATED);
										}
										else if(hasApi && !validApi)
										{
											error(HttpErrorType.SERVICE_API_NOT_AUTHENTICATED);
										}
										else
										{
											error(HttpErrorType.SERVICE_NOT_IMPLEMENTED);
										}
									}
								}
								else
								{
									error(HttpErrorType.HTTP_ORIGIN_UNSUPPORTED);
								}
							}
							else
							{
								error(HttpErrorType.HTTP_METHOD_NOT_IMPLEMENTED);
							}
						}
						else
						{
							error(HttpErrorType.HTTP_METHOD_NOT_SUPPORTED);
						}
					}
					else
					{
						error(HttpErrorType.HTTP_METHOD_MISSING);
					}
				}
				else
				{
					error(HttpErrorType.SERVICE_METHOD_MISSING);
				}
			}
			else
			{
				error(HttpErrorType.SERVICE_METHOD_NAME_MISSING);
			}
		}
		catch(Exception e)
		{
			exception(e);
		}
	}
	
	protected void error(HttpErrorType error)
	{
		LinkedList<HttpError> errors = new LinkedList<HttpError>();
		errors.add(new HttpError(error));
		errors(errors);
	}
	
	protected abstract void errors(LinkedList<HttpError> errors);
	protected abstract void exception(Exception e);
	protected abstract boolean isValidAjaxRequest(Ajax ajax);
	protected abstract boolean isValidApiRequest(Api api);
	protected abstract SerialMapper<RequestConfig> getRequestMapper();
	protected abstract RequestConfig getRequestConfig();
	protected abstract SerialMapper<ResponseConfig> getResponseMapper();
	protected abstract ResponseConfig getResponseConfig();
	protected abstract boolean isOriginAllowed();
	
	protected String getResponseContentType()
	{
		return MimeType.APPLICATION_JSON.getValue();
	}
	
	protected String getMethodName()
	{
		String methodName = null;
		String path = httpServletRequest.getPathInfo();
		Matcher matcher = METHOD_PATTERN.matcher(path);
		if(matcher.find())
		{
			methodName = matcher.group(METHOD);
		}
		return methodName;
	}
	
	protected Method getMethod(String name)
	{
		LinkedList<Method> methods = getMethods(getClass());
		for(Method method : methods)
		{
			if(method.getName().equalsIgnoreCase(name))
			{
				method.setAccessible(true);
				return method;
			}
		}
		return null;
	}
	
	protected static LinkedList<Method> getMethods(Type type)
	{
		if(type == null)
		{
			return new LinkedList<Method>();
		}
		else
		{
			Class<?> klass = MapperUtil.getClass(type);
			if(HttpServiceOld.class.equals(klass))
			{
				return new LinkedList<Method>();
			}
			else
			{
				LinkedList<Method> methods = getMethods(klass.getSuperclass());
				for(Method method : Arrays.asList(klass.getDeclaredMethods()))
				{
					if(!Modifier.isStatic(method.getModifiers()))
					{
						methods.add(method);
					}
				}
				return methods;
			}
		}
	}
	
	protected LinkedList<Field> getFields(Type type)
	{
		if(type == null)
		{
			return new LinkedList<Field>();
		}
		else
		{
			Class<?> klass = MapperUtil.getClass(type);
			LinkedList<Field> fields = getFields(klass.getSuperclass());
			for(Field field : Arrays.asList(klass.getDeclaredFields()))
			{
				if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
				{
					fields.add(field);
				}
			}
			return fields;
		}
	}
	
	protected boolean hasAnnotation(Method method, Class<? extends Annotation> annotationClass)
	{
		return getAnnotation(method, annotationClass) != null;
	}
	
	protected <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass)
	{
		T annotation = method.getDeclaredAnnotation(annotationClass);
		if(annotation == null)
		{
			annotation = getClass().getDeclaredAnnotation(annotationClass);
		}
		return annotation;
	}
	
	protected Class<? extends Annotation> getHttpMethodAnnotationClass(HttpMethod httpMethod)
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
	
	protected LinkedList<HttpError> validate(Object request)
	{
		LinkedList<HttpError> errors = new LinkedList<HttpError>();
		if(request != null)
		{
			LinkedList<Field> fields = getFields(request.getClass());
			for(Field field : fields)
			{
				filterField(field, request);
				Boolean valid = mandatoryField(field, request);
				if(valid == null || valid)
				{
					if(!validateField(field, request))
					{
						errors.add(new HttpError(HttpErrorType.REQUEST_FIELD_INVALID, readableName(field.getName())));
					}
				}
				else
				{
					errors.add(new HttpError(HttpErrorType.REQUEST_FIELD_REQUIRED, readableName(field.getName())));
				}
			}
		}
		return errors;
	}
	
	protected void filterField(Field field, Object request)
	{
		Filter filter = field.getDeclaredAnnotation(Filter.class);
		if(filter != null)
		{
			try
			{
				field.setAccessible(true);
				Object value = field.get(request);
				String name = filter.value();
				if(name != null && !name.isEmpty() && value != null)
				{
					HttpFieldFilterer fieldFilterer = FIELD_FILTERERS.get(name);
					if(fieldFilterer != null)
					{
						field.set(request, fieldFilterer.filter(value));
					}
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	protected Boolean mandatoryField(Field field, Object request)
	{
		Boolean valid = null;
		Required mandatory = field.getDeclaredAnnotation(Required.class);
		if(mandatory != null)
		{
			try
			{
				field.setAccessible(true);
				Object value = field.get(request);
				valid = value != null;
				if(valid && value != null && value instanceof String)
				{
					valid = !((String) value).trim().isEmpty();
				}
			}
			catch(Exception e)
			{
				
			}
		}
		return valid;
	}
	
	protected boolean validateField(Field field, Object request)
	{
		boolean valid = true;
		Validate validate = field.getDeclaredAnnotation(Validate.class);
		if(validate != null)
		{
			try
			{
				field.setAccessible(true);
				Object value = field.get(request);
				String name = validate.value();
				if(name != null && !name.isEmpty() && value != null)
				{
					HttpFieldValidator fieldValidator = FIELD_VALIDATORS.get(name);
					if(fieldValidator != null)
					{
						valid = fieldValidator.validate(value);
					}
					else if(value instanceof String)
					{
						valid = ((String) value).matches(name);
					}
				}
			}
			catch(Exception e)
			{
				
			}
		}
		return valid;
	}
	
	protected static String readableName(String name)
	{
		StringBuilder builder = new StringBuilder();
		if(name != null)
		{
			for(char c : name.toCharArray())
			{
				if(Character.isUpperCase(c))
				{
					builder.append(" ");
					builder.append(Character.toLowerCase(c));
				}
				else
				{
					builder.append(c);
				}
			}
		}
		return builder.toString();
	}
	
	protected boolean isValidCsrfToken()
	{
		if(!dev)
		{
			String requestCsrfToken = httpServletRequest.getParameter(CSRF_TOKEN);
			String sessionCsrfToken = getCsrfToken();
			return requestCsrfToken != null && sessionCsrfToken != null && requestCsrfToken.equals(sessionCsrfToken);
		}
		else
		{
			return true;
		}
	}
	
	protected String getCsrfToken()
	{
		String csrfToken = null;
		Object csrfTokenObject = httpServletRequest.getSession().getAttribute(CSRF_TOKEN);
		if(csrfTokenObject != null && csrfTokenObject instanceof String)
		{
			csrfToken = (String) csrfTokenObject;
		}
		return csrfToken;
	}
	
	protected String createCsrfToken()
	{
		String csrfToken = IdUtil.random(5);
		httpServletRequest.getSession().setAttribute(CSRF_TOKEN, csrfToken);
		return csrfToken;
	}
	
	protected boolean isAllowed(Object request)
	{
		return true;
	}
	
}
