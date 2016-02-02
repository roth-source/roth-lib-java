package roth.lib.java.jdbc.mysql.sql;

import roth.lib.java.jdbc.sql.Group;

@SuppressWarnings("serial")
public class MysqlGroup extends Group implements MysqlSqlFactory
{
	
	public MysqlGroup()
	{
		
	}
	
	public static MysqlGroup byName(String name)
	{
		return (MysqlGroup) new MysqlGroup().setName(name);
	}
	
	public static MysqlGroup byTableName(String table, String name)
	{
		return (MysqlGroup) new MysqlGroup().setTable(table).setName(name);
	}
	
	public static MysqlGroup bySql(String sql)
	{
		return (MysqlGroup) new MysqlGroup().setSql(sql);
	}
	
}
