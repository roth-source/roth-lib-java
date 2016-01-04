package roth.lib.java.db.orient;

import java.sql.Array;

import roth.lib.java.db.DbArray;

public class OrientDbArray extends DbArray implements OrientDbWrapper
{
	
	public OrientDbArray(Array array)
	{
		super(array);
	}
	
}
