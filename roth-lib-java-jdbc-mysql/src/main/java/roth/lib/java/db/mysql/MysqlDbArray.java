package roth.lib.java.db.mysql;

import java.sql.Array;

import roth.lib.java.db.DbArray;

public class MysqlDbArray extends DbArray implements MysqlDbWrapper
{
	
	public MysqlDbArray(Array array)
	{
		super(array);
	}
	
}
