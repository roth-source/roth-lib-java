package roth.lib.java.api.digitalocean.test;

import roth.lib.java.api.digitalocean.type.SizeType;

public class DropletActionTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//rebootDroplet();
		//powerCycleDroplet();
		//shutdownDroplet();
		//powerOffDroplet();
		//powerOnDroplet();
		//passwordResetDroplet();
		//resizeDroplet();
		//restoreDroplet();
		//rebuildDroplet();
		//renameDroplet();
		//changeKernel();
		//enableIpv6();
		//disableBackups();
		//enablePrivateNetworking();
		//getDropletAction();
	}
	
	public static void rebootDroplet()
	{
		clientFactory.getDropletActionClient().rebootDroplet(1861975);
	}
	
	public static void powerCycleDroplet()
	{
		clientFactory.getDropletActionClient().powerCycleDroplet(1861975);
	}
	
	public static void shutdownDroplet()
	{
		clientFactory.getDropletActionClient().shutdownDroplet(1861975);
	}
	
	public static void powerOffDroplet()
	{
		clientFactory.getDropletActionClient().powerOffDroplet(1861975);
	}
	
	public static void powerOnDroplet()
	{
		clientFactory.getDropletActionClient().powerOnDroplet(1861975);
	}
	
	public static void passwordResetDroplet()
	{
		clientFactory.getDropletActionClient().passwordResetDroplet(1861975);
	}
	
	public static void resizeDroplet()
	{
		clientFactory.getDropletActionClient().resizeDroplet(1861975, SizeType._512MB);
	}
	
	public static void restoreDroplet()
	{
		clientFactory.getDropletActionClient().restoreDroplet(1861975, 123);
	}
	
	public static void rebuildDroplet()
	{
		clientFactory.getDropletActionClient().rebuildDroplet(1861975, 123);
	}
	
	public static void renameDroplet()
	{
		clientFactory.getDropletActionClient().renameDroplet(1861975, "ubuntu-test");
	}
	
	public static void changeKernel()
	{
		clientFactory.getDropletActionClient().changeKernel(1861975, 123);
	}
	
	public static void enableIpv6()
	{
		clientFactory.getDropletActionClient().enableIpv6(1861975);
	}
	
	public static void disableBackups()
	{
		clientFactory.getDropletActionClient().disableBackups(1861975);
	}
	
	public static void enablePrivateNetworking()
	{
		clientFactory.getDropletActionClient().enablePrivateNetworking(1861975);
	}
	
	public static void getDropletAction()
	{
		clientFactory.getDropletActionClient().getDropletAction(1861975, 1);
	}
	
}
