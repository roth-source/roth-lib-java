package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class StackScriptIdResponse implements Serializable
{
	@Property(name = "StackScriptID")
	protected Integer stackScriptId;
	
	public StackScriptIdResponse()
	{
		
	}
	
	public Integer getStackScriptId()
	{
		return stackScriptId;
	}
	
	public StackScriptIdResponse setStackScriptId(Integer stackScriptId)
	{
		this.stackScriptId = stackScriptId;
		return this;
	}
	
}
