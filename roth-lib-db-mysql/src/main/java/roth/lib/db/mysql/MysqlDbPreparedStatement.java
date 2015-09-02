package roth.lib.db.mysql;

import java.sql.PreparedStatement;

import roth.lib.db.DbPreparedStatement;

public class MysqlDbPreparedStatement extends DbPreparedStatement implements MysqlDbWrapper
{
	
	public MysqlDbPreparedStatement(PreparedStatement preparedStatement)
	{
		super(preparedStatement);
	}
	
}
