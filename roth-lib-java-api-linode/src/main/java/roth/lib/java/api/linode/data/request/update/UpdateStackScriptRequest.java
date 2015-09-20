package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.StackScriptRequest;

@Entity
@SuppressWarnings("serial")
public class UpdateStackScriptRequest extends StackScriptRequest
{
	@Property(name = "StackScriptID")
	protected Integer stackScriptId;
	
	public UpdateStackScriptRequest(Integer stackScriptId)
	{
		super();
		setStackScriptId(stackScriptId);
	}
	
	public Integer getStackScriptId()
	{
		return stackScriptId;
	}
	
	public UpdateStackScriptRequest setStackScriptId(Integer stackScriptId)
	{
		this.stackScriptId = stackScriptId;
		return this;
	}
	
}
