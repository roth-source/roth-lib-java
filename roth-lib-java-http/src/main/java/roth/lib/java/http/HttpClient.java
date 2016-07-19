package roth.lib.java.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import roth.lib.java.inputter.Inputter;

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
			if(connection instanceof HttpsURLConnection)
			{
				setSslSocketFactory((HttpsURLConnection) connection);
			}
			return new HttpConnection(url, (HttpURLConnection) connection, debug);
		}
		else
		{
			throw new HttpException("unable to cast connection to HttpURLConnection");
		}
	}
	
	protected void setSslSocketFactory(HttpsURLConnection connection)
	{
		
	}
	
	public <T> HttpResponse<T> connect(HttpRequest<?> request, Inputter<T> inputter, boolean debug) throws IOException
	{
		return connection(request.getUrl(), debug).connect(request, inputter);
	}
	
	public HttpResponse<?> connect(HttpRequest<?> request, boolean debug) throws IOException
	{
		return connection(request.getUrl(), debug).connect(request);
	}
	
}
