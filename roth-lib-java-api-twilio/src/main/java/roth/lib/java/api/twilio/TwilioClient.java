package roth.lib.java.api.twilio;

import java.util.Base64;

import roth.lib.java.api.FormJsonApiClient;
import roth.lib.java.net.http.HttpHeaders;
import roth.lib.java.net.http.HttpProtocol;
import roth.lib.java.net.http.HttpResponse;
import roth.lib.java.net.http.HttpUrl;
import roth.lib.java.net.http.type.AuthorizationType;
import roth.lib.java.net.http.type.MimeType;

public abstract class TwilioClient extends FormJsonApiClient<Object, TwilioResponse>
{
	protected static String HOST			= "api.twilio.com";
	protected static String VERSION			= "/2010-04-01";
	protected static String ACCOUNTS		= "/Accounts";
	protected static String MESSAGES		= "/Messages";
	protected static String CALLS			= "/Calls";
	protected static String JSON			= ".json";
	
	protected static String TIME_FORMAT		= "EEE, d MMM yyyy HH:mm:ss Z";
	
	protected String accountSid;
	protected String authToken;
	protected String headerToken;
	
	public TwilioClient(String accountSid, String authToken, boolean debug)
	{
		super(debug);
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.headerToken = Base64.getEncoder().encodeToString((accountSid + ":" + authToken).getBytes());
		setTimeFormat(TIME_FORMAT);
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
	protected HttpHeaders setHeaders(HttpHeaders headers)
	{
		return headers.setAuthorization(AuthorizationType.BASIC, headerToken).setContentType(MimeType.APPLICATION_X_WWW_FORM_URLENCODED);
	}
	
	@Override
	protected <T extends TwilioResponse> void checkResponse(HttpResponse<T> response)
	{
		TwilioResponse twilioResponse = response.getEntity();
		if(twilioResponse.getErrorCode() != null)
		{
			throw new TwilioException(twilioResponse.getErrorCode() + " : " + twilioResponse.getErrorMessage());
		}
	}
	
}
