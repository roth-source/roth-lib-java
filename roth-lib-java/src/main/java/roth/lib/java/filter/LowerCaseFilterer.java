package roth.lib.java.filter;

public class LowerCaseFilterer implements Filterer
{
	
	@Override
	public String filter(String value)
	{
		return value.toLowerCase();
	}
	
}
