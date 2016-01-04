package roth.lib.java.db.orient;

import java.sql.CallableStatement;

import roth.lib.java.db.DbCallableStatement;

public class OrientDbCallableStatement extends DbCallableStatement implements OrientDbWrapper
{
	
	public OrientDbCallableStatement(CallableStatement callableStatement)
	{
		super(callableStatement);
	}
	
}
