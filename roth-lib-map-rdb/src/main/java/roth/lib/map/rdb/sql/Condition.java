package roth.lib.map.rdb.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Condition extends Sql
{
	protected Logic logic;
	
	protected Condition() {}
	
	public abstract LinkedList<Object> values();
	public abstract String toString();
	public abstract String toStringNested();
	
	public static enum Op
	{
		EQ			(" = ?"),
		NE			(" != ?"),
		LT			(" < ?"),
		GT			(" > ?"),
		LE			(" <= ?"),
		GE			(" >= ?"),
		IN			(" IN (%s)"),
		NOT_IN		(" NOT IN (%s)"),
		LIKE		(" LIKE ?"),
		NOT_LIKE	(" NOT LIKE ?"),
		BETWEEN		(" BETWEEN ? AND ?"),
		IS_NULL		(" IS NULL"),
		IS_NOT_NULL	(" IS NOT NULL"),
		;
		
		final String value;
		
		Op(String value)
		{
			this.value = value;
		}
		
		public String get()
		{
			return value;
		}
	}
	
	public static enum Logic
	{
		AND		("    AND "),
		OR		("     OR "),
		;
		
		final String value;
		
		Logic(String value)
		{
			this.value = value;
		}
		
		public String get()
		{
			return value;
		}
	}
	
}
