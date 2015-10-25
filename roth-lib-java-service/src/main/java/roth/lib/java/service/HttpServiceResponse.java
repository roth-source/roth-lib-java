package roth.lib.java.service;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.service.endpoint.HttpError;

@Entity
@SuppressWarnings("serial")
public class HttpServiceResponse implements Serializable
{
	@Property(name = "dev")
	protected HttpServiceDev dev;
	
	@Property(name = "errors")
	protected LinkedList<HttpError> errors;
	
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
	
	public LinkedList<HttpError> getErrors()
	{
		if(errors == null)
		{
			errors = new LinkedList<HttpError>();
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

	public HttpServiceResponse setCsrfToken(String csrfToken)
	{
		getDev().setCsrfToken(csrfToken);
		return this;
	}
	
	public HttpServiceResponse setErrors(LinkedList<HttpError> errors)
	{
		getErrors().addAll(errors);
		return this;
	}
	
}
