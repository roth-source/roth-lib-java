package roth.lib.api.twilio;

import java.lang.reflect.Type;
import java.util.Base64;

import roth.lib.api.twilio.data.response.TwilioResponse;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;
import roth.lib.map.form.FormConfig;
import roth.lib.map.form.FormMapper;
import roth.lib.map.form.FormSerializer;
import roth.lib.map.json.JsonConfig;
import roth.lib.map.json.JsonDeserializer;
import roth.lib.map.json.JsonMapper;
import roth.lib.net.http.HttpProtocol;
import roth.lib.net.http.HttpRequestHeaders;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.api.ApiClient;
import roth.lib.net.http.header.model.Authorization;
import roth.lib.net.http.header.model.ContentType;
import roth.lib.net.http.header.type.MimeType;

public abstract class TwilioClient extends ApiClient<Object, TwilioResponse>
{
	protected static String HOST			= "api.twilio.com";
	protected static String VERSION			= "/2010-04-01";
	protected static String ACCOUNTS		= "/Accounts";
	protected static String MESSAGES		= "/Messages";
	protected static String CALLS			= "/Calls";
	protected static String JSON			= ".json";
	
	protected static String TIME_FORMAT				= "EEE, d MMM yyyy HH:mm:ss Z";
	protected static FormConfig formConfig 			= new FormConfig().setTimeFormat(TIME_FORMAT);
	protected static FormConfig formConfigDebug 	= new FormConfig().setTimeFormat(TIME_FORMAT).setPrettyPrinting(true);
	protected static JsonConfig jsonConfig 			= new JsonConfig().setTimeFormat(TIME_FORMAT);
	protected static JsonConfig jsonConfigDebug 	= new JsonConfig().setTimeFormat(TIME_FORMAT).setPrettyPrinting(true);
	
	protected String accountSid;
	protected String authToken;
	protected String headerToken;
	
	public TwilioClient(String accountSid, String authToken, boolean debug)
	{
		super(debug);
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.headerToken = Base64.getEncoder().encodeToString((accountSid + ":" + authToken).getBytes());
	}
	
	@Override
	protected HttpUrl url()
	{
		return new HttpUrl().setProtocol(HttpProtocol.HTTPS).setHost(HOST).setPath(VERSION + ACCOUNTS + "/" + accountSid);
	}
	
	protected HttpUrl url(String service)
	{
		return url().addPath(service + JSON);
	}
	
	@Override
	protected HttpRequestHeaders getRequestHeaders(HttpUrl url)
	{
		return url.getHeaders().setAuthorization(new Authorization("Basic", headerToken)).setContentType(new ContentType(MimeType.APPLICATION_X_WWW_FORM_URLENCODED));
	}
	
	@Override
	protected Serializer<Object> getSerializer(Object apiRequest)
	{
		return new FormSerializer<Object>(apiRequest).setConfig(formConfig);
	}
	
	@Override
	protected <T extends TwilioResponse> Deserializer<T> getDeserializer(Type type)
	{
		return new JsonDeserializer<T>(type).setConfig(jsonConfig);
	}
	
	@Override
	protected String debugRequest(Object apiRequest)
	{
		return FormMapper.get().serialize(apiRequest, formConfigDebug);
	}
	
	@Override
	protected String debugResponse(TwilioResponse apiResponse)
	{
		return JsonMapper.get().serialize(apiResponse, jsonConfigDebug);
	}
	
	@Override
	protected String debugBody(String body)
	{
		return JsonMapper.get().format(body);
	}
	
	@Override
	protected <T extends TwilioResponse> void check(HttpResponse<T> response)
	{
		
	}
	
}
