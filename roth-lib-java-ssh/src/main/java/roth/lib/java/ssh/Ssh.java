package roth.lib.java.ssh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.LinkedList;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import roth.lib.java.util.IoUtil;

public class Ssh implements AutoCloseable
{
	protected static String EXEC = "exec";
	
	protected SshConfig config;
	protected JSch jsch;
	protected Session session;
	protected ChannelExec channelExec;
	
	public Ssh(String host)
	{
		this(new SshConfig(host));
	}
	
	public Ssh(SshConfig config)
	{
		this.config = config;
		jsch = new JSch();
		try
		{
			if(!config.hasPassword())
			{
				jsch.addIdentity(config.getKey());
			}
			openChannelExec();
		}
		catch(JSchException e)
		{
			throw new SshException(e);
		}
	}
	
	public void connect() throws JSchException
	{
		if(session == null || !session.isConnected())
		{
			session = jsch.getSession(config.getUsername(), config.getHost(), config.getPort());
			if(config.hasPassword())
			{
				session.setPassword(config.getPassword());
			}
			session.setConfig(new Hashtable<String, String>(config.getConfig()));
			session.connect();
		}
	}
	
	public Sftp sftp()
	{
		return new Sftp(this);
	}
	
	public ChannelExec openChannelExec() throws JSchException
	{
		connect();
		if(channelExec == null || channelExec.isClosed())
		{
			channelExec = (ChannelExec) session.openChannel(EXEC);
		}
		return channelExec;
	}
	
	public LinkedList<String> exec(String command)
	{
		LinkedList<String> lines = new LinkedList<String>();
		try
		{
			openChannelExec();
			channelExec.setCommand(command);
			channelExec.setPty(true);
			InputStream input = channelExec.getInputStream();
			channelExec.connect();
			String line = null;
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(input));)
			{
				while((line = reader.readLine()) != null)
				{
					lines.add(line);
				}
			}
		}
		catch(JSchException | IOException e)
		{
			throw new SshException(e);
		}
		finally
		{
			closeChannelExec();
		}
		return lines;
	}
	
	public void scp(File file, String dest)
	{
		scp(file, file.getName(), dest);
	}
	
	public void scp(File file, String fileName, String dest)
	{
		try
		{
			openChannelExec();
			String command = "scp -t " + dest;
			channelExec.setCommand(command);
			try(OutputStream output = channelExec.getOutputStream(); FileInputStream fileInput = new FileInputStream(file);)
			{
				channelExec.connect();
				String command2 = "C0644 " + file.length() + " " + fileName + "\n";
				output.write(command2.getBytes());
				output.flush();
				IoUtil.copy(fileInput, output);
				output.write((byte) 0);
				output.flush();
			}
		}
		catch(Exception e)
		{
			throw new SshException(e);
		}
		finally
		{
			closeChannelExec();
		}
	}
	
	public void closeChannelExec()
	{
		if(channelExec != null)
		{
			channelExec.disconnect();
		}
	}
	
	public void closeSession()
	{
		if(session != null)
		{
			session.disconnect();
		}
	}
	
	@Override
	public void close()
	{
		closeChannelExec();
		closeSession();
	}
	
}
