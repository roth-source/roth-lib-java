package roth.lib.java.map;

import roth.lib.java.reflector.EntityReflector;

public abstract class MapperReflector extends EntityReflector
{
	
	public MapperReflector()
	{
		
	}
	
	public abstract Mapper getMapper();
	public abstract Mapper getMapper(MapperConfig mapperConfig);
	
}
