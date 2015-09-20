package roth.lib.java.db.sql;


@SuppressWarnings("serial")
public class Havings extends Conditions
{
	public static final String HAVING	= " HAVING ";
	
	public Havings() {}
	
	public Havings and(Having having)
	{
		having.logic = Logic.AND;
		conditions.add(having);
		return this;
	}
	
	public Havings or(Having having)
	{
		having.logic = Logic.OR;
		conditions.add(having);
		return this;
	}
	
	public Havings and(Havings havings)
	{
		havings.logic = Logic.AND;
		this.conditions.add(havings);
		return this;
	}
	
	public Havings or(Havings havings)
	{
		havings.logic = Logic.OR;
		this.conditions.add(havings);
		return this;
	}
	
	@Override
	public String toString()
	{
		return !conditions.isEmpty() ? LF + HAVING + toStringNested() : "";
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
			boolean isHavings = condition instanceof Havings;
			builder.append(isHavings ? "(" : "");
			builder.append(condition.toStringNested());
			builder.append(isHavings ? ")" : "");
		}
		return builder.toString();
	}
	
}
