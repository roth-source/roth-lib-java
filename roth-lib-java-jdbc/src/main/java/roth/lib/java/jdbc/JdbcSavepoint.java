package roth.lib.java.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;

public abstract class JdbcSavepoint implements Savepoint, JdbcWrapper
{
	protected Savepoint savepoint;
	
	public JdbcSavepoint(Savepoint savepoint)
	{
		this.savepoint = savepoint;
	}
	
	public int getSavepointId() throws SQLException
	{
		return savepoint.getSavepointId();
	}
	
	public String getSavepointName() throws SQLException
	{
		return savepoint.getSavepointName();
	}
	
}
