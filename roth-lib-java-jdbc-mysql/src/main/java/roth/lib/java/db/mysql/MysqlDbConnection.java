package roth.lib.java.db.mysql;

import java.sql.Connection;

import roth.lib.java.db.DbConnection;

public class MysqlDbConnection extends DbConnection implements MysqlDbWrapper
{
	
	public MysqlDbConnection(Connection connection)
	{
		super(connection);
	}
	
}
