package roth.lib.map.odb;

import com.orientechnologies.orient.core.db.OPartitionedDatabasePool;

public class Odb
{
	protected static final int MAX_CONNECTIONS = 64;
	
	protected OPartitionedDatabasePool connectionPool;
	
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if(connectionPool != null)
				{
					connectionPool.close();
				}
			}
		}));
	}
	
	protected Odb()
	{
		
	}
	
	public Odb(String url)
	{
		this(url, MAX_CONNECTIONS);
	}
	
	public Odb(String url, int maxConnections)
	{
		this(url, null, null, maxConnections);
	}
	
	public Odb(String url, String username, String password)
	{
		this(url, username, password, MAX_CONNECTIONS);
	}
	
	public Odb(String url, String username, String password, int maxConnections)
	{
		connectionPool = new OPartitionedDatabasePool(url, username, password, maxConnections);
	}
	
}
