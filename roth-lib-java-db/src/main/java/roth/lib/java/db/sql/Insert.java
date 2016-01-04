package roth.lib.java.db.sql;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public abstract class Insert extends Values
{
	protected String table;
	protected LinkedList<String> names =  new LinkedList<String>();
	
	public Insert()
	{
		
	}

	public Insert setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Insert setValues(Collection<Object> values)
	{
		this.values = new LinkedList<Object>(values);
		return this;
	}
	
	public Insert setNameValues(Map<String, Object> nameValues)
	{
		names = new LinkedList<String>();
		values = new LinkedList<Object>();
		for(Entry<String, Object> nameValueEntry : nameValues.entrySet())
		{
			names.add(nameValueEntry.getKey());
			values.add(nameValueEntry.getValue());
		}
		return this;
	}
	
	public Insert setNameValues(Collection<String> names, Collection<?> values)
	{
		if(names.size() != values.size())
		{
			throw new IllegalArgumentException("names is different size than values");
		}
		this.names = new LinkedList<String>(names);
		this.values = new LinkedList<Object>(values);
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(INSERT + tick(table) + " (" + tick(names) + ")");
		builder.append(LF + VALUES + "(" + param(values.size()) + ")");
		builder.append(END);
		return builder.toString();
	}
	
}
