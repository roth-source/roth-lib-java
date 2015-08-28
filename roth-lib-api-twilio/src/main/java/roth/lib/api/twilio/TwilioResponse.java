package roth.lib.api.twilio;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class TwilioResponse implements Serializable
{
	@Property(name = "account_sid")
	protected String accountSid;
	
	@Property(name = "api_version")
	protected String apiVersion;
	
	public TwilioResponse()
	{
		
	}
	
	public String getAccountSid()
	{
		return accountSid;
	}
	
	public String getApiVersion()
	{
		return apiVersion;
	}
	
	public TwilioResponse setAccountSid(String accountSid)
	{
		this.accountSid = accountSid;
		return this;
	}
	
	public TwilioResponse setApiVersion(String apiVersion)
	{
		this.apiVersion = apiVersion;
		return this;
	}
	
}
