package roth.lib.java;

import java.io.Serializable;
import java.util.LinkedHashSet;

@SuppressWarnings("serial")
public abstract class Model implements Serializable
{
	protected transient LinkedHashSet<String> deserializedNames = new LinkedHashSet<String>();
	
	public Model()
	{
		
	}
	
	public LinkedHashSet<String> getDeserializedNames()
	{
		return deserializedNames;
	}
	
	public void setDeserializedName(String name)
	{
		deserializedNames.add(name);
	}
	
	public void resetDeserializedNames()
	{
		deserializedNames.clear();
	}
	
}
