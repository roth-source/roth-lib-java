package roth.lib.net.http.header.model;

import java.net.HttpCookie;
import java.util.Calendar;
import java.util.List;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class SetCookie extends Header<SetCookie> implements Characters
{
	protected String cookieName;
	protected String cookieValue;
	protected Calendar expires;
	protected String domain;
	protected String path;
	protected boolean secure;
	protected boolean httpOnly;
	
	public SetCookie()
	{
		
	}
	
	public SetCookie(String value)
	{
		this.value = value;
	}
	
	public SetCookie(String cookieName, String cookieValue)
	{
		this.cookieName = cookieName;
		this.cookieValue = cookieValue;
	}
	
	public String getCookieName()
	{
		return cookieName;
	}
	
	public String getCookieValue()
	{
		return cookieValue;
	}
	
	public Calendar getExpires()
	{
		return expires;
	}
	
	public String getDomain()
	{
		return domain;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public boolean isSecure()
	{
		return secure;
	}
	
	public boolean isHttpOnly()
	{
		return httpOnly;
	}
	
	public SetCookie setCookieName(String cookieName)
	{
		this.cookieName = cookieName;
		return this;
	}
	
	public SetCookie setCookieValue(String cookieValue)
	{
		this.cookieValue = cookieValue;
		return this;
	}
	
	public SetCookie setExpires(Calendar expires)
	{
		this.expires = expires;
		return this;
	}
	
	public SetCookie setDomain(String domain)
	{
		this.domain = domain;
		return this;
	}
	
	public SetCookie setPath(String path)
	{
		this.path = path;
		return this;
	}
	
	public SetCookie setSecure(boolean secure)
	{
		this.secure = secure;
		return this;
	}
	
	public SetCookie setHttpOnly(boolean httpOnly)
	{
		this.httpOnly = httpOnly;
		return this;
	}
	
	@Override
	public SetCookie deserialize(String value)
	{
		return new SetCookie(value);
	}
	
	@Override
	public SetCookie parse()
	{
		List<HttpCookie> cookies = HttpCookie.parse(value);
		if(cookies != null && !cookies.isEmpty())
		{
			HttpCookie cookie = cookies.get(0);
			cookieName = cookie.getName();
			cookieValue = cookie.getValue();
			domain = cookie.getDomain();
			path = cookie.getPath();
			secure = cookie.getSecure();
			httpOnly = cookie.isHttpOnly();
		}
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(cookieName != null && cookieValue != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(cookieName);
			builder.append(EQUAL);
			builder.append(cookieValue);
			if(expires != null)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("Expires");
				builder.append(EQUAL);
				builder.append(HeaderUtil.formatCalendar(expires));
			}
			if(domain != null)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("Domain");
				builder.append(EQUAL);
				builder.append(domain);
			}
			if(path != null)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("Path");
				builder.append(EQUAL);
				builder.append(path);
			}
			if(secure)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("Secure");
			}
			if(httpOnly)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("HttpOnly");
			}
			return builder.toString();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		SetCookie setCookie = new SetCookie().deserialize("login_value=aptexx; expires=Sun, 13-Mar-2016 18:28:16 GMT; Max-Age=31536000; path=/; domain=opendrive.com");
		setCookie.parse();
		System.out.println(setCookie.getCookieValue());
	}
	
}
