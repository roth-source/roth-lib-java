package roth.lib.java.api.linode.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.model.LinodeError;

@Entity
@SuppressWarnings("serial")
public class LinodeResponse<T> implements Serializable
{
	@Property(name = "DATA")
	protected T data;
	
	@Property(name = "ACTION")
	protected String action;
	
	@Property(name = "ERRORARRAY")
	protected LinkedList<LinodeError> errors;
	
	public LinodeResponse()
	{
		
	}
	
	public T getData()
	{
		return data;
	}
	
	public String getAction()
	{
		return action;
	}
	
	public LinkedList<LinodeError> getErrors()
	{
		return errors;
	}
	
	public LinodeResponse<T> setData(T data)
	{
		this.data = data;
		return this;
	}
	
	public LinodeResponse<T> setAction(String action)
	{
		this.action = action;
		return this;
	}
	
	public LinodeResponse<T> setErrors(LinkedList<LinodeError> errors)
	{
		this.errors = errors;
		return this;
	}
	
}
