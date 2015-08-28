package roth.lib.api.twilio.message;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class SendMessageRequest implements Serializable
{
	@Property(name = "From")
	protected String from;
	
	@Property(name = "To")
	protected String to;
	
	@Property(name = "Body")
	protected String body;
	
	@Property(name = "MediaUrl")
	protected String mediaUrl;
	
	@Property(name = "StatusCallback")
	protected String statusCallback;
	
	public SendMessageRequest(String from, String to, String body)
	{
		this.from = from;
		this.to = to;
		this.body = body;
	}
	
	public String getFrom()
	{
		return from;
	}
	
	public String getTo()
	{
		return to;
	}
	
	public String getBody()
	{
		return body;
	}
	
	public String getMediaUrl()
	{
		return mediaUrl;
	}
	
	public String getStatusCallback()
	{
		return statusCallback;
	}
	
	public SendMessageRequest setFrom(String from)
	{
		this.from = from;
		return this;
	}
	
	public SendMessageRequest setTo(String to)
	{
		this.to = to;
		return this;
	}
	
	public SendMessageRequest setBody(String body)
	{
		this.body = body;
		return this;
	}
	
	public SendMessageRequest setMediaUrl(String mediaUrl)
	{
		this.mediaUrl = mediaUrl;
		return this;
	}
	
	public SendMessageRequest setStatusCallback(String statusCallback)
	{
		this.statusCallback = statusCallback;
		return this;
	}
	
}
