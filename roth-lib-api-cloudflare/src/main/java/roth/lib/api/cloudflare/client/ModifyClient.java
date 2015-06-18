package roth.lib.api.cloudflare.client;

import roth.lib.api.cloudflare.data.request.CacheLevelRequest;
import roth.lib.api.cloudflare.data.request.CacheRequest;
import roth.lib.api.cloudflare.data.request.DevModeRequest;
import roth.lib.api.cloudflare.data.request.FileRequest;
import roth.lib.api.cloudflare.data.request.IpActionRequest;
import roth.lib.api.cloudflare.data.request.Ipv6Request;
import roth.lib.api.cloudflare.data.request.MinificationRequest;
import roth.lib.api.cloudflare.data.request.Mirage2Request;
import roth.lib.api.cloudflare.data.request.RefreshRequest;
import roth.lib.api.cloudflare.data.request.RocketLoaderRequest;
import roth.lib.api.cloudflare.data.request.SecurityLevelRequest;
import roth.lib.api.cloudflare.data.response.CloudFlareResponse;
import roth.lib.api.cloudflare.data.response.FileResponse;
import roth.lib.api.cloudflare.data.response.IpActionResponse;
import roth.lib.api.cloudflare.data.response.ZoneResponse;
import roth.lib.map.Generic;

public class ModifyClient extends CloudFlareClient
{
	protected static String SET_SECURITY_LEVEL		= "sec_lvl";
	protected static String SET_CACHE_LEVEL	 		= "cache_lvl";
	protected static String SET_DEV_MODE		 	= "devmode";
	protected static String CLEAR_CACHE	 			= "fpurge_ts";
	protected static String CLEAR_FILE	 			= "zone_file_purge";
	protected static String REFRESH_ZONE		 	= "zone_grab";
	protected static String WHITELIST_IP			= "wl";
	protected static String BLACKLIST_IP			= "ban";
	protected static String UNLIST_IP				= "nul";
	protected static String SET_IPV6				= "ipv46";
	protected static String SET_ROCKET_LOADER		= "async";
	protected static String SET_MINIFICATION		= "minify";
	protected static String SET_MIRAGE2				= "mirage2";
	
	public ModifyClient(String email, String token)
	{
		this(email, token, false);
	}
	
	public ModifyClient(String email, String token, boolean debug)
	{
		super(email, token, debug);
	}
	
	public CloudFlareResponse<Void> setSecurityLevel(SecurityLevelRequest securityLevelRequest)
	{
		return post(url(), securityLevelRequest.setAction(SET_SECURITY_LEVEL), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setCacheLevel(CacheLevelRequest cacheLevelRequest)
	{
		return post(url(), cacheLevelRequest.setAction(SET_CACHE_LEVEL), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<ZoneResponse> setDevMode(DevModeRequest devModeRequest)
	{
		return post(url(), devModeRequest.setAction(SET_DEV_MODE), new Generic<CloudFlareResponse<ZoneResponse>>(){});
	}
	
	public CloudFlareResponse<ZoneResponse> clearCache(CacheRequest cacheRequest)
	{
		return post(url(), cacheRequest.setAction(CLEAR_CACHE), new Generic<CloudFlareResponse<ZoneResponse>>(){});
	}
	
	public CloudFlareResponse<FileResponse> clearFile(FileRequest fileRequest)
	{
		return post(url(), fileRequest.setAction(CLEAR_FILE), new Generic<CloudFlareResponse<FileResponse>>(){});
	}
	
	public CloudFlareResponse<Void> refreshZone(RefreshRequest refreshRequest)
	{
		return post(url(), refreshRequest.setAction(REFRESH_ZONE), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<IpActionResponse> whitelistIp(IpActionRequest ipActionRequest)
	{
		return post(url(), ipActionRequest.setAction(WHITELIST_IP), new Generic<CloudFlareResponse<IpActionResponse>>(){});
	}
	
	public CloudFlareResponse<IpActionResponse> blacklistIp(IpActionRequest ipActionRequest)
	{
		return post(url(), ipActionRequest.setAction(BLACKLIST_IP), new Generic<CloudFlareResponse<IpActionResponse>>(){});
	}
	
	public CloudFlareResponse<IpActionResponse> unlistIp(IpActionRequest ipActionRequest)
	{
		return post(url(), ipActionRequest.setAction(UNLIST_IP), new Generic<CloudFlareResponse<IpActionResponse>>(){});
	}
	
	public CloudFlareResponse<Void> setIpv6(Ipv6Request ipv6Request)
	{
		return post(url(), ipv6Request.setAction(SET_IPV6), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setRocketLoader(RocketLoaderRequest rocketLoaderRequest)
	{
		return post(url(), rocketLoaderRequest.setAction(SET_ROCKET_LOADER), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setMinification(MinificationRequest minificationRequest)
	{
		return post(url(), minificationRequest.setAction(SET_MINIFICATION), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setMirage2(Mirage2Request mirage2Request)
	{
		return post(url(), mirage2Request.setAction(SET_MIRAGE2), new Generic<CloudFlareResponse<Void>>(){});
	}
	
}
