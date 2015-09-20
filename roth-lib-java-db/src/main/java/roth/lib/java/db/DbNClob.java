package roth.lib.java.db;

import java.sql.NClob;

public abstract class DbNClob extends DbClob implements NClob
{
	protected NClob nClob;
	
	public DbNClob(NClob nClob)
	{
		super(nClob);
		this.nClob = nClob;
	}
	
}
