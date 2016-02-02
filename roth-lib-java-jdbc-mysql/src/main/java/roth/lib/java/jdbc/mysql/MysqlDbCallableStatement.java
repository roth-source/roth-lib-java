package roth.lib.java.jdbc.mysql;

import java.sql.CallableStatement;

import roth.lib.java.jdbc.DbCallableStatement;

public class MysqlDbCallableStatement extends DbCallableStatement implements MysqlDbWrapper
{
	
	public MysqlDbCallableStatement(CallableStatement callableStatement)
	{
		super(callableStatement);
	}
	
}
