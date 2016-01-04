package roth.lib.java.db.orient;

import java.sql.Connection;

import roth.lib.java.db.DbConnection;

public class OrientDbConnection extends DbConnection implements OrientDbWrapper
{
	
	public OrientDbConnection(Connection connection)
	{
		super(connection);
	}
	
}
