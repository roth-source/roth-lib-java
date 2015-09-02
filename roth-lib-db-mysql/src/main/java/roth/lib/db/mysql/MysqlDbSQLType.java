package roth.lib.db.mysql;

import java.sql.SQLType;

import roth.lib.db.DbSQLType;

public class MysqlDbSQLType extends DbSQLType implements MysqlDbWrapper
{
	
	public MysqlDbSQLType(SQLType sqlType)
	{
		super(sqlType);
	}
	
}
