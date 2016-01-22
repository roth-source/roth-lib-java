package roth.lib.java.db;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Driver;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;

import javax.sql.DataSource;

import roth.lib.java.Callback;
import roth.lib.java.Characters;
import roth.lib.java.db.sql.Delete;
import roth.lib.java.db.sql.Insert;
import roth.lib.java.db.sql.Select;
import roth.lib.java.db.sql.Sql;
import roth.lib.java.db.sql.SqlFactory;
import roth.lib.java.db.sql.Update;
import roth.lib.java.db.sql.Wheres;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertyReflector;

public abstract class DbDataSource implements DataSource, DbWrapper, Characters, SqlFactory
{
	protected MapperType mapperType;
	protected MapperReflector mapperReflector;
	protected DbDriver driver;
	protected String url;
	protected String username;
	protected String password;
	protected Properties properties;
	protected int maxConnections = 20;
	protected int loginTimeout = 60;
	protected int deadLockRetries = 3;
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
				System.out.println("Closing Connections");
				close();
			}
		}));
	}
	
	protected DbDataSource(MapperType mapperType)
	{
		this.mapperType = mapperType;
	}
	
	public DbDataSource(MapperType mapperType, String driver, String url)
	{
		this.mapperType = mapperType;
		init(driver, url);
	}
	
	public DbDataSource(MapperType mapperType, String driver, String url, Properties properties)
	{
		this.mapperType = mapperType;
		init(driver, url, properties);
	}
	
	public DbDataSource(MapperType mapperType, String driver, String url, String username, String password)
	{
		this.mapperType = mapperType;
		init(driver, url, username, password);
	}
	
	public DbDataSource(MapperType mapperType, String driver, String url, String username, String password, Properties properties)
	{
		this.mapperType = mapperType;
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
		this.mapperReflector = MapperReflector.get();
	}
	
	protected abstract boolean isDeadLockException(Exception e);
	
	public MapperType getMapperType()
	{
		return mapperType;
	}
	
	public MapperReflector getMapperReflector()
	{
		return mapperReflector;
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
	
	public void setDeadLockRetries(int deadLockRetries)
	{
		this.deadLockRetries = deadLockRetries;
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
				e.printStackTrace();
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
	
	public int getDeadLockRetries()
	{
		return deadLockRetries;
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
	
	public LinkedList<String> getGeneratedColumns(Type type)
	{
		LinkedList<String> generatedColumns = new LinkedList<String>();
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
		if(entityReflector != null)
		{
			for(PropertyReflector propertyReflector : entityReflector.getGeneratedReflectors(getMapperType()))
			{
				generatedColumns.add(propertyReflector.getPropertyName(getMapperType()));
			}
		}
		return generatedColumns;
	}
	
	public void setGeneratedFields(DbResultSet resultSet, LinkedList<String> generatedColumns, DbModel model)
	{
		try
		{
			if(resultSet.next())
			{
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(model.getClass());
				for(String name : generatedColumns)
				{
					PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
					if(propertyReflector != null)
					{
						try
						{
							Field field = propertyReflector.getField();
							Object value = resultSet.getValue(1, field.getType());
							field.set(model, value);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Wheres toIdWheres(Class<?> klass, Object...values)
	{
		Wheres wheres = newWheres();
		if(values != null)
		{
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(klass);
			int i = 0;
			for(PropertyReflector idReflector : entityReflector.getIdReflectors(getMapperType()))
			{
				String propertyName = idReflector.getPropertyName(getMapperType());
				wheres.andWhere(newWhere().setOpType(OP_EQ).setName(propertyName).addValues(values[i++]));
			}
		}
		return wheres;
	}
	
	public Wheres toIdWheres(DbModel model)
	{
		Wheres wheres = newWheres();
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(model.getClass());
		for(PropertyReflector idReflector : entityReflector.getIdReflectors(getMapperType()))
		{
			try
			{
				String propertyName = idReflector.getPropertyName(getMapperType());
				String fieldName = idReflector.getFieldName();
				Field field = idReflector.getField();
				Object value = null;
				if(model.getDirtyIdMap().containsKey(fieldName))
				{
					value = model.getDirtyIdMap().get(fieldName);
				}
				else
				{
					value = field.get(model);
				}
				if(value != null)
				{
					wheres.andWhere(newWhere().setOpType(OP_EQ).setName(propertyName).addValues(value));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return wheres;
	}
	
	public Select toSelect(DbModel model)
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(model.getClass());
		Wheres wheres = toIdWheres(model);
		if(!wheres.isEmpty())
		{
			return newSelect().from(entityReflector.getEntityName()).wheres(wheres);
		}
		return null;
	}
	
	public Insert toInsert(DbModel model)
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(model.getClass());
		LinkedHashMap<String, Object> nameValues = new LinkedHashMap<String, Object>();
		for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
		{
			String column = propertyReflector.getPropertyName(getMapperType());
			Field field = propertyReflector.getField();
			try
			{
				Object value = field.get(model);
				nameValues.put(column, value);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(!nameValues.isEmpty())
		{
			return newInsert().setTable(entityReflector.getEntityName()).setNameValues(nameValues);
		}
		return null;
	}
	
	public Update toUpdate(DbModel model)
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(model.getClass());
		LinkedHashMap<String, Object> nameValues = new LinkedHashMap<String, Object>();
		if(model.isDirty())
		{
			for(String name : model.getDirtyNames())
			{
				PropertyReflector propertyReflector = entityReflector.getFieldReflector(name, getMapperType(), getMapperReflector());
				if(propertyReflector != null)
				{
					String column = propertyReflector.getPropertyName(getMapperType());
					Field field = propertyReflector.getField();
					try
					{
						Object value = field.get(model);
						nameValues.put(column, value);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		Wheres wheres = null;
		if(!nameValues.isEmpty() && !(wheres = toIdWheres(model)).isEmpty())
		{
			return newUpdate().setTable(entityReflector.getEntityName()).setNameValues(nameValues).wheres(wheres);
		}
		return null;
	}
	
	public Delete toDelete(DbModel model)
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(model.getClass());
		Wheres wheres = toIdWheres(model);
		if(!wheres.isEmpty())
		{
			return newDelete().setTable(entityReflector.getEntityName()).wheres(wheres);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> void fromDb(DbResultSet resultSet, Callback<T> callback)
	{
		try
		{
			Class<T> klass = callback.getKlass();
			ResultSetMetaData metaData = resultSet.getMetaData();
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(klass);
			if(entityReflector != null)
			{
				while(resultSet.next())
				{
					T model = fromDb(resultSet, klass, metaData, entityReflector);
					if(model != null)
					{
						callback.call(model);
					}
				}
			}
			else
			{
				while(resultSet.next())
				{
					Object value = resultSet.getValue(1, klass);
					if(value != null && value.getClass().isAssignableFrom(klass))
					{
						callback.call((T) value);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> LinkedList<T> fromDb(DbResultSet resultSet, Class<T> klass)
	{
		LinkedList<T> models = new LinkedList<T>();
		try
		{
			ResultSetMetaData metaData = resultSet.getMetaData();
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(klass);
			if(entityReflector != null)
			{
				while(resultSet.next())
				{
					T model = fromDb(resultSet, klass, metaData, entityReflector);
					if(model != null)
					{
						models.add(model);
					}
				}
			}
			else
			{
				while(resultSet.next())
				{
					Object value = resultSet.getValue(1, klass);
					if(value != null && value.getClass().isAssignableFrom(klass))
					{
						models.add((T) value);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return models;
	}
	
	public <T> T fromDb(DbResultSet resultSet, Class<T> klass, ResultSetMetaData metaData, EntityReflector entityReflector)
	{
		T model = null;
		try
		{
			Constructor<T> constructor = null;
			constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			model = constructor.newInstance();
			if(model instanceof DbModel)
			{
				((DbModel) model).setDb(this).persisted();
			}
			for(int i = 1; i <= metaData.getColumnCount(); i++)
			{
				String columnLabel = metaData.getColumnLabel(i).toUpperCase();
				PropertyReflector propertyReflector = entityReflector.getPropertyReflector(columnLabel, getMapperType(), getMapperReflector());
				if(propertyReflector != null)
				{
					Field field = propertyReflector.getField();
					Object value = resultSet.getValue(columnLabel, field.getType());
					field.set(model, value);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}
	
	public LinkedList<LinkedHashMap<String, Object>> fromDb(DbResultSet resultSet)
	{
		LinkedList<LinkedHashMap<String, Object>> dataMaps = new LinkedList<LinkedHashMap<String, Object>>();
		try
		{
			while(resultSet.next())
			{
				LinkedHashMap<String, Object> dataMap = new LinkedHashMap<String, Object>();
				try
				{
					ResultSetMetaData metaData = resultSet.getMetaData();
					for(int i = 1; i <= metaData.getColumnCount(); i++)
					{
						String columnLabel = metaData.getColumnLabel(i);
						dataMap.put(columnLabel, resultSet.getObject(columnLabel));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(!dataMap.isEmpty())
				{
					dataMaps.add(dataMap);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dataMaps;
	}
	
	public DbPreparedStatement prepareStatement(DbConnection connection, Sql sql) throws SQLException
	{
		return prepareStatement(connection, sql.toString(), sql.getValues());
	}
	
	public DbPreparedStatement prepareStatement(DbConnection connection, Sql sql,  LinkedList<String> generated) throws SQLException
	{
		return prepareStatement(connection, sql.toString(), sql.getValues(), generated);
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
			logWriter.println(NEW_LINE + preparedStatement.toString());
		}
		return preparedStatement;
	}
	
	public String table(Class<?> klass)
	{
		String table = null;
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(klass);
		if(entityReflector != null)
		{
			table = entityReflector.getEntityName();
			if(table == null)
			{
				throw new DbException("Entity name not found");
			}
		}
		else
		{
			throw new DbException("Entity annotation not found");
		}
		return table;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends DbModel> T query(T model)
	{
		Select select = toSelect(model);
		if(select != null)
		{
			return query(select, (Class<T>) model.getClass());
		}
		return null;
	}
	
	public <T> T query(Select select, Class<T> klass)
	{
		select.limit(1);
		return query(select.toString(), select.getValues(), klass);
	}
	
	public <T> T query(String sql, Class<T> klass)
	{
		return query(sql, (Collection<Object>) null, klass);
	}
	
	public <T> T query(String sql, Map<String, Object> valueMap, Class<T> klass)
	{
		LinkedList<T> models = queryAll(sql, valueMap, klass);
		return !models.isEmpty() ? models.get(0) : null;
	}
	
	public <T> T query(String sql, Collection<Object> values, Class<T> klass)
	{
		LinkedList<T> models = queryAll(sql, values, klass);
		return !models.isEmpty() ? models.get(0) : null;
	}
	
	public <T> LinkedList<T> queryAll(Select select, Class<T> klass)
	{
		return queryAll(select.toString(), select.getValues(), klass);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Class<T> klass)
	{
		return queryAll(sql, (Collection<Object>) null, klass);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Map<String, Object> valueMap, Class<T> klass)
	{
		LinkedList<T> models = new LinkedList<T>();
		try(DbConnection connection = getConnection())
		{
			models = queryAll(sql, valueMap, klass, connection); 
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return models;
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
		return queryAll(select.toString(), select.getValues(), klass, connection);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Class<T> klass, DbConnection connection) throws SQLException
	{
		return queryAll(sql, (Collection<Object>) null, klass, connection);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Map<String, Object> valueMap, Class<T> klass, DbConnection connection) throws SQLException
	{
		DbNamedQuery namedQuery = namedQuery(sql, valueMap);
		return queryAll(namedQuery.getSql(), namedQuery.getValues(), klass, connection);
	}
	
	public <T> LinkedList<T> queryAll(String sql, Collection<Object> values, Class<T> klass, DbConnection connection) throws SQLException
	{
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			try(DbResultSet resultSet = preparedStatement.executeQuery())
			{
				LinkedList<T> result = fromDb(resultSet, klass);
				connection.commit();
				return result;
			}
		}
	}
	
	public <T> void queryAll(Select select, Callback<T> callback)
	{
		queryAll(select.toString(), select.getValues(), callback);
	}
	
	public <T> void queryAll(String sql, Callback<T> callback)
	{
		queryAll(sql, (Collection<Object>) null, callback);
	}
	
	public <T> void queryAll(String sql, Map<String, Object> valueMap, Callback<T> callback)
	{
		try(DbConnection connection = getConnection())
		{
			queryAll(sql, valueMap, callback, connection); 
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
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
		queryAll(select.toString(), select.getValues(), callback, connection);
	}
	
	public <T> void queryAll(String sql, Callback<T> callback, DbConnection connection) throws SQLException
	{
		queryAll(sql, (Collection<Object>) null, callback, connection);
	}
	
	public <T> void queryAll(String sql, Map<String, Object> valueMap, Callback<T> callback, DbConnection connection) throws SQLException
	{
		DbNamedQuery namedQuery = namedQuery(sql, valueMap);
		queryAll(namedQuery.getSql(), namedQuery.getValues(), callback, connection);
	}
	
	public <T> void queryAll(String sql, Collection<Object> values, Callback<T> callback, DbConnection connection) throws SQLException
	{
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			try(DbResultSet resultSet = preparedStatement.executeQuery())
			{
				fromDb(resultSet, callback);
				connection.commit();
			}
		}
	}
	
	public LinkedHashMap<String, Object> query(Select select)
	{
		select.limit(1);
		return query(select.toString(), select.getValues());
	}
	
	public LinkedHashMap<String, Object> query(String sql)
	{
		return query(sql, (Collection<Object>) null);
	}
	
	public LinkedHashMap<String, Object> query(String sql, Map<String, Object> valueMap)
	{
		LinkedList<LinkedHashMap<String, Object>> maps = queryAll(sql, valueMap);
		return !maps.isEmpty() ? maps.get(0) : null;
	}
	
	public LinkedHashMap<String, Object> query(String sql, Collection<Object> values)
	{
		LinkedList<LinkedHashMap<String, Object>> maps = queryAll(sql, values);
		return !maps.isEmpty() ? maps.get(0) : null;
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(Select select)
	{
		return queryAll(select.toString(), select.getValues());
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, Map<String, Object> valueMap)
	{
		LinkedList<LinkedHashMap<String, Object>> maps = new LinkedList<LinkedHashMap<String, Object>>();
		try(DbConnection connection = getConnection())
		{
			maps = queryAll(sql, valueMap, connection); 
		}
		catch(SQLException e)
		{
			throw new DbException(e);
		}
		return maps;
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
		return queryAll(select.toString(), select.getValues(), connection);
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, DbConnection connection) throws SQLException
	{
		return queryAll(sql, (Collection<Object>) null, connection);
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, Map<String, Object> valueMap, DbConnection connection) throws SQLException
	{
		DbNamedQuery namedQuery = namedQuery(sql, valueMap);
		return queryAll(namedQuery.getSql(), namedQuery.getValues(), connection);
	}
	
	public LinkedList<LinkedHashMap<String, Object>> queryAll(String sql, Collection<Object> values, DbConnection connection) throws SQLException
	{
		try(DbPreparedStatement preparedStatement = prepareStatement(connection, sql, values))
		{
			try(DbResultSet resultSet = preparedStatement.executeQuery())
			{
				 LinkedList<LinkedHashMap<String, Object>> result = fromDb(resultSet);
				 connection.commit();
				 return result;
			}
		}
	}
	
	public int executeInsert(DbModel model)
	{
		Insert insert = toInsert(model);
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
		return executeInsert(insert.toString(), insert.getValues(), model);
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
		return executeInsert(sql, values, (DbModel) null, 0);
	}
	
	protected int executeInsert(String sql, Collection<Object> values, DbModel model, int attempt)
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
				throw e;
			}
		}
		catch(SQLException e)
		{
			if(isDeadLockException(e) && attempt++ < getDeadLockRetries())
			{
				result = executeInsert(sql, values, model, attempt);
			}
			else
			{
				throw new DbException(e);
			}
		}
		return result;
	}
	
	public int executeInsert(DbModel model, DbConnection connection) throws SQLException
	{
		Insert insert = toInsert(model);
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
		return executeInsert(insert.toString(), insert.getValues(), model, connection);
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
			generatedColumns = getGeneratedColumns(model.getClass());
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
						setGeneratedFields(resultSet, generatedColumns, model);
					}
				}
			}
		}
		return result;
	}
	
	public int executeUpdate(DbModel model)
	{
		Update update = toUpdate(model);
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
		return executeUpdate(update.toString(), update.getValues(), model);
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
		return executeUpdate(sql, values, model, 0);
	}
	
	protected int executeUpdate(String sql, Collection<Object> values, DbModel model, int attempt)
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
				throw e;
			}
		}
		catch(SQLException e)
		{
			if(isDeadLockException(e) && attempt++ < getDeadLockRetries())
			{
				result = executeUpdate(sql, values, model, attempt);
			}
			else
			{
				throw new DbException(e);
			}
		}
		return result;
	}
	
	public int executeUpdate(DbModel model, DbConnection connection) throws SQLException
	{
		Update update = toUpdate(model);
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
		return executeUpdate(update.toString(), update.getValues(), model, connection);
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
		Delete delete = toDelete(model);
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
		return executeDelete(delete.toString(), delete.getValues(), model);
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
		return executeDelete(sql, values, model, 0);
	}
	
	protected int executeDelete(String sql, Collection<Object> values, DbModel model, int attempt)
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
				throw e;
			}
		}
		catch(SQLException e)
		{
			if(isDeadLockException(e) && attempt++ < getDeadLockRetries())
			{
				result = executeDelete(sql, values, model, attempt);
			}
			else
			{
				throw new DbException(e);
			}
		}
		return result;
	}
	
	public int executeDelete(DbModel model, DbConnection connection) throws SQLException
	{
		Delete delete = toDelete(model);
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
		return executeDelete(delete.toString(), delete.getValues(), model, connection);
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
	
	public DbNamedQuery namedQuery(String sql, Map<String, Object> valueMap)
	{
		return new DbNamedQuery(sql, valueMap);
	}
	
}
