package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Upgrade extends Header<Upgrade> implements Characters
{
	protected LinkedList<String> upgrades = new LinkedList<String>();
	
	public Upgrade()
	{
		
	}
	
	public Upgrade(String value)
	{
		this.value = value;
	}
	
	public Upgrade addValue(String upgrade)
	{
		upgrades.add(upgrade);
		return this;
	}
	
	public LinkedList<String> getUpgrades()
	{
		return upgrades;
	}
	
	@Override
	public Upgrade deserialize(String value)
	{
		return new Upgrade(value);
	}
	
	@Override
	public Upgrade parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		if(!upgrades.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(String upgrade : upgrades)
			{
				builder.append(seperator);
				builder.append(upgrade);
				if(BLANK.equals(seperator))
				{
					seperator += COMMA;
					seperator += SPACE;
				}
			}
			return builder.toString();
		}
		return null;
	}
	
}
