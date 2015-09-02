package roth.lib.db.mysql;

import java.sql.ResultSetMetaData;

import roth.lib.db.DbResultSetMetaData;

public class MysqlDbResultSetMetaData extends DbResultSetMetaData implements MysqlDbWrapper
{
	
	public MysqlDbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		super(resultSetMetaData);
	}
	
}
