package roth.lib.net.http;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

import roth.lib.Characters;
import roth.lib.map.Deserializer;
import roth.lib.map.Mapper;
import roth.lib.net.http.header.HeaderMapper;

public class HttpConnection implements Characters
{
	protected HttpUrl url;
	protected HttpURLConnection connection;
	protected boolean debug;
	
	public HttpConnection(HttpUrl url, HttpURLConnection connection, boolean debug)
	{
		this.url = url;
		this.connection = connection;
		this.debug = debug;
	}
	
	public HttpUrl getUrl()
	{
		return url;
	}
	
	public HttpURLConnection getConnection()
	{
		return connection;
	}
	
	public OutputStream getOutputStream() throws IOException
	{
		return connection.getOutputStream();
	}
	
	public Writer getWriter() throws IOException
	{
		return new OutputStreamWriter(getOutputStream(), Mapper.UTF_8);
	}
	
	public BufferedWriter getBufferedWriter() throws IOException
	{
		return new BufferedWriter(getWriter());
	}
	
	public InputStream getInputStream() throws IOException
	{
		return connection.getInputStream();
	}
	
	public InputStream getErrorStream() throws IOException
	{
		return connection.getErrorStream();
	}
	
	public InputStream getAnyStream()
	{
		InputStream input = null;
		try
		{
			input = getInputStream();
		}
		catch(Exception e)
		{
			
		}
		if(input == null)
		{
			try
			{
				input = getErrorStream();
			}
			catch(Exception e)
			{
				
			}
		}
		return input;
	}
	
	public GZIPInputStream getGzipInputStream() throws IOException
	{
		return new GZIPInputStream(getInputStream());
	}
	
	public Reader getReader() throws IOException
	{
		return new InputStreamReader(getInputStream(), Mapper.UTF_8);
	}
	
	public Reader getGzipReader() throws IOException
	{
		return new InputStreamReader(getGzipInputStream(), Mapper.UTF_8);
	}
	
	public <T> HttpResponse<T> connect(HttpRequest<?> request, Deserializer<T> deserializer) throws IOException
	{
		connection.setDoInput(true);
		connection.setRequestMethod(request.getMethod().toString());
		HeaderMapper.get().request(request.getHeaders(), connection);
		if(request.hasSerializer())
		{
			connection.setDoOutput(true);
			request.getSerializer().serialize(getOutputStream());
		}
		connection.connect();
		HttpResponse<T> response = new HttpResponse<T>();
		response.parseStatus(connection);
		HttpResponseHeaders headers = HeaderMapper.get().response(connection, HttpResponseHeaders.class);
		response.setHeaders(headers);
		if(response.isSuccess())
		{
			if(deserializer != null || debug)
			{
				if(debug)
				{
					ByteArrayOutputStream output = readAll(getInputStream());
					response.setBody(new String(output.toByteArray(), Mapper.UTF_8));
					if(deserializer != null)
					{
						response.setEntity(deserializer.deserialize(new ByteArrayInputStream(output.toByteArray())));
					}
				}
				else
				{
					response.setEntity(deserializer.deserialize(getInputStream()));
				}
			}
			else
			{
				response.setInput(getAnyStream());
			}
		}
		else
		{
			response.setInput(getAnyStream());
		}
		return response;
	}
	
	public HttpResponse<?> connect(HttpRequest<?> request) throws IOException
	{
		connection.setDoInput(true);
		connection.setRequestMethod(request.getMethod().toString());
		HeaderMapper.get().request(request.getHeaders(), connection);
		if(request.hasSerializer())
		{
			connection.setDoOutput(true);
			request.getSerializer().serialize(getOutputStream());
		}
		connection.connect();
		HttpResponse<?> response = new HttpResponse<>();
		response.parseStatus(connection);
		HttpResponseHeaders headers = HeaderMapper.get().response(connection, HttpResponseHeaders.class);
		response.setHeaders(headers);
		response.setInput(getAnyStream());
		return response;
	}
	
	protected String readLine(InputStream input) throws IOException
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int b;
		read: while((b = input.read()) > -1)
		{
			switch(b)
			{
				case CARRIAGE_RETURN:
				{
					break;
				}
				case NEW_LINE:
				{
					break read;
				}
				default:
				{
					output.write(b);
					break;
				}
			}
		}
		return new String(output.toByteArray(), Mapper.UTF_8);
	}
	
	protected ByteArrayOutputStream readAll(InputStream input) throws IOException
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int n = 0;
		while((n = input.read(buffer)) != -1)
		{
			output.write(buffer, 0, n);
		}
		output.flush();
		return output;
	}
	
}
