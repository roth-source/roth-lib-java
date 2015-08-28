package roth.lib.db.maria;

import java.sql.PreparedStatement;

import roth.lib.db.DbPreparedStatement;

public class MariaDbPreparedStatement extends DbPreparedStatement implements MariaDbWrapper
{
	
	public MariaDbPreparedStatement(PreparedStatement preparedStatement)
	{
		super(preparedStatement);
	}
	
}
