package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Columns extends Sql
{
	protected static final String SELECT = " SELECT ";
	protected static final String COLUMN = COMMA + LF + "        ";
	
	protected LinkedList<Column> columns = new LinkedList<Column>();
	
	public Columns()
	{
		
	}
	
	public Columns(Column... columns)
	{
		this.columns.addAll(Arrays.asList(columns));
	}
	
	public Columns(String table)
	{
		columns.add(Column.all(table));
	}
	
	public Columns add(Column column)
	{
		columns.add(column);
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
