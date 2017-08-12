package roth.lib.java.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import roth.lib.java.http.HttpUrl;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.type.MimeType;
import roth.lib.java.util.IdUtil;

public abstract class HttpService
{
	public static String X_SESSION				= "X-Session";
	public static String SESSION				= "session";
	public static String X_CSRF_TOKEN			= "X-Csrf-Token";
	public static String CSRF_TOKEN				= "csrfToken";
	
	protected List<HttpError> errors = new List<HttpError>();
	protected ServletContext servletContext;
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	protected MimeType requestContentType;
	protected MimeType responseContentType;
	protected Mapper requestMapper;
	protected Mapper responseMapper;
	protected String service;
	protected String method;
	protected Map<String, String> paramMap = new Map<>();
	
	public HttpService()
	{
		
	}
	
	public abstract boolean isAjaxAuthenticated(MethodReflector methodReflector);
	public abstract boolean isApiAuthenticated(MethodReflector methodReflector);
	public abstract boolean isAuthorized(MethodReflector methodReflector, Object request);
	
	public List<HttpError> getErrors()
	{
		return errors;
	}
	
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}
	
	public void clearErrors()
	{
		errors.clear();
	}
	
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
	
	public MimeType getRequestContentType()
	{
		return requestContentType;
	}
	
	public MimeType getResponseContentType()
	{
		return responseContentType;
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
	
	public Map<String, String> getParamMap()
	{
		return paramMap;
	}
	
	public HttpService setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
		return this;
	}
	
	public HttpService setHttpServletRequest(HttpServletRequest httpServletRequest)
	{
		this.httpServletRequest = httpServletRequest;
		this.paramMap = HttpUrl.parseParamMap(httpServletRequest.getQueryString());
		return this;
	}
	
	public HttpService setHttpServletResponse(HttpServletResponse httpServletResponse)
	{
		this.httpServletResponse = httpServletResponse;
		return this;
	}
	
	public HttpService setRequestContentType(MimeType requestContentType)
	{
		this.requestContentType = requestContentType;
		return this;
	}
	
	public HttpService setResponseContentType(MimeType responseContentType)
	{
		this.responseContentType = responseContentType;
		return this;
	}
	
	public HttpService setRequestMapper(Mapper requestMapper)
	{
		this.requestMapper = requestMapper;
		return this;
	}
	
	public HttpService setResponseMapper(Mapper responseMapper)
	{
		this.responseMapper = responseMapper;
		return this;
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
	
	public String generateCsrfToken()
	{
		return IdUtil.random(10);
	}
	
	public boolean isValidCsrfToken()
	{
		String requestCsrfToken = getRequestCsrfToken();
		String sessionCsrfToken = getSessionCsrfToken();
		return requestCsrfToken != null && sessionCsrfToken != null && requestCsrfToken.equals(sessionCsrfToken);
	}
	
	public String getRequestCsrfToken()
	{
		return paramMap.get(CSRF_TOKEN);
	}

	public String getRequestSessionId()
	{
		return paramMap.get(SESSION);
	}
	
	public String getSessionCsrfToken()
	{
		return null;
	}
	
	public String getIp()
	{
		return getHttpServletRequest().getRemoteAddr();
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

	public abstract HttpError exception(HttpError error, Throwable e);
	
	public boolean isDebug()
	{
		return false;
	}
	
	public void debug(String service, String method, String request, String response)
	{
		
	}
	
}
