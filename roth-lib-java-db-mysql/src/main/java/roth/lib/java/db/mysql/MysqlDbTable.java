package roth.lib.java.db.mysql;

import roth.lib.java.db.DbTable;
import roth.lib.java.db.mysql.sql.MysqlSqlFactory;

public abstract class MysqlDbTable<T> extends DbTable<T> implements MysqlSqlFactory
{
	
	protected MysqlDbTable(Class<T> klass)
	{
		super(klass);
	}
	
}
