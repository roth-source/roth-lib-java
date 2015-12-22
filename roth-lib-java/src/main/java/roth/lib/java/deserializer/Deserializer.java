package roth.lib.java.deserializer;

public abstract class Deserializer<T>
{
	
	protected Deserializer()
	{
		
	}
	
	public T deserialize(String value, String timeFormat, Class<?> klass)
	{
		return deserialize(value, timeFormat);
	}
	
	public abstract T deserialize(String value, String timeFormat);
	
}
