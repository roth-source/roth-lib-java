package roth.lib.java.db.mysql;

import roth.lib.java.db.DbTable;

public abstract class MysqlDbTable<T> extends DbTable<T>
{
	
	protected MysqlDbTable(Class<T> klass)
	{
		super(klass);
	}
	
}
