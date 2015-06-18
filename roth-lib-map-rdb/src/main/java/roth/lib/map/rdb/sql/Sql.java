package roth.lib.map.rdb.sql;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Sql implements Serializable
{
	public static final String LF = "\n";
	public static final String END = ";";
	public static final String DOT = ".";
	public static final String ALL = "*";
	public static final String TICK = "`";
	public static final String COMMA = ", ";
	public static final String PARAM = " = ?";
	public static final String AS = " AS ";
	
	protected Sql() {}
	
	public static String tick(String value)
	{
		if(value != null && !value.isEmpty())
		{
			value = value.trim();
			if(!value.startsWith(TICK))
			{
				value = TICK + value;
			}
			if(!value.endsWith(TICK))
			{
				value = value + TICK;
			}
		}
		return value;
	}
	
	public static String tick(Collection<String> values)
	{
		StringBuilder builder = new StringBuilder();
		String comma = "";
		for(String value : values)
		{
			builder.append(comma);
			builder.append(tick(value));
			comma = COMMA;
		}
		return builder.toString();
	}
	
	public static String list(Collection<?> values)
	{
		return list(values, COMMA);
	}
	
	public static String list(Collection<?> values, String delimiter)
	{
		StringBuilder builder = new StringBuilder();
		String currentDelimiter = "";
		for(Object value : values)
		{
			builder.append(currentDelimiter);
			builder.append(value.toString());
			currentDelimiter = delimiter;
		}
		return builder.toString();
	}
	
	public static String param(int amount)
	{
		StringBuilder params = new StringBuilder();
		String comma = "";
		for(int i = 0; i < amount; i++)
		{
			params.append(comma + "?");
			comma = COMMA;
		}
		return params.toString();
	}
	
	public static String param(Collection<String> names)
	{
		StringBuilder builder = new StringBuilder();
		String comma = "";
		for(String name : names)
		{
			builder.append(comma);
			builder.append(tick(name));
			builder.append(PARAM);
			comma = COMMA;
		}
		return builder.toString();
	}
	
	public static LinkedList<Object> values(Object...values)
	{
		return new LinkedList<Object>(Arrays.asList(values));
	}
	
	public LinkedList<Object> values()
	{
		return new LinkedList<Object>();
	}
	
	public String sql()
	{
		return toString();
	}
	
}
