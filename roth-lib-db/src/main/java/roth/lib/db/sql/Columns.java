package roth.lib.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Columns extends Sql
{
	protected static final String SELECT = " SELECT ";
	
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
		return SELECT + list(columns);
	}
	
}
