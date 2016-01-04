package roth.lib.java.db.mysql.sql;

import roth.lib.java.db.sql.On;

@SuppressWarnings("serial")
public class MysqlOn extends On implements MysqlSqlFactory
{
	
	public MysqlOn()
	{
		
	}
	
	public static MysqlOn on(String sql, Object...values)
	{
		return (MysqlOn) new MysqlOn().setSql(sql).addValues(values);
	}
	
	public static MysqlOn on(String table1, String name1, String table2, String name2, Object...values)
	{
		return (MysqlOn) new MysqlOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2).addValues(values);
	}
	
}
