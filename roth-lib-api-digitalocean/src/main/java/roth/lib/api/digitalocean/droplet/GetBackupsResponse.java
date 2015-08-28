package roth.lib.api.digitalocean.droplet;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Image;
import roth.lib.api.digitalocean.model.Meta;

@SuppressWarnings("serial")
public class GetBackupsResponse implements Serializable
{
	@Property(name = "backups")
	protected LinkedList<Image> backups;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetBackupsResponse()
	{
		
	}
	
	public LinkedList<Image> getBackups()
	{
		return backups;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public GetBackupsResponse setBackups(LinkedList<Image> backups)
	{
		this.backups = backups;
		return this;
	}
	
	public GetBackupsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
