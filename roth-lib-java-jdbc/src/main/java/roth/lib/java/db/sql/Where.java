package roth.lib.java.db.sql;

import java.util.Collection;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Where extends Condition
{
	protected String sql;
	protected String table;
	protected String name;
	protected String opType;
	
	protected Where()
	{
		
	}

	@Override
	public Where setLogicType(String logicType)
	{
		this.logicType = logicType;
		return this;
	}
	
	public Where setSql(String sql)
	{
		this.sql = sql;
		return this;
	}
	
	public Where setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Where setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Where setOpType(String opType)
	{
		this.opType = opType;
		return this;
	}
	
	public Where setValues(Collection<?> values)
	{
		this.values = new List<Object>().allowNull().collection(values);
		return this;
	}
	
	public Where addValues(Object...values)
	{
		this.values.array(values);
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
