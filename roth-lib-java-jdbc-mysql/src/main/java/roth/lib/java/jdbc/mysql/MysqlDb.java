package roth.lib.java.jdbc.mysql;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Properties;

import roth.lib.java.jdbc.DbConnection;
import roth.lib.java.jdbc.DbDataSource;
import roth.lib.java.jdbc.mysql.sql.MysqlSqlFactory;
import roth.lib.java.lang.List;
import roth.lib.java.mapper.MapperType;

public class MysqlDb extends DbDataSource implements MysqlDbWrapper, MysqlSqlFactory
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
		if(e instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException)
		{
			deadLockExcepione = true;
		}
		else if(e instanceof com.mysql.jdbc.exceptions.MySQLTransactionRollbackException)
		{
			deadLockExcepione = true;
		}
		return deadLockExcepione;
	}
	
	@Override
	protected void setTimeZone(DbConnection connection)
	{
		try
		{
			String timeZone = ZonedDateTime.now().getOffset().toString();
			execute("SET time_zone = ?", new List<Object>(timeZone), connection);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
