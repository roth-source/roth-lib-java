package roth.lib.java.db.orient;

import java.sql.RowId;

import roth.lib.java.db.DbRowId;

public class OrientDbRowId extends DbRowId implements OrientDbWrapper
{
	
	public OrientDbRowId(RowId rowId)
	{
		super(rowId);
	}
	
}
