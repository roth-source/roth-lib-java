package roth.lib.java.db.sql;

@SuppressWarnings("serial")
public abstract class Order extends Sql
{
	protected String sql;
	protected String table;
	protected String name;
	protected String orderType = ASC;
	
	protected Order()
	{
		
	}

	public Order setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public Order setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Order setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Order setOrderType(String orderType)
	{
		this.orderType = orderType;
		return this;
	}
	
	@Override
	public String toString()
	{
		if(sql != null)
		{
			return sql;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			if(table != null)
			{
				builder.append(tick(table));
				builder.append(DOT);
			}
			builder.append(tick(name));
			builder.append(orderType);
			return builder.toString();
		}
	}
	
}
