package roth.lib.java.jdbc.mysql;

import java.sql.DriverAction;

import roth.lib.java.jdbc.DbDriverAction;

public class MysqlDbDriverAction extends DbDriverAction implements MysqlDbWrapper
{
	
	public MysqlDbDriverAction(DriverAction driverAction)
	{
		super(driverAction);
	}
	
}
