package roth.lib.db.maria;

import java.sql.NClob;

import roth.lib.db.DbNClob;

public class MariaDbNClob extends DbNClob implements MariaDbWrapper
{
	
	public MariaDbNClob(NClob nClob)
	{
		super(nClob);
	}
	
}
