package roth.lib.java.api.cloudflare.test;

import roth.lib.java.api.cloudflare.modify.BlacklistIpRequest;
import roth.lib.java.api.cloudflare.modify.ClearCacheRequest;
import roth.lib.java.api.cloudflare.modify.ClearFileRequest;
import roth.lib.java.api.cloudflare.modify.RefreshZoneRequest;
import roth.lib.java.api.cloudflare.modify.SetCacheLevelRequest;
import roth.lib.java.api.cloudflare.modify.SetDevModeRequest;
import roth.lib.java.api.cloudflare.modify.SetIpv6Request;
import roth.lib.java.api.cloudflare.modify.SetMinificationRequest;
import roth.lib.java.api.cloudflare.modify.SetMirage2Request;
import roth.lib.java.api.cloudflare.modify.SetRocketLoaderRequest;
import roth.lib.java.api.cloudflare.modify.SetSecurityLevelRequest;
import roth.lib.java.api.cloudflare.modify.UnlistIpRequest;
import roth.lib.java.api.cloudflare.modify.WhitelistIpRequest;
import roth.lib.java.api.cloudflare.type.CacheLevelType;
import roth.lib.java.api.cloudflare.type.MinificationType;
import roth.lib.java.api.cloudflare.type.RocketLoaderType;
import roth.lib.java.api.cloudflare.type.SecurityLevelType;


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
		clientFactory.getModifyClient().setSecurityLevel(new SetSecurityLevelRequest(domain, SecurityLevelType.MED));
	}
	
	public static void setCacheLevel()
	{
		clientFactory.getModifyClient().setCacheLevel(new SetCacheLevelRequest(domain, CacheLevelType.AGG));
	}
	
	public static void setDevMode()
	{
		clientFactory.getModifyClient().setDevMode(new SetDevModeRequest(domain, false));
	}
	
	public static void clearCache()
	{
		clientFactory.getModifyClient().clearCache(new ClearCacheRequest(domain));
	}
	
	public static void clearFile()
	{
		clientFactory.getModifyClient().clearFile(new ClearFileRequest(domain, "http://www.roth.am/test.jpg"));
	}
	
	public static void refreshZone()
	{
		clientFactory.getModifyClient().refreshZone(new RefreshZoneRequest(7262791));
	}
	
	public static void whitelistIp()
	{
		clientFactory.getModifyClient().whitelistIp(new WhitelistIpRequest("127.0.0.1"));
	}
	
	public static void blacklistIp()
	{
		clientFactory.getModifyClient().blacklistIp(new BlacklistIpRequest("127.0.0.1"));
	}
	
	public static void unlistIp()
	{
		clientFactory.getModifyClient().unlistIp(new UnlistIpRequest("127.0.0.1"));
	}
	
	public static void setIpv6()
	{
		clientFactory.getModifyClient().setIpv6(new SetIpv6Request(domain, false));
	}
	
	public static void setRocketLoader()
	{
		clientFactory.getModifyClient().setRocketLoader(new SetRocketLoaderRequest(domain, RocketLoaderType.OFF));
	}
	
	public static void setMinification()
	{
		clientFactory.getModifyClient().setMinification(new SetMinificationRequest(domain, MinificationType.OFF));
	}
	
	public static void setMirage2()
	{
		clientFactory.getModifyClient().setMirage2(new SetMirage2Request(domain, false));
	}
	
}
