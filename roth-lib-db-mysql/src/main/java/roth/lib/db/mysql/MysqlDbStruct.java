package roth.lib.db.mysql;

import java.sql.Struct;

import roth.lib.db.DbStruct;

public class MysqlDbStruct extends DbStruct implements MysqlDbWrapper
{
	
	public MysqlDbStruct(Struct struct)
	{
		super(struct);
	}
	
}
