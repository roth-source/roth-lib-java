package roth.lib.java.jdbc.mysql;

import java.sql.ResultSet;

import roth.lib.java.jdbc.DbResultSet;

public class MysqlDbResultSet extends DbResultSet implements MysqlDbWrapper
{
	
	public MysqlDbResultSet(ResultSet resultSet)
	{
		super(resultSet);
	}
	
}
