package roth.lib.java.jdbc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

public abstract class DbClob implements Clob, DbWrapper
{
	protected Clob clob;
	
	public DbClob(Clob clob)
	{
		this.clob = clob;
	}
	
	@Override
	public long length() throws SQLException
	{
		return clob.length();
	}
	
	@Override
	public String getSubString(long pos, int length) throws SQLException
	{
		return clob.getSubString(pos, length);
	}
	
	@Override
	public Reader getCharacterStream() throws SQLException
	{
		return clob.getCharacterStream();
	}
	
	@Override
	public InputStream getAsciiStream() throws SQLException
	{
		return clob.getAsciiStream();
	}
	
	@Override
	public long position(String searchstr, long start) throws SQLException
	{
		return clob.position(searchstr, start);
	}
	
	@Override
	public long position(Clob searchstr, long start) throws SQLException
	{
		return clob.position(searchstr, start);
	}
	
	@Override
	public int setString(long pos, String str) throws SQLException
	{
		return clob.setString(pos, str);
	}
	
	@Override
	public int setString(long pos, String str, int offset, int len) throws SQLException
	{
		return clob.setString(pos, str, offset, len);
	}
	
	@Override
	public OutputStream setAsciiStream(long pos) throws SQLException
	{
		return clob.setAsciiStream(pos);
	}
	
	@Override
	public Writer setCharacterStream(long pos) throws SQLException
	{
		return clob.setCharacterStream(pos);
	}
	
	@Override
	public void truncate(long len) throws SQLException
	{
		clob.truncate(len);
	}
	
	@Override
	public void free() throws SQLException
	{
		clob.free();
	}
	
	@Override
	public Reader getCharacterStream(long pos, long length) throws SQLException
	{
		return clob.getCharacterStream(pos, length);
	}
	
}
