package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Orders extends Sql
{
	protected LinkedList<Order> orders = new LinkedList<Order>();
	
	protected Orders()
	{
		
	}
	
	public Orders setOrders(LinkedList<Order> orders)
	{
		this.orders = orders;
		return this;
	}
	
	public Orders addOrders(Order... orders)
	{
		this.orders.addAll(Arrays.asList(orders));
		return this;
	}
	
	@Override
	public String toString()
	{
		return !orders.isEmpty() ? LF + ORDER_BY + list(orders) : "";
	}
	
}
