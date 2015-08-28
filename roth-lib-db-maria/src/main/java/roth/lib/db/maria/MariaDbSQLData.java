package roth.lib.db.maria;

import java.sql.SQLData;

import roth.lib.db.DbSQLData;

public class MariaDbSQLData extends DbSQLData implements MariaDbWrapper
{
	
	public MariaDbSQLData(SQLData sqlData)
	{
		super(sqlData);
	}
	
}
