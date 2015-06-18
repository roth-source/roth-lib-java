package roth.lib.api.vultr.client;

import java.lang.reflect.Type;

import roth.lib.api.vultr.request.VultrRequest;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;
import roth.lib.map.form.FormConfig;
import roth.lib.map.form.FormMapper;
import roth.lib.map.form.FormSerializer;
import roth.lib.map.json.JsonConfig;
import roth.lib.map.json.JsonDeserializer;
import roth.lib.map.json.JsonMapper;
import roth.lib.net.http.HttpProtocol;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.api.ApiClient;

public class VultrClient extends ApiClient<VultrRequest, Object>
{
	protected static String HOST			= "api.vultr.com";
	protected static String VERSION			= "/v1";
	protected static String SERVER			= "/server";
	protected static String BANDWIDTH		= "/bandwidth";
	protected static String API_KEY			= "api_key";
	
	protected static String TIME_FORMAT				= "yyyy-MM-dd HH:mm:ss.S";
	protected static FormConfig FORM_CONFIG 		= new FormConfig().setTimeFormat(TIME_FORMAT);
	protected static FormConfig FORM_CONFIG_DEBUG 	= new FormConfig().setTimeFormat(TIME_FORMAT).setPrettyPrinting(true);
	protected static JsonConfig JSON_CONFIG 		= new JsonConfig().setTimeFormat(TIME_FORMAT);
	protected static JsonConfig JSON_CONFIG_DEBUG 	= new JsonConfig().setTimeFormat(TIME_FORMAT).setPrettyPrinting(true);
	
	protected String apiKey;
	
	protected VultrClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	protected VultrClient(String apiKey, boolean debug)
	{
		super(debug);
		this.apiKey = apiKey;
	}
	
	@Override
	protected HttpUrl url()
	{
		return new HttpUrl().setProtocol(HttpProtocol.HTTPS).setHost(HOST).setParameter(API_KEY, apiKey);
	}
	
	protected HttpUrl url(String service)
	{
		return url().setPath(VERSION + service);
	}
	
	protected HttpUrl url(String service, VultrRequest vultrRequest)
	{
		return url(service).setParameters(FormMapper.get().convert(vultrRequest, FORM_CONFIG));
	}
	
	@Override
	protected Serializer<VultrRequest> getSerializer(VultrRequest apiRequest)
	{
		return new FormSerializer<VultrRequest>(apiRequest).setConfig(FORM_CONFIG);
	}
	
	@Override
	protected <T> Deserializer<T> getDeserializer(Type type)
	{
		return new JsonDeserializer<T>(type).setConfig(JSON_CONFIG);
	}
	
	@Override
	protected String debugRequest(VultrRequest apiRequest)
	{
		return FormMapper.get().serialize(apiRequest, FORM_CONFIG_DEBUG);
	}
	
	@Override
	protected String debugResponse(Object apiResponse)
	{
		return JsonMapper.get().serialize(apiResponse, JSON_CONFIG_DEBUG);
	}
	
	@Override
	protected String debugBody(String body)
	{
		return JsonMapper.get().format(body);
	}
	
	@Override
	protected <T> void check(HttpResponse<T> response)
	{
		
	}
	
}
