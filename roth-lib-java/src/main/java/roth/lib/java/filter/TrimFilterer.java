package roth.lib.java.filter;

public class TrimFilterer implements Filterer
{
	
	@Override
	public String filter(String value)
	{
		return value.trim();
	}
	
}
