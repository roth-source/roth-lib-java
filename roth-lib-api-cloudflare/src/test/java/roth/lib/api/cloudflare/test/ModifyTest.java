package roth.lib.api.cloudflare.test;

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
import roth.lib.api.cloudflare.data.type.CacheLevelType;
import roth.lib.api.cloudflare.data.type.MinificationType;
import roth.lib.api.cloudflare.data.type.RocketLoaderType;
import roth.lib.api.cloudflare.data.type.SecurityLevelType;


public class ModifyTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//setSecurityLevel();
		//setCacheLevel();
		//setDevMode();
		//clearCache();
		//clearFile();
		//refreshZone();
		//whitelistIp();
		//blacklistIp();
		//unlistIp();
		//setIpv6();
		//setRocketLoader();
		//setMinification();
		//setMirage2();
	}
	
	public static void setSecurityLevel()
	{
		clientFactory.getModifyClient().setSecurityLevel(new SecurityLevelRequest(domain, SecurityLevelType.MED));
	}
	
	public static void setCacheLevel()
	{
		clientFactory.getModifyClient().setCacheLevel(new CacheLevelRequest(domain, CacheLevelType.AGG));
	}
	
	public static void setDevMode()
	{
		clientFactory.getModifyClient().setDevMode(new DevModeRequest(domain, false));
	}
	
	public static void clearCache()
	{
		clientFactory.getModifyClient().clearCache(new CacheRequest(domain));
	}
	
	public static void clearFile()
	{
		clientFactory.getModifyClient().clearFile(new FileRequest(domain, "http://www.roth.am/test.jpg"));
	}
	
	public static void refreshZone()
	{
		clientFactory.getModifyClient().refreshZone(new RefreshRequest(7262791));
	}
	
	public static void whitelistIp()
	{
		clientFactory.getModifyClient().whitelistIp(new IpActionRequest("127.0.0.1"));
	}
	
	public static void blacklistIp()
	{
		clientFactory.getModifyClient().blacklistIp(new IpActionRequest("127.0.0.1"));
	}
	
	public static void unlistIp()
	{
		clientFactory.getModifyClient().unlistIp(new IpActionRequest("127.0.0.1"));
	}
	
	public static void setIpv6()
	{
		clientFactory.getModifyClient().setIpv6(new Ipv6Request(domain, false));
	}
	
	public static void setRocketLoader()
	{
		clientFactory.getModifyClient().setRocketLoader(new RocketLoaderRequest(domain, RocketLoaderType.OFF));
	}
	
	public static void setMinification()
	{
		clientFactory.getModifyClient().setMinification(new MinificationRequest(domain, MinificationType.OFF));
	}
	
	public static void setMirage2()
	{
		clientFactory.getModifyClient().setMirage2(new Mirage2Request(domain, false));
	}
	
}
