package roth.lib.java.db.mysql;

import java.sql.SQLType;

import roth.lib.java.db.DbSQLType;

public class MysqlDbSQLType extends DbSQLType implements MysqlDbWrapper
{
	
	public MysqlDbSQLType(SQLType sqlType)
	{
		super(sqlType);
	}
	
}
