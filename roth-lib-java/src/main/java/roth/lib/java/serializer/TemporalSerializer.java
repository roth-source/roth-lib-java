package roth.lib.java.serializer;

public abstract class TemporalSerializer<T> extends EscapedSerializer<T>
{
	
	protected TemporalSerializer()
	{
		
	}
	
	@Override
	public boolean isEscapable(Object value, String timeFormat)
	{
		return timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat);
	}
	
}
