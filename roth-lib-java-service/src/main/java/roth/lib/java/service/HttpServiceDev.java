package roth.lib.java.service;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class HttpServiceDev implements Serializable
{
	@Property(name = "jsessionid")
	protected String jsessionId;
	
	public HttpServiceDev()
	{
		
	}
	
	public String getJsessionId()
	{
		return jsessionId;
	}

	public HttpServiceDev setJsessionId(String jsessionId)
	{
		this.jsessionId = jsessionId;
		return this;
	}
	
}
