package roth.lib.java.net.http;

import java.net.HttpCookie;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import roth.lib.java.Characters;
import roth.lib.java.net.http.type.AuthorizationType;
import roth.lib.java.net.http.type.EncodingType;
import roth.lib.java.net.http.util.HeaderUtil;
import roth.lib.java.type.MimeType;

public class HttpHeaders implements Characters
{
	public static final String HOST					= "Host";
	public static final String ACCEPT				= "Accept";
	public static final String ACCEPT_CHARSET		= "Accept-Charset";
	public static final String ACCEPT_ENCODING		= "Accept-Encoding";
	public static final String ACCEPT_LANGUAGE		= "Accept-Language";
	public static final String AUTHORIZATION		= "Authorization";
	public static final String CACHE_CONTROL		= "Cache-Control";
	public static final String CONNECTION			= "Connection";
	public static final String CONTENT_LENGTH		= "Content-Length";
	public static final String CONTENT_MD5			= "Content-MD5";
	public static final String CONTENT_TYPE			= "Content-Type";
	public static final String COOKIE				= "Cookie";
	public static final String DATE					= "Date";
	public static final String EXPECT				= "Expect";
	public static final String FROM					= "From";
	public static final String IF_MATCH				= "If-Match";
	public static final String IF_MODIFIED_SINCE	= "If-Modified-Since";
	public static final String IF_NONE_MATCH		= "If-None-Match";
	public static final String IF_RANGE				= "If-Range";
	public static final String IF_UNMODIFIED_SINCE	= "If-Unmodified-Since";
	public static final String MAX_FORWARDS			= "Max-Forwards";
	public static final String ORIGIN				= "Origin";
	public static final String PRAGMA				= "Pragma";
	public static final String PROXY_AUTHORIZATION	= "Proxy-Authorization";
	public static final String RANGE				= "Range";
	public static final String REFERER				= "Referer";
	public static final String TE					= "TE";
	public static final String USER_AGENT			= "User-Agent";
	public static final String UPGRADE				= "Upgrade";
	public static final String VIA					= "Via";
	public static final String WARNING				= "Warning";
	
	protected LinkedHashMap<String, List<String>> headersMap = new LinkedHashMap<String, List<String>>();
	
	protected HttpHeaders()
	{
		
	}
	
	public LinkedHashMap<String, List<String>> getHeadersMap()
	{
		return headersMap;
	}
	
	public HttpHeaders setHeader(String name, String value)
	{
		headersMap.put(name, Arrays.asList(value));
		return this;
	}
	
	public HttpHeaders setHeader(String name, Collection<String> values)
	{
		headersMap.put(name, new LinkedList<String>(values));
		return this;
	}
	
	public HttpHeaders setHeader(HttpHeader header)
	{
		if(header != null)
		{
			headersMap.put(header.getName(), Arrays.asList(header.getValue()));
		}
		return this;
	}
	
	public HttpHeaders setHeaders(HttpHeader... headers)
	{
		if(headers != null)
		{
			for(HttpHeader header : headers)
			{
				headersMap.put(header.getName(), Arrays.asList(header.getValue()));
			}
		}
		return this;
	}
	
	public static HttpHeaders set(HttpHeader... headers)
	{
		return new HttpHeaders().setHeaders(headers);
	}

	public HttpHeaders setHost(String host)
	{
		setHeader(HOST, host);
		return this;
	}
	
	public HttpHeaders setAccept(MimeType accept)
	{
		setAccept(accept.toString());
		return this;
	}
	
	public HttpHeaders setAccept(String accept)
	{
		setHeader(ACCEPT, accept);
		return this;
	}
	
	public HttpHeaders setAcceptCharset(String acceptCharset)
	{
		setHeader(ACCEPT_CHARSET, acceptCharset);
		return this;
	}
	
	public HttpHeaders setAcceptEncoding(EncodingType acceptEncoding)
	{
		setAcceptEncoding(acceptEncoding.toString());
		return this;
	}
	
	public HttpHeaders setAcceptEncoding(String acceptEncoding)
	{
		setHeader(ACCEPT_ENCODING, acceptEncoding);
		return this;
	}
	
	public HttpHeaders setAcceptLanguage(String acceptLanguage)
	{
		setHeader(ACCEPT_LANGUAGE, acceptLanguage);
		return this;
	}
	
	public HttpHeaders setAuthorization(AuthorizationType authoriztionType, String authorization)
	{
		setAuthorization(authoriztionType.toString() + SPACE + authorization);
		return this;
	}
	
	public HttpHeaders setAuthorization(String authorization)
	{
		setHeader(AUTHORIZATION, authorization);
		return this;
	}
	
	public HttpHeaders setCacheControl(String cacheControl)
	{
		setHeader(CACHE_CONTROL, cacheControl);
		return this;
	}
	
	public HttpHeaders setConnection(String connection)
	{
		setHeader(CONNECTION, connection);
		return this;
	}
	
	public HttpHeaders setContentLength(Integer contentLength)
	{
		setContentLength(String.valueOf(contentLength));
		return this;
	}
	
	public HttpHeaders setContentLength(Long contentLength)
	{
		setContentLength(String.valueOf(contentLength));
		return this;
	}
	
