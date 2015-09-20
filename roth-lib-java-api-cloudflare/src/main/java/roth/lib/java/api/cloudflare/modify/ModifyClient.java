package roth.lib.java.api.cloudflare.modify;

import roth.lib.java.Generic;
import roth.lib.java.api.cloudflare.CloudFlareClient;
import roth.lib.java.api.cloudflare.CloudFlareResponse;

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
	
	public CloudFlareResponse<Void> setSecurityLevel(SetSecurityLevelRequest request)
	{
		return post(url(), request.setAction(SET_SECURITY_LEVEL), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setCacheLevel(SetCacheLevelRequest request)
	{
		return post(url(), request.setAction(SET_CACHE_LEVEL), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<SetDevModeResponse> setDevMode(SetDevModeRequest request)
	{
		return post(url(), request.setAction(SET_DEV_MODE), new Generic<CloudFlareResponse<SetDevModeResponse>>(){});
	}
	
	public CloudFlareResponse<ClearCacheResponse> clearCache(ClearCacheRequest request)
	{
		return post(url(), request.setAction(CLEAR_CACHE), new Generic<CloudFlareResponse<ClearCacheResponse>>(){});
	}
	
	public CloudFlareResponse<ClearFileResponse> clearFile(ClearFileRequest request)
	{
		return post(url(), request.setAction(CLEAR_FILE), new Generic<CloudFlareResponse<ClearFileResponse>>(){});
	}
	
	public CloudFlareResponse<Void> refreshZone(RefreshZoneRequest request)
	{
		return post(url(), request.setAction(REFRESH_ZONE), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<WhitelistIpResponse> whitelistIp(WhitelistIpRequest request)
	{
		return post(url(), request.setAction(WHITELIST_IP), new Generic<CloudFlareResponse<WhitelistIpResponse>>(){});
	}
	
	public CloudFlareResponse<BlacklistIpResponse> blacklistIp(BlacklistIpRequest request)
	{
		return post(url(), request.setAction(BLACKLIST_IP), new Generic<CloudFlareResponse<BlacklistIpResponse>>(){});
	}
	
	public CloudFlareResponse<UnlistIpResponse> unlistIp(UnlistIpRequest request)
	{
		return post(url(), request.setAction(UNLIST_IP), new Generic<CloudFlareResponse<UnlistIpResponse>>(){});
	}
	
	public CloudFlareResponse<Void> setIpv6(SetIpv6Request request)
	{
		return post(url(), request.setAction(SET_IPV6), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setRocketLoader(SetRocketLoaderRequest request)
	{
		return post(url(), request.setAction(SET_ROCKET_LOADER), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setMinification(SetMinificationRequest request)
	{
		return post(url(), request.setAction(SET_MINIFICATION), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<Void> setMirage2(SetMirage2Request request)
	{
		return post(url(), request.setAction(SET_MIRAGE2), new Generic<CloudFlareResponse<Void>>(){});
	}
	
}
