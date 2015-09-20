package roth.lib.java.db.mysql;

import java.sql.CallableStatement;

import roth.lib.java.db.DbCallableStatement;

public class MysqlDbCallableStatement extends DbCallableStatement implements MysqlDbWrapper
{
	
	public MysqlDbCallableStatement(CallableStatement callableStatement)
	{
		super(callableStatement);
	}
	
}
