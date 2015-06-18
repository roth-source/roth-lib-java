package roth.lib.net.http;

import roth.lib.Characters;
import roth.lib.map.Serializer;

public class HttpRequest<T> implements Characters
{
	protected HttpMethod method = HttpMethod.GET;
	protected HttpUrl url;
	protected HttpVersion version = HttpVersion.HTTP_1_1;
	protected HttpRequestHeaders headers;
	protected Serializer<T> serializer;
	
	public HttpRequest()
	{
		
	}
	
	public HttpRequest(HttpMethod method, HttpUrl url)
	{
		this.method = method;
		this.url = url;
	}
	
	public HttpMethod getMethod()
	{
		return method;
	}
	
	public HttpUrl getUrl()
	{
		return url;
	}
	
	public HttpVersion getVersion()
	{
		return version;
	}
	
	public HttpRequestHeaders getHeaders()
	{
		return headers;
	}
	
	public Serializer<T> getSerializer()
	{
		return serializer;
	}
	
	public boolean hasSerializer()
	{
		return serializer != null;
	}
	
	public HttpRequest<T> setMethod(HttpMethod method)
	{
		this.method = method;
		return this;
	}
	
	public HttpRequest<T> setUrl(HttpUrl url)
	{
		this.url = url;
		return this;
	}
	
	public HttpRequest<T> setVersion(HttpVersion version)
	{
		this.version = version;
		return this;
	}
	
	public HttpRequest<T> setHeaders(HttpRequestHeaders headers)
	{
		this.headers = headers;
		return this;
	}
	
	public HttpRequest<T> setSerializer(Serializer<T> serializer)
	{
		this.serializer = serializer;
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(NEW_LINE);
		builder.append("REQUEST");
		builder.append(NEW_LINE);
		builder.append("----------------------------------");
		builder.append(NEW_LINE);
		if(method != null)
		{
			builder.append(method.name());
			builder.append(SPACE);
		}
		if(url != null)
		{
			builder.append(url.toResourcePath());
			builder.append(SPACE);
		}	
		if(version != null)
		{
			builder.append(version.getValue());
		}
		builder.append(CARRIAGE_RETURN);
		builder.append(NEW_LINE);
		if(headers != null)
		{
			builder.append(headers);
		}
		builder.append(CARRIAGE_RETURN);
		builder.append(NEW_LINE);
		return builder.toString();
	}
	
}
