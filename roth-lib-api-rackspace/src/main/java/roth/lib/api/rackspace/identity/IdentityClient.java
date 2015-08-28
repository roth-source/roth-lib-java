package roth.lib.api.rackspace.identity;

import java.util.LinkedList;

import roth.lib.api.rackspace.RackspaceClient;
import roth.lib.api.rackspace.RackspaceException;
import roth.lib.api.rackspace.model.Access;
import roth.lib.api.rackspace.model.AuthToken;
import roth.lib.api.rackspace.model.Endpoint;
import roth.lib.api.rackspace.model.Service;
import roth.lib.api.rackspace.model.User;
import roth.lib.api.rackspace.type.RegionType;
import roth.lib.api.rackspace.type.ServiceType;
import roth.lib.net.http.HttpHeaders;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.type.MimeType;

public class IdentityClient extends RackspaceClient
{
	protected static String HOST 		= "identity.api.rackspacecloud.com";
	protected static String VERSION 	= "/v2.0";
	protected static String SERVICE 	= "/tokens";
	protected static int MAX_ATTEMPTS	= 3;
	
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
	
	public String getUsername()
	{
		return username;
	}
	
	public String getApiKey()
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
	
	public synchronized Access getAccess()
	{
		return getAccess(1);
	}
	
	public synchronized Access getAccess(int attempt)
	{
		if(access == null)
		{
			AuthResponse authResponse = auth(new AuthRequest(username, apiKey));
			if(authResponse != null && authResponse.getAccess() != null)
			{
				access = authResponse.getAccess();
			}
			else if(attempt < MAX_ATTEMPTS)
			{
				access = getAccess(++attempt);
			}
			else
			{
				throw new RackspaceException("authentication failed " + MAX_ATTEMPTS + " attempts");
			}
		}
		return access;
	}
	
	public synchronized Access resetAccess()
	{
		access = null;
		return getAccess();
	}
	
	public synchronized AuthToken getAuthToken()
	{
		AuthToken authToken = getAccess().getAuthToken();
		if(authToken.isExpired())
		{
			authToken = resetAccess().getAuthToken();
		}
		return authToken;
	}
	
	public synchronized LinkedList<Service> getServices()
	{
		return getAccess().getServices();
	}
	
	public synchronized Service getService(ServiceType serviceType)
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
	
	public synchronized User getUser()
	{
		return getAccess().getUser();
	}
	
	public synchronized RegionType getRegionType()
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
	
	public synchronized Endpoint getEndpoint(ServiceType serviceType)
	{
		return getEndpoint(serviceType, getRegionType());
	}
	
	public synchronized Endpoint getEndpoint(ServiceType serviceType, RegionType regionType)
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
	protected HttpHeaders setHeaders(HttpHeaders headers)
	{
		return headers.setContentType(MimeType.APPLICATION_JSON);
	}
	
	protected AuthResponse auth(AuthRequest request)
	{
		return post(url(), request, AuthResponse.class);
	}
	
}
