package roth.lib.java._static.config;

import java.io.Serializable;
import java.util.LinkedHashSet;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Scenario implements Serializable
{
	@Property(name = "request")
	protected LinkedHashSet<String> requests = new LinkedHashSet<String>();
	
	@Property(name = "response")
	protected LinkedHashSet<String> responses = new LinkedHashSet<String>();
	
	public Scenario()
	{
		
	}

	public LinkedHashSet<String> getRequests()
	{
		return requests;
	}

	public LinkedHashSet<String> getResponses()
	{
		return responses;
	}

	public void setRequests(LinkedHashSet<String> requests)
	{
		this.requests = requests;
	}

	public void setResponses(LinkedHashSet<String> responses)
	{
		this.responses = responses;
	}
	
}
