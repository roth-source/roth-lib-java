package roth.lib.java.api.linode.test;

import java.io.File;
import java.util.Properties;

import roth.lib.java.api.linode.LinodeClientFactory;
import roth.lib.java.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static LinodeClientFactory clientFactory = LinodeClientFactory.get(test.getProperty("apiKey"), true);
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
