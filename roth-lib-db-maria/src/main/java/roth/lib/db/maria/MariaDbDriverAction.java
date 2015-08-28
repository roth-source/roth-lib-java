package roth.lib.db.maria;

import java.sql.DriverAction;

import roth.lib.db.DbDriverAction;

public class MariaDbDriverAction extends DbDriverAction implements MariaDbWrapper
{
	
	public MariaDbDriverAction(DriverAction driverAction)
	{
		super(driverAction);
	}
	
}
