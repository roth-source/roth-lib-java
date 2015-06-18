package roth.lib.api.digitalocean.client;

import java.lang.reflect.Type;

import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;
import roth.lib.map.json.JsonConfig;
import roth.lib.map.json.JsonDeserializer;
import roth.lib.map.json.JsonMapper;
import roth.lib.map.json.JsonSerializer;
import roth.lib.net.http.HttpProtocol;
import roth.lib.net.http.HttpRequestHeaders;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.api.ApiClient;
import roth.lib.net.http.header.model.Authorization;
import roth.lib.net.http.header.model.ContentType;
import roth.lib.net.http.header.type.MimeType;

public abstract class DigitalOceanClient extends ApiClient<Object, Object>
{
	protected static String HOST			= "api.digitalocean.com";
	protected static String VERSION			= "/v2";
	protected static String ACTIONS 		= "/actions/";
	protected static String DOMAINS 		= "/domains/";
	protected static String RECORDS 		= "/records/";
	protected static String DROPLETS 		= "/droplets/";
	protected static String KERNELS 		= "/kernels/";
	protected static String SNAPSHOTS 		= "/snapshots/";
	protected static String BACKUPS 		= "/backups/";
	protected static String IMAGES 			= "/images/";
	protected static String ACCOUNT_KEYS	= "/account/keys/";
	protected static String REGIONS 		= "/regions/";
	protected static String SIZES 			= "/sizes/";
	protected static String PAGE			= "page";
	protected static String PER_PAGE		= "per_page";
	protected static String AUTHORIZATION	= "Authorization";
	protected static String BEARER			= "Bearer";
	
	protected static String TIME_FORMAT				= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	protected static JsonConfig JSON_CONFIG 		= new JsonConfig().setTimeFormat(TIME_FORMAT);
	protected static JsonConfig JSON_CONFIG_DEBUG 	= new JsonConfig().setTimeFormat(TIME_FORMAT).setPrettyPrinting(true);
	
	protected String token;
	
	protected DigitalOceanClient(String token)
	{
		this(token, false);
	}
	
	protected DigitalOceanClient(String token, boolean debug)
	{
		super(debug);
		this.token = token;
	}
	
	protected HttpUrl url()
	{
		return new HttpUrl().setProtocol(HttpProtocol.HTTPS).setHost(HOST);
	}
	
	protected HttpUrl url(String service)
	{
		return url().setPath(VERSION + service);
	}
	
	protected HttpUrl url(String service, int page)
	{
		return url(service).setParameter(PAGE, String.valueOf(page));
	}
	
	@Override
	protected HttpRequestHeaders getRequestHeaders(HttpUrl url)
	{
		return url.getHeaders().setAuthorization(new Authorization(BEARER, token)).setContentType(new ContentType(MimeType.APPLICATION_JSON));
	}
	
	@Override
	protected Serializer<Object> getSerializer(Object apiRequest)
	{
		return new JsonSerializer<Object>(apiRequest).setConfig(JSON_CONFIG);
	}
	
	@Override
	protected <T> Deserializer<T> getDeserializer(Type type)
	{
		return new JsonDeserializer<T>(type).setConfig(JSON_CONFIG);
	}
	
	@Override
	protected String debugRequest(Object apiRequest)
	{
		return JsonMapper.get().serialize(apiRequest, JSON_CONFIG_DEBUG);
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
