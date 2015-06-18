package roth.lib.net.http;

import roth.lib.annotation.Property;
import roth.lib.net.http.header.Headers;
import roth.lib.net.http.header.model.Accept;
import roth.lib.net.http.header.model.AcceptCharset;
import roth.lib.net.http.header.model.AcceptEncoding;
import roth.lib.net.http.header.model.AcceptLanguage;
import roth.lib.net.http.header.model.Authorization;
import roth.lib.net.http.header.model.CacheControl;
import roth.lib.net.http.header.model.Connection;
import roth.lib.net.http.header.model.ContentLength;
import roth.lib.net.http.header.model.ContentMd5;
import roth.lib.net.http.header.model.ContentType;
import roth.lib.net.http.header.model.Cookie;
import roth.lib.net.http.header.model.Date;
import roth.lib.net.http.header.model.Expect;
import roth.lib.net.http.header.model.From;
import roth.lib.net.http.header.model.Host;
import roth.lib.net.http.header.model.IfMatch;
import roth.lib.net.http.header.model.IfModifiedSince;
import roth.lib.net.http.header.model.IfNoneMatch;
import roth.lib.net.http.header.model.IfRange;
import roth.lib.net.http.header.model.IfUnmodifiedSince;
import roth.lib.net.http.header.model.MaxForwards;
import roth.lib.net.http.header.model.Origin;
import roth.lib.net.http.header.model.Pragma;
import roth.lib.net.http.header.model.ProxyAuthorization;
import roth.lib.net.http.header.model.Range;
import roth.lib.net.http.header.model.Referer;
import roth.lib.net.http.header.model.Te;
import roth.lib.net.http.header.model.Upgrade;
import roth.lib.net.http.header.model.UserAgent;
import roth.lib.net.http.header.model.Via;
import roth.lib.net.http.header.model.Warning;

public class HttpRequestHeaders extends Headers
{
	@Property(name = "Host")
	protected Host host;
	
	@Property(name = "Accept")
	protected Accept accept;
	
	@Property(name = "Accept-Charset")
	protected AcceptCharset acceptCharset;
	
	@Property(name = "Accept-Encoding")
	protected AcceptEncoding acceptEncoding;
	
	@Property(name = "Accept-Language")
	protected AcceptLanguage acceptLanguage;
	
	@Property(name = "Authorization")
	protected Authorization authorization;
	
	@Property(name = "Cache-Control")
	protected CacheControl cacheControl;
	
	@Property(name = "Connection")
	protected Connection connection;
	
	@Property(name = "Content-Length")
	protected ContentLength contentLength;
	
	@Property(name = "Content-MD5")
	protected ContentMd5 contentMd5;
	
	@Property(name = "Content-Type")
	protected ContentType contentType;
	
	@Property(name = "Cookie")
	protected Cookie cookie;
	
	@Property(name = "Date")
	protected Date date;
	
	@Property(name = "Expect")
	protected Expect expect;
	
	@Property(name = "From")
	protected From from;
	
	@Property(name = "If-Match")
	protected IfMatch ifMatch;
	
	@Property(name = "If-Modified-Since")
	protected IfModifiedSince ifModifiedSince;
	
	@Property(name = "If-None-Match")
	protected IfNoneMatch ifNoneMatch;
	
	@Property(name = "If-Range")
	protected IfRange ifRange;
	
	@Property(name = "If-Unmodified-Since")
	protected IfUnmodifiedSince ifUnmodifiedSince;
	
	@Property(name = "Max-Forwards")
	protected MaxForwards maxForwards;
	
	@Property(name = "Origin")
	protected Origin origin;
	
	@Property(name = "Pragma")
	protected Pragma pragma;
	
	@Property(name = "Proxy-Authorization")
	protected ProxyAuthorization proxyAuthorization;
	
	@Property(name = "Range")
	protected Range range;
	
	@Property(name = "Referer")
	protected Referer referer;
	
	@Property(name = "TE")
	protected Te te;
	
	@Property(name = "Upgrade")
	protected Upgrade upgrade;
	
	@Property(name = "User-Agent")
	protected UserAgent userAgent;
	
	@Property(name = "Via")
	protected Via via;
	
