package roth.lib.net.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUrl
{
	public static final int HTTP_PORT			= 80;
	public static final int HTTPS_PORT			= 443;
	public static final String PROTOCOL 		= "protocol";
	public static final String HOST 			= "host";
	public static final String PORT	 			= "port";
	public static final String PATH	 			= "path";
	public static final String PARAMETERS	 	= "parameters";
	public static final String HASH	 			= "hash";
	public static final String NAME	 			= "name";
	public static final String VALUE	 		= "value";
	public static final Pattern URL_PATTERN;	
	public static final Pattern PARAMETER_PATTERN;	
	
	static
	{
		URL_PATTERN = Pattern.compile
		(
			"^(?<" + PROTOCOL + ">[A-Za-z0-9]+)://" +
			"(?<" + HOST + ">[A-Za-z0-9.\\-]+)" +
			"(?::(?<" + PORT + ">[0-9]+))?" +
			"(?<" + PATH + ">/[A-Za-z0-9._~%/\\-]+)?" +
			"(?:\\?(?<" + PARAMETERS + ">[A-Za-z0-9._~%=&/\\-]+))?" + 
			"(?:#(?<" + HASH + ">[A-Za-z0-9._~%/\\-]+))?$"
		);
		PARAMETER_PATTERN = Pattern.compile
		(
			"(?<" + NAME + ">[A-Za-z0-9.-_~%]+)=" + 
			"(?<" + VALUE + ">[A-Za-z0-9.-_~%]*)(?:&|$)"
		);
	}
	
	protected HttpProtocol protocol;
	protected InetAddress inetAddress;
	protected int port;
	protected String path = "/";
	protected LinkedHashMap<String, String> parameterMap = new LinkedHashMap<String, String>();
	protected String hash;
	
	public HttpUrl()
	{
		
	}
	
	public HttpUrl(String url)
	{
		parse(url);
	}
	
	public HttpUrl(boolean secure)
	{
		setProtocol(secure ? HttpProtocol.HTTPS : HttpProtocol.HTTP);
	}
	
	protected void parse(String url)
	{
		Matcher matcher = URL_PATTERN.matcher(url);
		if(matcher.matches())
		{
			String protocol = matcher.group(PROTOCOL);
			if(protocol != null)
			{
				try
				{
					this.protocol = HttpProtocol.valueOf(protocol.toUpperCase());
				}
				catch(IllegalArgumentException e)
				{
					throw new HttpUrlException("invalid protocol - " + protocol, e);
				}
			}
			else
			{
				throw new HttpUrlException("invalid protocol");
			}
			String host = matcher.group(HOST);
			if(host != null)
			{
				try
				{
					inetAddress = InetAddress.getByName(host);
				}
				catch(UnknownHostException e)
				{
					throw new HttpUrlException("invalid host - " + host, e);
				}
			}
			else
			{
				throw new HttpUrlException("invalid host");
			}
			String port = matcher.group(PORT);
			if(port != null)
			{
				try
				{
					this.port = Integer.parseInt(port);
				}
				catch(NumberFormatException e)
				{
					throw new HttpUrlException("invalid port - " + port, e);
				}
			}
			else if(HttpProtocol.HTTP.equals(this.protocol))
			{
				this.port = HTTP_PORT;
			}
			else if(HttpProtocol.HTTPS.equals(this.protocol))
			{
				this.port = HTTPS_PORT;
			}
			else
			{
				throw new HttpUrlException("invalid port");
			}
			String path = matcher.group(PATH);
			this.path = path != null ? path : "/";
			String parameters = matcher.group(PARAMETERS);
			if(parameters != null)
			{
				Matcher parameterMatcher = PARAMETER_PATTERN.matcher(parameters);
				while(parameterMatcher.find())
				{
					String name = parameterMatcher.group(NAME);
					String value = parameterMatcher.group(VALUE);
					this.parameterMap.put(name, value);
				}
			}
			String hash = matcher.group(HASH);
			this.hash = hash;
		}
		else
		{
			throw new HttpUrlException("invalid url format - " + url);
		}
	}
	
	public HttpProtocol getProtocol()
	{
		return protocol;
	}
	
	public InetAddress getInetAddress()
	{
		return inetAddress;
	}
	
	public String getHost()
	{
		return inetAddress.getHostName();
	}
	
	public int getPort()
	{
		return port;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public LinkedHashMap<String, String> getParameterMap()
	{
		return parameterMap;
	}
	
	public String getHash()
	{
		return hash;
	}
	
	public HttpHeaders getHeaders()
	{
		return new HttpHeaders().setHeader("Host", getHost());
	}
	
	public HttpUrl setProtocol(HttpProtocol protocol)
	{
		this.protocol = protocol;
		if(HttpProtocol.HTTP.equals(protocol))
		{
			this.port = HTTP_PORT;
		}
		else if(HttpProtocol.HTTPS.equals(protocol))
		{
			this.port = HTTPS_PORT;
		}
		return this;
	}
	
	public HttpUrl setHost(String host)
	{
		try
		{
			this.inetAddress = InetAddress.getByName(host);
		}
		catch(UnknownHostException e)
		{
			throw new HttpUrlException(e);
		}
		return this;
	}
	
	public HttpUrl setInetAddress(InetAddress inetAddress)
	{
		this.inetAddress = inetAddress;
		return this;
	}
	
	public HttpUrl setPort(int port)
	{
		this.port = port;
		return this;
	}
	
	public HttpUrl setPath(String path)
	{
		this.path = path;
		return this;
	}
	
	public HttpUrl addPath(String path)
	{
		this.path += path;
		return this;
	}
	
	public HttpUrl setParameter(String name, String value)
	{
		this.parameterMap.put(name, value);
		return this;
	}
	
	public HttpUrl setParameters(Map<String, String> parameterMap)
	{
		this.parameterMap.putAll(parameterMap);
		return this;
	}
	
	public HttpUrl setHash(String hash)
	{
		this.hash = hash;
		return this;
	}
	
	public boolean isSecure()
	{
		return HttpProtocol.HTTPS.equals(protocol);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(protocol.toString().toLowerCase());
		builder.append("://");
		builder.append(inetAddress.getHostName());
		builder.append(port != HTTP_PORT && port != HTTPS_PORT ? ":" + port : "");
		builder.append(toResourcePath());
		return builder.toString();
	}
	
	public String toResourcePath()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(path);
		if(!parameterMap.isEmpty())
		{
			builder.append("?");
			String seperator = "";
			for(Entry<String, String> parameterEntry : parameterMap.entrySet())
			{
				builder.append(seperator);
				builder.append(parameterEntry.getKey());
				builder.append("=");
				builder.append(parameterEntry.getValue());
				if("".equals(seperator))
				{
					seperator = "&";
				}
			}
		}
		if(hash != null)
		{
			builder.append("#");
			builder.append(hash);
		}
		return builder.toString();
	}
	
}
