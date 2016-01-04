package roth.lib.java.db.orient;

import java.sql.Savepoint;

import roth.lib.java.db.DbSavepoint;

public class OrientDbSavepoint extends DbSavepoint implements OrientDbWrapper
{
	
	public OrientDbSavepoint(Savepoint savepoint)
	{
		super(savepoint);
	}
	
}
