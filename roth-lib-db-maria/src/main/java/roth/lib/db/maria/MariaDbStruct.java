package roth.lib.db.maria;

import java.sql.Struct;

import roth.lib.db.DbStruct;

public class MariaDbStruct extends DbStruct implements MariaDbWrapper
{
	
	public MariaDbStruct(Struct struct)
	{
		super(struct);
	}
	
}
