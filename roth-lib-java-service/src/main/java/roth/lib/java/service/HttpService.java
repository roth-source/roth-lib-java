package roth.lib.java.service;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roth.lib.java.annotation.Filter;
import roth.lib.java.annotation.Required;
import roth.lib.java.annotation.Validate;
import roth.lib.java.map.Mapper;
import roth.lib.java.service.endpoint.HttpError;
import roth.lib.java.service.endpoint.HttpErrorType;
import roth.lib.java.util.IdUtil;
import roth.lib.java.util.ReflectionUtil;

public abstract class HttpService
{
	public static String X_CSRF_TOKEN			= "X-Csrf-Token";
	public static String CSRF_TOKEN				= "csrfToken";
	public static String NUMBER 				= "number";
	public static String PHONE 					= "phone";
	public static String EMAIL 					= "email";
	
	protected static String NUMBER_FORMAT 		= "[^0-9]";
	protected static String PHONE_FORMAT 		= "^[0-9]{10}$";
	protected static String EMAIL_FORMAT 		= "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
	
	protected static HttpFieldFilterer numberFilterer;
	protected static HttpFieldValidator phoneValidator;
	protected static HttpFieldValidator emailValidator;
	protected static LinkedHashMap<String, HttpFieldFilterer> fieldFilterers = new LinkedHashMap<String, HttpFieldFilterer>();
	protected static LinkedHashMap<String, HttpFieldValidator> fieldValidators = new LinkedHashMap<String, HttpFieldValidator>();
	
	static
	{
		numberFilterer = new HttpFieldFilterer()
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
		phoneValidator = new HttpFieldValidator()
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
		emailValidator = new HttpFieldValidator()
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
		fieldFilterers.put(NUMBER, numberFilterer);
		fieldValidators.put(PHONE, phoneValidator);
		fieldValidators.put(EMAIL, emailValidator);
	}
	
	protected ServletContext servletContext;
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	protected Mapper requestMapper;
	protected Mapper responseMapper;
	protected String service;
	protected String method;
	
	public HttpService()
	{
		
	}
	
	public abstract boolean isAjaxAuthenticated(String context);
	public abstract boolean isApiAuthenticated(String context);
	public abstract boolean isAuthorized(String context, Object request);
	
	public ServletContext getServletContext()
	{
		return servletContext;
	}
	
	public HttpServletRequest getHttpServletRequest()
	{
		return httpServletRequest;
	}
	
	public HttpServletResponse getHttpServletResponse()
	{
		return httpServletResponse;
	}
	
	public Mapper getRequestMapper()
	{
		return requestMapper;
	}
	
	public Mapper getResponseMapper()
	{
		return responseMapper;
	}
	
	public String getService()
	{
		return service;
	}
	
	public String getMethod()
	{
		return method;
	}
	
	public HttpService setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
		return this;
	}
	
	public HttpService setHttpServletRequest(HttpServletRequest httpServletRequest)
	{
		this.httpServletRequest = httpServletRequest;
		return this;
	}
	
	public HttpService setHttpServletResponse(HttpServletResponse httpServletResponse)
	{
		this.httpServletResponse = httpServletResponse;
		return this;
	}
	
	public void setRequestMapper(Mapper requestMapper)
	{
		this.requestMapper = requestMapper;
	}
	
	public void setResponseMapper(Mapper responseMapper)
	{
		this.responseMapper = responseMapper;
	}
	
	public HttpService setService(String service)
	{
		this.service = service;
		return this;
	}

	public HttpService setMethod(String method)
	{
		this.method = method;
		return this;
	}
	
	public void initDev()
	{
		
	}
	
	public HttpSession initSession()
	{
		HttpSession session = httpServletRequest.getSession(false);
		if(session != null)
		{
			session.invalidate();
		}
		session = httpServletRequest.getSession(true);
		String csrfToken = generateCsrfToken();
		session.setAttribute(CSRF_TOKEN, csrfToken);
		httpServletResponse.setHeader(X_CSRF_TOKEN, csrfToken);
		return session;
	}
	
	public String generateCsrfToken()
	{
		return IdUtil.random(10);
	}
	
	public boolean isValidCsrfToken(Object request, boolean dev)
	{
		String requestCsrfToken = getRequestCsrfToken(request, dev);
		String sessionCsrfToken = getSessionCsrfToken();
		return requestCsrfToken != null && sessionCsrfToken != null && requestCsrfToken.equals(sessionCsrfToken);
	}
	
	public String getRequestCsrfToken(Object request, boolean dev)
	{
		return httpServletRequest.getParameter(CSRF_TOKEN);
	}
	
	public String getSessionCsrfToken()
	{
		String csrfToken = null;
		Object csrfTokenObject = httpServletRequest.getSession().getAttribute(CSRF_TOKEN);
		if(csrfTokenObject != null && csrfTokenObject instanceof String)
		{
			csrfToken = (String) csrfTokenObject;
		}
		return csrfToken;
	}
	
	public LinkedList<HttpError> validate(Object request)
	{
		LinkedList<HttpError> errors = new LinkedList<HttpError>();
		if(request != null)
		{
			LinkedList<Field> fields = ReflectionUtil.getFields(request.getClass());
			for(Field field : fields)
			{
				filterField(field, request);
				Boolean valid = requiredField(field, request);
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
	
	public void filterField(Field field, Object request)
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
					HttpFieldFilterer fieldFilterer = fieldFilterers.get(name);
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
	
	public Boolean requiredField(Field field, Object request)
	{
		Boolean valid = null;
		Required required = field.getDeclaredAnnotation(Required.class);
		if(required != null)
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
	
	public boolean validateField(Field field, Object request)
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
					HttpFieldValidator fieldValidator = fieldValidators.get(name);
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
	
	public static String readableName(String name)
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
	
}
