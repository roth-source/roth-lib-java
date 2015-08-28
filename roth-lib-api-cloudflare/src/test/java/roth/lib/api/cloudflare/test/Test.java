package roth.lib.api.cloudflare.test;

import java.io.File;
import java.util.Properties;

import roth.lib.api.cloudflare.CloudFlareClientFactory;
import roth.lib.util.PropertiesUtil;


public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static CloudFlareClientFactory clientFactory = CloudFlareClientFactory.get(test.getProperty("email"), test.getProperty("apiKey"), true);
	protected static String domain = test.getProperty("domain");
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
