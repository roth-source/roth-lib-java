package roth.lib.db.maria;

import java.sql.Driver;

import roth.lib.db.DbDriver;

public class MariaDbDriver extends DbDriver implements MariaDbWrapper
{

	public MariaDbDriver(Driver driver)
	{
		super(driver);
	}
	
}
