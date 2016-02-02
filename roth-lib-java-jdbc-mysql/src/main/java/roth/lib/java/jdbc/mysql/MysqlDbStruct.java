package roth.lib.java.jdbc.mysql;

import java.sql.Struct;

import roth.lib.java.jdbc.DbStruct;

public class MysqlDbStruct extends DbStruct implements MysqlDbWrapper
{
	
	public MysqlDbStruct(Struct struct)
	{
		super(struct);
	}
	
}
