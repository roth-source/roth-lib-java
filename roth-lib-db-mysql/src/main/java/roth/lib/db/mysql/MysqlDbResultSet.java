package roth.lib.db.mysql;

import java.sql.ResultSet;

import roth.lib.db.DbResultSet;

public class MysqlDbResultSet extends DbResultSet implements MysqlDbWrapper
{
	
	public MysqlDbResultSet(ResultSet resultSet)
	{
		super(resultSet);
	}
	
}