	@Property(name = "Warning")
	protected Warning warning;
	
	public HttpRequestHeaders(HttpUrl url)
	{
		this.host = new Host(url.getHost());
	}
	
	@Override
	public HttpRequestHeaders setHeader(String name, String value)
	{
		super.setHeader(name, value);
		return this;
	}
	
	public Host getHost()
	{
		return host != null ? host.parse() : null;
	}
	
	public Accept getAccept()
	{
		return accept != null ? accept.parse() : null;
	}
	
	public AcceptCharset getAcceptCharset()
	{
		return acceptCharset != null ? acceptCharset.parse() : null;
	}
	
	public AcceptEncoding getAcceptEncoding()
	{
		return acceptEncoding != null ? acceptEncoding.parse() : null;
	}
	
	public AcceptLanguage getAcceptLanguage()
	{
		return acceptLanguage != null ? acceptLanguage.parse() : null;
	}
	
	public Authorization getAuthorization()
	{
		return authorization != null ? authorization.parse() : null;
	}
	
	public CacheControl getCacheControl()
	{
		return cacheControl != null ? cacheControl.parse() : null;
	}
	
	public Connection getConnection()
	{
		return connection != null ? connection.parse() : null;
	}
	
	public ContentLength getContentLength()
	{
		return contentLength != null ? contentLength.parse() : null;
	}
	
	public ContentMd5 getContentMd5()
	{
		return contentMd5 != null ? contentMd5.parse() : null;
	}
	
	public ContentType getContentType()
	{
		return contentType != null ? contentType.parse() : null;
	}
	
	public Cookie getCookie()
	{
		return cookie != null ? cookie.parse() : null;
	}
	
	public Date getDate()
	{
		return date != null ? date.parse() : null;
	}
	
	public Expect getExpect()
	{
		return expect != null ? expect.parse() : null;
	}
	
	public From getFrom()
	{
		return from != null ? from.parse() : null;
	}
	
	public IfMatch getIfMatch()
	{
		return ifMatch != null ? ifMatch.parse() : null;
	}
	
	public IfModifiedSince getIfModifiedSince()
	{
		return ifModifiedSince != null ? ifModifiedSince.parse() : null;
	}
	
	public IfNoneMatch getIfNoneMatch()
	{
		return ifNoneMatch != null ? ifNoneMatch.parse() : null;
	}
	
	public IfRange getIfRange()
	{
		return ifRange != null ? ifRange.parse() : null;
	}
	
	public IfUnmodifiedSince getIfUnmodifiedSince()
	{
		return ifUnmodifiedSince != null ? ifUnmodifiedSince.parse() : null;
	}
	
	public MaxForwards getMaxForwards()
	{
		return maxForwards != null ? maxForwards.parse() : null;
	}
	
	public Origin getOrigin()
	{
		return origin != null ? origin.parse() : null;
	}
	
	public Pragma getPragma()
	{
		return pragma != null ? pragma.parse() : null;
	}
	
	public ProxyAuthorization getProxyAuthorization()
	{
		return proxyAuthorization != null ? proxyAuthorization.parse() : null;
	}
	
	public Range getRange()
	{
		return range != null ? range.parse() : null;
	}
	
	public Referer getReferer()
	{
		return referer != null ? referer.parse() : null;
	}
	
	public Te getTe()
	{
		return te != null ? te.parse() : null;
	}
	
	public Upgrade getUpgrade()
	{
		return upgrade != null ? upgrade.parse() : null;
	}
	
	public UserAgent getUserAgent()
	{
		return userAgent != null ? userAgent.parse() : null;
	}
	
	public Via getVia()
	{
		return via != null ? via.parse() : null;
	}
	
	public Warning getWarning()
	{
		return warning != null ? warning.parse() : null;
	}
	
	public HttpRequestHeaders setHost(Host host)
	{
		this.host = host;
		return this;
	}
	
	public HttpRequestHeaders setAccept(Accept accept)
	{
		this.accept = accept;
		return this;
	}
	
	public HttpRequestHeaders setAcceptCharset(AcceptCharset acceptCharset)
	{
		this.acceptCharset = acceptCharset;
		return this;
	}
	
