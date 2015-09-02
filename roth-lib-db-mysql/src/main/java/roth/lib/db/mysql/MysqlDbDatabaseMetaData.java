package roth.lib.db.mysql;

import java.sql.DatabaseMetaData;

import roth.lib.db.DbDatabaseMetaData;

public class MysqlDbDatabaseMetaData extends DbDatabaseMetaData implements MysqlDbWrapper
{
	
	public MysqlDbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		super(databaseMetaData);
	}
	
}
