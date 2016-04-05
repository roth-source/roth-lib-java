package roth.lib.java.jdbc.mysql;

import java.sql.ResultSet;

import roth.lib.java.jdbc.JdbcResultSet;

public class MysqlDbResultSet extends JdbcResultSet implements MysqlDbWrapper
{
	
	public MysqlDbResultSet(ResultSet resultSet)
	{
		super(resultSet);
	}
	
}
