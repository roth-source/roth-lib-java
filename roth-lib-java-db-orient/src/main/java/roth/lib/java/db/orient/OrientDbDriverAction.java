package roth.lib.java.db.orient;

import java.sql.DriverAction;

import roth.lib.java.db.DbDriverAction;

public class OrientDbDriverAction extends DbDriverAction implements OrientDbWrapper
{
	
	public OrientDbDriverAction(DriverAction driverAction)
	{
		super(driverAction);
	}
	
}
