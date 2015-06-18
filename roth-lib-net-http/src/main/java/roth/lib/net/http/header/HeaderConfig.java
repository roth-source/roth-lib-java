package roth.lib.net.http.header;

import roth.lib.map.Config;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;
import roth.lib.net.http.header.model.Accept;
import roth.lib.net.http.header.model.AcceptCharset;
import roth.lib.net.http.header.model.AcceptEncoding;
import roth.lib.net.http.header.model.AcceptLanguage;
import roth.lib.net.http.header.model.AcceptPatch;
import roth.lib.net.http.header.model.AcceptRanges;
import roth.lib.net.http.header.model.AccessControlAllowOrigin;
import roth.lib.net.http.header.model.Age;
import roth.lib.net.http.header.model.Allow;
import roth.lib.net.http.header.model.Authorization;
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
import roth.lib.net.http.header.model.Cookie;
import roth.lib.net.http.header.model.Date;
import roth.lib.net.http.header.model.ETag;
import roth.lib.net.http.header.model.Expect;
import roth.lib.net.http.header.model.Expires;
import roth.lib.net.http.header.model.From;
import roth.lib.net.http.header.model.Host;
import roth.lib.net.http.header.model.IfMatch;
import roth.lib.net.http.header.model.IfModifiedSince;
import roth.lib.net.http.header.model.IfNoneMatch;
import roth.lib.net.http.header.model.IfRange;
import roth.lib.net.http.header.model.IfUnmodifiedSince;
import roth.lib.net.http.header.model.LastModified;
import roth.lib.net.http.header.model.Location;
import roth.lib.net.http.header.model.MaxForwards;
import roth.lib.net.http.header.model.Origin;
import roth.lib.net.http.header.model.Pragma;
import roth.lib.net.http.header.model.ProxyAuthenticate;
import roth.lib.net.http.header.model.ProxyAuthorization;
import roth.lib.net.http.header.model.Range;
import roth.lib.net.http.header.model.Referer;
import roth.lib.net.http.header.model.Refresh;
import roth.lib.net.http.header.model.RetryAfter;
import roth.lib.net.http.header.model.Server;
import roth.lib.net.http.header.model.SetCookie;
import roth.lib.net.http.header.model.Te;
import roth.lib.net.http.header.model.Trailer;
import roth.lib.net.http.header.model.TransferEncoding;
import roth.lib.net.http.header.model.Upgrade;
import roth.lib.net.http.header.model.UserAgent;
import roth.lib.net.http.header.model.Vary;
import roth.lib.net.http.header.model.Via;
import roth.lib.net.http.header.model.Warning;
import roth.lib.net.http.header.model.WwwAuthenticate;

public class HeaderConfig extends Config
{
	protected static HeaderConfig instance;
	
	public HeaderConfig()
	{
		
	}
	
	public static HeaderConfig get()
	{
		if(instance == null)
		{
			instance = new HeaderConfig();
		}
		return instance;
	}
	
	public static void set(HeaderConfig newInstance)
	{
		instance = newInstance;
	}
	
	protected void initSerializers()
	{
		
	}
	
	protected void initDeserializers()
	{
		deserializerMap.put(Host.class, new Host());
		deserializerMap.put(Accept.class, new Accept());
		deserializerMap.put(AcceptCharset.class, new AcceptCharset());
		deserializerMap.put(AcceptEncoding.class, new AcceptEncoding());
		deserializerMap.put(AcceptLanguage.class, new AcceptLanguage());
		deserializerMap.put(AcceptPatch.class, new AcceptPatch());
		deserializerMap.put(AcceptRanges.class, new AcceptRanges());
		deserializerMap.put(AccessControlAllowOrigin.class, new AccessControlAllowOrigin());
		deserializerMap.put(Age.class, new Age());
		deserializerMap.put(Allow.class, new Allow());
		deserializerMap.put(Authorization.class, new Authorization());
		deserializerMap.put(CacheControl.class, new CacheControl());
		deserializerMap.put(Connection.class, new Connection());
		deserializerMap.put(ContentDisposition.class, new ContentDisposition());
		deserializerMap.put(ContentEncoding.class, new ContentEncoding());
		deserializerMap.put(ContentLanguage.class, new ContentLanguage());
		deserializerMap.put(ContentLength.class, new ContentLength());
		deserializerMap.put(ContentLocation.class, new ContentLocation());
		deserializerMap.put(ContentMd5.class, new ContentMd5());
		deserializerMap.put(ContentRange.class, new ContentRange());
		deserializerMap.put(ContentType.class, new ContentType());
		deserializerMap.put(Cookie.class, new Cookie());
		deserializerMap.put(Date.class, new Date());
		deserializerMap.put(ETag.class, new ETag());
		deserializerMap.put(Expect.class, new Expect());
		deserializerMap.put(Expires.class, new Expires());
		deserializerMap.put(From.class, new From());
		deserializerMap.put(IfMatch.class, new IfMatch());
		deserializerMap.put(IfModifiedSince.class, new IfModifiedSince());
		deserializerMap.put(IfNoneMatch.class, new IfNoneMatch());
		deserializerMap.put(IfRange.class, new IfRange());
		deserializerMap.put(IfUnmodifiedSince.class, new IfUnmodifiedSince());
		deserializerMap.put(LastModified.class, new LastModified());
		deserializerMap.put(Location.class, new Location());
		deserializerMap.put(MaxForwards.class, new MaxForwards());
		deserializerMap.put(Origin.class, new Origin());
		deserializerMap.put(Pragma.class, new Pragma());
		deserializerMap.put(ProxyAuthenticate.class, new ProxyAuthenticate());
		deserializerMap.put(ProxyAuthorization.class, new ProxyAuthorization());
		deserializerMap.put(Range.class, new Range());
		deserializerMap.put(Referer.class, new Referer());
		deserializerMap.put(Refresh.class, new Refresh());
		deserializerMap.put(RetryAfter.class, new RetryAfter());
		deserializerMap.put(Server.class, new Server());
		deserializerMap.put(SetCookie.class, new SetCookie());
		deserializerMap.put(Te.class, new Te());
		deserializerMap.put(Trailer.class, new Trailer());
		deserializerMap.put(TransferEncoding.class, new TransferEncoding());
		deserializerMap.put(Upgrade.class, new Upgrade());
		deserializerMap.put(Upgrade.class, new Upgrade());
		deserializerMap.put(UserAgent.class, new UserAgent());
		deserializerMap.put(Vary.class, new Vary());
		deserializerMap.put(Via.class, new Via());
		deserializerMap.put(Warning.class, new Warning());
		deserializerMap.put(WwwAuthenticate.class, new WwwAuthenticate());
	}
	
	@Override
	public HeaderConfig setPrettyPrinting(boolean prettyPrinting)
	{
		return this;
	}
	
	@Override
	public HeaderConfig setSerializeNulls(boolean serializeNulls)
	{
		return this;
	}
	
	@Override
	public <T> HeaderConfig setSerializer(Class<T> klass, Serializer<T> serializer)
	{
		serializerMap.put(klass, serializer);
		return this;
	}
	
	@Override
	public <T> HeaderConfig setDeserializer(Class<T> klass, Deserializer<T> deserializer)
	{
		deserializerMap.put(klass, deserializer);
		return this;
	}
	
}
