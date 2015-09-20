package roth.lib.java.db;

import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;

import javax.sql.DataSource;

import roth.lib.java.Callback;
import roth.lib.java.db.sql.Delete;
import roth.lib.java.db.sql.Insert;
import roth.lib.java.db.sql.Select;
import roth.lib.java.db.sql.Sql;
import roth.lib.java.db.sql.Update;

public abstract class DbDataSource implements DataSource, DbWrapper
{
	protected DbDriver driver;
	protected String url;
	protected String username;
	protected String password;
	protected Properties properties;
	protected DbReflector reflector;
	protected int maxConnections = 20;
	protected int loginTimeout = 60;
	protected PrintWriter logWriter;
	protected ConcurrentLinkedDeque<DbConnection> availableConnections = new ConcurrentLinkedDeque<DbConnection>();
	protected ConcurrentLinkedDeque<DbConnection> usedConnections = new ConcurrentLinkedDeque<DbConnection>();
	protected DbCloseHandler closeHandler = new DbCloseHandler()
	{
		@Override
		public void close(DbConnection connection)
		{
			usedConnections.remove(connection);
			availableConnections.add(connection);
		}
	};
	
	{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				close();
			}
		}));
	}
	
	protected DbDataSource()
	{
		
	}
	
	public DbDataSource(String driver, String url)
	{
		init(driver, url);
	}
	
	public DbDataSource(String driver, String url, Properties properties)
	{
		init(driver, url, properties);
	}
	
	public DbDataSource(String driver, String url, String username, String password)
	{
		init(driver, url, username, password);
	}
	
	public DbDataSource(String driver, String url, String username, String password, Properties properties)
	{
		init(driver, url, username, password, properties);
	}
	
	protected void init(String driver, String url)
	{
		init(driver, url, new Properties());
	}
	
	protected void init(String driver, String url, Properties properties)
	{
		init(driver, url, null, null, properties);
	}
	
	protected void init(String driver, String url, String username, String password)
	{
		init(driver, url, username, password, new Properties());
	}
	
	protected void init(String driver, String url, String username, String password, Properties properties)
	{
		this.driver = createDriver(driver);
		this.url = url;
		this.username = username;
		this.password = password;
		this.properties = properties;
		this.reflector = createDbReflector();
	}
	
	protected abstract DbReflector createDbReflector();
	
	public DbReflector getReflector()
	{
		return reflector;
	}
	
	public DbDriver createDriver(String driverName)
	{
		try
		{
			Class<?> driverClass = Class.forName(driverName);
			Object driver = driverClass.newInstance();
			if(driver instanceof Driver)
			{
				return wrap((Driver) driver);
			}
			else
			{
				throw new IllegalArgumentException(driverName + " is not instance of Driver class");
			}
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(e);
		}
	}
	
	public void setMaxConnections(int maxConnections)
	{
		this.maxConnections = maxConnections;
	}
	
	@Override
	public DbConnection getConnection() throws SQLException
	{
		return getConnection(username, password);
	}
	
	@Override
	public DbConnection getConnection(String username, String password) throws SQLException
	{
		DbConnection connection = getAvailableConnection();
		if(connection == null)
		{
			if(usedConnections.size() < maxConnections)
			{
				connection = createConnection(username, password);
			}
			long start = System.currentTimeMillis();
			long timeout = loginTimeout * 1000;
			while(connection == null && System.currentTimeMillis() - start < timeout)
			{
				try
				{
					Thread.sleep(500);
					connection = getAvailableConnection();
				} 
				catch(InterruptedException e)
				{
					throw new SQLException(e);
				}
			}
		}
		return connection;
	}
	
	protected DbConnection getAvailableConnection()
	{
		DbConnection connection = null;
		while((connection = availableConnections.poll()) != null)
		{
			try
			{
				if(connection.isValid(loginTimeout))
				{
					usedConnections.add(connection);
					break;
				}
				else
				{
					connection.closeWrapped();
				}
			}
			catch(Exception e)
			{
				
			}
		}
		return connection;
	}
	
	protected DbConnection createConnection(String username, String password) throws SQLException
	{
		if(username != null)
		{
			properties.put("user", username);
		}
		if(password != null)
		{
			properties.put("password", password);
		}
		DbConnection connection = wrap(driver.connect(url, properties));
		connection.setCloseHandler(closeHandler);
		connection.setAutoCommit(false);
		usedConnections.add(connection);
		return connection;
	}
	
	@Override
	public PrintWriter getLogWriter()
	{
		return logWriter;
	}
	
	@Override
	public void setLogWriter(PrintWriter logWriter)
	{
		this.logWriter = logWriter;
	}
	
	@Override
	public void setLoginTimeout(int loginTimeout)
	{
		this.loginTimeout = loginTimeout;
	}
	
	@Override
	public int getLoginTimeout()
	{
		return loginTimeout;
	}
	
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		throw new SQLFeatureNotSupportedException();
	}
	
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		throw new SQLException();
	}
	
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return false;
	}
	
	public DbPreparedStatement prepareStatement(DbConnection connection, Sql sql) throws SQLException
	{
		return prepareStatement(connection, sql.sql(), sql.values());
	}
	
	public DbPreparedStatement prepareStatement(DbConnection connection, Sql sql,  LinkedList<String> generated) throws SQLException
	{
		return prepareStatement(connection, sql.sql(), sql.values(), generated);
	}
	
	public DbPreparedStatement prepareStatement(DbConnection connection, String sql, Collection<Object> values) throws SQLException
	{
		return prepareStatement(connection, sql, values, null);
	}
	
	public DbPreparedStatement prepareStatement(DbConnection connection, String sql, Collection<Object> values, LinkedList<String> generatedColumns) throws SQLException
	{
		if(generatedColumns != null && !generatedColumns.isEmpty())
		{
			return setValues(connection.prepareStatement(sql, generatedColumns.toArray(new String[]{})), values);
		}
		else
		{
			return setValues(connection.prepareStatement(sql), values);
		}
	}
	
	public DbPreparedStatement setValues(DbPreparedStatement preparedStatement, Collection<Object> values) throws SQLException
	{
		if(values != null)
		{
			int i = 1;
			for(Object value : values)
			{
				preparedStatement.setObject(i++, value);
			}
		}
		if(logWriter != null)
		{
			logWriter.println(Sql.LF + preparedStatement.toString());
		}
		return preparedStatement;
	}
	
	public <T extends DbModel> String table(Class<T> klass)
	{
		return getReflector().getEntityName(klass);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends DbModel> T query(T model)
	{
		Select select = getReflector().toSelect(model);
		if(select != null)
		{
			return query(select, (Class<T>) model.getClass());
		}
		return null;
	}
	
	public <T> T query(Select select, Class<T> klass)
	{
		select.limit(1);
		return query(select.sql(), select.values(), klass);
	}
	
	public <T> T query(String sql, Class<T> klass)
	{
		return query(sql, (Collection<Object>) null, klass);
	}
	
	public <T> T query(String sql, Collection<Object> values, Class<T> klass)
	{
		LinkedList<T> models = queryAll(sql, values, klass);
		return !models.isEmpty() ? models.get(0) : null;
	}
	
	public <T> LinkedList<T> queryAll(Select select, Class<T> klass)
	{
		return queryAll(select.sql(), select.values(), klass);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Class<T> klass)
	{
		return queryAll(sql, (Collection<Object>) null, klass);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Collection<Object> values, Class<T> klass)
	{
		LinkedList<T> models = new LinkedList<T>();
		try(DbConnection connection = getConnection())
		{
			models = queryAll(sql, values, klass, connection); 
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return models;
	}
	
	public <T> LinkedList<T> queryAll(Select select, Class<T> klass, DbConnection connection) throws SQLException
	{
		return queryAll(select.sql(), select.values(), klass, connection);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Class<T> klass, DbConnection connection) throws SQLException
	{
		return queryAll(sql, (Collection<Object>) null, klass, connection);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Collection<Object> values, Class<T> klass, DbConnection connection) throws SQLException
	{
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			try(DbResultSet resultSet = preparedStatement.executeQuery())
			{
				LinkedList<T> result = getReflector().fromDb(resultSet, this, klass);
				connection.commit();
				return result;
			}
		}
	}
	
	public <T> void queryAll(Select select, Callback<T> callback)
	{
		queryAll(select.sql(), select.values(), callback);
	}
	
	public <T> void queryAll(String sql, Callback<T> callback)
	{
		queryAll(sql, (Collection<Object>) null, callback);
	}
	
	public <T> void queryAll(String sql, Collection<Object> values, Callback<T> callback)
	{
		try(DbConnection connection = getConnection())
		{
			queryAll(sql, values, callback, connection); 
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
	}
	
	public <T> void queryAll(Select select, Callback<T> callback, DbConnection connection) throws SQLException
	{
		queryAll(select.sql(), select.values(), callback, connection);
	}
	
	public <T> void queryAll(String sql, Callback<T> callback, DbConnection connection) throws SQLException
	{
		queryAll(sql, (Collection<Object>) null, callback, connection);
	}
	
	public <T> void queryAll(String sql, Collection<Object> values, Callback<T> callback, DbConnection connection) throws SQLException
	{
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			try(DbResultSet resultSet = preparedStatement.executeQuery())
			{
				getReflector().fromDb(resultSet, this, callback);
				connection.commit();
			}
		}
	}
	
	public LinkedHashMap<String, Object> query(Select select)
	{
		select.limit(1);
		return query(select.sql(), select.values());
	}
	
	public LinkedHashMap<String, Object> query(String sql)
	{
		return query(sql, (Collection<Object>) null);
	}
	
	public LinkedHashMap<String, Object> query(String sql, Collection<Object> values)
	{
		LinkedList<LinkedHashMap<String, Object>> maps = queryAll(sql, values);
		return !maps.isEmpty() ? maps.get(0) : null;
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(Select select)
	{
		return queryAll(select.sql(), select.values());
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, Collection<Object> values)
	{
		LinkedList<LinkedHashMap<String, Object>> maps = new LinkedList<LinkedHashMap<String, Object>>();
		try(DbConnection connection = getConnection())
		{
			maps = queryAll(sql, values, connection); 
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return maps;
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(Select select, DbConnection connection) throws SQLException
	{
		return queryAll(select.sql(), select.values(), connection);
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, DbConnection connection) throws SQLException
	{
		return queryAll(sql, (Collection<Object>) null, connection);
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			try(DbResultSet resultSet = preparedStatement.executeQuery())
			{
				 LinkedList<LinkedHashMap<String, Object>> result = getReflector().fromDb(resultSet);
				 connection.commit();
				 return result;
			}
		}
	}
	
	public int executeInsert(DbModel model)
	{
		Insert insert = getReflector().toInsert(model);
		if(insert != null)
		{
			return executeInsert(insert, model);
		}
		return 0;
	}
	
	public int executeInsert(Insert insert)
	{
		return executeInsert(insert, (DbModel) null);
	}
	
	public int executeInsert(Insert insert, DbModel model)
	{
		return executeInsert(insert.sql(), insert.values(), model);
	}
	
	public int executeInsert(String sql)
	{
		return executeInsert(sql, (Collection<Object>) null);
	}
	
	public int executeInsert(String sql, Collection<Object> values)
	{
		return executeInsert(sql, values, (DbModel) null);
	}
	
	protected int executeInsert(String sql, Collection<Object> values, DbModel model)
	{
		int result = 0;
		try(DbConnection connection = getConnection())
		{
			try
			{
				result = executeInsert(sql, values, model, connection);
				connection.commit();
			}
			catch(SQLException e)
			{
				connection.rollback();
				throw new DbException(e);
			}
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return result;
	}
	
	public int executeInsert(DbModel model, DbConnection connection) throws SQLException
	{
		Insert insert = getReflector().toInsert(model);
		if(insert != null)
		{
			return executeInsert(insert, model, connection);
		}
		return 0;
	}
	
	public int executeInsert(Insert insert, DbConnection connection) throws SQLException
	{
		return executeInsert(insert, (DbModel) null, connection);
	}
	
	public int executeInsert(Insert insert, DbModel model, DbConnection connection) throws SQLException
	{
		return executeInsert(insert.sql(), insert.values(), model, connection);
	}
	
	public int executeInsert(String sql, DbConnection connection) throws SQLException
	{
		return executeInsert(sql, (Collection<Object>) null, connection);
	}
	
	public int executeInsert(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		return executeInsert(sql, values, (DbModel) null, connection);
	}
	
	protected int executeInsert(String sql, Collection<Object> values, DbModel model, DbConnection connection) throws SQLException
	{
		int result = 0;
		LinkedList<String> generatedColumns = new LinkedList<String>();
		if(model != null)
		{
			generatedColumns = getReflector().getGeneratedColumns(model.getClass());
		}
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values, generatedColumns))
		{
			result = preparedStatement.executeUpdate();
			if(model != null)
			{
				model.persisted();
				model.resetDirty();
				if(!generatedColumns.isEmpty())
				{
					try(DbResultSet resultSet = preparedStatement.getGeneratedKeys())
					{
						getReflector().setGeneratedFields(resultSet, generatedColumns, model);
					}
				}
			}
		}
		return result;
	}
	
	public int executeUpdate(DbModel model)
	{
		Update update = getReflector().toUpdate(model);
		if(update != null)
		{
			return executeUpdate(update, model);
		}
		return 0;
	}
	
	public int executeUpdate(Update update)
	{
		return executeUpdate(update, (DbModel) null);
	}
	
	public int executeUpdate(Update update, DbModel model)
	{
		return executeUpdate(update.sql(), update.values(), model);
	}
	
	public int executeUpdate(String sql)
	{
		return executeUpdate(sql, (Collection<Object>) null);
	}
	
	public int executeUpdate(String sql, Collection<Object> values)
	{
		return executeUpdate(sql, values, (DbModel) null);
	}
	
	protected int executeUpdate(String sql, Collection<Object> values, DbModel model)
	{
		int result = 0;
		try(DbConnection connection = getConnection())
		{
			try
			{
				result = executeUpdate(sql, values, model, connection);
				connection.commit();
			}
			catch(SQLException e)
			{
				connection.rollback();
				throw new DbException(e);
			}
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return result;
	}
	
	public int executeUpdate(DbModel model, DbConnection connection) throws SQLException
	{
		Update update = getReflector().toUpdate(model);
		if(update != null)
		{
			return executeUpdate(update, model, connection);
		}
		return 0;
	}
	
	public int executeUpdate(Update update, DbConnection connection) throws SQLException
	{
		return executeUpdate(update, (DbModel) null, connection);
	}
	
	public int executeUpdate(Update update, DbModel model, DbConnection connection) throws SQLException
	{
		return executeUpdate(update.sql(), update.values(), model, connection);
	}
	
	public int executeUpdate(String sql, DbConnection connection) throws SQLException
	{
		return executeUpdate(sql, (Collection<Object>) null, connection);
	}
	
	public int executeUpdate(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		return executeUpdate(sql, values, (DbModel) null, connection);
	}
	
	protected int executeUpdate(String sql, Collection<Object> values, DbModel model, DbConnection connection) throws SQLException
	{
		int result = 0;
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			result = preparedStatement.executeUpdate();
			if(model != null)
			{
				model.persisted();
				model.resetDirty();
			}
		}
		return result;
	}
	
	public int executeDelete(DbModel model)
	{
		Delete delete = getReflector().toDelete(model);
		if(delete != null)
		{
			return executeDelete(delete, model);
		}
		return 0;
	}
	
	public int executeDelete(Delete delete)
	{
		return executeDelete(delete, (DbModel) null);
	}
	
	public int executeDelete(Delete delete, DbModel model)
	{
		return executeDelete(delete.sql(), delete.values(), model);
	}
	
	public int executeDelete(String sql)
	{
		return executeDelete(sql, (Collection<Object>) null);
	}
	
	public int executeDelete(String sql, Collection<Object> values)
	{
		return executeDelete(sql, values, (DbModel) null);
	}
	
	protected int executeDelete(String sql, Collection<Object> values, DbModel model)
	{
		int result = 0;
		try(DbConnection connection = getConnection())
		{
			try
			{
				result = executeDelete(sql, values, model, connection);
				connection.commit();
			}
			catch(SQLException e)
			{
				connection.rollback();
				throw new DbException(e);
			}
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return result;
	}
	
	public int executeDelete(DbModel model, DbConnection connection) throws SQLException
	{
		Delete delete = getReflector().toDelete(model);
		if(delete != null)
		{
			return executeDelete(delete, connection);
		}
		return 0;
	}
	
	public int executeDelete(Delete delete, DbConnection connection) throws SQLException
	{
		return executeDelete(delete, (DbModel) null, connection);
	}
	
	public int executeDelete(Delete delete, DbModel model, DbConnection connection) throws SQLException
	{
		return executeDelete(delete.sql(), delete.values(), model, connection);
	}
	
	public int executeDelete(String sql, DbConnection connection) throws SQLException
	{
		return executeDelete(sql, (Collection<Object>) null, connection);
	}
	
	public int executeDelete(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		return executeDelete(sql, values, (DbModel) null, connection);
	}
	
	protected int executeDelete(String sql, Collection<Object> values, DbModel model, DbConnection connection) throws SQLException
	{
		int result = 0;
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			result = preparedStatement.executeUpdate();
			if(model != null)
			{
				model.deleted();
				model.resetDirty();
			}
		}
		return result;
	}
	
	public void close()
	{
		for(DbConnection dbConnection : availableConnections)
		{
			try
			{
				dbConnection.connection.close();
			}
			catch(Exception e)
			{
				
			}
		}
		for(DbConnection dbConnection : usedConnections)
		{
			try
			{
				dbConnection.connection.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
}
