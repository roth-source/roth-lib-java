package roth.lib.java.jdbc.sql;

import java.util.Collection;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Where extends Condition
{
	protected String sql;
	protected Select select;
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
	
	public Where setSelect(Select select)
	{
		this.select = select;
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
	public List<Object> getValues()
	{
		if(select != null)
		{
			return select.getValues();
		}
		else
		{
			return super.getValues();
		}
	}
	
	@Override
	public String toString()
	{
		return toString(false);
	}
	
	@Override
	public String toString(boolean nested)
	{
		StringBuilder builder = new StringBuilder();
		if(sql != null)
		{
			if(select != null)
			{
				builder.append(String.format(sql, select.toString(false)));
			}
			else
			{
				builder.append(sql);
			}
		}
		else
		{
			if(OP_EQ.equals(opType))
			{
				Object value = getValues().getFirst();
				if(value == null)
				{
					opType = OP_IS_NULL;
					values.clear();
				}
			}
			else if(OP_NE.equals(opType))
			{
				Object value = getValues().getFirst();
				if(value == null)
				{
					opType = OP_IS_NOT_NULL;
					values.clear();
				}
			}
			if(table != null)
			{
				builder.append(tick(table));
				builder.append(DOT);
			}
			builder.append(tick(name));
			builder.append(opType);
		}
		return builder.toString();
	}
	
}
