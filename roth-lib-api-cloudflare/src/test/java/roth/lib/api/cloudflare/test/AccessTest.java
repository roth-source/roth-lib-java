package roth.lib.api.cloudflare.test;

import roth.lib.api.cloudflare.access.CheckZonesRequest;
import roth.lib.api.cloudflare.access.GetIpRequest;
import roth.lib.api.cloudflare.access.GetRecordsRequest;
import roth.lib.api.cloudflare.access.GetSettingsRequest;
import roth.lib.api.cloudflare.access.GetStatsRequest;
import roth.lib.api.cloudflare.type.IntervalType;


public class AccessTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		getStats();
		//getZones();
		//getRecords();
		//checkZones();
		//getIp();
		//getSettings();
	}
	
	public static void getStats()
	{
		clientFactory.getAccessClient().getStats(new GetStatsRequest(domain, IntervalType.PAST_7_DAYS));
	}
	
	public static void getZones()
	{
		clientFactory.getAccessClient().getZones();
	}
	
	public static void getRecords()
	{
		clientFactory.getAccessClient().getRecords(new GetRecordsRequest(domain));
	}
	
	public static void checkZones()
	{
		clientFactory.getAccessClient().checkZones(new CheckZonesRequest(domain));
	}
	
	public static void getIp()
	{
		clientFactory.getAccessClient().getIp(new GetIpRequest("173.245.57.134"));
	}
	
	public static void getSettings()
	{
		clientFactory.getAccessClient().getSettings(new GetSettingsRequest(domain));
	}
	
}
