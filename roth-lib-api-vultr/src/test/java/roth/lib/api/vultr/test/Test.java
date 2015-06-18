package roth.lib.api.vultr.test;

import java.io.File;
import java.util.Properties;

import roth.lib.api.vultr.client.VultrClientFactory;
import roth.lib.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static VultrClientFactory clientFactory = VultrClientFactory.get(test.getProperty("apiKey"), true);
	
	public static void main(String[] args)
	{
		
	}
	
}
