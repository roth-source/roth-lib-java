package roth.lib.java.map.serializer;

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
