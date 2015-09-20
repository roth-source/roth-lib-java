package roth.lib.java.api.linode.test;

import roth.lib.java.api.linode.data.request.get.GetAvailKernelRequest;
import roth.lib.java.api.linode.data.request.get.GetAvailPlanRequest;
import roth.lib.java.api.linode.data.request.get.GetAvailStackScriptRequest;

public class UtilityTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getApiSpec();
		getAvailDatacenters();
		//getAvailDistributions();
		//getAvailKernels();
		//getAvailPlans();
		//getAvailNodeBalancers();
		//getAvailStackScripts();
		//testEcho();
		//testFail();
	}
	
	public static void getApiSpec()
	{
		clientFactory.getUtilityClient().getApiSpec();
	}
	
	public static void getAvailDatacenters()
	{
		clientFactory.getUtilityClient().getAvailDatacenters();
	}
	
	public static void getAvailDistributions()
	{
		clientFactory.getUtilityClient().getAvailDistributions();
	}
	
	public static void getAvailKernels()
	{
		clientFactory.getUtilityClient().getAvailKernel(new GetAvailKernelRequest());
	}
	
	public static void getAvailPlans()
	{
		clientFactory.getUtilityClient().getAvailPlan(new GetAvailPlanRequest().setPlanId(4));
	}
	
	public static void getAvailNodeBalancers()
	{
		clientFactory.getUtilityClient().getAvailNodeBalancers();
	}
	
	public static void getAvailStackScripts()
	{
		clientFactory.getUtilityClient().getAvailStackScript(new GetAvailStackScriptRequest().setDistributionId(127).setKeywords("OrientDB"));
	}
	
	public static void testEcho()
	{
		clientFactory.getUtilityClient().testEcho(new GetAvailStackScriptRequest().setDistributionId(127).setKeywords("OrientDB"));
	}
	
	public static void testFail()
	{
		clientFactory.getUtilityClient().testFail();
	}
	
}
