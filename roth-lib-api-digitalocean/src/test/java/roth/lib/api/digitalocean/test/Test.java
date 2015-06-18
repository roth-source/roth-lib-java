package roth.lib.api.digitalocean.test;

import java.io.File;
import java.util.Properties;

import roth.lib.api.digitalocean.client.DigitalOceanClientFactory;
import roth.lib.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static DigitalOceanClientFactory clientFactory = DigitalOceanClientFactory.get(test.getProperty("apiKey"), true);
	protected static String domain = test.getProperty("domain");
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
