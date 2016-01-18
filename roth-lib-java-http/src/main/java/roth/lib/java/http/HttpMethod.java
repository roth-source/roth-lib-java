package roth.lib.java.http;

public enum HttpMethod
{
	OPTIONS,
	HEAD,
	GET,
	POST,
	PUT,
	DELETE,
	;
	
	public static HttpMethod fromString(String value)
	{
		HttpMethod httpMethod = null;
		try
		{
			if(value != null)
			{
				httpMethod = HttpMethod.valueOf(value.toUpperCase());
			}
		}
		catch(Exception e)
		{
			
		}
		return httpMethod;
	}
	
}
