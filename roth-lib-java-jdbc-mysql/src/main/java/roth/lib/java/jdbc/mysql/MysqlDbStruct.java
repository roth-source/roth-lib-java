package roth.lib.java.jdbc.mysql;

import java.sql.Struct;

import roth.lib.java.jdbc.JdbcStruct;

public class MysqlDbStruct extends JdbcStruct implements MysqlDbWrapper
{
	
	public MysqlDbStruct(Struct struct)
	{
		super(struct);
	}
	
}
