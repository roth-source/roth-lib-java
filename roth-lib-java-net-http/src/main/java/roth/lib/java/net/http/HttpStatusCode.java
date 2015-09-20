package roth.lib.java.net.http;

public enum HttpStatusCode
{
	_100 ("Continue"),
	_101 ("Switching Protocols"),
	
	_200 ("OK"),
	_201 ("Created"),
	_202 ("Accepted"),
	_203 ("Non-Authoritative Information"),
	_204 ("No Content"),
	_205 ("Reset Content"),
	_206 ("Partial Content"),
	
	_300 ("Multiple Choices"),
	_301 ("Moved Permanently"),
	_302 ("Found"),
	_303 ("See Other"),
	_304 ("Not Modified"),
	_305 ("Use Proxy"),
	_307 ("Temporary Redirect"),
	
	_400 ("Bad Request"),
	_401 ("Unauthorized"),
	_402 ("Payment Required"),
	_403 ("Forbidden"),
	_404 ("Not Found"),
	_405 ("Method Not Allowed"),
	_406 ("Not Acceptable"),
	_407 ("Proxy Authentication Required"),
	_408 ("Request Time-out"),
	_409 ("Conflict"),
	_410 ("Gone"),
	_411 ("Length Required"),
	_412 ("Precondition Failed"),
	_413 ("Request Entity Too Large"),
	_414 ("Request-URI Too Large"),
	_415 ("Unsupported Media Type"),
	_416 ("Requested range not satisfiable"),
	_417 ("Expectation Failed"),
	
	_500 ("Internal Server Error"),
	_501 ("Not Implemented"),
	_502 ("Bad Gateway"),
	_503 ("Service Unavailable"),
	_504 ("Gateway Time-out"),
	_505 ("HTTP Version not supported"),
	;
	
	protected String description;
	
	HttpStatusCode(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getCode()
	{
		int code = 0;
		try
		{
			code = Integer.parseInt(name().substring(1));
		}
		catch(Exception e)
		{
			
		}
		return code;
	}
	
	public boolean isInformational()
	{
		int code = getCode();
		return 100 <= code && code < 200;
	}
	
	public boolean isSuccess()
	{
		int code = getCode();
		return 200 <= code && code < 300;
	}
	
	public boolean isRedirection()
	{
		int code = getCode();
		return 300 <= code && code < 400;
	}
	
	public boolean isClientError()
	{
		int code = getCode();
		return 400 <= code && code < 500;
	}
	
	public boolean isServerError()
	{
		int code = getCode();
		return 500 <= code && code < 600;
	}
	
	public static HttpStatusCode fromInteger(Integer value)
	{
		return fromString(String.valueOf(value));
	}
	
	public static HttpStatusCode fromString(String value)
	{
		HttpStatusCode code = null;
		try
		{
			code = valueOf("_" + value);
		}
		catch(Exception e)
		{
			
		}
		return code;
	}
	
}
