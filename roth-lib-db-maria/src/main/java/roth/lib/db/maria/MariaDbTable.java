package roth.lib.db.maria;

import roth.lib.db.DbTable;

public abstract class MariaDbTable<T> extends DbTable<T>
{
	
	protected MariaDbTable(Class<T> klass)
	{
		super(klass);
	}
	
}
