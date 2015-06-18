package roth.lib.api.rackspace.client;

import java.util.LinkedList;

import roth.lib.api.rackspace.data.model.Access;
import roth.lib.api.rackspace.data.model.AuthToken;
import roth.lib.api.rackspace.data.model.Endpoint;
import roth.lib.api.rackspace.data.model.Service;
import roth.lib.api.rackspace.data.model.User;
import roth.lib.api.rackspace.data.request.AuthRequest;
import roth.lib.api.rackspace.data.response.AuthResponse;
import roth.lib.api.rackspace.data.type.RegionType;
import roth.lib.api.rackspace.data.type.ServiceType;
import roth.lib.net.http.HttpRequestHeaders;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.header.model.ContentType;
import roth.lib.net.http.header.type.MimeType;

public class IdentityClient extends RackspaceClient
{
	protected static String HOST 		= "identity.api.rackspacecloud.com";
	protected static String VERSION 	= "/v2.0";
	protected static String SERVICE 	= "/tokens";
	
	protected String username;
	protected String apiKey;
	protected Access access;
	
	public IdentityClient(String username, String apiKey)
	{
		this(username, apiKey, false);
	}
	
	public IdentityClient(String username, String apiKey, boolean debug)
	{
		super(debug);
		this.username = username;
		this.apiKey = apiKey;
	}
	
	protected String getUsername()
	{
		return username;
	}
	
	protected String getApiKey()
	{
		return apiKey;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
	}
	
	public Access getAccess()
	{
		if(access == null)
		{
			AuthResponse authResponse = auth(new AuthRequest(username, apiKey));
			if(authResponse != null && authResponse.getAccess() != null)
			{
				access = authResponse.getAccess();
			}
			else
			{
				throw new RackspaceException("authentication failed");
			}
		}
		return access;
	}
	
	public Access resetAccess()
	{
		access = null;
		return getAccess();
	}
	
	public AuthToken getAuthToken()
	{
		AuthToken authToken = getAccess().getAuthToken();
		if(authToken.isExpired())
		{
			authToken = resetAccess().getAuthToken();
		}
		return authToken;
	}
	
	public LinkedList<Service> getServices()
	{
		return getAccess().getServices();
	}
	
	public Service getService(ServiceType serviceType)
	{
		for(Service service : getServices())
		{
			if(serviceType.getName().equalsIgnoreCase(service.getName()))
			{
				return service;
			}
		}
		return null;
	}
	
	public User getUser()
	{
		return getAccess().getUser();
	}
	
	public RegionType getRegionType()
	{
		RegionType regionType = RegionType.ORD;
		try
		{
			regionType = RegionType.valueOf(getUser().getDefaultRegion().toUpperCase());
		}
		catch(Exception e)
		{
			
		}
		return regionType;
	}
	
	public Endpoint getEndpoint(ServiceType serviceType)
	{
		return getEndpoint(serviceType, getRegionType());
	}
	
	public Endpoint getEndpoint(ServiceType serviceType, RegionType regionType)
	{
		Service service = getService(serviceType);
		if(service != null)
		{
			for(Endpoint endpoint : service.getEndpoints())
			{
				if(regionType.name().equalsIgnoreCase(endpoint.getRegion()))
				{
					return endpoint;
				}
			}
		}
		return null;
	}
	
	@Override
	protected HttpUrl url()
	{
		return new HttpUrl(true).setHost(HOST).setPath(VERSION + SERVICE);
	}
	
	@Override
	protected HttpRequestHeaders getRequestHeaders(HttpUrl url)
	{
		return url.getHeaders().setContentType(new ContentType(MimeType.APPLICATION_JSON));
	}
	
	protected AuthResponse auth(AuthRequest request)
	{
		return post(url(), request, AuthResponse.class);
	}
	
}
