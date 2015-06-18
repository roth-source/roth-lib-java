package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Image;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class BackupsResponse implements Serializable
{
	@Property(name = "backups")
	protected LinkedList<Image> backups;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public BackupsResponse()
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
	
	public BackupsResponse setBackups(LinkedList<Image> backups)
	{
		this.backups = backups;
		return this;
	}
	
	public BackupsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
