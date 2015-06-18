package roth.lib.net.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import roth.lib.map.Deserializer;

public class HttpClient
{
	
	public HttpClient()
	{
		super();
	}
	
	public HttpConnection connection(HttpUrl url, boolean debug) throws IOException
	{
		URLConnection connection = new URL(url.toString()).openConnection();
		if(connection instanceof HttpURLConnection)
		{
			return new HttpConnection(url, (HttpURLConnection) connection, debug);
		}
		else
		{
			throw new HttpException("unable to cast connection to HttpURLConnection");
		}
	}
	
	public <T> HttpResponse<T> connect(HttpRequest<?> request, Deserializer<T> deserializer, boolean debug) throws IOException
	{
		return connection(request.getUrl(), debug).connect(request, deserializer);
	}
	
	public HttpResponse<?> connect(HttpRequest<?> request, boolean debug) throws IOException
	{
		return connection(request.getUrl(), debug).connect(request);
	}
	
}
