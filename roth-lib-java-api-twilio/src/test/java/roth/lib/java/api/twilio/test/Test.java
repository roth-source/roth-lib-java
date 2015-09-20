package roth.lib.java.api.twilio.test;

import java.io.File;
import java.util.Properties;

import roth.lib.java.api.twilio.TwilioClientFactory;
import roth.lib.java.util.PropertiesUtil;

public class Test
{
	protected static Properties test = PropertiesUtil.load(new File("dev/test.properties"));
	protected static TwilioClientFactory clientFactory = TwilioClientFactory.get(test.getProperty("accountSid"), test.getProperty("authToken"), true);
	protected static String from = test.getProperty("from");
	protected static String to = test.getProperty("to");
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
