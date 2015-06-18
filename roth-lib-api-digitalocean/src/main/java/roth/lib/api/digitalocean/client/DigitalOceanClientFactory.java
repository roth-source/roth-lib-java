package roth.lib.api.digitalocean.client;

import java.util.LinkedHashMap;

public class DigitalOceanClientFactory
{
	protected static LinkedHashMap<String, DigitalOceanClientFactory> instanceMap = new LinkedHashMap<String, DigitalOceanClientFactory>();
	
	protected ActionClient actionClient;
	protected DomainRecordClient domainRecordClient;
	protected DomainClient domainClient;
	protected DropletActionClient dropletActionClient;
	protected DropletClient dropletClient;
	protected ImageActionClient imageActionClient;
	protected ImageClient imageClient;
	protected KeyClient keyClient;
	protected RegionClient regionClient;
	protected SizeClient sizeClient;
	
	protected DigitalOceanClientFactory(String token, boolean debug)
	{
		actionClient = new ActionClient(token, debug);
		domainRecordClient = new DomainRecordClient(token, debug);
		domainClient = new DomainClient(token, debug);
		dropletActionClient = new DropletActionClient(token, debug);
		dropletClient = new DropletClient(token, debug);
		imageActionClient = new ImageActionClient(token, debug);
		imageClient = new ImageClient(token, debug);
		keyClient = new KeyClient(token, debug);
		regionClient = new RegionClient(token, debug);
		sizeClient = new SizeClient(token, debug);
	}
	
	public static DigitalOceanClientFactory get(String token)
	{
		return get(token, false);
	}
	
	public static DigitalOceanClientFactory get(String token, boolean debug)
	{
		DigitalOceanClientFactory clientFactory = instanceMap.get(token);
		if(clientFactory == null)
		{
			clientFactory = new DigitalOceanClientFactory(token, debug);
			instanceMap.put(token, clientFactory);
		}
		return clientFactory;
	}
	
	public static LinkedHashMap<String, DigitalOceanClientFactory> getInstanceMap()
	{
		return instanceMap;
	}
	
	public ActionClient getActionClient()
	{
		return actionClient;
	}
	
	public DomainRecordClient getDomainRecordClient()
	{
		return domainRecordClient;
	}
	
	public DomainClient getDomainClient()
	{
		return domainClient;
	}
	
	public DropletActionClient getDropletActionClient()
	{
		return dropletActionClient;
	}
	
	public DropletClient getDropletClient()
	{
		return dropletClient;
	}
	
	public ImageActionClient getImageActionClient()
	{
		return imageActionClient;
	}
	
	public ImageClient getImageClient()
	{
		return imageClient;
	}
	
	public KeyClient getKeyClient()
	{
		return keyClient;
	}
	
	public RegionClient getRegionClient()
	{
		return regionClient;
	}
	
	public SizeClient getSizeClient()
	{
		return sizeClient;
	}
	
}
