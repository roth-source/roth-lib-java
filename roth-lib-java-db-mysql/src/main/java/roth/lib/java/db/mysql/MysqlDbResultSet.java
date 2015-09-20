package roth.lib.java.db.mysql;

import java.sql.ResultSet;

import roth.lib.java.db.DbResultSet;

public class MysqlDbResultSet extends DbResultSet implements MysqlDbWrapper
{
	
	public MysqlDbResultSet(ResultSet resultSet)
	{
		super(resultSet);
	}
	
}
