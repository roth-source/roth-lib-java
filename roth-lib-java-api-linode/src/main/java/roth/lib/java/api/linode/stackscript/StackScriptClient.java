package roth.lib.java.api.linode.stackscript;

import java.util.LinkedList;

import roth.lib.java.Generic;
import roth.lib.java.api.linode.LinodeClient;
import roth.lib.java.api.linode.data.model.StackScript;
import roth.lib.java.api.linode.data.request.create.CreateStackScriptRequest;
import roth.lib.java.api.linode.data.request.delete.DeleteStackScriptRequest;
import roth.lib.java.api.linode.data.request.get.GetStackScriptRequest;
import roth.lib.java.api.linode.data.request.update.UpdateStackScriptRequest;
import roth.lib.java.api.linode.data.response.LinodeResponse;
import roth.lib.java.api.linode.data.response.StackScriptIdResponse;

public class StackScriptClient extends LinodeClient
{
	
	public StackScriptClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public StackScriptClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public LinodeResponse<LinkedList<StackScript>> getStackScripts()
	{
		return getStackScript(new GetStackScriptRequest());
	}
	
	public LinodeResponse<LinkedList<StackScript>> getStackScript(GetStackScriptRequest getStackScriptRequest)
	{
		return post(url(), getStackScriptRequest.setApiAction(GET_STACK_SCRIPT), new Generic<LinodeResponse<LinkedList<StackScript>>>(){});
	}
	
	public LinodeResponse<StackScriptIdResponse> createStackScript(CreateStackScriptRequest createStackScriptRequest)
	{
		return post(url(), createStackScriptRequest.setApiAction(CREATE_STACK_SCRIPT), new Generic<LinodeResponse<StackScriptIdResponse>>(){});
	}
	
	public LinodeResponse<StackScriptIdResponse> updateStackScript(UpdateStackScriptRequest updateStackScriptRequest)
	{
		return post(url(), updateStackScriptRequest.setApiAction(UPDATE_STACK_SCRIPT), new Generic<LinodeResponse<StackScriptIdResponse>>(){});
	}
	
	public LinodeResponse<StackScriptIdResponse> deleteStackScript(DeleteStackScriptRequest deleteStackScriptRequest)
	{
		return post(url(), deleteStackScriptRequest.setApiAction(DELETE_STACK_SCRIPT), new Generic<LinodeResponse<StackScriptIdResponse>>(){});
	}
	
}
