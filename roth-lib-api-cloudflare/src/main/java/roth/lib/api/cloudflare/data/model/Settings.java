package roth.lib.api.cloudflare.data.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Settings implements Serializable
{
	@Property(name = "objs")
	protected LinkedList<SettingObj> objs = new LinkedList<SettingObj>();
	
	public Settings()
	{
		
	}
	
	public LinkedList<SettingObj> getObjs()
	{
		return objs;
	}
	
	public Settings setObjs(LinkedList<SettingObj> objs)
	{
		this.objs = objs;
		return this;
	}
	
}
