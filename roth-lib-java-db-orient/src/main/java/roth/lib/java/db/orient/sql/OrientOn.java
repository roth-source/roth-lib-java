package roth.lib.java.db.orient.sql;

import roth.lib.java.db.sql.On;

@SuppressWarnings("serial")
public class OrientOn extends On implements OrientSqlFactory
{
	
	public OrientOn()
	{
		
	}
	
	public static OrientOn on(String sql, Object...values)
	{
		return (OrientOn) new OrientOn().setSql(sql).addValues(values);
	}
	
	public static OrientOn on(String table1, String name1, String table2, String name2, Object...values)
	{
		return (OrientOn) new OrientOn().setTable1(table1).setName1(name1).setTable2(table2).setName2(name2).addValues(values);
	}
	
}
