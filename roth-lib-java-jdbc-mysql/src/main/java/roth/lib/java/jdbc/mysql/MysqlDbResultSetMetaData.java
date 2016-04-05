package roth.lib.java.jdbc.mysql;

import java.sql.ResultSetMetaData;

import roth.lib.java.jdbc.JdbcResultSetMetaData;

public class MysqlDbResultSetMetaData extends JdbcResultSetMetaData implements MysqlDbWrapper
{
	
	public MysqlDbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		super(resultSetMetaData);
	}
	
}
