package roth.lib.api.cloudflare.client;

import java.util.LinkedHashMap;


public class CloudFlareClientFactory
{
	protected static LinkedHashMap<String, CloudFlareClientFactory> instanceMap = new LinkedHashMap<String, CloudFlareClientFactory>();
	
	protected AccessClient accessClient;
	protected ModifyClient modifyClient;
	protected RecordClient recordClient;
	
	protected CloudFlareClientFactory(String email, String token, boolean debug)
	{
		accessClient = new AccessClient(email, token, debug);
		modifyClient = new ModifyClient(email, token, debug);
		recordClient = new RecordClient(email, token, debug);
	}
	
	public static CloudFlareClientFactory get(String email, String token)
	{
		return get(email, token, false);
	}
	
	public static CloudFlareClientFactory get(String email, String token, boolean debug)
	{
		String key = email + ":" + token;
		CloudFlareClientFactory ClientFactory = instanceMap.get(key);
		if(ClientFactory == null)
		{
			ClientFactory = new CloudFlareClientFactory(email, token, debug);
			instanceMap.put(key, ClientFactory);
		}
		return ClientFactory;
	}

	public static LinkedHashMap<String, CloudFlareClientFactory> getInstanceMap()
	{
		return instanceMap;
	}
	
	public AccessClient getAccessClient()
	{
		return accessClient;
	}
	
	public ModifyClient getModifyClient()
	{
		return modifyClient;
	}
	
	public RecordClient getRecordClient()
	{
		return recordClient;
	}
	
}
