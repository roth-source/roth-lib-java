package roth.lib.java.api.vultr.client;

import java.util.LinkedHashMap;

public class VultrClientFactory
{
	protected static LinkedHashMap<String, VultrClientFactory> instanceMap = new LinkedHashMap<String, VultrClientFactory>();
	
	protected ServerClient serverClient;
	
	private VultrClientFactory(String apiKey, boolean debug)
	{
		serverClient = new ServerClient(apiKey, debug);
	}
	
	public static VultrClientFactory get(String apiKey)
	{
		return get(apiKey, false);
	}
	
	public static VultrClientFactory get(String apiKey, boolean debug)
	{
		VultrClientFactory clientFactory = instanceMap.get(apiKey);
		if(clientFactory == null)
		{
			clientFactory = new VultrClientFactory(apiKey, debug);
			instanceMap.put(apiKey, clientFactory);
		}
		return clientFactory;
	}
	
	public static LinkedHashMap<String, VultrClientFactory> getInstanceMap()
	{
		return instanceMap;
	}
	
	public ServerClient getServerClient()
	{
		return serverClient;
	}
	
}
