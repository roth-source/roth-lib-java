package roth.lib.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		return values != null ? Arrays.asList(values) : new ArrayList<String>();
	}
	
}
