package roth.lib.api.linode.client;

import java.util.LinkedHashMap;

public class LinodeClientFactory
{
	protected static LinkedHashMap<String, LinodeClientFactory> instanceMap = new LinkedHashMap<String, LinodeClientFactory>();
	
	protected NodeClient nodeClient;
	protected NodeBalancerClient nodeBalancerClient;
	protected StackScriptClient stackScriptClient;
	protected DomainClient domainClient;
	protected AccountClient accountClient;
	protected UtilityClient utilityClient;
	
	private LinodeClientFactory(String apiKey, boolean debug)
	{
		nodeClient = new NodeClient(apiKey, debug);
		nodeBalancerClient = new NodeBalancerClient(apiKey, debug);
		stackScriptClient = new StackScriptClient(apiKey, debug);
		domainClient = new DomainClient(apiKey, debug);
		accountClient = new AccountClient(apiKey, debug);
		utilityClient = new UtilityClient(apiKey, debug);
	}
	
	public static LinodeClientFactory get(String apiKey)
	{
		return get(apiKey, false);
	}
	
	public static LinodeClientFactory get(String apiKey, boolean debug)
	{
		LinodeClientFactory clientFactory = instanceMap.get(apiKey);
		if(clientFactory == null)
		{
			clientFactory = new LinodeClientFactory(apiKey, debug);
			instanceMap.put(apiKey, clientFactory);
		}
		return clientFactory;
	}
	
	public static LinkedHashMap<String, LinodeClientFactory> getInstanceMap()
	{
		return instanceMap;
	}
	
	public NodeClient getNodeClient()
	{
		return nodeClient;
	}
	
	public NodeBalancerClient getNodeBalancerClient()
	{
		return nodeBalancerClient;
	}
	
	public StackScriptClient getStackScriptClient()
	{
		return stackScriptClient;
	}
	
	public DomainClient getDomainClient()
	{
		return domainClient;
	}
	
	public AccountClient getAccountClient()
	{
		return accountClient;
	}
	
	public UtilityClient getUtilityClient()
	{
		return utilityClient;
	}
	
}
