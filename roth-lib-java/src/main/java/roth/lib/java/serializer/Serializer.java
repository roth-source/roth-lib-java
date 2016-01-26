package roth.lib.java.serializer;

public abstract class Serializer<T>
{
	
	protected Serializer()
	{
		
	}
	
	public boolean isEscapable(Object value, String timeFormat)
	{
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String serialize(Object value, String timeFormat)
	{
		return serializeValue((T) value, timeFormat);
	}
	
	public String serializeValue(T value, String timeFormat)
	{
		return serializeValue((T) value);
	}
	
	public String serializeValue(T value)
	{
		return null;
	}
	
}
