package roth.lib.api.twilio;

import roth.lib.api.twilio.data.request.SendMessageRequest;
import roth.lib.api.twilio.data.response.SendMessageResponse;

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
