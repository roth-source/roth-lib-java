package roth.lib.api.rackspace.test;
import java.io.File;
import java.util.Properties;

import roth.lib.api.rackspace.client.IdentityClient;
import roth.lib.api.rackspace.client.RackspaceClientFactory;
import roth.lib.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static RackspaceClientFactory clientFactory = RackspaceClientFactory.get(new IdentityClient(test.getProperty("username"), test.getProperty("apiKey")), true);
	
	public static void main(String[] args)
	{
		
	}
	
}
