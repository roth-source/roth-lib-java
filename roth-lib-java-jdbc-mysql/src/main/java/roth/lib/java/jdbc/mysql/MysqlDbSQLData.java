package roth.lib.java.jdbc.mysql;

import java.sql.SQLData;

import roth.lib.java.jdbc.DbSQLData;

public class MysqlDbSQLData extends DbSQLData implements MysqlDbWrapper
{
	
	public MysqlDbSQLData(SQLData sqlData)
	{
		super(sqlData);
	}
	
}
