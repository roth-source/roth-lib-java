package roth.lib.db.mysql;

import java.sql.Driver;

import roth.lib.db.DbDriver;

public class MysqlDbDriver extends DbDriver implements MysqlDbWrapper
{

	public MysqlDbDriver(Driver driver)
	{
		super(driver);
	}
	
}
