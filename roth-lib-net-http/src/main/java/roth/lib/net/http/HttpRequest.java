package roth.lib.net.http;

import roth.lib.Characters;
import roth.lib.map.outputter.Outputter;

public class HttpRequest<T> implements Characters
{
	protected HttpMethod method = HttpMethod.GET;
	protected HttpUrl url;
	protected HttpVersion version = HttpVersion.HTTP_1_1;
	protected HttpHeaders headers;
	protected Outputter outputter;
	
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
	
	public HttpHeaders getHeaders()
	{
		return headers;
	}
	
	public Outputter getOutputter()
	{
		return outputter;
	}
	
	public boolean hasOutputter()
	{
		return outputter != null;
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
	
	public HttpRequest<T> setHeaders(HttpHeaders headers)
	{
		this.headers = headers;
		return this;
	}
	
	public HttpRequest<T> setOutputter(Outputter outputter)
	{
		this.outputter = outputter;
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
