package roth.lib.map.serializer;

public abstract class Serializer<T>
{
	
	protected Serializer()
	{
		
	}
	
	public boolean isEscapable()
	{
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String serialize(Object value, String timeFormat)
	{
		return serialize((T) value);
	}
	
	public abstract String serialize(T value);
	
}
