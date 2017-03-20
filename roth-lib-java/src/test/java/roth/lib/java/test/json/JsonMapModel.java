package roth.lib.java.test.json;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import roth.lib.java.mapper.MapperConfig;

@Entity
public class JsonMapModel
{
	@Property(name = "typeTypeMap")
	protected Map<JsonMapType, JsonMapType> typeTypeMap = new Map<>();
	
	@Property(name = "typeStringMap")
	protected Map<JsonMapType, String> typeStringMap = new Map<>();
	
	@Property(name = "typeBooleanMap")
	protected Map<JsonMapType, Boolean> typeBooleanMap = new Map<>();
	
	@Property(name = "typeListMap")
	protected Map<JsonMapType, List<String>> typeListMap = new Map<>();
	
	@Property(name = "typeEntityMap")
	protected Map<JsonMapType, JsonMapSubModel> typeEntityMap = new Map<>();
	
	@Override
	public String toString()
	{
		MapperConfig mapperConfig = new MapperConfig().setSerializeNulls(true);
		return new JsonMapper(mapperConfig).setPrettyPrint(true).serialize(this);
	}
	
}
