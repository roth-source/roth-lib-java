package roth.lib.java.db.orient;

import java.sql.Driver;

import roth.lib.java.db.DbDriver;

public class OrientDbDriver extends DbDriver implements OrientDbWrapper
{

	public OrientDbDriver(Driver driver)
	{
		super(driver);
	}
	
}
