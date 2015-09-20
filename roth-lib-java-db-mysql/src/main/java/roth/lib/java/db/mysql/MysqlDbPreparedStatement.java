package roth.lib.java.db.mysql;

import java.sql.PreparedStatement;

import roth.lib.java.db.DbPreparedStatement;

public class MysqlDbPreparedStatement extends DbPreparedStatement implements MysqlDbWrapper
{
	
	public MysqlDbPreparedStatement(PreparedStatement preparedStatement)
	{
		super(preparedStatement);
	}
	
}
