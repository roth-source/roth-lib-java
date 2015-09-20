package roth.lib.java.api.digitalocean.droplet;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.type.RegionType;
import roth.lib.java.api.digitalocean.type.SizeType;

@Entity
@SuppressWarnings("serial")
public class CreateDropletRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "region")
	protected String region;
	
	@Property(name = "size")
	protected String size;
	
	@Property(name = "image")
	protected String image;
	
	@Property(name = "ssh_keys")
	protected LinkedList<Integer> sshKeys;
	
	@Property(name = "backups")
	protected Boolean backups;
	
	@Property(name = "ipv6")
	protected Boolean ipv6;
	
	@Property(name = "protected_networking")
	protected Boolean protectedNetworking;
	
	public CreateDropletRequest(String name, String region, String size, String image)
	{
		this.name = name;
		this.region = region;
		this.size = size;
		this.image = image;
	}
	
	public CreateDropletRequest(String name, RegionType regionType, SizeType sizeType, String image)
	{
		this.name = name;
		setRegionType(regionType);
		setSizeType(sizeType);
		this.image = image;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public String getSize()
	{
		return size;
	}
	
	public String getImage()
	{
		return image;
	}
	
	public LinkedList<Integer> getSshKeys()
	{
		return sshKeys;
	}
	
	public Boolean getBackups()
	{
		return backups;
	}
	
	public Boolean getIpv6()
	{
		return ipv6;
	}
	
	public Boolean getProtectedNetworking()
	{
		return protectedNetworking;
	}
	
	public CreateDropletRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public CreateDropletRequest setRegion(String region)
	{
		this.region = region;
		return this;
	}
	
	public CreateDropletRequest setSize(String size)
	{
		this.size = size;
		return this;
	}
	
	public CreateDropletRequest setImage(String image)
	{
		this.image = image;
		return this;
	}
	
	public CreateDropletRequest setSshKeys(LinkedList<Integer> sshKeys)
	{
		this.sshKeys = sshKeys;
		return this;
	}
	
	public CreateDropletRequest setBackups(Boolean backups)
	{
		this.backups = backups;
		return this;
	}
	
	public CreateDropletRequest setIpv6(Boolean ipv6)
	{
		this.ipv6 = ipv6;
		return this;
	}
	
	public CreateDropletRequest setProtectedNetworking(Boolean protectedNetworking)
	{
		this.protectedNetworking = protectedNetworking;
		return this;
	}
	
	public CreateDropletRequest setRegionType(RegionType regionType)
	{
		this.region = regionType.get();
		return this;
	}
	
	public CreateDropletRequest setSizeType(SizeType sizeType)
	{
		size = sizeType.get();
		return this;
	}
	
}
