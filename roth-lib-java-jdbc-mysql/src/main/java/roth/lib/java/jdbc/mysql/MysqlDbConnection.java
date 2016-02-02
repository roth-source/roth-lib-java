package roth.lib.java.jdbc.mysql;

import java.sql.Connection;

import roth.lib.java.jdbc.DbConnection;

public class MysqlDbConnection extends DbConnection implements MysqlDbWrapper
{
	
	public MysqlDbConnection(Connection connection)
	{
		super(connection);
	}
	
}
