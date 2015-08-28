package roth.lib.api.digitalocean.droplet;

import roth.lib.api.digitalocean.DigitalOceanClient;
import roth.lib.api.digitalocean.action.GetActionsResponse;

public class DropletClient extends DigitalOceanClient
{
	
	public DropletClient(String token)
	{
		this(token, false);
	}
	
	public DropletClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public GetDropletsResponse getDroplets(int page)
	{
		return get(url(DROPLETS, page), GetDropletsResponse.class);
	}
	
	public GetDropletResponse getDroplet(int id)
	{
		return get(url(DROPLETS + id), GetDropletResponse.class);
	}
	
	public GetKernelsResponse getKernels(int id)
	{
		return get(url(DROPLETS + id + KERNELS), GetKernelsResponse.class);
	}
	
	public GetSnapshotsResponse getSnapshots(int id)
	{
		return get(url(DROPLETS + id + SNAPSHOTS), GetSnapshotsResponse.class);
	}
	
	public GetBackupsResponse getBackups(int id)
	{
		return get(url(DROPLETS + id + BACKUPS), GetBackupsResponse.class);
	}
	
	public GetActionsResponse getActions(int id)
	{
		return get(url(DROPLETS + id + ACTIONS), GetActionsResponse.class);
	}
	
	public GetDropletResponse createDroplet(CreateDropletRequest dropletRequest)
	{
		return post(url(DROPLETS), dropletRequest, GetDropletResponse.class);
	}
	
	public void deleteDroplet(int id)
	{
		delete(url(DROPLETS + id));
	}
	
}
