package roth.lib.java.db.orient.sql;

import roth.lib.java.db.sql.Group;

@SuppressWarnings("serial")
public class OrientGroup extends Group implements OrientSqlFactory
{
	
	public OrientGroup()
	{
		
	}
	
	public static OrientGroup byName(String name)
	{
		return (OrientGroup) new OrientGroup().setName(name);
	}
	
	public static OrientGroup byTableName(String table, String name)
	{
		return (OrientGroup) new OrientGroup().setTable(table).setName(name);
	}
	
	public static OrientGroup bySql(String sql)
	{
		return (OrientGroup) new OrientGroup().setSql(sql);
	}
	
}
