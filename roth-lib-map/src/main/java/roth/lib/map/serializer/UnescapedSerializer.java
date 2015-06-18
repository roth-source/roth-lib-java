package roth.lib.map.serializer;

import roth.lib.map.Serializer;

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
