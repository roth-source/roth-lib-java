package roth.lib.java.jdbc.mysql;

import java.sql.SQLType;

import roth.lib.java.jdbc.JdbcSQLType;

public class MysqlDbSQLType extends JdbcSQLType implements MysqlDbWrapper
{
	
	public MysqlDbSQLType(SQLType sqlType)
	{
		super(sqlType);
	}
	
}
