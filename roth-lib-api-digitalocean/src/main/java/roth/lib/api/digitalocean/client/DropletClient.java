package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.DropletRequest;
import roth.lib.api.digitalocean.data.response.ActionsResponse;
import roth.lib.api.digitalocean.data.response.BackupsResponse;
import roth.lib.api.digitalocean.data.response.DropletResponse;
import roth.lib.api.digitalocean.data.response.DropletsResponse;
import roth.lib.api.digitalocean.data.response.KernelsResponse;
import roth.lib.api.digitalocean.data.response.SnapshotsResponse;

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
	
	public DropletsResponse getDroplets(int page)
	{
		return get(url(DROPLETS, page), DropletsResponse.class);
	}
	
	public DropletResponse getDroplet(int id)
	{
		return get(url(DROPLETS + id), DropletResponse.class);
	}
	
	public KernelsResponse getKernels(int id)
	{
		return get(url(DROPLETS + id + KERNELS), KernelsResponse.class);
	}
	
	public SnapshotsResponse getSnapshots(int id)
	{
		return get(url(DROPLETS + id + SNAPSHOTS), SnapshotsResponse.class);
	}
	
	public BackupsResponse getBackups(int id)
	{
		return get(url(DROPLETS + id + BACKUPS), BackupsResponse.class);
	}
	
	public ActionsResponse getActions(int id)
	{
		return get(url(DROPLETS + id + ACTIONS), ActionsResponse.class);
	}
	
	public DropletResponse createDroplet(DropletRequest dropletRequest)
	{
		return post(url(DROPLETS), dropletRequest, DropletResponse.class);
	}
	
	public void deleteDroplet(int id)
	{
		delete(url(DROPLETS + id));
	}
	
}
