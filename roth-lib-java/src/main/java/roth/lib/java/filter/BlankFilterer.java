package roth.lib.java.filter;

public class BlankFilterer implements Filterer
{
	
	@Override
	public String filter(String value)
	{
		return !value.trim().isEmpty() ? value : null;
	}
	
}
