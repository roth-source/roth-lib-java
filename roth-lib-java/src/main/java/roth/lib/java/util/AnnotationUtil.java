package roth.lib.java.util;

import roth.lib.java.lang.List;

public class AnnotationUtil
{
	
	protected AnnotationUtil()
	{
		
	}
	
	public static String validate(String value)
	{
		return value != null && !value.isEmpty() ? value : null;
	}
	
	public static List<String> validate(String[] values)
	{
		return values != null ? new List<String>(values) : new List<String>();
	}
	
}
