package roth.lib.java.db;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class DbResultSetMetaData implements ResultSetMetaData, DbWrapper
{
	protected ResultSetMetaData resultSetMetaData;
	
	public DbResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		this.resultSetMetaData = resultSetMetaData;
	}
	
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		return resultSetMetaData.unwrap(iface);
	}
	
	public int getColumnCount() throws SQLException
	{
		return resultSetMetaData.getColumnCount();
	}
	
	public boolean isAutoIncrement(int column) throws SQLException
	{
		return resultSetMetaData.isAutoIncrement(column);
	}
	
	public boolean isCaseSensitive(int column) throws SQLException
	{
		return resultSetMetaData.isCaseSensitive(column);
	}
	
	public boolean isSearchable(int column) throws SQLException
	{
		return resultSetMetaData.isSearchable(column);
	}
	
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return resultSetMetaData.isWrapperFor(iface);
	}
	
	public boolean isCurrency(int column) throws SQLException
	{
		return resultSetMetaData.isCurrency(column);
	}
	
	public int isNullable(int column) throws SQLException
	{
		return resultSetMetaData.isNullable(column);
	}
	
	public boolean isSigned(int column) throws SQLException
	{
		return resultSetMetaData.isSigned(column);
	}
	
	public int getColumnDisplaySize(int column) throws SQLException
	{
		return resultSetMetaData.getColumnDisplaySize(column);
	}
	
	public String getColumnLabel(int column) throws SQLException
	{
		return resultSetMetaData.getColumnLabel(column);
	}
	
	public String getColumnName(int column) throws SQLException
	{
		return resultSetMetaData.getColumnName(column);
	}
	
	public String getSchemaName(int column) throws SQLException
	{
		return resultSetMetaData.getSchemaName(column);
	}
	
	public int getPrecision(int column) throws SQLException
	{
		return resultSetMetaData.getPrecision(column);
	}
	
	public int getScale(int column) throws SQLException
	{
		return resultSetMetaData.getScale(column);
	}
	
	public String getTableName(int column) throws SQLException
	{
		return resultSetMetaData.getTableName(column);
	}
	
	public String getCatalogName(int column) throws SQLException
	{
		return resultSetMetaData.getCatalogName(column);
	}
	
	public int getColumnType(int column) throws SQLException
	{
		return resultSetMetaData.getColumnType(column);
	}
	
	public String getColumnTypeName(int column) throws SQLException
	{
		return resultSetMetaData.getColumnTypeName(column);
	}
	
	public boolean isReadOnly(int column) throws SQLException
	{
		return resultSetMetaData.isReadOnly(column);
	}
	
	public boolean isWritable(int column) throws SQLException
	{
		return resultSetMetaData.isWritable(column);
	}
	
	public boolean isDefinitelyWritable(int column) throws SQLException
	{
		return resultSetMetaData.isDefinitelyWritable(column);
	}
	
	public String getColumnClassName(int column) throws SQLException
	{
		return resultSetMetaData.getColumnClassName(column);
	}
	
}
