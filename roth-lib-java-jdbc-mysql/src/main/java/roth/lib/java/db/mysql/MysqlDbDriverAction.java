package roth.lib.java.db.mysql;

import java.sql.DriverAction;

import roth.lib.java.db.DbDriverAction;

public class MysqlDbDriverAction extends DbDriverAction implements MysqlDbWrapper
{
	
	public MysqlDbDriverAction(DriverAction driverAction)
	{
		super(driverAction);
	}
	
}
