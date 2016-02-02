package roth.lib.java.jdbc.mysql;

import java.sql.SQLType;

import roth.lib.java.jdbc.DbSQLType;

public class MysqlDbSQLType extends DbSQLType implements MysqlDbWrapper
{
	
	public MysqlDbSQLType(SQLType sqlType)
	{
		super(sqlType);
	}
	
}
