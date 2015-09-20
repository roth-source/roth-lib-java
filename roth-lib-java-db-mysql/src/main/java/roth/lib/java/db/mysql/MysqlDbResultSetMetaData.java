package roth.lib.java.db.mysql;

import java.sql.ResultSetMetaData;

import roth.lib.java.db.DbResultSetMetaData;

public class MysqlDbResultSetMetaData extends DbResultSetMetaData implements MysqlDbWrapper
{
	
	public MysqlDbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		super(resultSetMetaData);
	}
	
}
