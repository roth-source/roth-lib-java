package roth.lib.java.api.digitalocean.test;


public class RegionTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		getRegions();
	}
	
	public static void getRegions()
	{
		clientFactory.getRegionClient().getRegions();
	}
	
}
