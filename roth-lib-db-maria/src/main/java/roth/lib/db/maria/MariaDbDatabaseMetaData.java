package roth.lib.db.maria;

import java.sql.DatabaseMetaData;

import roth.lib.db.DbDatabaseMetaData;

public class MariaDbDatabaseMetaData extends DbDatabaseMetaData implements MariaDbWrapper
{
	
	public MariaDbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		super(databaseMetaData);
	}
	
}
