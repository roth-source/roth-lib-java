package roth.lib.service.endpoint;

import roth.lib.service.HttpService;
import roth.lib.service.annotation.Method;
import roth.lib.service.util.InitialContextUtil;

public class HttpEndpointService extends HttpService
{
	
	@Method(authenticated = false)
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
	
	@Method(authenticated = false)
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
	
	@Method(authenticated = false)
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
	public boolean isAjaxAuthenticated(String context)
	{
		return true;
	}
	
	@Override
	public boolean isApiAuthenticated(String context)
	{
		return true;
	}
	
	@Override
	public boolean isAuthorized(String context, Object request)
	{
		return true;
	}
	
}
