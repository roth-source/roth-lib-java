package roth.lib.db.mysql;

import java.sql.Connection;

import roth.lib.db.DbConnection;

public class MysqlDbConnection extends DbConnection implements MysqlDbWrapper
{
	
	public MysqlDbConnection(Connection connection)
	{
		super(connection);
	}
	
}
