package roth.lib.java.db.orient;

import java.sql.SQLType;

import roth.lib.java.db.DbSQLType;

public class OrientDbSQLType extends DbSQLType implements OrientDbWrapper
{
	
	public OrientDbSQLType(SQLType sqlType)
	{
		super(sqlType);
	}
	
}
