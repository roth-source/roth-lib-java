package roth.lib.api.linode.test;

import java.io.File;
import java.util.Properties;

import roth.lib.api.linode.client.LinodeClientFactory;
import roth.lib.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static LinodeClientFactory clientFactory = LinodeClientFactory.get(test.getProperty("apiKey"), true);
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
