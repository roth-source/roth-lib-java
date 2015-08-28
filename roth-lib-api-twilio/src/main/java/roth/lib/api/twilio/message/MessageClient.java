package roth.lib.api.twilio.message;

import roth.lib.api.twilio.TwilioClient;

public class MessageClient extends TwilioClient
{
	
	public MessageClient(String accountSid, String authToken, boolean debug)
	{
		super(accountSid, authToken, debug);
	}
	
	public SendMessageResponse sendMessage(SendMessageRequest request)
	{
		return post(url(MESSAGES), request, SendMessageResponse.class);
	}
	
}
