package roth.lib.db.maria;

import java.sql.Ref;

import roth.lib.db.DbRef;

public class MariaDbRef extends DbRef implements MariaDbWrapper
{
	
	public MariaDbRef(Ref ref)
	{
		super(ref);
	}
	
}
