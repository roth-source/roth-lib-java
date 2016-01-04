package roth.lib.java.db.orient;

import java.sql.ResultSet;

import roth.lib.java.db.DbResultSet;

public class OrientDbResultSet extends DbResultSet implements OrientDbWrapper
{
	
	public OrientDbResultSet(ResultSet resultSet)
	{
		super(resultSet);
	}
	
}
