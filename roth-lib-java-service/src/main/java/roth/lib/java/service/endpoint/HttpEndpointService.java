package roth.lib.java.service.endpoint;

import roth.lib.java.service.HttpService;
import roth.lib.java.service.annotation.ServiceMethod;
import roth.lib.java.service.reflector.MethodReflector;
import roth.lib.java.service.util.InitialContextUtil;

public class HttpEndpointService extends HttpService
{
	
	@ServiceMethod(authenticated = false, get = true)
	public HttpEndpointEnv env()
	{
		HttpEndpointEnv endpointEnv = new HttpEndpointEnv();
		String env = InitialContextUtil.getEnv();
		if(env != null)
		{
			endpointEnv.setEnv(env);
		}
		else
		{
			endpointEnv.setEnv("dev");
		}
		return endpointEnv;
	}
	
	@ServiceMethod(authenticated = false, get = true)
	public HttpEndpointList list()
	{
		HttpEndpointList endpointList = new HttpEndpointList();
		String[] endpoints = InitialContextUtil.getEndpoints();
		if(endpoints != null)
		{
			endpointList.setEndpoints(endpoints);
		}
		else
		{
			endpointList.addEndpoint(getEndpoint());
		}
		return endpointList;
	}
	
	@ServiceMethod(authenticated = false, get = true)
	protected String getEndpoint()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(httpServletRequest.getScheme());
		builder.append("://");
		builder.append(httpServletRequest.getServerName());
		int port = httpServletRequest.getServerPort();
		builder.append((port != 80 && port != 443) ? ":" + port : "");
		builder.append(httpServletRequest.getContextPath());
		String endpoint = builder.toString();
		endpoint += !endpoint.endsWith("/") ? "/" : "";
		return endpoint;
	}
	
	@Override
	public boolean isAjaxAuthenticated(MethodReflector methodReflector)
	{
		return true;
	}
	
	@Override
	public boolean isApiAuthenticated(MethodReflector methodReflector)
	{
		return true;
	}
	
	@Override
	public boolean isAuthorized(MethodReflector methodReflector, Object request)
	{
		return true;
	}
	
}
