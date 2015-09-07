package roth.lib.service.endpoint;

import java.util.Arrays;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
public class HttpEndpointList
{
	@Property(name = "endpoints")
	protected LinkedList<String> endpoints = new LinkedList<String>();
	
	public HttpEndpointList()
	{
		
	}
	
	public LinkedList<String> getEndpoints()
	{
		return endpoints;
	}
	
	public HttpEndpointList setEndpoints(String[] endpoints)
	{
		this.endpoints = new LinkedList<String>(Arrays.asList(endpoints));
		return this;
	}
	
	public HttpEndpointList addEndpoint(String endpoint)
	{
		this.endpoints.add(endpoint);
		return this;
	}
	
}
