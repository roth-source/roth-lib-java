package roth.lib.java.db.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Orders extends Sql
{
	protected List<Order> orders = new List<Order>();
	
	protected Orders()
	{
		
	}
	
	public Orders setOrders(List<Order> orders)
	{
		this.orders = orders;
		return this;
	}
	
	public Orders addOrders(Order... orders)
	{
		this.orders.array(orders);
		return this;
	}
	
	@Override
	public String toString()
	{
		return !orders.isEmpty() ? LF + ORDER_BY + list(orders) : "";
	}
	
}
