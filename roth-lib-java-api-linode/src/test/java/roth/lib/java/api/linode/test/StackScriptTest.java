package roth.lib.java.api.linode.test;

import roth.lib.java.api.linode.data.request.create.CreateStackScriptRequest;
import roth.lib.java.api.linode.data.request.delete.DeleteStackScriptRequest;
import roth.lib.java.api.linode.data.request.update.UpdateStackScriptRequest;

public class StackScriptTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getStackScripts();
		//createStackScript();
		//updateStackScript();
		//deleteStackScript();
	}
	
	public static void getStackScripts()
	{
		clientFactory.getStackScriptClient().getStackScripts();
	}
	
	public static void createStackScript()
	{
		clientFactory.getStackScriptClient().createStackScript(new CreateStackScriptRequest("test", "128", "#!/bin/bash"));
	}
	
	public static void updateStackScript()
	{
		clientFactory.getStackScriptClient().updateStackScript(new UpdateStackScriptRequest(9682));
	}
	
	public static void deleteStackScript()
	{
		clientFactory.getStackScriptClient().deleteStackScript(new DeleteStackScriptRequest(9682));
	}
	
}
