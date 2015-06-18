package roth.lib.net.ssh;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;

public class Sftp implements AutoCloseable
{
	protected static String SFTP = "sftp";
	
	protected Ssh ssh;
	protected ChannelSftp channelSftp;
	
	public Sftp(Ssh ssh)
	{
		this.ssh = ssh;
	}
	
	public ChannelSftp openChannelSftp() throws JSchException
	{
		ssh.connect();
		if(channelSftp == null || channelSftp.isClosed())
		{
			channelSftp = (ChannelSftp) ssh.session.openChannel(SFTP);
			channelSftp.connect();
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
		catch(Exception e)
		{
			e.printStackTrace();
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
			openChannelSftp();
			channelSftp.put(input, dest);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void put(String source, String dest)
	{
		try
		{
			openChannelSftp();
			channelSftp.put(new ByteArrayInputStream(source.getBytes()), dest);
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
