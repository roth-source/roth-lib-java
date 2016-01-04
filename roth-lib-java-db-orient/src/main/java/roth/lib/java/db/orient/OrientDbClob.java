package roth.lib.java.db.orient;

import java.sql.Clob;

import roth.lib.java.db.DbClob;

public class OrientDbClob extends DbClob implements OrientDbWrapper
{
	
	public OrientDbClob(Clob clob)
	{
		super(clob);
	}
	
}
