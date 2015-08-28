package roth.lib.db.maria;

import java.sql.RowId;

import roth.lib.db.DbRowId;

public class MariaDbRowId extends DbRowId implements MariaDbWrapper
{
	
	public MariaDbRowId(RowId rowId)
	{
		super(rowId);
	}
	
}
