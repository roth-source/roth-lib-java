package roth.lib.java.api.digitalocean.test;


public class SizeTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		getSizes();
	}
	
	public static void getSizes()
	{
		clientFactory.getSizeClient().getSizes();
	}
	
}
