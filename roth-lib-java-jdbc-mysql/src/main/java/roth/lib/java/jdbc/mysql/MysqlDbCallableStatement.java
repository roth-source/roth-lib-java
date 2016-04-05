package roth.lib.java.jdbc.mysql;

import java.sql.CallableStatement;

import roth.lib.java.jdbc.JdbcCallableStatement;

public class MysqlDbCallableStatement extends JdbcCallableStatement implements MysqlDbWrapper
{
	
	public MysqlDbCallableStatement(CallableStatement callableStatement)
	{
		super(callableStatement);
	}
	
}
