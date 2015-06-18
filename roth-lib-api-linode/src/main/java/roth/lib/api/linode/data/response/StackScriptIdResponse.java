package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Property;

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
