package roth.lib.java.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

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
	
	// COMMANDS
	
	
	
	@Override
	public void close() throws Exception
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
