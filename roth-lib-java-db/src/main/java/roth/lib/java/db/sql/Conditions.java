package roth.lib.java.db.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Conditions extends Condition
{
	protected LinkedList<Condition> conditions = new LinkedList<Condition>();
	
	public boolean isEmpty()
	{
		return conditions.isEmpty();
	}
	
	public abstract Conditions setConditions(LinkedList<Condition> conditions);
	public abstract Conditions andConditions(Condition...conditions);
	public abstract Conditions orConditions(Condition...conditions);
	public abstract String toString(boolean nested);
	
	@Override
	public LinkedList<Object> getValues()
	{
		LinkedList<Object> values = new LinkedList<Object>();
		for(Condition condition : conditions)
		{
			values.addAll(condition.getValues());
		}
		return values;
	}
	
}
