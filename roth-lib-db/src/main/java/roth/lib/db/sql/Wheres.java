package roth.lib.db.sql;


@SuppressWarnings("serial")
public class Wheres extends Conditions
{
	public static final String WHERE	= "  WHERE ";
	
	
	public Wheres() {}
	
	public Wheres and(Where where)
	{
		where.logic = Logic.AND;
		conditions.add(where);
		return this;
	}
	
	public Wheres or(Where where)
	{
		where.logic = Logic.OR;
		conditions.add(where);
		return this;
	}
	
	public Wheres and(Wheres wheres)
	{
		wheres.logic = Logic.AND;
		this.conditions.add(wheres);
		return this;
	}
	
	public Wheres or(Wheres wheres)
	{
		wheres.logic = Logic.OR;
		this.conditions.add(wheres);
		return this;
	}
	
	@Override
	public String toString()
	{
		return !conditions.isEmpty() ? LF + WHERE + toStringNested() : "";
	}
	
	@Override
	public String toStringNested()
	{
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for(Condition condition : conditions)
		{
			if(first)
			{
				first = false;
			}
			else
			{
				builder.append(LF + condition.logic.get());
			}
			boolean isWheres = condition instanceof Wheres;
			builder.append(isWheres ? "(" : "");
			builder.append(condition.toStringNested());
			builder.append(isWheres ? ")" : "");
		}
		return builder.toString();
	}
	
}
