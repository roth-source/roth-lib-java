package roth.lib.api.twilio.test;

import roth.lib.api.twilio.message.SendMessageRequest;

public class MessageTest extends Test
{
	
	public static void main(String[] args)
	{
		sendMessage();
	}
	
	public static void sendMessage()
	{
		clientFactory.getMessageClient().sendMessage(new SendMessageRequest(from, to, "test message"));
	}
	
}
