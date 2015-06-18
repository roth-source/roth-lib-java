package roth.lib.map.rdb.sql;

import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Conditions extends Condition
{
	protected LinkedList<Condition> conditions = new LinkedList<Condition>();

	public boolean isEmpty()
	{
		return conditions.isEmpty();
	}
	
	@Override
	public LinkedList<Object> values()
	{
		LinkedList<Object> values = new LinkedList<Object>();
		for(Condition condition : conditions)
		{
			values.addAll(condition.values());
		}
		return values;
	}
	
}
