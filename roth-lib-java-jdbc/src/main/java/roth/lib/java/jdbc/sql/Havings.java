package roth.lib.java.jdbc.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Havings extends Conditions
{

	public Havings()
	{
		
	}
	
	@Override
	public Havings setLogicType(String logicType)
	{
		this.logicType = logicType;
		return this;
	}
	
	@Override
	public Havings setConditions(List<Condition> conditions)
	{
		this.conditions = conditions;
		return this;
	}
	
	@Override
	public Havings andConditions(Condition...conditions)
	{
		for(Condition condition : conditions)
		{
			condition.setLogicType(LOGIC_AND);
			this.conditions.add(condition);
		}
		return this;
	}
	
	@Override
	public Havings orConditions(Condition...conditions)
	{
		for(Condition condition : conditions)
		{
			condition.setLogicType(LOGIC_OR);
			this.conditions.add(condition);
		}
		return this;
	}
	
	public Havings andHaving(Having having)
	{
		andConditions(having);
		return this;
	}
	
	public Havings orHaving(Having having)
	{
		orConditions(having);
		return this;
	}
	
	public Havings andHavings(Havings havings)
	{
		havings.setLogicType(LOGIC_AND);
		this.conditions.add(havings);
		return this;
	}
	
	public Havings orHavings(Havings havings)
	{
		havings.setLogicType(LOGIC_OR);
		this.conditions.add(havings);
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
			builder.append(LF + HAVING);
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
			boolean isHavings = condition instanceof Havings;
			builder.append(isHavings ? "(" : "");
			builder.append(condition.toString(true));
			builder.append(isHavings ? ")" : "");
		}
		return builder.toString();
	}
	
}
