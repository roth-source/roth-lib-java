package roth.lib.java.jdbc.mysql;

import java.sql.Array;

import roth.lib.java.jdbc.DbArray;

public class MysqlDbArray extends DbArray implements MysqlDbWrapper
{
	
	public MysqlDbArray(Array array)
	{
		super(array);
	}
	
}
