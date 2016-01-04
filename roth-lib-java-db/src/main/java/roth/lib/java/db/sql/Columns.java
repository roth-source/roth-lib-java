package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Columns extends Sql
{
	protected LinkedList<Column> columns = new LinkedList<Column>();
	
	protected Columns()
	{
		
	}
	
	public Columns setColumns(LinkedList<Column> columns)
	{
		this.columns = columns;
		return this;
	}
	
	public Columns addColumns(Column... columns)
	{
		this.columns.addAll(Arrays.asList(columns));
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		String seperator = SELECT;
		for(Column column : columns)
		{
			builder.append(seperator);
			builder.append(column.toString());
			seperator = COLUMN;
		}
		return builder.toString();
	}
	
}
