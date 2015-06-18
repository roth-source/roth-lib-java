package roth.lib.api.cloudflare.client;

import roth.lib.api.cloudflare.data.request.CheckZonesRequest;
import roth.lib.api.cloudflare.data.request.CloudFlareRequest;
import roth.lib.api.cloudflare.data.request.IpRequest;
import roth.lib.api.cloudflare.data.request.RecordsRequest;
import roth.lib.api.cloudflare.data.request.SettingsRequest;
import roth.lib.api.cloudflare.data.request.StatsRequest;
import roth.lib.api.cloudflare.data.response.CheckZonesResponse;
import roth.lib.api.cloudflare.data.response.CloudFlareResponse;
import roth.lib.api.cloudflare.data.response.IpResponse;
import roth.lib.api.cloudflare.data.response.RecordsResponse;
import roth.lib.api.cloudflare.data.response.SettingsResponse;
import roth.lib.api.cloudflare.data.response.StatsResponse;
import roth.lib.api.cloudflare.data.response.ZonesResponse;
import roth.lib.map.Generic;

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
	
	public CloudFlareResponse<StatsResponse> getStats(StatsRequest statsRequest)
	{
		return post(url(), statsRequest.setAction(GET_STATS), new Generic<CloudFlareResponse<StatsResponse>>(){});
	}
	
	public CloudFlareResponse<ZonesResponse> getZones()
	{
		return post(url(), new CloudFlareRequest().setAction(GET_ZONES), new Generic<CloudFlareResponse<ZonesResponse>>(){});
	}
	
	public CloudFlareResponse<RecordsResponse> getRecords(RecordsRequest recordsRequest)
	{
		return post(url(), recordsRequest.setAction(GET_RECORDS), new Generic<CloudFlareResponse<RecordsResponse>>(){});
	}
	
	public CloudFlareResponse<CheckZonesResponse> checkZones(CheckZonesRequest checkZonesRequest)
	{
		return post(url(), checkZonesRequest.setAction(CHECK_ZONES), new Generic<CloudFlareResponse<CheckZonesResponse>>(){});
	}
	
	public CloudFlareResponse<IpResponse> getIp(IpRequest ipRequest)
	{
		return post(url(), ipRequest.setAction(GET_IP), new Generic<CloudFlareResponse<IpResponse>>(){});
	}
	
	public CloudFlareResponse<SettingsResponse> getSettings(SettingsRequest settingsRequest)
	{
		return post(url(), settingsRequest.setAction(GET_SETTINGS), new Generic<CloudFlareResponse<SettingsResponse>>(){});
	}
	
}
