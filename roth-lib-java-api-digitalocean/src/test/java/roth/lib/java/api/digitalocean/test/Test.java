package roth.lib.java.api.digitalocean.test;

import java.io.File;
import java.util.Properties;

import roth.lib.java.api.digitalocean.DigitalOceanClientFactory;
import roth.lib.java.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static DigitalOceanClientFactory clientFactory = DigitalOceanClientFactory.get(test.getProperty("apiKey"), true);
	protected static String domain = test.getProperty("domain");
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
