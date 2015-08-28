package roth.lib.db.sql;

@SuppressWarnings("serial")
public class Column extends Sql
{
	protected String sql;
	
	protected Column(String sql)
	{
		this.sql = sql;
	}
	
	public static Column all(String table)
	{
		return new Column(tick(table) + DOT + ALL);
	}
	
	public static Column sql(String sql)
	{
		return new Column(sql);
	}
	
	public static Column sqlAs(String sql, String alias)
	{
		return new Column(sql + AS + tick(alias));
	}
	
	public static Column name(String name)
	{
		return new Column(tick(name));
	}
	
	public static Column nameAs(String name, String alias)
	{
		return new Column(tick(name) + AS + tick(alias));
	}
	
	public static Column tableName(String table, String name)
	{
		return new Column(tick(table) + DOT + tick(name));
	}
	
	public static Column tableNameAs(String table, String name, String alias)
	{
		return new Column(tick(table) + DOT + tick(name) + AS + tick(alias));
	}
	
	@Override
	public String toString()
	{
		return sql;
	}
	
}
