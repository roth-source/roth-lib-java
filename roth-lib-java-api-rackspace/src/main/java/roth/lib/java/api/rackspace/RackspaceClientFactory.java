package roth.lib.java.api.rackspace;

import java.util.LinkedHashMap;

import roth.lib.java.api.rackspace.file.FileClient;
import roth.lib.java.api.rackspace.identity.IdentityClient;

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
