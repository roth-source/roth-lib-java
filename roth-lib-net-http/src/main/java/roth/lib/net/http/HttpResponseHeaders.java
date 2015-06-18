package roth.lib.net.http;

import java.util.LinkedList;

import roth.lib.annotation.Property;
import roth.lib.net.http.header.Headers;
import roth.lib.net.http.header.model.AcceptPatch;
import roth.lib.net.http.header.model.AcceptRanges;
import roth.lib.net.http.header.model.AccessControlAllowOrigin;
import roth.lib.net.http.header.model.Age;
import roth.lib.net.http.header.model.Allow;
import roth.lib.net.http.header.model.CacheControl;
import roth.lib.net.http.header.model.Connection;
import roth.lib.net.http.header.model.ContentDisposition;
import roth.lib.net.http.header.model.ContentEncoding;
import roth.lib.net.http.header.model.ContentLanguage;
import roth.lib.net.http.header.model.ContentLength;
import roth.lib.net.http.header.model.ContentLocation;
import roth.lib.net.http.header.model.ContentMd5;
import roth.lib.net.http.header.model.ContentRange;
import roth.lib.net.http.header.model.ContentType;
import roth.lib.net.http.header.model.Date;
import roth.lib.net.http.header.model.ETag;
import roth.lib.net.http.header.model.Expires;
import roth.lib.net.http.header.model.LastModified;
import roth.lib.net.http.header.model.Location;
import roth.lib.net.http.header.model.Pragma;
import roth.lib.net.http.header.model.ProxyAuthenticate;
import roth.lib.net.http.header.model.Refresh;
import roth.lib.net.http.header.model.RetryAfter;
import roth.lib.net.http.header.model.Server;
import roth.lib.net.http.header.model.SetCookie;
import roth.lib.net.http.header.model.Trailer;
import roth.lib.net.http.header.model.TransferEncoding;
import roth.lib.net.http.header.model.Upgrade;
import roth.lib.net.http.header.model.Vary;
import roth.lib.net.http.header.model.Via;
import roth.lib.net.http.header.model.Warning;
import roth.lib.net.http.header.model.WwwAuthenticate;

public class HttpResponseHeaders extends Headers
{
	@Property(name = "Date")
	protected Date date;
	
	@Property(name = "Accept-Patch")
	protected AcceptPatch acceptPatch;
	
	@Property(name = "Accept-Ranges")
	protected AcceptRanges acceptRanges;
	
	@Property(name = "Access-Control-Allow-Origin")
	protected AccessControlAllowOrigin accessControlAllowOrigin;
	
	@Property(name = "Age")
	protected Age age;
	
	@Property(name = "Allow")
	protected Allow allow;
	
	@Property(name = "Cache-Control")
	protected CacheControl cacheControl;
	
	@Property(name = "Connection")
	protected Connection connection;
	
	@Property(name = "Content-Disposition")
	protected ContentDisposition contentDisposition;
	
	@Property(name = "Content-Encoding")
	protected ContentEncoding contentEncoding;
	
	@Property(name = "Content-Language")
	protected ContentLanguage contentLanguage;
	
	@Property(name = "Content-Length")
	protected ContentLength contentLength;
	
	@Property(name = "Content-Location")
	protected ContentLocation contentLocation;
	
	@Property(name = "Content-MD5")
	protected ContentMd5 contentMd5;
	
	@Property(name = "Content-Range")
	protected ContentRange contentRange;
	
	@Property(name = "Content-Type")
	protected ContentType contentType;
	
	@Property(name = "ETag")
	protected ETag eTag;
	
	@Property(name = "Expires")
	protected Expires expires;
	
	@Property(name = "Last-Modified")
	protected LastModified lastModified;
	
	@Property(name = "Location")
	protected Location location;
	
	@Property(name = "Pragma")
	protected Pragma pragma;
	
	@Property(name = "Proxy-Authenticate")
	protected ProxyAuthenticate proxyAuthenticate;
	
	@Property(name = "Refresh")
	protected Refresh refresh;
	
	@Property(name = "Retry-After")
	protected RetryAfter retryAfter;
	
	@Property(name = "Server")
	protected Server server;
	
	@Property(name = "Set-Cookie")
	protected LinkedList<SetCookie> setCookies = new LinkedList<SetCookie>();
	
	@Property(name = "Trailer")
	protected Trailer trailer;
	
	@Property(name = "Transfer-Encoding")
	protected TransferEncoding transferEncoding;
	
	@Property(name = "Upgrade")
	protected Upgrade upgrade;
	
	@Property(name = "Vary")
	protected Vary vary;
	
