package roth.lib.api.rackspace;

import roth.lib.Characters;
import roth.lib.api.JsonApiClient;
import roth.lib.api.rackspace.identity.IdentityClient;
import roth.lib.api.rackspace.model.AuthToken;
import roth.lib.api.rackspace.model.Endpoint;
import roth.lib.api.rackspace.type.ServiceType;
import roth.lib.net.http.HttpResponse;

public abstract class RackspaceClient extends JsonApiClient<Object, Object> implements Characters
{
	protected static String TIME_FORMAT	= "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	protected IdentityClient identityClient;
	
	protected RackspaceClient(boolean debug)
	{
		super(debug);
		setTimeFormat(TIME_FORMAT);
	}
	
	protected RackspaceClient(IdentityClient identityClient, boolean debug)
	{
		super(debug);
		this.identityClient = identityClient;
	}
	
	public IdentityClient getIdentityClient()
	{
		return identityClient;
	}
	
	public AuthToken getAuthToken()
	{
		return identityClient.getAuthToken();
	}
	
	public String getAutoTokenId()
	{
		return getAuthToken().getId();
	}
	
	public Endpoint getEndpoint(ServiceType serviceType)
	{
		return identityClient.getEndpoint(serviceType);
	}
	
	@Override
	protected <T> void checkResponse(HttpResponse<T> response)
	{
		
	}
	
}
