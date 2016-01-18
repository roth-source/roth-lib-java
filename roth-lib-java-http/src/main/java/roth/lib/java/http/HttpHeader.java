package roth.lib.java.http;

public class HttpHeader
{
	protected String name;
	protected String value;
	
	public HttpHeader(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public HttpHeader setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public HttpHeader setValue(String value)
	{
		this.value = value;
		return this;
	}
	
}
