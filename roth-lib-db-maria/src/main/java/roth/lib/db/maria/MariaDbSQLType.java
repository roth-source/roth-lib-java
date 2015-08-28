package roth.lib.db.maria;

import java.sql.SQLType;

import roth.lib.db.DbSQLType;

public class MariaDbSQLType extends DbSQLType implements MariaDbWrapper
{
	
	public MariaDbSQLType(SQLType sqlType)
	{
		super(sqlType);
	}
	
}
