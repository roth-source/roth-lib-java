package roth.lib.api.rackspace.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class User implements Serializable
{
	@Property(name = "id")
	protected String id;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "RAX-AUTH:defaultRegion")
	protected String defaultRegion;
	
	@Property(name = "roles")
	protected LinkedList<Role> roles = new LinkedList<Role>();
	
	public User()
	{
		
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDefaultRegion()
	{
		return defaultRegion;
	}
	
	public LinkedList<Role> getRoles()
	{
		return roles;
	}
	
	public User setId(String id)
	{
		this.id = id;
		return this;
	}
	
	public User setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public User setDefaultRegion(String defaultRegion)
	{
		this.defaultRegion = defaultRegion;
		return this;
	}
	
	public User setRoles(LinkedList<Role> roles)
	{
		this.roles = roles;
		return this;
	}
	
}
