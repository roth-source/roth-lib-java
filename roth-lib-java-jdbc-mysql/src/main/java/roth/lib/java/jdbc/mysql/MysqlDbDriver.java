package roth.lib.java.jdbc.mysql;

import java.sql.Driver;

import roth.lib.java.jdbc.DbDriver;

public class MysqlDbDriver extends DbDriver implements MysqlDbWrapper
{

	public MysqlDbDriver(Driver driver)
	{
		super(driver);
	}
	
}
