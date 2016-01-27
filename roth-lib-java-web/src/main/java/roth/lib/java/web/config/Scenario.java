package roth.lib.java.web.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.lang.Set;

@Entity
@SuppressWarnings("serial")
public class Scenario implements Serializable
{
	@Property(name = "request")
	protected Set<String> requests = new Set<String>();
	
	@Property(name = "response")
	protected Set<String> responses = new Set<String>();
	
	public Scenario()
	{
		
	}

	public Set<String> getRequests()
	{
		return requests;
	}

	public Set<String> getResponses()
	{
		return responses;
	}

	public void setRequests(Set<String> requests)
	{
		this.requests = requests;
	}

	public void setResponses(Set<String> responses)
	{
		this.responses = responses;
	}
	
}
