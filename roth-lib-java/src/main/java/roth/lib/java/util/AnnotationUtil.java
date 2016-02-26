package roth.lib.java.util;

public class AnnotationUtil
{
	
	protected AnnotationUtil()
	{
		
	}
	
	public static String validate(String value)
	{
		return value != null && !value.isEmpty() ? value : null;
	}
	
}
