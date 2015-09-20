package roth.lib.java.db.mysql;

import java.sql.Driver;

import roth.lib.java.db.DbDriver;

public class MysqlDbDriver extends DbDriver implements MysqlDbWrapper
{

	public MysqlDbDriver(Driver driver)
	{
		super(driver);
	}
	
}
