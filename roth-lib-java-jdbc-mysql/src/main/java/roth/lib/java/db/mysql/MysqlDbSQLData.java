package roth.lib.java.db.mysql;

import java.sql.SQLData;

import roth.lib.java.db.DbSQLData;

public class MysqlDbSQLData extends DbSQLData implements MysqlDbWrapper
{
	
	public MysqlDbSQLData(SQLData sqlData)
	{
		super(sqlData);
	}
	
}
