package roth.lib.java.jdbc.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Wheres extends Conditions
{
	
	public Wheres()
	{
		
	}
	
	@Override
	public Wheres setLogicType(String logicType)
	{
		this.logicType = logicType;
		return this;
	}
	
	@Override
	public Wheres setConditions(List<Condition> conditions)
	{
		this.conditions = conditions;
		return this;
	}
	
	@Override
	public Wheres andConditions(Condition...conditions)
	{
		for(Condition condition : conditions)
		{
			condition.setLogicType(LOGIC_AND);
			this.conditions.add(condition);
		}
		return this;
	}
	
	@Override
	public Wheres orConditions(Condition...conditions)
	{
		for(Condition condition : conditions)
		{
			condition.setLogicType(LOGIC_OR);
			this.conditions.add(condition);
		}
		return this;
	}
	
	public Wheres andWhere(Where where)
	{
		andConditions(where);
		return this;
	}
	
	public Wheres orWhere(Where where)
	{
		orConditions(where);
		return this;
	}
	
	public Wheres andWheres(Wheres wheres)
	{
		wheres.setLogicType(LOGIC_AND);
		this.conditions.add(wheres);
		return this;
	}
	
	public Wheres orWheres(Wheres wheres)
	{
		wheres.setLogicType(LOGIC_OR);
		this.conditions.add(wheres);
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
		StringBuilder builder = new StringBuilder();
		if(!nested && !conditions.isEmpty())
		{
			builder.append(LF + WHERE);
		}
		boolean first = true;
		for(Condition condition : conditions)
		{
			if(first)
			{
				first = false;
			}
			else
			{
				builder.append(LF + condition.logicType);
			}
			boolean isWheres = condition instanceof Wheres;
			builder.append(isWheres ? "(" : "");
			builder.append(condition.toString(true));
			builder.append(isWheres ? ")" : "");
		}
		return builder.toString();
	}
	
}
