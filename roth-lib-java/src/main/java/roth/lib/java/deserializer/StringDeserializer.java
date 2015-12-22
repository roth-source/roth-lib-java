package roth.lib.java.deserializer;

public class StringDeserializer extends Deserializer<String>
{
	
	public StringDeserializer()
	{
		super();
	}
	
	@Override
	public String deserialize(String value, String timeFormat)
	{
		return value;
	}
	
}
