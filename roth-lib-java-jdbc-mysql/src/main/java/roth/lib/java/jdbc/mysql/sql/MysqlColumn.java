package roth.lib.java.jdbc.mysql.sql;

import roth.lib.java.jdbc.sql.Column;

@SuppressWarnings("serial")
public class MysqlColumn extends Column implements MysqlSqlFactory
{
	
	public MysqlColumn()
	{
		
	}
	
	public static MysqlColumn all(String table)
	{
		return (MysqlColumn) new MysqlColumn().setTable(table).setName(ALL);
	}
	
	public static MysqlColumn sql(String sql)
	{
		return (MysqlColumn) new MysqlColumn().setSql(sql);
	}
	
	public static MysqlColumn sqlAs(String sql, String alias)
	{
		return (MysqlColumn) new MysqlColumn().setSql(sql).setAlias(alias);
	}
	
	public static MysqlColumn name(String name)
	{
		return (MysqlColumn) new MysqlColumn().setName(name);
	}
	
	public static MysqlColumn nameAs(String name, String alias)
	{
		return (MysqlColumn) new MysqlColumn().setName(name).setAlias(alias);
	}
	
	public static MysqlColumn tableName(String table, String name)
	{
		return (MysqlColumn) new MysqlColumn().setTable(table).setName(name);
	}
	
	public static MysqlColumn tableNameAs(String table, String name, String alias)
	{
		return (MysqlColumn) new MysqlColumn().setTable(table).setName(name).setAlias(alias);
	}
	
}
