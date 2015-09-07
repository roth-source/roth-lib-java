package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

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
