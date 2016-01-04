package roth.lib.java.db.orient;

import java.sql.PreparedStatement;

import roth.lib.java.db.DbPreparedStatement;

public class OrientDbPreparedStatement extends DbPreparedStatement implements OrientDbWrapper
{
	
	public OrientDbPreparedStatement(PreparedStatement preparedStatement)
	{
		super(preparedStatement);
	}
	
}
