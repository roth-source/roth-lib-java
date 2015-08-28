package roth.lib.db.maria;

import java.sql.Clob;

import roth.lib.db.DbClob;

public class MariaDbClob extends DbClob implements MariaDbWrapper
{
	
	public MariaDbClob(Clob clob)
	{
		super(clob);
	}
	
}
