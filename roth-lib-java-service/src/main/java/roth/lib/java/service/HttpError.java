package roth.lib.java.service;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class HttpError implements Serializable
{
	@Property(name = "type")
	protected String type;
	
	@Property(name = "message")
	protected String message;
	
	@Property(name = "context")
	protected String context;
	
	public HttpError()
	{
		
	}
	
	public HttpError(HttpErrorType type)
	{
		this(type.name());
	}
	
	public HttpError(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public HttpError setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public HttpError setMessage(String message)
	{
		this.message = message;
		return this;
	}
	
	public HttpError setContext(String context)
	{
		this.context = context;
		return this;
	}
	
}
