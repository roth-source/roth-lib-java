package roth.lib.java.db.mysql;

import java.sql.Struct;

import roth.lib.java.db.DbStruct;

public class MysqlDbStruct extends DbStruct implements MysqlDbWrapper
{
	
	public MysqlDbStruct(Struct struct)
	{
		super(struct);
	}
	
}
