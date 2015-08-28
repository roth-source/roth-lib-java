package roth.lib.api.digitalocean;

import roth.lib.api.JsonApiClient;
import roth.lib.net.http.HttpHeaders;
import roth.lib.net.http.HttpProtocol;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.HttpUrl;
import roth.lib.net.http.type.AuthorizationType;
import roth.lib.net.http.type.MimeType;

public abstract class DigitalOceanClient extends JsonApiClient<Object, Object>
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
	
	protected static String TIME_FORMAT		= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	protected String token;
	
	protected DigitalOceanClient(String token)
	{
		this(token, false);
	}
	
	protected DigitalOceanClient(String token, boolean debug)
	{
		super(debug);
		this.token = token;
		setTimeFormat(TIME_FORMAT);
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
	protected HttpHeaders setHeaders(HttpHeaders headers)
	{
		return headers.setAuthorization(AuthorizationType.BEARER, token).setContentType(MimeType.APPLICATION_JSON);
	}
	
	@Override
	protected <T> void checkResponse(HttpResponse<T> response)
	{
		
	}
	
}
