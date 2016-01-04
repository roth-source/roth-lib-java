package roth.lib.java.db.orient;

import java.sql.Struct;

import roth.lib.java.db.DbStruct;

public class OrientDbStruct extends DbStruct implements OrientDbWrapper
{
	
	public OrientDbStruct(Struct struct)
	{
		super(struct);
	}
	
}
