package roth.lib.java.db;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;
import roth.lib.java.db.sql.Sql;

public class DbNamedQuery implements Characters
{
	protected static Pattern NAMED_PATTERN = Pattern.compile(":(\\w+)");
	
	protected String sql = "";
	protected LinkedList<Object> values = new LinkedList<Object>();
	
	public DbNamedQuery(String sql, Map<String, Object> valueMap)
	{
		StringBuilder builder = new StringBuilder();
		int start = 0;
		Matcher namedMatcher = NAMED_PATTERN.matcher(sql);
		while(namedMatcher.find())
		{
			builder.append(sql.substring(start, namedMatcher.start()));
			String name = namedMatcher.group(1);
			Object value = valueMap.get(name);
			values.add(value);
			if(value instanceof Collection)
			{
				int length = ((Collection<?>) value).size();
				if(length > 0)
				{
					builder.append(Sql.param(length));
				}
			}
			else if(value instanceof Object[])
			{
				int length = ((Object[]) value).length;
				if(length > 0)
				{
					builder.append(Sql.param(length));
				}
			}
			else
			{
				builder.append(QUESTION);
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
	
	public LinkedList<Object> getValues()
	{
		return values;
	}
	
}
