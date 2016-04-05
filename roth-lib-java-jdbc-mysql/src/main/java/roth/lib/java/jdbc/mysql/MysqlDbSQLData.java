package roth.lib.java.jdbc.mysql;

import java.sql.SQLData;

import roth.lib.java.jdbc.JdbcSQLData;

public class MysqlDbSQLData extends JdbcSQLData implements MysqlDbWrapper
{
	
	public MysqlDbSQLData(SQLData sqlData)
	{
		super(sqlData);
	}
	
}
