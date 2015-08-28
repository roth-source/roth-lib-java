package roth.lib.db;

import java.sql.SQLException;
import java.sql.Savepoint;

public abstract class DbSavepoint implements Savepoint, DbWrapper
{
	protected Savepoint savepoint;
	
	public DbSavepoint(Savepoint savepoint)
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
