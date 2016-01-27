package roth.lib.java;

import java.io.Serializable;

import roth.lib.java.lang.Set;

@SuppressWarnings("serial")
public abstract class Model implements Serializable
{
	protected transient Set<String> deserializedNames = new Set<String>();
	
	public Model()
	{
		
	}
	
	public Set<String> getDeserializedNames()
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
