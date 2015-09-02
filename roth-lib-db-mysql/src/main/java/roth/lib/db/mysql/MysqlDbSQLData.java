package roth.lib.db.mysql;

import java.sql.SQLData;

import roth.lib.db.DbSQLData;

public class MysqlDbSQLData extends DbSQLData implements MysqlDbWrapper
{
	
	public MysqlDbSQLData(SQLData sqlData)
	{
		super(sqlData);
	}
	
}
