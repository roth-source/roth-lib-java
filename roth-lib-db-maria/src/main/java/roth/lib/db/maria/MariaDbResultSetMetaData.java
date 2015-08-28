package roth.lib.db.maria;

import java.sql.ResultSetMetaData;

import roth.lib.db.DbResultSetMetaData;

public class MariaDbResultSetMetaData extends DbResultSetMetaData implements MariaDbWrapper
{
	
	public MariaDbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		super(resultSetMetaData);
	}
	
}
