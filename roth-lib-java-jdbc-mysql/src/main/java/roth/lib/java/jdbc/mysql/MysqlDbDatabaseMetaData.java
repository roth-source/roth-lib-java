package roth.lib.java.jdbc.mysql;

import java.sql.DatabaseMetaData;

import roth.lib.java.jdbc.JdbcDatabaseMetaData;

public class MysqlDbDatabaseMetaData extends JdbcDatabaseMetaData implements MysqlDbWrapper
{
	
	public MysqlDbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		super(databaseMetaData);
	}
	
}
