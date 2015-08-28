package roth.lib.db.maria;

import java.sql.Savepoint;

import roth.lib.db.DbSavepoint;

public class MariaDbSavepoint extends DbSavepoint implements MariaDbWrapper
{
	
	public MariaDbSavepoint(Savepoint savepoint)
	{
		super(savepoint);
	}
	
}
