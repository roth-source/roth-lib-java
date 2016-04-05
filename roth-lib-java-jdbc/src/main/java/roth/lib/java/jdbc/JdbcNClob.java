package roth.lib.java.jdbc;

import java.sql.NClob;

public abstract class JdbcNClob extends JdbcClob implements NClob
{
	protected NClob nClob;
	
	public JdbcNClob(NClob nClob)
	{
		super(nClob);
		this.nClob = nClob;
	}
	
}
