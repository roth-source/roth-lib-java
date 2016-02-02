package roth.lib.java.jdbc;

import java.sql.SQLType;

public abstract class DbSQLType implements SQLType, DbWrapper
{
	protected SQLType sqlType;
	
	public DbSQLType(SQLType sqlType)
	{
		this.sqlType = sqlType;
	}
	
	public String getName()
	{
		return sqlType.getName();
	}
	
	public String getVendor()
	{
		return sqlType.getVendor();
	}
	
	public Integer getVendorTypeNumber()
	{
		return sqlType.getVendorTypeNumber();
	}
	
}
