package roth.lib.java.deserializer;


public class CharacterDeserializer extends PrimitiveDeserializer<Character>
{
	
	public CharacterDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Character deserialize(String value, String timeFormat)
	{
		Character object = isNullable() ? null : '\u0000';
		try
		{
			object = value.charAt(0);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
