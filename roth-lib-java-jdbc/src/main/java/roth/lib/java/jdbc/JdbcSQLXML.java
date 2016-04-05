package roth.lib.java.jdbc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.SQLXML;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

public abstract class JdbcSQLXML implements SQLXML, JdbcWrapper
{
	protected SQLXML sqlXml;
	
	public JdbcSQLXML(SQLXML sqlXml)
	{
		this.sqlXml = sqlXml;
	}
	
	public void free() throws SQLException
	{
		sqlXml.free();
	}
	
	public InputStream getBinaryStream() throws SQLException
	{
		return sqlXml.getBinaryStream();
	}
	
	public OutputStream setBinaryStream() throws SQLException
	{
		return sqlXml.setBinaryStream();
	}
	
	public Reader getCharacterStream() throws SQLException
	{
		return sqlXml.getCharacterStream();
	}
	
	public Writer setCharacterStream() throws SQLException
	{
		return sqlXml.setCharacterStream();
	}
	
	public String getString() throws SQLException
	{
		return sqlXml.getString();
	}
	
	public void setString(String value) throws SQLException
	{
		sqlXml.setString(value);
	}
	
	public <T extends Source> T getSource(Class<T> sourceClass) throws SQLException
	{
		return sqlXml.getSource(sourceClass);
	}
	
	public <T extends Result> T setResult(Class<T> resultClass) throws SQLException
	{
		return sqlXml.setResult(resultClass);
	}
	
}