	@Property(name = "Via")
	protected Via via;
	
	@Property(name = "Warning")
	protected Warning warning;
	
	@Property(name = "WWW-Authenticate")
	protected WwwAuthenticate wwwAuthenticate;
	
	public HttpResponseHeaders()
	{
		
	}
	
	@Override
	public HttpResponseHeaders setHeader(String name, String value)
	{
		super.setHeader(name, value);
		return this;
	}
	
	public Date getDate()
	{
		return date != null ? date.parse() : null;
	}
	
	public AcceptPatch getAcceptPatch()
	{
		return acceptPatch != null ? acceptPatch.parse() : null;
	}
	
	public AcceptRanges getAcceptRanges()
	{
		return acceptRanges != null ? acceptRanges.parse() : null;
	}
	
	public AccessControlAllowOrigin getAccessControlAllowOrigin()
	{
		return accessControlAllowOrigin != null ? accessControlAllowOrigin.parse() : null;
	}
	
	public Age getAge()
	{
		return age != null ? age.parse() : null;
	}
	
	public Allow getAllow()
	{
		return allow != null ? allow.parse() : null;
	}
	
	public CacheControl getCacheControl()
	{
		return cacheControl != null ? cacheControl.parse() : null;
	}
	
	public Connection getConnection()
	{
		return connection != null ? connection.parse() : null;
	}
	
	public ContentDisposition getContentDisposition()
	{
		return contentDisposition != null ? contentDisposition.parse() : null;
	}
	
	public ContentEncoding getContentEncoding()
	{
		return contentEncoding != null ? contentEncoding.parse() : null;
	}
	
	public ContentLanguage getContentLanguage()
	{
		return contentLanguage != null ? contentLanguage.parse() : null;
	}
	
	public ContentLength getContentLength()
	{
		return contentLength != null ? contentLength.parse() : null;
	}
	
	public ContentLocation getContentLocation()
	{
		return contentLocation != null ? contentLocation.parse() : null;
	}
	
	public ContentMd5 getContentMd5()
	{
		return contentMd5 != null ? contentMd5.parse() : null;
	}
	
	public ContentRange getContentRange()
	{
		return contentRange != null ? contentRange.parse() : null;
	}
	
	public ContentType getContentType()
	{
		return contentType != null ? contentType.parse() : null;
	}
	
	public ETag geteTag()
	{
		return eTag != null ? eTag.parse() : null;
	}
	
	public Expires getExpires()
	{
		return expires != null ? expires.parse() : null;
	}
	
	public LastModified getLastModified()
	{
		return lastModified != null ? lastModified.parse() : null;
	}
	
	public Location getLocation()
	{
		return location != null ? location.parse() : null;
	}
	
	public Pragma getPragma()
	{
		return pragma != null ? pragma.parse() : null;
	}
	
	public ProxyAuthenticate getProxyAuthenticate()
	{
		return proxyAuthenticate != null ? proxyAuthenticate.parse() : null;
	}
	
	public Refresh getRefresh()
	{
		return refresh != null ? refresh.parse() : null;
	}
	
	public RetryAfter getRetryAfter()
	{
		return retryAfter != null ? retryAfter.parse() : null;
	}
	
	public Server getServer()
	{
		return server != null ? server.parse() : null;
	}
	
	public LinkedList<SetCookie> getSetCookies()
	{
		for(SetCookie setCookie : setCookies)
		{
			setCookie.parse();
		}
		return setCookies;
	}
	
	public Trailer getTrailer()
	{
		return trailer != null ? trailer.parse() : null;
	}
	
	public TransferEncoding getTransferEncoding()
	{
		return transferEncoding != null ? transferEncoding.parse() : null;
	}
	
	public Upgrade getUpgrade()
	{
		return upgrade != null ? upgrade.parse() : null;
	}
	
	public Vary getVary()
	{
		return vary != null ? vary.parse() : null;
	}
	
	public Via getVia()
	{
		return via != null ? via.parse() : null;
	}
	
	public Warning getWarning()
	{
		return warning != null ? warning.parse() : null;
	}
	
	public WwwAuthenticate getWwwAuthenticate()
	{
		return wwwAuthenticate != null ? wwwAuthenticate.parse() : null;
	}
	
	public HttpResponseHeaders setDate(Date date)
	{
		this.date = date;
		return this;
	}
	
	public HttpResponseHeaders setAcceptPatch(AcceptPatch acceptPatch)
	{
		this.acceptPatch = acceptPatch;
		return this;
	}
	
