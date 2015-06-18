package roth.lib.map.rdb.sql;

@SuppressWarnings("serial")
public class Group extends Sql
{
	private String sql;
	
	protected Group(String sql)
	{
		this.sql = sql;
	}
	
	public static Group byName(String name)
	{
		return new Group(tick(name));
	}
	
	public static Group byTableName(String table, String name)
	{
		return new Group(tick(table) + DOT + tick(name));
	}
	
	public static Group bySql(String sql)
	{
		return new Group(sql);
	}
	
	@Override
	public String toString()
	{
		return sql;
	}
	
}
