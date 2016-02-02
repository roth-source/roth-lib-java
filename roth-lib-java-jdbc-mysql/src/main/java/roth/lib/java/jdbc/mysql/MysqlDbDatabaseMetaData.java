package roth.lib.java.jdbc.mysql;

import java.sql.DatabaseMetaData;

import roth.lib.java.jdbc.DbDatabaseMetaData;

public class MysqlDbDatabaseMetaData extends DbDatabaseMetaData implements MysqlDbWrapper
{
	
	public MysqlDbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		super(databaseMetaData);
	}
	
}
