package roth.lib.api.cloudflare.client;

import java.lang.reflect.Type;

import roth.lib.api.cloudflare.data.request.CloudFlareRequest;
import roth.lib.api.cloudflare.data.response.CloudFlareResponse;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;
import roth.lib.map.form.FormConfig;
import roth.lib.map.form.FormMapper;
import roth.lib.map.form.FormSerializer;
import roth.lib.map.json.JsonConfig;
import roth.lib.map.json.JsonDeserializer;
import roth.lib.map.json.JsonMapper;
import roth.lib.net.http.HttpHeader;
import roth.lib.net.http.HttpMethod;
import roth.lib.net.http.HttpProtocol;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.api.ApiClient;

public abstract class CloudFlareClient extends ApiClient<CloudFlareRequest, CloudFlareResponse<?>>
{
	protected static String HOST		= "www.cloudflare.com";
	protected static String PATH		= "/api_json.html";
	protected static String SUCCESS 	= "success";
	
	protected static FormConfig FORM_CONFIG 		= new FormConfig();
	protected static FormConfig FORM_CONFIG_DEBUG 	= new FormConfig().setPrettyPrinting(true);
	protected static JsonConfig JSON_CONFIG 		= new JsonConfig();
	protected static JsonConfig JSON_CONFIG_DEBUG 	= new JsonConfig().setPrettyPrinting(true);
	
	protected String email;
	protected String token;
	
	protected CloudFlareClient(String email, String token)
	{
		this(email, token, false);
	}
	
	protected CloudFlareClient(String email, String token, boolean debug)
	{
		super(debug);
		this.email = email;
		this.token = token;
	}
	
	@Override
	protected HttpUrl url()
	{
		return new HttpUrl().setProtocol(HttpProtocol.HTTPS).setHost(HOST).setPath(PATH);
	}
	
	@Override
	protected Serializer<CloudFlareRequest> getSerializer(CloudFlareRequest apiRequest)
	{
		return new FormSerializer<CloudFlareRequest>(apiRequest).setConfig(FORM_CONFIG);
	}
	
	@Override
	protected <T extends CloudFlareResponse<?>> Deserializer<T> getDeserializer(Type type)
	{
		return new JsonDeserializer<T>(type).setConfig(JSON_CONFIG);
	}
	
	@Override
	protected String debugRequest(CloudFlareRequest apiRequest)
	{
		return FormMapper.get().serialize(apiRequest, FORM_CONFIG_DEBUG);
	}
	
	@Override
	protected String debugResponse(CloudFlareResponse<?> apiResponse)
	{
		return JsonMapper.get().serialize(apiResponse, JSON_CONFIG_DEBUG);
	}
	
	@Override
	protected String debugBody(String body)
	{
		return JsonMapper.get().format(body);
	}
	
	@Override
	protected <T extends CloudFlareResponse<?>> T connect(HttpUrl url, CloudFlareRequest cloudFlareRequest, Type type, HttpMethod method, HttpHeader... headers)
	{
		if(cloudFlareRequest != null)
		{
			cloudFlareRequest.setEmail(email);
			cloudFlareRequest.setToken(token);
		}
		else
		{
			throw new CloudFlareException("request cannot be null");
		}
		return super.connect(url, cloudFlareRequest, type, method);
	}
	
	@Override
	protected <T extends CloudFlareResponse<?>> void check(HttpResponse<T> response)
	{
		T cloudFlareResponse = response.getEntity();
		if(cloudFlareResponse != null)
		{
			String result = cloudFlareResponse.getResult();
			if(result == null)
			{
				throw new CloudFlareException("result field is null");
			}
			else if(!SUCCESS.equalsIgnoreCase(result))
			{
				throw new CloudFlareException(result + " : " + cloudFlareResponse.getMsg());
			}
		}
	}
	
}
