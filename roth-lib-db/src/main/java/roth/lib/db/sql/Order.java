package roth.lib.db.sql;

@SuppressWarnings("serial")
public class Order extends Sql
{
	public static final String ORDER_BY = "  ORDER BY ";
	public static final String ASC 	= " ASC";
	public static final String DESC = " DESC";
	
	protected String sql;
	
	protected Order(String sql)
	{
		this.sql = sql;
	}
	
	public static Order byAsc(String name)
	{
		return new Order(tick(name) + ASC);
	}
	
	public static Order byAsc(String table, String name)
	{
		return new Order(tick(table) + DOT + tick(name) + ASC);
	}
	
	public static Order byDesc(String name)
	{
		return new Order(tick(name) + DESC);
	}
	
	public static Order byDesc(String table, String name)
	{
		return new Order(tick(table) + DOT + tick(name) + DESC);
	}
	
	public static Order bySql(String sql)
	{
		return new Order(sql);
	}
	
	@Override
	public String toString()
	{
		return sql;
	}
	
}