	public HttpResponseHeaders setAcceptRanges(AcceptRanges acceptRanges)
	{
		this.acceptRanges = acceptRanges;
		return this;
	}
	
	public HttpResponseHeaders setAccessControlAllowOrigin(AccessControlAllowOrigin accessControlAllowOrigin)
	{
		this.accessControlAllowOrigin = accessControlAllowOrigin;
		return this;
	}
	
	public HttpResponseHeaders setAge(Age age)
	{
		this.age = age;
		return this;
	}
	
	public HttpResponseHeaders setAllow(Allow allow)
	{
		this.allow = allow;
		return this;
	}
	
	public HttpResponseHeaders setCacheControl(CacheControl cacheControl)
	{
		this.cacheControl = cacheControl;
		return this;
	}
	
	public HttpResponseHeaders setConnection(Connection connection)
	{
		this.connection = connection;
		return this;
	}
	
	public HttpResponseHeaders setContentDisposition(ContentDisposition contentDisposition)
	{
		this.contentDisposition = contentDisposition;
		return this;
	}
	
	public HttpResponseHeaders setContentEncoding(ContentEncoding contentEncoding)
	{
		this.contentEncoding = contentEncoding;
		return this;
	}
	
	public HttpResponseHeaders setContentLanguage(ContentLanguage contentLanguage)
	{
		this.contentLanguage = contentLanguage;
		return this;
	}
	
	public HttpResponseHeaders setContentLength(ContentLength contentLength)
	{
		this.contentLength = contentLength;
		return this;
	}
	
	public HttpResponseHeaders setContentLocation(ContentLocation contentLocation)
	{
		this.contentLocation = contentLocation;
		return this;
	}
	
	public HttpResponseHeaders setContentMd5(ContentMd5 contentMd5)
	{
		this.contentMd5 = contentMd5;
		return this;
	}
	
	public HttpResponseHeaders setContentRange(ContentRange contentRange)
	{
		this.contentRange = contentRange;
		return this;
	}
	
	public HttpResponseHeaders setContentType(ContentType contentType)
	{
		this.contentType = contentType;
		return this;
	}
	
	public HttpResponseHeaders seteTag(ETag eTag)
	{
		this.eTag = eTag;
		return this;
	}
	
	public HttpResponseHeaders setExpires(Expires expires)
	{
		this.expires = expires;
		return this;
	}
	
	public HttpResponseHeaders setLastModified(LastModified lastModified)
	{
		this.lastModified = lastModified;
		return this;
	}
	
	public HttpResponseHeaders setLocation(Location location)
	{
		this.location = location;
		return this;
	}
	
	public HttpResponseHeaders setPragma(Pragma pragma)
	{
		this.pragma = pragma;
		return this;
	}
	
	public HttpResponseHeaders setProxyAuthenticate(ProxyAuthenticate proxyAuthenticate)
	{
		this.proxyAuthenticate = proxyAuthenticate;
		return this;
	}
	
	public HttpResponseHeaders setRefresh(Refresh refresh)
	{
		this.refresh = refresh;
		return this;
	}
	
	public HttpResponseHeaders setRetryAfter(RetryAfter retryAfter)
	{
		this.retryAfter = retryAfter;
		return this;
	}
	
	public HttpResponseHeaders setServer(Server server)
	{
		this.server = server;
		return this;
	}
	
	public HttpResponseHeaders setSetCookies(LinkedList<SetCookie> setCookies)
	{
		this.setCookies = setCookies;
		return this;
	}
	
	public HttpResponseHeaders setTrailer(Trailer trailer)
	{
		this.trailer = trailer;
		return this;
	}
	
	public HttpResponseHeaders setTransferEncoding(TransferEncoding transferEncoding)
	{
		this.transferEncoding = transferEncoding;
		return this;
	}
	
	public HttpResponseHeaders setUpgrade(Upgrade upgrade)
	{
		this.upgrade = upgrade;
		return this;
	}
	
	public HttpResponseHeaders setVary(Vary vary)
	{
		this.vary = vary;
		return this;
	}
	
	public HttpResponseHeaders setVia(Via via)
	{
		this.via = via;
		return this;
	}
	
	public HttpResponseHeaders setWarning(Warning warning)
	{
		this.warning = warning;
		return this;
	}
	
	public HttpResponseHeaders setWwwAuthenticate(WwwAuthenticate wwwAuthenticate)
	{
		this.wwwAuthenticate = wwwAuthenticate;
		return this;
	}
	
	@Override
	public HttpResponseHeaders setHeaders(HttpHeader... headers)
	{
		super.setHeaders(headers);
		return this;
	}
	
}
