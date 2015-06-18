package roth.lib.map.rdb.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class Orders extends Sql
{
	public static final String ORDER_BY = "  ORDER BY ";
	
	private LinkedList<Order> orders = new LinkedList<Order>();
	
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
