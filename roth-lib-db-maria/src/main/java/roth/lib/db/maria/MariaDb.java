package roth.lib.db.maria;

import java.util.Properties;

import roth.lib.db.DbReflector;
import roth.lib.db.DbDataSource;

public class MariaDb extends DbDataSource implements MariaDbWrapper
{
	
	public MariaDb()
	{
		super();
	}
	
	public MariaDb(String driver, String url)
	{
		super(driver, url);
	}
	
	public MariaDb(String driver, String url, String username, String password)
	{
		super(driver, url, username, password);
	}
	
	public MariaDb(String driver, String url, Properties properties)
	{
		super(driver, url, properties);
	}
	
	public MariaDb(String driver, String url, String username, String password, Properties properties)
	{
		super(driver, url, username, password, properties);
	}
	
	@Override
	protected DbReflector createDbReflector()
	{
		return new MariaDbAccessor();
	}
	
}
