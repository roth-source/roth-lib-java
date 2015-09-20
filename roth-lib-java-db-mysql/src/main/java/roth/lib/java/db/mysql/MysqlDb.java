package roth.lib.java.db.mysql;

import java.util.Properties;

import roth.lib.java.db.DbDataSource;
import roth.lib.java.db.DbReflector;

public class MysqlDb extends DbDataSource implements MysqlDbWrapper
{
	
	public MysqlDb()
	{
		super();
	}
	
	public MysqlDb(String driver, String url)
	{
		super(driver, url);
	}
	
	public MysqlDb(String driver, String url, String username, String password)
	{
		super(driver, url, username, password);
	}
	
	public MysqlDb(String driver, String url, Properties properties)
	{
		super(driver, url, properties);
	}
	
	public MysqlDb(String driver, String url, String username, String password, Properties properties)
	{
		super(driver, url, username, password, properties);
	}
	
	@Override
	protected DbReflector createDbReflector()
	{
		return new MysqlDbAccessor();
	}
	
}
