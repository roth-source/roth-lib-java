package roth.lib.java.filter;

public class UpperCaseFilterer implements Filterer
{
	
	@Override
	public String filter(String value)
	{
		return value.toUpperCase();
	}
	
}
