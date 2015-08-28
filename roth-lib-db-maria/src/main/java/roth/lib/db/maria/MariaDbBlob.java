package roth.lib.db.maria;

import java.sql.Blob;

import roth.lib.db.DbBlob;

public class MariaDbBlob extends DbBlob implements MariaDbWrapper
{
	
	public MariaDbBlob(Blob blob)
	{
		super(blob);
	}
	
}
