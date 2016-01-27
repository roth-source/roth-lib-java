package roth.lib.java.db.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Columns extends Sql
{
	protected List<Column> columns = new List<Column>();
	
	protected Columns()
	{
		
	}
	
	public Columns setColumns(List<Column> columns)
	{
		this.columns = columns;
		return this;
	}
	
	public Columns addColumns(Column... columns)
	{
		this.columns.addAll(columns);
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
