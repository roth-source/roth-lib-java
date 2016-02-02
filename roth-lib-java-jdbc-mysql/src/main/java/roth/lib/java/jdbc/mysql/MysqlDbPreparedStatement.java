package roth.lib.java.jdbc.mysql;

import java.sql.PreparedStatement;

import roth.lib.java.jdbc.DbPreparedStatement;

public class MysqlDbPreparedStatement extends DbPreparedStatement implements MysqlDbWrapper
{
	
	public MysqlDbPreparedStatement(PreparedStatement preparedStatement)
	{
		super(preparedStatement);
	}
	
}
