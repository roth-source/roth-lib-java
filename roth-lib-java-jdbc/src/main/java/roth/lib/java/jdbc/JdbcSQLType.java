package roth.lib.java.jdbc;

import java.sql.SQLType;

public abstract class JdbcSQLType implements SQLType, JdbcWrapper
{
	protected SQLType sqlType;
	
	public JdbcSQLType(SQLType sqlType)
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
