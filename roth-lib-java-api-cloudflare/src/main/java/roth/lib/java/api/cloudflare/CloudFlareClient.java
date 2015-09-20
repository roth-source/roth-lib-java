package roth.lib.java.api.cloudflare;

import java.lang.reflect.Type;

import roth.lib.java.api.FormJsonApiClient;
import roth.lib.java.net.http.HttpHeader;
import roth.lib.java.net.http.HttpMethod;
import roth.lib.java.net.http.HttpProtocol;
import roth.lib.java.net.http.HttpResponse;
import roth.lib.java.net.http.HttpUrl;

public abstract class CloudFlareClient extends FormJsonApiClient<CloudFlareRequest, CloudFlareResponse<?>>
{
	protected static String HOST		= "www.cloudflare.com";
	protected static String PATH		= "/api_json.html";
	protected static String SUCCESS 	= "success";
	
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
	protected <T extends CloudFlareResponse<?>> T connect(HttpUrl url, CloudFlareRequest cloudFlareRequest, Type type, HttpMethod method, boolean gzip, HttpHeader... headers)
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
		return super.connect(url, cloudFlareRequest, type, method, gzip);
	}
	
	@Override
	protected <T extends CloudFlareResponse<?>> void checkResponse(HttpResponse<T> response)
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
