package roth.lib.java.api.twilio.test;

import roth.lib.java.api.twilio.call.MakeCallRequest;
import roth.lib.java.util.UrlUtil;

public class CallTest extends Test
{
	
	public static void main(String[] args)
	{
		makeCall();
	}
	
	public static void makeCall()
	{
		String url = "https://demo.aptexx.com/voice/read/body/" + UrlUtil.encode("This is a test call.");
		clientFactory.getCallClient().makeCall(new MakeCallRequest(from, to, url));
	}
	
}
