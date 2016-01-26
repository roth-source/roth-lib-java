package roth.lib.java.test.json;

import roth.lib.java.Generic;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.mapper.MapperConfig;

public class GenericTest
{
	protected static MapperConfig mapperConfig = new MapperConfig().setSerializeNulls(true);
	
	public static void main(String[] args)
	{
		//serialize();
		deserialize();
		//test();
	}
	
	protected static void serialize()
	{
		GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>> genericModel = new GenericModelOne<>();
		genericModel.setValue(true).setModel(new GenericModelTwo<GenericModelA, GenericModelB>().setValue("test").setModel1(new GenericModelA().setValue(1234)).setModel2(new GenericModelB().setValue(1.234)));
		new JsonMapper(mapperConfig).setPrettyPrint(true).serialize(genericModel, System.out);
	}
	
	protected static void deserialize()
	{
		String data = "{\"value\":true,\"model\":{\"value\":\"test\",\"model1\":{\"value\":1234},\"model2\":{\"value\":1.234}}}";
		GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>> model = new JsonMapper(mapperConfig).setPrettyPrint(true).deserialize(data, new Generic<GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>>>(){});
		System.out.println(model);
	}
	
	protected static void test()
	{
		/*
		Generic<?> generic = new Generic<GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>>>(){};
		Type type = generic.getType();
		LinkedList<PropertyFieldReflector> propertyFields = mapper.getPropertyFieldAccessors(type);
		for(PropertyFieldReflector propertyField : propertyFields)
		{
			Type fieldType = propertyField.getFieldType();
			Class<?> fieldKlass = ReflectionUtil.getTypeClass(fieldType);
			System.out.println(fieldKlass);
		}
		*/
	}
	
}
