package roth.lib.java.api.cloudflare.access;

import roth.lib.java.Generic;
import roth.lib.java.api.cloudflare.CloudFlareClient;
import roth.lib.java.api.cloudflare.CloudFlareRequest;
import roth.lib.java.api.cloudflare.CloudFlareResponse;

public class AccessClient extends CloudFlareClient
{
	protected static String GET_STATS 			= "stats";
	protected static String GET_ZONES 			= "zone_load_multi";
	protected static String GET_RECORDS 		= "rec_load_all";
	protected static String CHECK_ZONES 		= "zone_check";
	protected static String GET_IP				= "ip_lkup";
	protected static String GET_SETTINGS 		= "zone_settings";
	
	public AccessClient(String email, String token)
	{
		this(email, token, false);
	}
	
	public AccessClient(String email, String token, boolean debug)
	{
		super(email, token, debug);
	}
	
	public CloudFlareResponse<GetStatsResponse> getStats(GetStatsRequest request)
	{
		return post(url(), request.setAction(GET_STATS), new Generic<CloudFlareResponse<GetStatsResponse>>(){});
	}
	
	public CloudFlareResponse<GetZonesResponse> getZones()
	{
		return post(url(), new CloudFlareRequest().setAction(GET_ZONES), new Generic<CloudFlareResponse<GetZonesResponse>>(){});
	}
	
	public CloudFlareResponse<GetRecordsResponse> getRecords(GetRecordsRequest request)
	{
		return post(url(), request.setAction(GET_RECORDS), new Generic<CloudFlareResponse<GetRecordsResponse>>(){});
	}
	
	public CloudFlareResponse<CheckZonesResponse> checkZones(CheckZonesRequest request)
	{
		return post(url(), request.setAction(CHECK_ZONES), new Generic<CloudFlareResponse<CheckZonesResponse>>(){});
	}
	
	public CloudFlareResponse<GetIpResponse> getIp(GetIpRequest request)
	{
		return post(url(), request.setAction(GET_IP), new Generic<CloudFlareResponse<GetIpResponse>>(){});
	}
	
	public CloudFlareResponse<GetSettingsResponse> getSettings(GetSettingsRequest request)
	{
		return post(url(), request.setAction(GET_SETTINGS), new Generic<CloudFlareResponse<GetSettingsResponse>>(){});
	}
	
}