	public HttpHeaders setContentLength(String contentLength)
	{
		setHeader(CONTENT_LENGTH, contentLength);
		return this;
	}
	
	public HttpHeaders setContentMd5(String contentMd5)
	{
		setHeader(CONTENT_MD5, contentMd5);
		return this;
	}
	
	public HttpHeaders setContentType(MimeType contentType)
	{
		setContentType(contentType.toString());
		return this;
	}
	
	public HttpHeaders setContentType(String contentType)
	{
		setHeader(CONTENT_TYPE, contentType);
		return this;
	}
	
	public HttpHeaders setCookie(HttpCookie cookie)
	{
		setCookie(cookie.toString());
		return this;
	}
	
	public HttpHeaders setCookie(String cookie)
	{
		setHeader(COOKIE, cookie);
		return this;
	}
	
	public HttpHeaders setDate(Calendar date)
	{
		setDate(HeaderUtil.formatCalendar(date));
		return this;
	}
	
	public HttpHeaders setDate(Date date)
	{
		setDate(HeaderUtil.formatDate(date));
		return this;
	}
	
	public HttpHeaders setDate(String date)
	{
		setHeader(DATE, date);
		return this;
	}
	
	public HttpHeaders setExpect(String expect)
	{
		setHeader(EXPECT, expect);
		return this;
	}
	
	public HttpHeaders setFrom(String from)
	{
		setHeader(FROM, from);
		return this;
	}
	
	public HttpHeaders setIfMatch(String ifMatch)
	{
		setHeader(IF_MATCH, ifMatch);
		return this;
	}
	
	public HttpHeaders setIfModifiedSince(Calendar ifModifiedSince)
	{
		setIfModifiedSince(HeaderUtil.formatCalendar(ifModifiedSince));
		return this;
	}
	
	public HttpHeaders setIfModifiedSince(Date ifModifiedSince)
	{
		setIfModifiedSince(HeaderUtil.formatDate(ifModifiedSince));
		return this;
	}
	
	public HttpHeaders setIfModifiedSince(String ifModifiedSince)
	{
		setHeader(IF_MODIFIED_SINCE, ifModifiedSince);
		return this;
	}
	
	public HttpHeaders setIfNoneMatch(String ifNoneMatch)
	{
		setHeader(IF_NONE_MATCH, ifNoneMatch);
		return this;
	}
	
	public HttpHeaders setIfRange(String ifRange)
	{
		setHeader(IF_RANGE, ifRange);
		return this;
	}
	
	public HttpHeaders setIfUnmodifiedSince(Calendar ifUnmodifiedSince)
	{
		setIfUnmodifiedSince(HeaderUtil.formatCalendar(ifUnmodifiedSince));
		return this;
	}
	
	public HttpHeaders setIfUnmodifiedSince(Date ifUnmodifiedSince)
	{
		setIfUnmodifiedSince(HeaderUtil.formatDate(ifUnmodifiedSince));
		return this;
	}
	
	public HttpHeaders setIfUnmodifiedSince(String ifUnmodifiedSince)
	{
		setHeader(IF_UNMODIFIED_SINCE, ifUnmodifiedSince);
		return this;
	}
	
	public HttpHeaders setMaxForwards(Integer maxForwards)
	{
		setMaxForwards(String.valueOf(maxForwards));
		return this;
	}
	
	public HttpHeaders setMaxForwards(String maxForwards)
	{
		setHeader(MAX_FORWARDS, maxForwards);
		return this;
	}
	
	public HttpHeaders setOrigin(String origin)
	{
		setHeader(ORIGIN, origin);
		return this;
	}
	
	public HttpHeaders setPragma(String pragma)
	{
		setHeader(PRAGMA, pragma);
		return this;
	}
	
	public HttpHeaders setProxyAuthorization(AuthorizationType authoriztionType, String proxyAuthorization)
	{
		setProxyAuthorization(authoriztionType.toString() + SPACE + proxyAuthorization);
		return this;
	}
	
	public HttpHeaders setProxyAuthorization(String proxyAuthorization)
	{
		setHeader(PROXY_AUTHORIZATION, proxyAuthorization);
		return this;
	}
	
	public HttpHeaders setRange(String range)
	{
		setHeader(RANGE, range);
		return this;
	}
	
	public HttpHeaders setReferer(String referer)
	{
		setHeader(REFERER, referer);
		return this;
	}
	
	public HttpHeaders setTe(String te)
	{
		setHeader(TE, te);
		return this;
	}
	
	public HttpHeaders setUpgrade(String upgrade)
	{
		setHeader(UPGRADE, upgrade);
		return this;
	}
	
	public HttpHeaders setUserAgent(String userAgent)
	{
		setHeader(USER_AGENT, userAgent);
		return this;
	}
	
	public HttpHeaders setVia(String via)
	{
		setHeader(VIA, via);
		return this;
	}
	
	public HttpHeaders setWarning(String warning)
	{
		setHeader(WARNING, warning);
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(Entry<String, List<String>> headersEntry : headersMap.entrySet())
		{
			String name = headersEntry.getKey();
			for(String value : headersEntry.getValue())
			{
				builder.append(name);
				builder.append(COLON);
				builder.append(SPACE);
				builder.append(value);
				builder.append(NEW_LINE);
			}
		}
		return builder.toString();
	}
	
}
