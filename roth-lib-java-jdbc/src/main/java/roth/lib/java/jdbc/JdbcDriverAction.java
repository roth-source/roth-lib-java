package roth.lib.java.jdbc;

import java.sql.DriverAction;

public abstract class JdbcDriverAction implements DriverAction, JdbcWrapper
{
	protected DriverAction driverAction;
	
	public JdbcDriverAction(DriverAction driverAction)
	{
		this.driverAction = driverAction;
	}
	
	public void deregister()
	{
		driverAction.deregister();
	}
	
}
