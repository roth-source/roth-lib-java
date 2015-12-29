package roth.lib.java.validate;

public abstract class StringFilterer implements Filterer
{
	
	@Override
	public Object filter(Object value)
	{
		if(value instanceof String)
		{
			value = filterString((String) value);
		}
		return value;
	}
	
	public abstract String filterString(String value);
	
}
