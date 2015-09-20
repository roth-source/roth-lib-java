package roth.lib.java.api.linode.data.request.delete;

import roth.lib.java.api.linode.data.request.StackScriptIdRequest;

@SuppressWarnings("serial")
public class DeleteStackScriptRequest extends StackScriptIdRequest
{
	
	public DeleteStackScriptRequest(Integer stackScriptId)
	{
		super();
		setStackScriptId(stackScriptId);
	}
	
}
