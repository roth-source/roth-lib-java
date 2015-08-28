package roth.lib.api.twilio;

import java.util.LinkedHashMap;

import roth.lib.api.twilio.call.CallClient;
import roth.lib.api.twilio.message.MessageClient;

public class TwilioClientFactory
{
	protected static LinkedHashMap<String, TwilioClientFactory> instanceMap = new LinkedHashMap<String, TwilioClientFactory>();
	
	protected MessageClient messageClient;
	protected CallClient callClient;
	
	protected TwilioClientFactory(String accountSid, String authToken, boolean debug)
	{
		messageClient = new MessageClient(accountSid, authToken, debug);
		callClient = new CallClient(accountSid, authToken, debug);
	}
	
	public static TwilioClientFactory get(String accountSid, String authToken)
	{
		return get(accountSid, authToken, false);
	}
	
	public static TwilioClientFactory get(String accountSid, String authToken, boolean debug)
	{
		String token = accountSid + ":" + authToken;
		TwilioClientFactory clientFactory = instanceMap.get(token);
		if(clientFactory == null)
		{
			clientFactory = new TwilioClientFactory(accountSid, authToken, debug);
			instanceMap.put(token, clientFactory);
		}
		return clientFactory;
	}
	
	public static LinkedHashMap<String, TwilioClientFactory> getInstanceMap()
	{
		return instanceMap;
	}
	
	public MessageClient getMessageClient()
	{
		return messageClient;
	}
	
	public CallClient getCallClient()
	{
		return callClient;
	}
	
}
