package roth.lib.java.web.dependency;

import roth.lib.java.lang.List;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
public class Dependency
{
	@Property(name = "local")
	protected String local;
	
	@Property(name = "source")
	protected String source;
	
	@Property(name = "external")
	protected String external;
	
	@Property(name = "assets")
	protected List<Dependency> assets = new List<Dependency>();
	
	public Dependency()
	{
		
	}
	
	public String getLocal()
	{
		return local;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public String getExternal()
	{
		return external;
	}
	
	public List<Dependency> getAssets()
	{
		return assets;
	}
	
	public void setLocal(String local)
	{
		this.local = local;
	}
	
	public void setSource(String source)
	{
		this.source = source;
	}
	
	public void setExternal(String external)
	{
		this.external = external;
	}
	
	public void setAssets(List<Dependency> assets)
	{
		this.assets = assets;
	}
	
}
