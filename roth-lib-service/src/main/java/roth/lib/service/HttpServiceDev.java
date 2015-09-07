package roth.lib.service;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class HttpServiceDev implements Serializable
{
	@Property(name = "jsessionid")
	protected String jsessionId;
	
	@Property(name = "csrfToken")
	protected String csrfToken;
	
	public HttpServiceDev()
	{
		
	}
	
	public String getJsessionId()
	{
		return jsessionId;
	}
	
	public String getCsrfToken()
	{
		return csrfToken;
	}
	
	public HttpServiceDev setJsessionId(String jsessionId)
	{
		this.jsessionId = jsessionId;
		return this;
	}
	
	public HttpServiceDev setCsrfToken(String csrfToken)
	{
		this.csrfToken = csrfToken;
		return this;
	}
	
}
