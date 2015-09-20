package roth.lib.java.api.cloudflare;

import java.io.Serializable;
import java.util.LinkedHashMap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class CloudFlareResponse<T> implements Serializable
{
	@Property(name = "response")
	protected T response;
	
	@Property(name = "result")
	protected String result;
	
	@Property(name = "msg")
	protected String msg;
	
	@Property(name = "attributes")
	protected LinkedHashMap<String, Object> attributes = new LinkedHashMap<String, Object>();
	
	public CloudFlareResponse()
	{
		
	}
	
	public T getResponse()
	{
		return response;
	}
	
	public String getResult()
	{
		return result;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public LinkedHashMap<String, Object> getAttributes()
	{
		return attributes;
	}
	
}
