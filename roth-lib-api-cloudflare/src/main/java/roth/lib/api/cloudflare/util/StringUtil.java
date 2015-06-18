package roth.lib.api.cloudflare.util;

public class StringUtil
{
	
	protected StringUtil()
	{
		
	}
	
	public static String comma(String[] values)
	{
		StringBuilder builder = new StringBuilder();
		if(values != null && values.length > 0)
		{
			String seperator = "";
			for(String value : values)
			{
				builder.append(seperator);
				builder.append(value);
				if("".equals(seperator))
				{
					seperator = ",";
				}
			}
		}
		return builder.toString();
	}
	
}
