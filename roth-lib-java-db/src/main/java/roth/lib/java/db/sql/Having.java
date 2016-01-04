package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Having extends Condition
{
	protected String sql;
	protected String table;
	protected String name;
	protected String opType;
	
	protected Having()
	{
		
	}

	@Override
	public Having setLogicType(String logicType)
	{
		this.logicType = logicType;
		return this;
	}
	
	public Having setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public Having setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Having setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Having setOpType(String opType)
	{
		this.opType = opType;
		return this;
	}
	
	public Having setValues(Collection<?> values)
	{
		this.values = new LinkedList<Object>(values);
		return this;
	}
	
	public Having addValues(Object...values)
	{
		this.values.addAll(Arrays.asList(values));
		return this;
	}
	
	@Override
	public String toString()
	{
		return toString(false);
	}
	
	@Override
	public String toString(boolean nested)
	{
		if(sql != null)
		{
			return sql;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			if(table != null)
			{
				builder.append(tick(table));
				builder.append(DOT);
			}
			builder.append(tick(name));
			builder.append(opType);
			return builder.toString();
		}
	}
	
}
