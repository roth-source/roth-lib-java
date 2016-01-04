package roth.lib.java.db.orient;

import java.sql.SQLData;

import roth.lib.java.db.DbSQLData;

public class OrientDbSQLData extends DbSQLData implements OrientDbWrapper
{
	
	public OrientDbSQLData(SQLData sqlData)
	{
		super(sqlData);
	}
	
}
