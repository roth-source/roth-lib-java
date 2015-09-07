package roth.lib.api.twilio;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class TwilioResponse implements Serializable
{
	@Property(name = "account_sid")
	protected String accountSid;
	
	@Property(name = "api_version")
	protected String apiVersion;
	
	@Property(name = "error_code")
	protected String errorCode;
	
	@Property(name = "error_message")
	protected String errorMessage;
	
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
	
	public String getErrorCode()
	{
		return errorCode;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
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
	
	public TwilioResponse setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
		return this;
	}
	
	public TwilioResponse setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
		return this;
	}
	
}
