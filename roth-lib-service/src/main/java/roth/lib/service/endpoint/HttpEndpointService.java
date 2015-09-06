package roth.lib.service.endpoint;

import roth.lib.service.HttpService;
import roth.lib.service.util.InitialContextUtil;

public class HttpEndpointService extends HttpService
{
	
	@Override
	public boolean isAuthorized(Object request)
	{
		return true;
	}
	
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
	
}
