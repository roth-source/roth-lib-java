package roth.lib.java.db.mysql.sql;

import roth.lib.java.db.sql.Order;

@SuppressWarnings("serial")
public class MysqlOrder extends Order implements MysqlSqlFactory
{
	
	public MysqlOrder()
	{
		
	}
	
	public static MysqlOrder byAsc(String name)
	{
		return (MysqlOrder) new MysqlOrder().setName(name).setOrderType(ASC);
	}
	
	public static MysqlOrder byAsc(String table, String name)
	{
		return (MysqlOrder) new MysqlOrder().setTable(table).setName(name).setOrderType(ASC);
	}
	
	public static MysqlOrder byDesc(String name)
	{
		return (MysqlOrder) new MysqlOrder().setName(name).setOrderType(DESC);
	}
	
	public static MysqlOrder byDesc(String table, String name)
	{
		return (MysqlOrder) new MysqlOrder().setTable(table).setName(name).setOrderType(DESC);
	}
	
	public static MysqlOrder bySql(String sql)
	{
		return (MysqlOrder) new MysqlOrder().setSql(sql);
	}
	
}
