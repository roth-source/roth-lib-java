package roth.lib.db.maria;

import java.sql.ResultSet;

import roth.lib.db.DbResultSet;

public class MariaDbResultSet extends DbResultSet implements MariaDbWrapper
{
	
	public MariaDbResultSet(ResultSet resultSet)
	{
		super(resultSet);
	}
	
}
