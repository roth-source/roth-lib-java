package roth.lib.java.filter;

public class NumberFilterer implements Filterer
{
	public static final String NUMBER_FILTER = "[^0-9\\-\\.]";
	
	@Override
	public String filter(String value)
	{
		return value.replaceAll(NUMBER_FILTER, "");
	}
	
}
