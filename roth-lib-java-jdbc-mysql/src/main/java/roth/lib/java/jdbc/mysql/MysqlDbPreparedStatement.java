package roth.lib.java.jdbc.mysql;

import java.sql.PreparedStatement;

import roth.lib.java.jdbc.JdbcPreparedStatement;

public class MysqlDbPreparedStatement extends JdbcPreparedStatement implements MysqlDbWrapper
{
	
	public MysqlDbPreparedStatement(PreparedStatement preparedStatement)
	{
		super(preparedStatement);
	}
	
}
