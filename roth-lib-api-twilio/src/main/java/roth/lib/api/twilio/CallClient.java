package roth.lib.api.twilio;

import roth.lib.api.twilio.data.request.MakeCallRequest;

public class CallClient extends TwilioClient
{

	public CallClient(String accountSid, String authToken, boolean debug)
	{
		super(accountSid, authToken, debug);
	}
	
	public void makeCall(MakeCallRequest request)
	{
		post(url(CALLS), request);
	}
	
}
