package roth.lib.java.db.orient;

import java.sql.ResultSetMetaData;

import roth.lib.java.db.DbResultSetMetaData;

public class OrientDbResultSetMetaData extends DbResultSetMetaData implements OrientDbWrapper
{
	
	public OrientDbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		super(resultSetMetaData);
	}
	
}
