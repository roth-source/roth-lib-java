package roth.lib.api.cloudflare.test;

import roth.lib.api.cloudflare.data.request.CheckZonesRequest;
import roth.lib.api.cloudflare.data.request.IpRequest;
import roth.lib.api.cloudflare.data.request.RecordsRequest;
import roth.lib.api.cloudflare.data.request.SettingsRequest;
import roth.lib.api.cloudflare.data.request.StatsRequest;
import roth.lib.api.cloudflare.data.type.IntervalType;


public class AccessTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getStats();
		//getZones();
		//getRecords();
		//checkZones();
		//getIp();
		//getSettings();
	}
	
	public static void getStats()
	{
		clientFactory.getAccessClient().getStats(new StatsRequest(domain, IntervalType.PAST_7_DAYS));
	}
	
	public static void getZones()
	{
		clientFactory.getAccessClient().getZones();
	}
	
	public static void getRecords()
	{
		clientFactory.getAccessClient().getRecords(new RecordsRequest(domain));
	}
	
	public static void checkZones()
	{
		clientFactory.getAccessClient().checkZones(new CheckZonesRequest(domain));
	}
	
	public static void getIp()
	{
		clientFactory.getAccessClient().getIp(new IpRequest("173.245.57.134"));
	}
	
	public static void getSettings()
	{
		clientFactory.getAccessClient().getSettings(new SettingsRequest(domain));
	}
	
}
