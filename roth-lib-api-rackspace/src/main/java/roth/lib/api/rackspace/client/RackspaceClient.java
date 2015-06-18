package roth.lib.api.rackspace.client;

import java.lang.reflect.Type;

import roth.lib.Characters;
import roth.lib.api.rackspace.data.model.AuthToken;
import roth.lib.api.rackspace.data.model.Endpoint;
import roth.lib.api.rackspace.data.type.ServiceType;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;
import roth.lib.map.json.JsonConfig;
import roth.lib.map.json.JsonDeserializer;
import roth.lib.map.json.JsonMapper;
import roth.lib.map.json.JsonSerializer;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.api.ApiClient;

public abstract class RackspaceClient extends ApiClient<Object, Object> implements Characters
{
	protected static String TIME_FORMAT				= "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	protected static JsonMapper JSON_MAPPER 		= new JsonMapper();
	protected static JsonConfig JSON_CONFIG 		= new JsonConfig().setTimeFormat(TIME_FORMAT);
	protected static JsonConfig JSON_CONFIG_DEBUG 	= new JsonConfig().setTimeFormat(TIME_FORMAT).setPrettyPrinting(true);
	
	protected IdentityClient identityClient;
	
	protected RackspaceClient(boolean debug)
	{
		super(debug);
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
	protected Serializer<Object> getSerializer(Object apiRequest)
	{
		return new JsonSerializer<Object>(apiRequest).setMapper(JSON_MAPPER).setConfig(JSON_CONFIG);
	}
	
	@Override
	protected <T> Deserializer<T> getDeserializer(Type type)
	{
		return new JsonDeserializer<T>(type).setMapper(JSON_MAPPER).setConfig(JSON_CONFIG);
	}
	
	@Override
	protected String debugRequest(Object apiRequest)
	{
		return JSON_MAPPER.serialize(apiRequest, JSON_CONFIG_DEBUG);
	}
	
	@Override
	protected String debugResponse(Object apiResponse)
	{
		return JSON_MAPPER.serialize(apiResponse, JSON_CONFIG_DEBUG);
	}
	
	@Override
	protected String debugBody(String body)
	{
		return JSON_MAPPER.format(body);
	}
	
	@Override
	protected <T> void check(HttpResponse<T> response)
	{
		
	}
	
}
