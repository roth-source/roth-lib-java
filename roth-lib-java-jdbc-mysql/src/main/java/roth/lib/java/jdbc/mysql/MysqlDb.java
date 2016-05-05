package roth.lib.java.jdbc.mysql;

import java.util.Properties;

import roth.lib.java.jdbc.Jdbc;
import roth.lib.java.jdbc.mysql.sql.MysqlSqlFactory;
import roth.lib.java.mapper.MapperType;

public class MysqlDb extends Jdbc implements MysqlDbWrapper, MysqlSqlFactory
{
	
	public MysqlDb()
	{
		super(MapperType.MYSQL);
	}
	
	public MysqlDb(String driver, String url)
	{
		super(MapperType.MYSQL, driver, url);
	}
	
	public MysqlDb(String driver, String url, String username, String password)
	{
		super(MapperType.MYSQL, driver, url, username, password);
	}
	
	public MysqlDb(String driver, String url, Properties properties)
	{
		super(MapperType.MYSQL, driver, url, properties);
	}
	
	public MysqlDb(String driver, String url, String username, String password, Properties properties)
	{
		super(MapperType.MYSQL, driver, url, username, password, properties);
	}
	
	@Override
	protected boolean isDeadLockException(Exception e)
	{
		boolean deadLockExcepione = false;
		try
		{
			if(e.getClass().equals(Class.forName("com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException")))
			{
				deadLockExcepione = true;
			}
			else if(e.getClass().equals(Class.forName("com.mysql.jdbc.exceptions.MySQLTransactionRollbackException")))
			{
				deadLockExcepione = true;
			}
		}
		catch(Throwable t)
		{
			
		}
		return deadLockExcepione;
	}
	
}
