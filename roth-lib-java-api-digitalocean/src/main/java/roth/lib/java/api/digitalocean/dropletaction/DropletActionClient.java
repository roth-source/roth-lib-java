package roth.lib.java.api.digitalocean.dropletaction;

import roth.lib.java.api.digitalocean.DigitalOceanClient;
import roth.lib.java.api.digitalocean.type.DropletActionType;
import roth.lib.java.api.digitalocean.type.SizeType;

public class DropletActionClient extends DigitalOceanClient
{
	
	public DropletActionClient(String token)
	{
		this(token, false);
	}
	
	public DropletActionClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	protected DropletActionResponse createDropletAction(int id, DropletActionType dropletActionType)
	{
		return createDropletAction(id, dropletActionType.getDropletActionRequest());
	}
	
	protected DropletActionResponse createDropletAction(int id, DropletActionRequest dropletActionRequest)
	{
		return post(url(DROPLETS + id + ACTIONS), dropletActionRequest, DropletActionResponse.class);
	}
	
	public DropletActionResponse rebootDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.REBOOT);
	}
	
	public DropletActionResponse powerCycleDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_CYCLE);
	}
	
	public DropletActionResponse shutdownDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.SHUTDOWN);
	}
	
	public DropletActionResponse powerOffDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_OFF);
	}
	
	public DropletActionResponse powerOnDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_ON);
	}
	
	public DropletActionResponse passwordResetDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_ON);
	}
	
	public DropletActionResponse resizeDroplet(int id, SizeType sizeType)
	{
		return createDropletAction(id, DropletActionType.RESIZE.getDropletActionRequest().setSizeType(sizeType));
	}
	
	public DropletActionResponse restoreDroplet(int id, int image)
	{
		return createDropletAction(id, DropletActionType.RESTORE.getDropletActionRequest().setImage(String.valueOf(image)));
	}
	
	public DropletActionResponse rebuildDroplet(int id, int image)
	{
		return rebuildDroplet(id, String.valueOf(image));
	}
	
	public DropletActionResponse rebuildDroplet(int id, String image)
	{
		return createDropletAction(id, DropletActionType.REBUILD.getDropletActionRequest().setImage(image));
	}
	
	public DropletActionResponse renameDroplet(int id, String name)
	{
		return createDropletAction(id, DropletActionType.RENAME.getDropletActionRequest().setName(name));
	}
	
	public DropletActionResponse changeKernel(int id, int kernel)
	{
		return createDropletAction(id, DropletActionType.CHANGE_KERNEL.getDropletActionRequest().setKernel(kernel));
	}
	
	public DropletActionResponse enableIpv6(int id)
	{
		return createDropletAction(id, DropletActionType.ENABLE_IPV6);
	}
	
	public DropletActionResponse disableBackups(int id)
	{
		return createDropletAction(id, DropletActionType.DISABLE_BACKUPS);
	}
	
	public DropletActionResponse enablePrivateNetworking(int id)
	{
		return createDropletAction(id, DropletActionType.ENABLE_PRIVATE_NETWORKING);
	}
	
	public DropletActionResponse getDropletAction(int id, int actionId)
	{
		return get(url(DROPLETS + id + ACTIONS + actionId), DropletActionResponse.class);
	}
	
}
