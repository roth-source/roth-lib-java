package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class StackScriptIdRequest extends LinodeRequest
{
	@Property(name = "StackScriptID")
	protected Integer stackScriptId;
	
	public StackScriptIdRequest()
	{
		
	}
	
	public Integer getStackScriptId()
	{
		return stackScriptId;
	}
	
	public StackScriptIdRequest setStackScriptId(Integer stackScriptId)
	{
		this.stackScriptId = stackScriptId;
		return this;
	}
	
}
