package roth.lib.db.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class Orders extends Sql
{
	public static final String ORDER_BY = "  ORDER BY ";
	
	protected LinkedList<Order> orders = new LinkedList<Order>();
	
	public Orders() {}
	
	public void add(Order order)
	{
		orders.add(order);
	}
	
	@Override
	public String toString()
	{
		return !orders.isEmpty() ? LF + ORDER_BY + list(orders) : "";
	}
	
}
