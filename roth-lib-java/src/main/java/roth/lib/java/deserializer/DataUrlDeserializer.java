package roth.lib.java.deserializer;

import roth.lib.java.DataUrl;
import roth.lib.java.util.DataUrlUtil;

public class DataUrlDeserializer extends Deserializer<DataUrl>
{
	
	public DataUrlDeserializer()
	{
		
	}
	
	@Override
	public DataUrl deserialize(String value)
	{
		return DataUrlUtil.parse(value);
	}
	
}
