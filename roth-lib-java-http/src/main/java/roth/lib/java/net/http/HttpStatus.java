package roth.lib.java.net.http;

import roth.lib.java.Characters;

public class HttpStatus implements Characters
{
	protected HttpStatusCode statusCode;
	protected Integer code;
	protected String reason;
	
	public HttpStatus()
	{
		
	}
	
	public HttpStatus(HttpStatusCode statusCode, String reason)
	{
		this.statusCode = statusCode;
		this.reason = reason;
	}
	
	public HttpStatusCode getStatusCode()
	{
		return statusCode;
	}
	
	public Integer getCode()
	{
		return code;
	}
	
	public String getReason()
	{
		return reason;
	}
	
	public HttpStatus setStatusCode(HttpStatusCode statusCode)
	{
		this.statusCode = statusCode;
		return this;
	}
	
	public HttpStatus setCode(Integer code)
	{
		this.code = code;
		return this;
	}
	
	public HttpStatus setReason(String reason)
	{
		this.reason = reason;
		return this;
	}
	
	public HttpStatus parseCode(int code)
	{
		statusCode = HttpStatusCode.fromInteger(code);
		this.code = code;
		return this;
	}
	
	public HttpStatus parseCode(String code)
	{
		statusCode = HttpStatusCode.fromString(code);
		try
		{
			this.code = Integer.parseInt(code);
		}
		catch(Exception e)
		{
			
		}
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		if(code != null)
		{
			builder.append(code);
			builder.append(SPACE);
		}
		if(reason != null)
		{
			builder.append(reason);
		}
		return builder.toString();
	}
	
}
