package roth.lib.java.db.mysql;

import java.util.Properties;

import roth.lib.java.db.DbDataSource;
import roth.lib.java.db.mysql.sql.MysqlSqlFactory;
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
	
}
