package roth.lib.db.maria;

import java.sql.CallableStatement;

import roth.lib.db.DbCallableStatement;

public class MariaDbCallableStatement extends DbCallableStatement implements MariaDbWrapper
{
	
	public MariaDbCallableStatement(CallableStatement callableStatement)
	{
		super(callableStatement);
	}
	
}
