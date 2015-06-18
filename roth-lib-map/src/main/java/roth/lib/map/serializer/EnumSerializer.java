package roth.lib.map.serializer;

public class EnumSerializer extends EscapedSerializer<Enum<?>>
{

	public EnumSerializer()
	{
		
	}
	
	@Override
	public String serialize(Enum<?> object)
	{
		return object != null ? object.toString() : null;
	}
	
}
