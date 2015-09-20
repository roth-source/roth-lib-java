package roth.lib.java.api.rackspace.test;
import java.io.File;
import java.util.Properties;

import roth.lib.java.api.rackspace.RackspaceClientFactory;
import roth.lib.java.api.rackspace.identity.IdentityClient;
import roth.lib.java.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static RackspaceClientFactory clientFactory = RackspaceClientFactory.get(new IdentityClient(test.getProperty("username"), test.getProperty("apiKey")), true);
	
	public static void main(String[] args)
	{
		test();
	}
	
	public static void test()
	{
		
	}
	
}
