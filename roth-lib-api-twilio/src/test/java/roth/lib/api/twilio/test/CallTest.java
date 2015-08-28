package roth.lib.api.twilio.test;

import java.net.URLEncoder;

import roth.lib.api.twilio.call.MakeCallRequest;

public class CallTest extends Test
{
	
	public static void main(String[] args)
	{
		makeCall();
	}
	
	public static void makeCall()
	{
		String url = "https://demo.aptexx.com/voice/read/body/";
		try
		{
			url += URLEncoder.encode("This is a test call.", "UTF-8");
		}
		catch(Exception e)
		{
			
		}
		clientFactory.getCallClient().makeCall(new MakeCallRequest(from, to, url));
	}
	
}
