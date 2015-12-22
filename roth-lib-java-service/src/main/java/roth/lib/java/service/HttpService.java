package roth.lib.java.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import roth.lib.java.mapper.Mapper;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.type.MimeType;
import roth.lib.java.util.IdUtil;

public abstract class HttpService
{
	public static String X_CSRF_TOKEN			= "X-Csrf-Token";
	public static String CSRF_TOKEN				= "csrfToken";
	
	protected ServletContext servletContext;
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	protected MimeType requestContentType;
	protected MimeType responseContentType;
	protected Mapper requestMapper;
	protected Mapper responseMapper;
	protected String service;
	protected String method;
	
	public HttpService()
	{
		
	}
	
	public abstract boolean isAjaxAuthenticated(MethodReflector methodReflector);
	public abstract boolean isApiAuthenticated(MethodReflector methodReflector);
	public abstract boolean isAuthorized(MethodReflector methodReflector, Object request);
	
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
	
}
