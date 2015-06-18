package roth.lib.util;

public class ThreadUtil
{
	
	protected ThreadUtil()
	{
		
	}
	
	public static void wait(int seconds)
	{
		wait((double) seconds);
	}
	
	public static void wait(double seconds)
	{
		try
		{
			Thread.sleep(Math.round(seconds * 1000));
		}
		catch(Exception e)
		{
			
		}
	}
	
}
