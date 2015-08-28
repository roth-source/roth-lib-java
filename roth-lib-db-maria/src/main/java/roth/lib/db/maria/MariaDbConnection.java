package roth.lib.db.maria;

import java.sql.Connection;

import roth.lib.db.DbCloseHandler;
import roth.lib.db.DbConnection;

public class MariaDbConnection extends DbConnection implements MariaDbWrapper
{
	
	public MariaDbConnection(Connection connection)
	{
		super(connection);
	}
	
	public MariaDbConnection(Connection connection, DbCloseHandler closeHandler)
	{
		super(connection, closeHandler);
	}
	
}
