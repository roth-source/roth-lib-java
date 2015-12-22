package roth.lib.java.serializer;

public class UnescapedSerializer extends Serializer<Object>
{
	
	public UnescapedSerializer()
	{
		
	}
	
	@Override
	public String serialize(Object object)
	{
		return object != null ? object.toString() : null;
	}
	
}
