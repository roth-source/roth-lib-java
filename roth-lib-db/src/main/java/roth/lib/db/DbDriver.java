package roth.lib.db;

import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class DbDriver implements Driver, DbWrapper
{
	protected Driver driver;
	
	public DbDriver(Driver driver)
	{
		this.driver = driver;
	}
	
	@Override
	public DbConnection connect(String url, Properties info) throws SQLException
	{
		return wrap(driver.connect(url, info));
	}
	
	@Override
	public boolean acceptsURL(String url) throws SQLException
	{
		return driver.acceptsURL(url);
	}
	
	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException
	{
		return driver.getPropertyInfo(url, info);
	}
	
	@Override
	public int getMajorVersion()
	{
		return driver.getMajorVersion();
	}
	
	@Override
	public int getMinorVersion()
	{
		return driver.getMinorVersion();
	}
	
	@Override
	public boolean jdbcCompliant()
	{
		return driver.jdbcCompliant();
	}
	
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		return driver.getParentLogger();
	}
	
}
