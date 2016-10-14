package roth.lib.java.test.table;

import roth.lib.java.deserializer.Deserializer;

public class TestDeserializer extends Deserializer<Integer>
{
	
	@Override
	public Integer deserialize(String value)
	{
		return Integer.parseInt(value);
	}
	
}
