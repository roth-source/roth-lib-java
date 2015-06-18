package roth.lib.api.digitalocean.data.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Droplet implements Serializable
{
	@Property(name = "id")
	protected Integer id;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "region")
	protected Region region;
	
	@Property(name = "image")
	protected Image image;
	
	@Property(name = "kernel")
	protected Kernel kernel;
	
	@Property(name = "size")
	protected Size size;
	
	@Property(name = "locked")
	protected Boolean locked;
	
	@Property(name = "created_at")
	protected Calendar createdAt;
	
	@Property(name = "status")
	protected String status;
	
	@Property(name = "networks")
	protected Networks networks;
	
	@Property(name = "backup_ids")
	protected LinkedList<Integer> backupIds;
	
	@Property(name = "snapshot_ids")
	protected LinkedList<Integer> snapshotIds;
	
	@Property(name = "action_ids")
	protected LinkedList<Integer> actionIds;
	
	public Droplet()
	{
		
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Region getRegion()
	{
		return region;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public Kernel getKernel()
	{
		return kernel;
	}
	
	public Size getSize()
	{
		return size;
	}
	
	public Boolean getLocked()
	{
		return locked;
	}
	
	public Calendar getCreatedAt()
	{
		return createdAt;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public Networks getNetworks()
	{
		return networks;
	}
	
	public LinkedList<Integer> getBackupIds()
	{
		return backupIds;
	}
	
	public LinkedList<Integer> getSnapshotIds()
	{
		return snapshotIds;
	}
	
	public LinkedList<Integer> getActionIds()
	{
		return actionIds;
	}
	
	public Droplet setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	public Droplet setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Droplet setRegion(Region region)
	{
		this.region = region;
		return this;
	}
	
	public Droplet setImage(Image image)
	{
		this.image = image;
		return this;
	}
	
	public Droplet setKernel(Kernel kernel)
	{
		this.kernel = kernel;
		return this;
	}
	
	public Droplet setSize(Size size)
	{
		this.size = size;
		return this;
	}
	
	public Droplet setLocked(Boolean locked)
	{
		this.locked = locked;
		return this;
	}
	
	public Droplet setCreatedAt(Calendar createdAt)
	{
		this.createdAt = createdAt;
		return this;
	}
	
	public Droplet setStatus(String status)
	{
		this.status = status;
		return this;
	}
	
	public Droplet setNetworks(Networks networks)
	{
		this.networks = networks;
		return this;
	}
	
	public Droplet setBackupIds(LinkedList<Integer> backupIds)
	{
		this.backupIds = backupIds;
		return this;
	}
	
	public Droplet setSnapshotIds(LinkedList<Integer> snapshotIds)
	{
		this.snapshotIds = snapshotIds;
		return this;
	}
	
	public Droplet setActionIds(LinkedList<Integer> actionIds)
	{
		this.actionIds = actionIds;
		return this;
	}
	
}
