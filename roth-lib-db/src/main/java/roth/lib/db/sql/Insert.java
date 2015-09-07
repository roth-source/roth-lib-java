package roth.lib.db.sql;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class Insert extends Sql
{
	public static final String INSERT = " INSERT INTO ";
	public static final String VALUES = " VALUES ";
	
	protected String table;
	protected LinkedList<String> names;
	protected LinkedList<Object> values;
	
	public Insert(String table, Collection<?> values)
	{
		this.table = table;
		this.values = new LinkedList<Object>(values);
	}
	
	public Insert(String table, Collection<String> names, Collection<?> values)
	{
		if(names.size() != values.size()) throw new IllegalArgumentException("names is different size than values");
		this.table = table;
		this.names = new LinkedList<String>(names);
		this.values = new LinkedList<Object>(values);
	}
	
	public Insert(String table, Map<String, Object> nameValues)
	{
		this.table = table;
		names = new LinkedList<String>();
		values = new LinkedList<Object>();
		for(Entry<String, Object> nameValueEntry : nameValues.entrySet())
		{
			names.add(nameValueEntry.getKey());
			values.add(nameValueEntry.getValue());
		}
	}
	
	public LinkedList<Object> values()
	{
		return new LinkedList<Object>(values);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT + tick(table) + (names != null && !names.isEmpty() ? " (" + tick(names) + ")" : ""));
		builder.append(LF + VALUES + "(" + param(values.size()) + ")");
		builder.append(END);
		return builder.toString();
	}
	
}
