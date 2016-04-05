package roth.lib.java.jdbc.mysql;

import java.sql.DriverAction;

import roth.lib.java.jdbc.JdbcDriverAction;

public class MysqlDbDriverAction extends JdbcDriverAction implements MysqlDbWrapper
{
	
	public MysqlDbDriverAction(DriverAction driverAction)
	{
		super(driverAction);
	}
	
}
