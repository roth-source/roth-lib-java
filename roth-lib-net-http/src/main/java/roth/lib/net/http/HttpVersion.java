package roth.lib.net.http;

public enum HttpVersion
{
	HTTP_1_0	("HTTP/1.0"),
	HTTP_1_1	("HTTP/1.1"),
	;
	
	protected String value;
	
	HttpVersion(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public static HttpVersion get(String value)
	{
		HttpVersion version = HTTP_1_1;
		for(HttpVersion testVersion : values())
		{
			if(testVersion.getValue().equalsIgnoreCase(value))
			{
				version = testVersion;
				break;
			}
		}
		return version;
	}
	
}
