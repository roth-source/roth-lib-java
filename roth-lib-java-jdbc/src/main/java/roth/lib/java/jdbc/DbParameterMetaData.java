package roth.lib.java.jdbc;

import java.sql.ParameterMetaData;
import java.sql.SQLException;

public abstract class DbParameterMetaData implements ParameterMetaData, DbWrapper
{
	protected ParameterMetaData parameterMetaData;
	
	public DbParameterMetaData(ParameterMetaData parameterMetaData)
	{
		this.parameterMetaData = parameterMetaData;
	}
	
	public int getParameterCount() throws SQLException
	{
		return parameterMetaData.getParameterCount();
	}
	
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		return parameterMetaData.unwrap(iface);
	}
	
	public int isNullable(int param) throws SQLException
	{
		return parameterMetaData.isNullable(param);
	}
	
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return parameterMetaData.isWrapperFor(iface);
	}
	
	public boolean isSigned(int param) throws SQLException
	{
		return parameterMetaData.isSigned(param);
	}
	
	public int getPrecision(int param) throws SQLException
	{
		return parameterMetaData.getPrecision(param);
	}
	
	public int getScale(int param) throws SQLException
	{
		return parameterMetaData.getScale(param);
	}
	
	public int getParameterType(int param) throws SQLException
	{
		return parameterMetaData.getParameterType(param);
	}
	
	public String getParameterTypeName(int param) throws SQLException
	{
		return parameterMetaData.getParameterTypeName(param);
	}
	
	public String getParameterClassName(int param) throws SQLException
	{
		return parameterMetaData.getParameterClassName(param);
	}
	
	public int getParameterMode(int param) throws SQLException
	{
		return parameterMetaData.getParameterMode(param);
	}
	
}
