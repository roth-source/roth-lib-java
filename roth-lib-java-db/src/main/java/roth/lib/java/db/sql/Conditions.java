package roth.lib.java.db.sql;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public abstract class Conditions extends Condition
{
	protected List<Condition> conditions = new List<Condition>();
	
	public boolean isEmpty()
	{
		return conditions.isEmpty();
	}
	
	public abstract Conditions setConditions(List<Condition> conditions);
	public abstract Conditions andConditions(Condition...conditions);
	public abstract Conditions orConditions(Condition...conditions);
	public abstract String toString(boolean nested);
	
	@Override
	public List<Object> getValues()
	{
		List<Object> values = new List<Object>().allowNull();
		for(Condition condition : conditions)
		{
			values.addAll(condition.getValues());
		}
		return values;
	}
	
}
