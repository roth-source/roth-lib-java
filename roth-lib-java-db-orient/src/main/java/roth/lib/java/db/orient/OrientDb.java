package roth.lib.java.db.orient;

import java.util.Properties;

import roth.lib.java.db.DbDataSource;
import roth.lib.java.db.orient.sql.OrientSqlFactory;
import roth.lib.java.mapper.MapperType;

public class OrientDb extends DbDataSource implements OrientDbWrapper, OrientSqlFactory
{
	
	public OrientDb()
	{
		super(MapperType.ORIENT);
	}
	
	public OrientDb(String driver, String url)
	{
		super(MapperType.ORIENT, driver, url);
	}
	
	public OrientDb(String driver, String url, String username, String password)
	{
		super(MapperType.ORIENT, driver, url, username, password);
	}
	
	public OrientDb(String driver, String url, Properties properties)
	{
		super(MapperType.ORIENT, driver, url, properties);
	}
	
	public OrientDb(String driver, String url, String username, String password, Properties properties)
	{
		super(MapperType.ORIENT, driver, url, username, password, properties);
	}
	
}
