package roth.lib.java.db.orient.sql;

import roth.lib.java.db.sql.Column;

@SuppressWarnings("serial")
public class OrientColumn extends Column implements OrientSqlFactory
{
	
	public OrientColumn()
	{
		
	}
	
	public static OrientColumn all(String table)
	{
		return (OrientColumn) new OrientColumn().setTable(table).setName(ALL);
	}
	
	public static OrientColumn sql(String sql)
	{
		return (OrientColumn) new OrientColumn().setSql(sql);
	}
	
	public static OrientColumn sqlAs(String sql, String alias)
	{
		return (OrientColumn) new OrientColumn().setSql(sql).setAlias(alias);
	}
	
	public static OrientColumn name(String name)
	{
		return (OrientColumn) new OrientColumn().setName(name);
	}
	
	public static OrientColumn nameAs(String name, String alias)
	{
		return (OrientColumn) new OrientColumn().setName(name).setAlias(alias);
	}
	
	public static OrientColumn tableName(String table, String name)
	{
		return (OrientColumn) new OrientColumn().setTable(table).setName(name);
	}
	
	public static OrientColumn tableNameAs(String table, String name, String alias)
	{
		return (OrientColumn) new OrientColumn().setTable(table).setName(name).setAlias(alias);
	}
	
}
