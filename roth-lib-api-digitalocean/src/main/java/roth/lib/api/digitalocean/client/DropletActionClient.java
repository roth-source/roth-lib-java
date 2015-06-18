package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.DropletActionRequest;
import roth.lib.api.digitalocean.data.response.ActionResponse;
import roth.lib.api.digitalocean.data.type.DropletActionType;
import roth.lib.api.digitalocean.data.type.SizeType;

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
	
	protected ActionResponse createDropletAction(int id, DropletActionType dropletActionType)
	{
		return createDropletAction(id, dropletActionType.getDropletActionRequest());
	}
	
	protected ActionResponse createDropletAction(int id, DropletActionRequest dropletActionRequest)
	{
		return post(url(DROPLETS + id + ACTIONS), dropletActionRequest, ActionResponse.class);
	}
	
	public ActionResponse rebootDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.REBOOT);
	}
	
	public ActionResponse powerCycleDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_CYCLE);
	}
	
	public ActionResponse shutdownDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.SHUTDOWN);
	}
	
	public ActionResponse powerOffDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_OFF);
	}
	
	public ActionResponse powerOnDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_ON);
	}
	
	public ActionResponse passwordResetDroplet(int id)
	{
		return createDropletAction(id, DropletActionType.POWER_ON);
	}
	
	public ActionResponse resizeDroplet(int id, SizeType sizeType)
	{
		return createDropletAction(id, DropletActionType.RESIZE.getDropletActionRequest().setSizeType(sizeType));
	}
	
	public ActionResponse restoreDroplet(int id, int image)
	{
		return createDropletAction(id, DropletActionType.RESTORE.getDropletActionRequest().setImage(String.valueOf(image)));
	}
	
	public ActionResponse rebuildDroplet(int id, int image)
	{
		return rebuildDroplet(id, String.valueOf(image));
	}
	
	public ActionResponse rebuildDroplet(int id, String image)
	{
		return createDropletAction(id, DropletActionType.REBUILD.getDropletActionRequest().setImage(image));
	}
	
	public ActionResponse renameDroplet(int id, String name)
	{
		return createDropletAction(id, DropletActionType.RENAME.getDropletActionRequest().setName(name));
	}
	
	public ActionResponse changeKernel(int id, int kernel)
	{
		return createDropletAction(id, DropletActionType.CHANGE_KERNEL.getDropletActionRequest().setKernel(kernel));
	}
	
	public ActionResponse enableIpv6(int id)
	{
		return createDropletAction(id, DropletActionType.ENABLE_IPV6);
	}
	
	public ActionResponse disableBackups(int id)
	{
		return createDropletAction(id, DropletActionType.DISABLE_BACKUPS);
	}
	
	public ActionResponse enablePrivateNetworking(int id)
	{
		return createDropletAction(id, DropletActionType.ENABLE_PRIVATE_NETWORKING);
	}
	
	public ActionResponse getDropletAction(int id, int actionId)
	{
		return get(url(DROPLETS + id + ACTIONS + actionId), ActionResponse.class);
	}
	
}
