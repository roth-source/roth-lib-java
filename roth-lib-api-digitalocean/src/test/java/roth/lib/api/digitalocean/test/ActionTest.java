package roth.lib.api.digitalocean.test;


public class ActionTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getActions();
		//getAction();
	}
	
	public static void getActions()
	{
		clientFactory.getActionClient().getActions(1);
	}
	
	public static void getAction()
	{
		clientFactory.getActionClient().getAction(27918013);
	}
	
}
