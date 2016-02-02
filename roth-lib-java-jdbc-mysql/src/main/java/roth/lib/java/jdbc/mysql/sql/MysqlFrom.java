package roth.lib.java.jdbc.mysql.sql;

import roth.lib.java.jdbc.sql.From;

@SuppressWarnings("serial")
public class MysqlFrom extends From implements MysqlSqlFactory
{
	protected MysqlIndexHint indexHint;
	
	public MysqlFrom()
	{
		
	}
	
	public MysqlFrom setIndexHint(MysqlIndexHint indexHint)
	{
		this.indexHint = indexHint;
		return this;
	}
	
	public static MysqlFrom name(String name)
	{
		return (MysqlFrom) new MysqlFrom().setName(name);
	}
	
	public static MysqlFrom nameAs(String name, String alias)
	{
		return (MysqlFrom) new MysqlFrom().setName(name);
	}
	
	public static MysqlFrom name(String name, MysqlIndexHint indexHint)
	{
		return (MysqlFrom) new MysqlFrom().setIndexHint(indexHint).setName(name);
	}
	
	public static MysqlFrom nameAs(String name, String alias, MysqlIndexHint indexHint)
	{
		return (MysqlFrom) new MysqlFrom().setIndexHint(indexHint).setName(name).setAlias(alias);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + (indexHint != null ? indexHint.toString() : "");
	}
	
}
