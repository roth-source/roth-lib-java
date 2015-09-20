package roth.lib.java.api.digitalocean.test;

import roth.lib.java.api.digitalocean.droplet.CreateDropletRequest;
import roth.lib.java.api.digitalocean.type.RegionType;
import roth.lib.java.api.digitalocean.type.SizeType;

public class DropletTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getDroplets();
		//getDroplet();
		//getKernels();
		//getSnapshots();
		//getBackups();
		//getActions();
		//createDroplet();
		//deleteDroplet();
	}
	
	public static void getDroplets()
	{
		clientFactory.getDropletClient().getDroplets(1);
	}
	
	public static void getDroplet()
	{
		clientFactory.getDropletClient().getDroplet(1861975);
	}
	
	public static void getKernels()
	{
		clientFactory.getDropletClient().getKernels(1861975);
	}
	
	public static void getSnapshots()
	{
		clientFactory.getDropletClient().getSnapshots(1861975);
	}
	
	public static void getBackups()
	{
		clientFactory.getDropletClient().getBackups(1861975);
	}
	
	public static void getActions()
	{
		clientFactory.getDropletClient().getActions(1861975);
	}
	
	public static void createDroplet()
	{
		clientFactory.getDropletClient().createDroplet(new CreateDropletRequest("ubuntu-test2", RegionType.NEW_YORK_2, SizeType._512MB, "3240036"));
	}
	
	public static void deleteDroplet()
	{
		clientFactory.getDropletClient().deleteDroplet(2018158);
	}
	
}