	public HttpRequestHeaders setAcceptEncoding(AcceptEncoding acceptEncoding)
	{
		this.acceptEncoding = acceptEncoding;
		return this;
	}
	
	public HttpRequestHeaders setAcceptLanguage(AcceptLanguage acceptLanguage)
	{
		this.acceptLanguage = acceptLanguage;
		return this;
	}
	
	public HttpRequestHeaders setAuthorization(Authorization authorization)
	{
		this.authorization = authorization;
		return this;
	}
	
	public HttpRequestHeaders setCacheControl(CacheControl cacheControl)
	{
		this.cacheControl = cacheControl;
		return this;
	}
	
	public HttpRequestHeaders setConnection(Connection connection)
	{
		this.connection = connection;
		return this;
	}
	
	public HttpRequestHeaders setContentLength(ContentLength contentLength)
	{
		this.contentLength = contentLength;
		return this;
	}
	
	public HttpRequestHeaders setContentMd5(ContentMd5 contentMd5)
	{
		this.contentMd5 = contentMd5;
		return this;
	}
	
	public HttpRequestHeaders setContentType(ContentType contentType)
	{
		this.contentType = contentType;
		return this;
	}
	
	public HttpRequestHeaders setCookie(Cookie cookie)
	{
		this.cookie = cookie;
		return this;
	}
	
	public HttpRequestHeaders setDate(Date date)
	{
		this.date = date;
		return this;
	}
	
	public HttpRequestHeaders setExpect(Expect expect)
	{
		this.expect = expect;
		return this;
	}
	
	public HttpRequestHeaders setFrom(From from)
	{
		this.from = from;
		return this;
	}
	
	public HttpRequestHeaders setIfMatch(IfMatch ifMatch)
	{
		this.ifMatch = ifMatch;
		return this;
	}
	
	public HttpRequestHeaders setIfModifiedSince(IfModifiedSince ifModifiedSince)
	{
		this.ifModifiedSince = ifModifiedSince;
		return this;
	}
	
	public HttpRequestHeaders setIfNoneMatch(IfNoneMatch ifNoneMatch)
	{
		this.ifNoneMatch = ifNoneMatch;
		return this;
	}
	
	public HttpRequestHeaders setIfRange(IfRange ifRange)
	{
		this.ifRange = ifRange;
		return this;
	}
	
	public HttpRequestHeaders setIfUnmodifiedSince(IfUnmodifiedSince ifUnmodifiedSince)
	{
		this.ifUnmodifiedSince = ifUnmodifiedSince;
		return this;
	}
	
	public HttpRequestHeaders setMaxForwards(MaxForwards maxForwards)
	{
		this.maxForwards = maxForwards;
		return this;
	}
	
	public HttpRequestHeaders setOrigin(Origin origin)
	{
		this.origin = origin;
		return this;
	}
	
	public HttpRequestHeaders setPragma(Pragma pragma)
	{
		this.pragma = pragma;
		return this;
	}
	
	public HttpRequestHeaders setProxyAuthorization(ProxyAuthorization proxyAuthorization)
	{
		this.proxyAuthorization = proxyAuthorization;
		return this;
	}
	
	public HttpRequestHeaders setRange(Range range)
	{
		this.range = range;
		return this;
	}
	
	public HttpRequestHeaders setReferer(Referer referer)
	{
		this.referer = referer;
		return this;
	}
	
	public HttpRequestHeaders setTe(Te te)
	{
		this.te = te;
		return this;
	}
	
	public HttpRequestHeaders setUpgrade(Upgrade upgrade)
	{
		this.upgrade = upgrade;
		return this;
	}
	
	public HttpRequestHeaders setUserAgent(UserAgent userAgent)
	{
		this.userAgent = userAgent;
		return this;
	}
	
	public HttpRequestHeaders setVia(Via via)
	{
		this.via = via;
		return this;
	}
	
	public HttpRequestHeaders setWarning(Warning warning)
	{
		this.warning = warning;
		return this;
	}
	
	@Override
	public HttpRequestHeaders setHeaders(HttpHeader... headers)
	{
		super.setHeaders(headers);
		return this;
	}
	
}
