package roth.lib.java.test.json;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.mapper.MapperConfig;

@Entity
public class JsonMapSubModel
{
	@Property(name = "testString")
	protected String testString;
	
	@Property(name = "testInteger")
	protected Integer testInteger;
	
	@Override
	public String toString()
	{
		MapperConfig mapperConfig = new MapperConfig().setSerializeNulls(true);
		return new JsonMapper(mapperConfig).setPrettyPrint(true).serialize(this);
	}
	
}
