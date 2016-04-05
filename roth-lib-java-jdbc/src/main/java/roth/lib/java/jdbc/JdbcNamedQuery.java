package roth.lib.java.jdbc;

import java.util.Arrays;
import java.util.Collection;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;

public class JdbcNamedQuery implements Characters
{
	protected static Pattern NAMED_PATTERN = Pattern.compile(":(\\w+)");
	
	protected String sql = "";
	protected List<Object> values = new List<Object>().allowNull();
	
	public JdbcNamedQuery(String sql, Map<String, Object> valueMap)
	{
		StringBuilder builder = new StringBuilder();
		int start = 0;
		Matcher namedMatcher = NAMED_PATTERN.matcher(sql);
		while(namedMatcher.find())
		{
			builder.append(sql.substring(start, namedMatcher.start()));
			String name = namedMatcher.group(1);
			Object value = valueMap.get(name);
			if(value instanceof Collection || value instanceof Object[])
			{
				Collection<?> collection = null;
				if(value instanceof Collection)
				{
					collection = (Collection<?>) value;
				}
				else if(value instanceof Object[])
				{
					collection = Arrays.asList((Object[]) value);
				}
				if(collection != null)
				{
					int length = collection.size();
					if(length > 0)
					{
						String seperator = "";
						for(int i = 0; i < length; i++)
						{
							builder.append(seperator);
							builder.append(QUESTION);
							seperator = String.valueOf(COMMA);
						}
						values.addAll(collection);
					}
				}
			}
			else
			{
				builder.append(QUESTION);
				values.add(value);
			}
			start = namedMatcher.end() + 1;
		}
		builder.append(sql.substring(start));
		sql = builder.toString();
	}
	
	public String getSql()
	{
		return sql;
	}
	
	public List<Object> getValues()
	{
		return values;
	}
	
}
