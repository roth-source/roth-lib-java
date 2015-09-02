package roth.lib.db.mysql;

import java.sql.DriverAction;

import roth.lib.db.DbDriverAction;

public class MysqlDbDriverAction extends DbDriverAction implements MysqlDbWrapper
{
	
	public MysqlDbDriverAction(DriverAction driverAction)
	{
		super(driverAction);
	}
	
}
