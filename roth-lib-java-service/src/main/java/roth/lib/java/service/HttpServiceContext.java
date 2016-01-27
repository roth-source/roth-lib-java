package roth.lib.java.service;

import roth.lib.java.lang.List;

public class HttpServiceContext
{
	protected List<HttpError> errors = new List<HttpError>();
	
	public HttpServiceContext()
	{
		
	}
	
	public List<HttpError> getErrors()
	{
		return errors;
	}
	
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}
	
}
