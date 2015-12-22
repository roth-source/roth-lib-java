package roth.lib.java.deserializer;

import roth.lib.java.DataUrl;
import roth.lib.java.util.DataUrlUtil;

public class DataUrlDeserializer extends Deserializer<DataUrl>
{
	
	public DataUrlDeserializer()
	{
		
	}
	
	@Override
	public DataUrl deserialize(String value, String timeFormat)
	{
		return DataUrlUtil.parse(value);
	}
	
}
