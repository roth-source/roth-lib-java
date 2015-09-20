package roth.lib.java.api.digitalocean.droplet;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Image;
import roth.lib.java.api.digitalocean.model.Meta;

@Entity
@SuppressWarnings("serial")
public class GetSnapshotsResponse implements Serializable
{
	@Property(name = "snapshots")
	protected LinkedList<Image> snapshots;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetSnapshotsResponse()
	{
		
	}
	
	public LinkedList<Image> getSnapshots()
	{
		return snapshots;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public GetSnapshotsResponse setSnapshots(LinkedList<Image> snapshots)
	{
		this.snapshots = snapshots;
		return this;
	}
	
	public GetSnapshotsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
