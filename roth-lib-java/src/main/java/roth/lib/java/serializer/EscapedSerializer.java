package roth.lib.java.serializer;

public class EscapedSerializer<T> extends Serializer<T>
{
	
	public EscapedSerializer()
	{
		
	}
	
	@Override
	public boolean isEscapable(Object value, String timeFormat)
	{
		return true;
	}
	
	@Override
	public String serializeValue(T object)
	{
		return object != null ? object.toString() : null;
	}
	
}
