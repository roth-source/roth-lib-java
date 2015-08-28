package roth.lib.db;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public abstract class DbBlob implements Blob, DbWrapper
{
	protected Blob blob;
	
	public DbBlob(Blob blob)
	{
		this.blob = blob;
	}
	
	@Override
	public long length() throws SQLException
	{
		return blob.length();
	}
	
	@Override
	public byte[] getBytes(long pos, int length) throws SQLException
	{
		return blob.getBytes(pos, length);
	}
	
	@Override
	public InputStream getBinaryStream() throws SQLException
	{
		return blob.getBinaryStream();
	}
	
	@Override
	public long position(byte[] pattern, long start) throws SQLException
	{
		return blob.position(pattern, start);
	}
	
	@Override
	public long position(Blob pattern, long start) throws SQLException
	{
		return blob.position(pattern, start);
	}
	
	@Override
	public int setBytes(long pos, byte[] bytes) throws SQLException
	{
		return blob.setBytes(pos, bytes);
	}
	
	@Override
	public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException
	{
		return blob.setBytes(pos, bytes);
	}
	
	@Override
	public OutputStream setBinaryStream(long pos) throws SQLException
	{
		return blob.setBinaryStream(pos);
	}
	
	@Override
	public void truncate(long len) throws SQLException
	{
		blob.truncate(len);
	}
	
	@Override
	public void free() throws SQLException
	{
		blob.free();
	}
	
	@Override
	public InputStream getBinaryStream(long pos, long length) throws SQLException
	{
		return blob.getBinaryStream(pos, length);
	}
	
}
