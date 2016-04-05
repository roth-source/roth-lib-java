package roth.lib.java.jdbc.mysql;

import java.sql.Array;

import roth.lib.java.jdbc.JdbcArray;

public class MysqlDbArray extends JdbcArray implements MysqlDbWrapper
{
	
	public MysqlDbArray(Array array)
	{
		super(array);
	}
	
}
