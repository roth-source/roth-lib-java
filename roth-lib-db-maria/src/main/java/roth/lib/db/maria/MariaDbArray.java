package roth.lib.db.maria;

import java.sql.Array;

import roth.lib.db.DbArray;

public class MariaDbArray extends DbArray implements MariaDbWrapper
{
	
	public MariaDbArray(Array array)
	{
		super(array);
	}
	
}
