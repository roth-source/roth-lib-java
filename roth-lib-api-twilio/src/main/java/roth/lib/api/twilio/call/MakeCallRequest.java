package roth.lib.api.twilio.call;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class MakeCallRequest implements Serializable
{
	@Property(name = "From")
	protected String from;
	
	@Property(name = "To")
	protected String to;
	
	@Property(name = "Url")
	protected String url;
	
	@Property(name = "Method")
	protected String method;
	
	@Property(name = "FallbackUrl")
	protected String fallbackUrl;
	
	@Property(name = "FallbackMethod")
	protected String fallbackMethod;
	
	@Property(name = "StatusCallback")
	protected String statusCallback;
	
	@Property(name = "StatusCallbackMethod")
	protected String statusCallbackMethod;
	
	@Property(name = "SendDigits")
	protected String sendDigits;
	
	@Property(name = "IfMachine")
	protected String ifMachine;
	
	@Property(name = "Timeout")
	protected Integer timeout;
	
	@Property(name = "Record")
	protected Boolean record;
	
	public MakeCallRequest(String from, String to, String url)
	{
		this.from = from;
		this.to = to;
		this.url = url;
	}
	
	public String getFrom()
	{
		return from;
	}
	
	public String getTo()
	{
		return to;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public String getMethod()
	{
		return method;
	}
	
	public String getFallbackUrl()
	{
		return fallbackUrl;
	}
	
	public String getFallbackMethod()
	{
		return fallbackMethod;
	}
	
	public String getStatusCallback()
	{
		return statusCallback;
	}
	
	public String getStatusCallbackMethod()
	{
		return statusCallbackMethod;
	}
	
	public String getSendDigits()
	{
		return sendDigits;
	}
	
	public String getIfMachine()
	{
		return ifMachine;
	}
	
	public Integer getTimeout()
	{
		return timeout;
	}
	
	public Boolean getRecord()
	{
		return record;
	}
	
	public MakeCallRequest setFrom(String from)
	{
		this.from = from;
		return this;
	}
	
	public MakeCallRequest setTo(String to)
	{
		this.to = to;
		return this;
	}
	
	public MakeCallRequest setUrl(String url)
	{
		this.url = url;
		return this;
	}
	
	public MakeCallRequest setMethod(String method)
	{
		this.method = method;
		return this;
	}
	
	public MakeCallRequest setFallbackUrl(String fallbackUrl)
	{
		this.fallbackUrl = fallbackUrl;
		return this;
	}
	
	public MakeCallRequest setFallbackMethod(String fallbackMethod)
	{
		this.fallbackMethod = fallbackMethod;
		return this;
	}
	
	public MakeCallRequest setStatusCallback(String statusCallback)
	{
		this.statusCallback = statusCallback;
		return this;
	}
	
	public MakeCallRequest setStatusCallbackMethod(String statusCallbackMethod)
	{
		this.statusCallbackMethod = statusCallbackMethod;
		return this;
	}
	
	public MakeCallRequest setSendDigits(String sendDigits)
	{
		this.sendDigits = sendDigits;
		return this;
	}
	
	public MakeCallRequest setIfMachine(String ifMachine)
	{
		this.ifMachine = ifMachine;
		return this;
	}
	
	public MakeCallRequest setTimeout(Integer timeout)
	{
		this.timeout = timeout;
		return this;
	}
	
	public MakeCallRequest setRecord(Boolean record)
	{
		this.record = record;
		return this;
	}
	
}
