package roth.lib.java.ssh;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class Sftp implements AutoCloseable
{
	protected static String SFTP = "sftp";
	
	protected Ssh ssh;
	protected ChannelSftp channelSftp;
	
	public Sftp(Ssh ssh)
	{
		this.ssh = ssh;
	}
	
	public ChannelSftp openChannelSftp()
	{
		try
		{
			ssh.connect();
			if(channelSftp == null || channelSftp.isClosed())
			{
				channelSftp = (ChannelSftp) ssh.session.openChannel(SFTP);
				channelSftp.connect();
			}
		}
		catch(JSchException e)
		{
			throw new SshException(e);
		}
		return channelSftp;
	}
	
	public LinkedList<String> ls(String path)
	{
		LinkedList<String> lines = new LinkedList<String>();
		try
		{
			openChannelSftp();
			Vector<?> vector = channelSftp.ls(path);
			for(Object object : vector)
			{
				if(object != null)
				{
					lines.add(object.toString());
				}
			}
		}
		catch(SftpException e)
		{
			throw new SshException(e);
		}
		return lines;
	}
	
	public void cd(String path)
	{
		try
		{
			openChannelSftp();
			channelSftp.cd(path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void put(File source, String dest)
	{
		try(FileInputStream input = new FileInputStream(source);)
		{
			put(input, dest);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void put(byte[] bytes, String dest)
	{
		put(new ByteArrayInputStream(bytes), dest);
	}
	
	public void put(InputStream input, String dest)
	{
		try
		{
			openChannelSftp();
			channelSftp.put(input, dest);
		}
		catch(SftpException e)
		{
			throw new SshException(e);
		}
	}
	
	public void closeChannelSftp()
	{
		if(channelSftp != null)
		{
			channelSftp.disconnect();
		}
	}
	
	@Override
	public void close()
	{
		closeChannelSftp();
	}
	
}
