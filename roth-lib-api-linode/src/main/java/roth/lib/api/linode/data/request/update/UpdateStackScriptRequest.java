package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.StackScriptRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

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
