package roth.lib.api.rackspace.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Access implements Serializable
{
	@Property(name = "token")
	protected AuthToken authToken;
	
	@Property(name = "serviceCatalog")
	protected LinkedList<Service> services = new LinkedList<Service>();
	
	@Property(name = "user")
	protected User user;
	
	public Access()
	{
		
	}
	
	public AuthToken getAuthToken()
	{
		return authToken;
	}
	
	public LinkedList<Service> getServices()
	{
		return services;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public Access setAuthToken(AuthToken authToken)
	{
		this.authToken = authToken;
		return this;
	}
	
	public Access setServices(LinkedList<Service> services)
	{
		this.services = services;
		return this;
	}
	
	public Access setUser(User user)
	{
		this.user = user;
		return this;
	}
	
}
