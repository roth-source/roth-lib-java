package roth.lib.db.mysql;

import java.sql.Array;

import roth.lib.db.DbArray;

public class MysqlDbArray extends DbArray implements MysqlDbWrapper
{
	
	public MysqlDbArray(Array array)
	{
		super(array);
	}
	
}
