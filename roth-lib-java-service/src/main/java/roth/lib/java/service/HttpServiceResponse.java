package roth.lib.java.service;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class HttpServiceResponse implements Serializable
{
	@Property(name = "dev")
	protected HttpServiceDev dev;
	
	public HttpServiceResponse()
	{
		
	}
	
	public HttpServiceDev getDev()
	{
		if(dev == null)
		{
			dev = new HttpServiceDev();
		}
		return dev;
	}
	
	public HttpServiceResponse setDev(HttpServiceDev dev)
	{
		this.dev = dev;
		return this;
	}
	
	public HttpServiceResponse setJsessionId(String jsessionId)
	{
		getDev().setJsessionId(jsessionId);
		return this;
	}

	public HttpServiceResponse setCsrfToken(String csrfToken)
	{
		getDev().setCsrfToken(csrfToken);
		return this;
	}
	
}
