package roth.lib.java.db.mysql;

import java.sql.DatabaseMetaData;

import roth.lib.java.db.DbDatabaseMetaData;

public class MysqlDbDatabaseMetaData extends DbDatabaseMetaData implements MysqlDbWrapper
{
	
	public MysqlDbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		super(databaseMetaData);
	}
	
}
