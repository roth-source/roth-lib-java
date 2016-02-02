package roth.lib.java.jdbc;

import java.sql.DriverAction;

public abstract class DbDriverAction implements DriverAction, DbWrapper
{
	protected DriverAction driverAction;
	
	public DbDriverAction(DriverAction driverAction)
	{
		this.driverAction = driverAction;
	}
	
	public void deregister()
	{
		driverAction.deregister();
	}
	
}
