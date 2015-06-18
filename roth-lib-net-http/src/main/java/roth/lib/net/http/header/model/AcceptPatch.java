package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class AcceptPatch extends Header<AcceptPatch> implements Characters
{
	protected LinkedList<AcceptPatchValue> acceptPatchValues = new LinkedList<AcceptPatchValue>();
	
	public AcceptPatch()
	{
		
	}
	
	public AcceptPatch(String value)
	{
		this.value = value;
	}
	
	public AcceptPatch addAcceptPatchValue(AcceptPatchValue acceptCharsetValue)
	{
		acceptPatchValues.add(acceptCharsetValue);
		return this;
	}
	
	public LinkedList<AcceptPatchValue> getAcceptPatchValues()
	{
		return acceptPatchValues;
	}
	
	@Override
	public AcceptPatch deserialize(String value)
	{
		return new AcceptPatch(value);
	}
	
	@Override
	public AcceptPatch parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(!acceptPatchValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(AcceptPatchValue acceptPatchValue : acceptPatchValues)
			{
				String acceptPatchValueString = acceptPatchValue.toString();
				if(acceptPatchValueString != null)
				{
					builder.append(seperator);
					builder.append(acceptPatchValueString);
					if(BLANK.equals(seperator))
					{
						seperator += COMMA;
						seperator += SPACE;
					}
				}
			}
			return builder.toString();
		}
		return null;
	}
	
}
