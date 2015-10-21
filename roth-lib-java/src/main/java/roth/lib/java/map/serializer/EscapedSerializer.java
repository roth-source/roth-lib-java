package roth.lib.java.map.serializer;

public class EscapedSerializer<T> extends Serializer<T>
{
	
	public EscapedSerializer()
	{
		
	}
	
	@Override
	public boolean isEscapable()
	{
		return true;
	}
	
	@Override
	public String serialize(T object)
	{
		return object != null ? object.toString() : null;
	}
	
}
