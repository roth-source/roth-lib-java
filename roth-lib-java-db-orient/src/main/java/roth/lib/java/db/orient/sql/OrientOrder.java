package roth.lib.java.db.orient.sql;

import roth.lib.java.db.sql.Order;

@SuppressWarnings("serial")
public class OrientOrder extends Order implements OrientSqlFactory
{
	
	public OrientOrder()
	{
		
	}
	
	public static OrientOrder byAsc(String name)
	{
		return (OrientOrder) new OrientOrder().setName(name).setOrderType(ASC);
	}
	
	public static OrientOrder byAsc(String table, String name)
	{
		return (OrientOrder) new OrientOrder().setTable(table).setName(name).setOrderType(ASC);
	}
	
	public static OrientOrder byDesc(String name)
	{
		return (OrientOrder) new OrientOrder().setName(name).setOrderType(DESC);
	}
	
	public static OrientOrder byDesc(String table, String name)
	{
		return (OrientOrder) new OrientOrder().setTable(table).setName(name).setOrderType(DESC);
	}
	
	public static OrientOrder bySql(String sql)
	{
		return (OrientOrder) new OrientOrder().setSql(sql);
	}
	
}
