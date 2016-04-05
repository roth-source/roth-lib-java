package roth.lib.java.jdbc.mysql;

import java.sql.Driver;

import roth.lib.java.jdbc.JdbcDriver;

public class MysqlDbDriver extends JdbcDriver implements MysqlDbWrapper
{

	public MysqlDbDriver(Driver driver)
	{
		super(driver);
	}
	
}
