package roth.lib.api.rackspace.client;

import java.util.LinkedHashMap;

public class RackspaceClientFactory
{
	protected static LinkedHashMap<String, RackspaceClientFactory> instanceMap = new LinkedHashMap<String, RackspaceClientFactory>();
	
	protected FileClient fileClient;
	
	protected RackspaceClientFactory(IdentityClient identityClient, boolean debug)
	{
		fileClient = new FileClient(identityClient, debug);
	}
	
	public static RackspaceClientFactory get(IdentityClient identityClient)
	{
		return get(identityClient, false);
	}
	
	public static RackspaceClientFactory get(IdentityClient identityClient, boolean debug)
	{
		String key = identityClient.getUsername() + ":" + identityClient.getApiKey();
		RackspaceClientFactory ClientFactory = instanceMap.get(key);
		if(ClientFactory == null)
		{
			ClientFactory = new RackspaceClientFactory(identityClient, debug);
			instanceMap.put(key, ClientFactory);
		}
		return ClientFactory;
	}
	
	public static LinkedHashMap<String, RackspaceClientFactory> getInstanceMap()
	{
		return instanceMap;
	}
	
	public FileClient getFileClient()
	{
		return fileClient;
	}
	
}
