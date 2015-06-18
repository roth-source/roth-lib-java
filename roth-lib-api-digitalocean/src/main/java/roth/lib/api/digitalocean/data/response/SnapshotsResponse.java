package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Image;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class SnapshotsResponse implements Serializable
{
	@Property(name = "snapshots")
	protected LinkedList<Image> snapshots;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public SnapshotsResponse()
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
	
	public SnapshotsResponse setSnapshots(LinkedList<Image> snapshots)
	{
		this.snapshots = snapshots;
		return this;
	}
	
	public SnapshotsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
