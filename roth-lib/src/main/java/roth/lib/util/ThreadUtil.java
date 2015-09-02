package roth.lib.util;

import java.util.Calendar;

public class ThreadUtil
{
	
	protected ThreadUtil()
	{
		
	}
	
	public static void waitSeconds(int seconds)
	{
		waitSeconds((double) seconds);
	}
	
	public static void waitSeconds(double seconds)
	{
		waitMillis(Math.round(seconds * 1000));
	}
	
	public static void waitMillis(long millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void waitUntil(Calendar time)
	{
		waitUntil(time.getTimeInMillis());
	}
	
	public static void waitUntil(long millis)
	{
		waitMillis(millis - System.currentTimeMillis());
	}
	
}
