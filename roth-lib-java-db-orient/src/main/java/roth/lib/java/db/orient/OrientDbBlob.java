package roth.lib.java.db.orient;

import java.sql.Blob;

import roth.lib.java.db.DbBlob;

public class OrientDbBlob extends DbBlob implements OrientDbWrapper
{
	
	public OrientDbBlob(Blob blob)
	{
		super(blob);
	}
	
}
