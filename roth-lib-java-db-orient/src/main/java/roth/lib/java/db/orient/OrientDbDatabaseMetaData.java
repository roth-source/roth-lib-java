package roth.lib.java.db.orient;

import java.sql.DatabaseMetaData;

import roth.lib.java.db.DbDatabaseMetaData;

public class OrientDbDatabaseMetaData extends DbDatabaseMetaData implements OrientDbWrapper
{
	
	public OrientDbDatabaseMetaData(DatabaseMetaData databaseMetaData)
	{
		super(databaseMetaData);
	}
	
}
