package roth.lib.java.service;

import java.io.Serializable;
import roth.lib.java.lang.List;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class HttpServiceResponse implements Serializable
{
	@Property(name = "dev")
	protected HttpServiceDev dev;
	
	@Property(name = "errors")
	protected List<HttpError> errors;
	
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
	
	public HttpServiceResponse addError(HttpError error)
	{
		getErrors().add(error);
		return this;
	}
	
	public List<HttpError> getErrors()
	{
		if(errors == null)
		{
			errors = new List<HttpError>();
		}
		return errors;
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
	
	public HttpServiceResponse setErrors(List<HttpError> errors)
	{
		getErrors().addAll(errors);
		return this;
	}
	
}
