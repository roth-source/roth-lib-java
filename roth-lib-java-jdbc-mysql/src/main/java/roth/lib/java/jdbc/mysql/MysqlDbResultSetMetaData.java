package roth.lib.java.jdbc.mysql;

import java.sql.ResultSetMetaData;

import roth.lib.java.jdbc.DbResultSetMetaData;

public class MysqlDbResultSetMetaData extends DbResultSetMetaData implements MysqlDbWrapper
{
	
	public MysqlDbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		super(resultSetMetaData);
	}
	
}
