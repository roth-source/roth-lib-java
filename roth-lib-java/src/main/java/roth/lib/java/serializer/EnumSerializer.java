package roth.lib.java.serializer;

public class EnumSerializer extends EscapedSerializer<Enum<?>>
{

	public EnumSerializer()
	{
		
	}
	
	@Override
	public String serializeValue(Enum<?> object)
	{
		return object != null ? object.toString() : null;
	}
	
}
