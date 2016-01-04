package roth.lib.java.db.orient;

import java.sql.Ref;

import roth.lib.java.db.DbRef;

public class OrientDbRef extends DbRef implements OrientDbWrapper
{
	
	public OrientDbRef(Ref ref)
	{
		super(ref);
	}
	
}
