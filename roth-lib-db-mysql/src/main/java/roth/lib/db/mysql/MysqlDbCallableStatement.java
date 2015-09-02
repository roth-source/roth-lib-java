package roth.lib.db.mysql;

import java.sql.CallableStatement;

import roth.lib.db.DbCallableStatement;

public class MysqlDbCallableStatement extends DbCallableStatement implements MysqlDbWrapper
{
	
	public MysqlDbCallableStatement(CallableStatement callableStatement)
	{
		super(callableStatement);
	}
	
}
