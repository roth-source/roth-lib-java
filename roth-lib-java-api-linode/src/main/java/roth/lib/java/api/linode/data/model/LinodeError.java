package roth.lib.java.api.linode.data.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class LinodeError implements Serializable
{
	@Property(name = "ERRORCODE")
	protected Integer errorCode;
	
	@Property(name = "ERRORMESSAGE")
	protected String errorMessage;
	
	public LinodeError()
	{
		
	}
	
	public Integer getErrorCode()
	{
		return errorCode;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public LinodeError setErrorCode(Integer errorCode)
	{
		this.errorCode = errorCode;
		return this;
	}
	
	public LinodeError setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
		return this;
	}
	
}
