package roth.lib.java.jdbc.mysql;

import java.sql.Connection;

import roth.lib.java.jdbc.JdbcConnection;

public class MysqlDbConnection extends JdbcConnection implements MysqlDbWrapper
{
	
	public MysqlDbConnection(Connection connection)
	{
		super(connection);
	}
	
}
