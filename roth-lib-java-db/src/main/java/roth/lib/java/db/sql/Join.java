package roth.lib.java.db.sql;

import java.util.Arrays;
import java.util.LinkedList;

@SuppressWarnings("serial")
public abstract class Join extends Sql
{
	protected String joinType = INNER_JOIN;
	protected Select select;
	protected String table;
	protected String alias;
	protected LinkedList<On> ons = new LinkedList<On>();
	
	protected Join()
	{
		
	}
	
	public Join setJoinType(String joinType)
	{
		this.joinType = joinType;
		return this;
	}
	
	public Join setSelect(Select select)
	{
		this.select = select;
		return this;
	}
	
	public Join setTable(String table)
	{
		this.table = table;
		return this;
	}
	
	public Join setAlias(String alias)
	{
		this.alias = alias;
		return this;
	}
	
	public Join setOns(LinkedList<On> ons)
	{
		this.ons = ons;
		return this;
	}
	
	public Join addOns(On...ons)
	{
		this.ons.addAll(Arrays.asList(ons));
		return this;
	}
	
	@Override
	public LinkedList<Object> getValues()
	{
		if(select != null)
		{
			return select.getValues();
		}
		else
		{
			LinkedList<Object> values = new LinkedList<Object>();
			for(On on : ons)
			{
				values.addAll(on.getValues());
			}
			return values;
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(joinType.toString());
		if(select != null)
		{
			builder.append("(");
			builder.append(LF);
			builder.append(select.toString(false));
			builder.append(LF);
			builder.append("        ) ");
		}
		else
		{
			builder.append(tick(table));
		}
		if(alias != null)
		{
			builder.append(AS);
			builder.append(tick(alias));
		}
		builder.append(ON);
		builder.append(list(ons, AND));
		return builder.toString();
	}
	
}
