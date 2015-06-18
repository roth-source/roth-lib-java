package roth.lib.api.digitalocean.data.request;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.type.RegionType;
import roth.lib.api.digitalocean.data.type.SizeType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DropletRequest implements Serializable
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
	
	public DropletRequest(String name, String region, String size, String image)
	{
		this.name = name;
		this.region = region;
		this.size = size;
		this.image = image;
	}
	
	public DropletRequest(String name, RegionType regionType, SizeType sizeType, String image)
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
	
	public DropletRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DropletRequest setRegion(String region)
	{
		this.region = region;
		return this;
	}
	
	public DropletRequest setSize(String size)
	{
		this.size = size;
		return this;
	}
	
	public DropletRequest setImage(String image)
	{
		this.image = image;
		return this;
	}
	
	public DropletRequest setSshKeys(LinkedList<Integer> sshKeys)
	{
		this.sshKeys = sshKeys;
		return this;
	}
	
	public DropletRequest setBackups(Boolean backups)
	{
		this.backups = backups;
		return this;
	}
	
	public DropletRequest setIpv6(Boolean ipv6)
	{
		this.ipv6 = ipv6;
		return this;
	}
	
	public DropletRequest setProtectedNetworking(Boolean protectedNetworking)
	{
		this.protectedNetworking = protectedNetworking;
		return this;
	}
	
	public DropletRequest setRegionType(RegionType regionType)
	{
		this.region = regionType.get();
		return this;
	}
	
	public DropletRequest setSizeType(SizeType sizeType)
	{
		size = sizeType.get();
		return this;
	}
	
}
