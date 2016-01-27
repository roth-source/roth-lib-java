package roth.lib.java.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import roth.lib.java.lang.List;
import roth.lib.java.util.IoUtil;

public class Ftp implements AutoCloseable
{
	protected FtpConfig config;
	protected FTPClient client;
	
	public Ftp(FtpConfig config)
	{
		this.config = config;
		connect();
	}
	
	public Ftp connect()
	{
		if(client == null || !client.isConnected())
		{
			try
			{
				client = config.isSecure() ? new FTPSClient(config.getProtocol(), config.isImplicit()) : new FTPClient();
				client.connect(config.getHost(), config.getPort());
				if(FTPReply.isPositiveCompletion(client.getReplyCode()))
				{
					if(config.getAccount() != null)
					{
						client.login(config.getUsername(), config.getPassword(), config.getAccount());
					}
					else
					{
						client.login(config.getUsername(), config.getPassword());
					}
					if(config.isPassive())
					{
						client.enterLocalPassiveMode();
					}
					else
					{
						client.enterLocalActiveMode();
					}
					client.setFileType(config.getType());
					client.setFileTransferMode(config.getMode());
				}
				else
				{
					throw new FtpException(String.format("failed to connect to %s:%d", config.getHost(), config.getPort()));
				}
			}
			catch(IOException e)
			{
				throw new FtpException(e);
			}
		}
		return this;
	}
	
	public FTPClient getFtpClient()
	{
		return client;
	}
	
	public FTPSClient getFtpsClient()
	{
		if(client instanceof FTPSClient)
		{
			return (FTPSClient) client;
		}
		else
		{
			throw new FtpException("not secure client");
		}
	}
	
	// CDUP Change to Parent Directory.
	public void cdUp()
	{
		try
		{
			connect();
			client.changeToParentDirectory();
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// CWD  Change working directory.
	public void cd(String path)
	{
		try
		{
			connect();
			client.changeWorkingDirectory(path);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// DELE Delete file.
	public void rm(String path)
	{
		try
		{
			connect();
			client.deleteFile(path);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// RMD  Remove a directory.
	public void rmdir(String path)
	{
		try
		{
			connect();
			client.removeDirectory(path);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// MKD  Make directory.
	public void mkdir(String path)
	{
		try
		{
			connect();
			client.makeDirectory(path);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// PWD  Print working directory. Returns the current directory of the host.
	public String pwd()
	{
		try
		{
			connect();
			return client.printWorkingDirectory();
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// LIST Returns information of a file or directory if specified, else information of the current working directory is returned.
	public List<FTPFile> ls()
	{
		try
		{
			connect();
			return new List<FTPFile>(client.listFiles());
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public List<FTPFile> ls(String path)
	{
		try
		{
			connect();
			return new List<FTPFile>(client.listFiles(path));
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// RNTO Rename to.
	public void mv(String from, String to)
	{
		try
		{
			connect();
			client.rename(from, to);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// RETR Retrieve a copy of the file
	public InputStream get(String filename)
	{
		try
		{
			connect();
			return client.retrieveFileStream(filename);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public void get(String filename, OutputStream output)
	{
		try
		{
			connect();
			client.retrieveFile(filename, output);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public void get(String filename, File file)
	{
		try(FileOutputStream output = new FileOutputStream(file);)
		{
			connect();
			client.retrieveFile(filename, output);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public byte[] getBytes(String filename)
	{
		try
		{
			connect();
			return IoUtil.toBytes(client.retrieveFileStream(filename));
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	// STOR Accept the data and to store the data as a file at the server site
	public OutputStream put(String filename)
	{
		try
		{
			connect();
			return client.storeFileStream(filename);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public void put(InputStream input, String filename)
	{
		try
		{
			connect();
			client.storeFile(filename, input);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public void put(byte[] bytes, String filename)
	{
		try
		{
			connect();
			client.storeFile(filename, new ByteArrayInputStream(bytes));
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	public void put(File file)
	{
		try(FileInputStream input = new FileInputStream(file);)
		{
			connect();
			client.storeFile(file.getName(), input);
		}
		catch(IOException e)
		{
			throw new FtpException(e);
		}
	}
	
	@Override
	public void close()
	{
		if(client != null)
		{
			try
			{
				client.logout();
				client.disconnect();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
}
