package roth.lib.db.mysql;

import roth.lib.db.DbTable;

public abstract class MysqlDbTable<T> extends DbTable<T>
{
	
	protected MysqlDbTable(Class<T> klass)
	{
		super(klass);
	}
	
}
