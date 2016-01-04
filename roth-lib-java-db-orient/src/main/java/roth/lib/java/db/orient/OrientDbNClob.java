package roth.lib.java.db.orient;

import java.sql.NClob;

import roth.lib.java.db.DbNClob;

public class OrientDbNClob extends DbNClob implements OrientDbWrapper
{
	
	public OrientDbNClob(NClob nClob)
	{
		super(nClob);
	}
	
}
