package roth.lib.service.util;

import javax.naming.InitialContext;

public class InitialContextUtil
{
	protected static String JAVA_COMP 		= "java:comp/";
	protected static String ENV 			= "env/";
	protected static String ENDPOINT_ENV	= "endpoint/env";
	protected static String ENDPOINT_LIST	= "endpoint/list";
	
	protected InitialContextUtil()
	{
		
	}
	
	public static Object lookup(String name)
	{
		Object object = null;
		try
		{
			InitialContext initialContext = new InitialContext();
			object = initialContext.lookup(JAVA_COMP + name);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
	public static String getEnvEntry(String name)
	{
		Object object = lookup(ENV + name);
		return object != null ? String.valueOf(object) : null;
	}
	
	public static Object getResource(String name)
	{
		return lookup(name);
	}
	
	public static String getEnv()
	{
		return getEnvEntry(ENDPOINT_ENV);
	}
	
	public static String[] getEndpoints()
	{
		String[] endpoints = null;
		String endpointList = getEnvEntry(ENDPOINT_LIST);
		if(endpointList != null)
		{
			endpoints = endpointList.split(",");
		}
		return endpoints;
	}
	
}
