package roth.lib.map.odb;

import java.lang.reflect.Field;

import roth.lib.annotation.Property;
import roth.lib.map.Mapper;
import roth.lib.map.mapper.PropertyMapper;

public class OdbMapper extends Mapper
{
	
	public OdbMapper()
	{
		propertyMappers.add(new PropertyMapper<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Field field, Property property)
			{
				if(property != null && property.odb())
				{
					if(isValid(property.odbName()))
					{
						return property.odbName();
					}
					else if(isValid(property.name()))
					{
						return property.name();
					}
				}
				return null;
			}
			
			@Override
			public boolean isEntityName(Field field, Property property)
			{
				return property != null && property.entityName();
			}
		});
	}
	
}
