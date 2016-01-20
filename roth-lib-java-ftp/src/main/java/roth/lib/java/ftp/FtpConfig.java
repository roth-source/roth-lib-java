package roth.lib.java.ftp;

public class FtpConfig
{
	protected static Integer PORT = 21;
	protected static String USERNAME = "root";
	protected static String PROTOCOL = "TLS";
	
	protected String host;
	protected boolean secure;
	protected Integer port;
	protected String username;
	protected String password;
	protected String account;
	protected String protocol;
	protected boolean implicit = true;
	
	public FtpConfig(String host)
	{
		this(host, true);
	}
	
	public FtpConfig(String host, boolean secure)
	{
		this.host = host;
		this.secure = secure;
	}
	
	public String getHost()
	{
		return host;
	}
	
	public boolean isSecure()
	{
		return secure;
	}
	
	public int getPort()
	{
		return port != null ? port : PORT;
	}
	
	public String getUsername()
	{
		return username != null ? username : USERNAME;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getAccount()
	{
		return account;
	}
	
	public String getProtocol()
	{
		return protocol != null ? protocol : PROTOCOL;
	}
	
	public boolean isImplicit()
	{
		return implicit;
	}
	
	public FtpConfig setHost(String host)
	{
		this.host = host;
		return this;
	}
	
	public FtpConfig setSecure(boolean secure)
	{
		this.secure = secure;
		return this;
	}
	
	public FtpConfig setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public FtpConfig setUsername(String username)
	{
		this.username = username;
		return this;
	}
	
	public FtpConfig setPassword(String password)
	{
		this.password = password;
		return this;
	}
	
	public FtpConfig setAccount(String account)
	{
		this.account = account;
		return this;
	}
	
	public FtpConfig setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public FtpConfig setImplicit(boolean implicit)
	{
		this.implicit = implicit;
		return this;
	}
	
}
