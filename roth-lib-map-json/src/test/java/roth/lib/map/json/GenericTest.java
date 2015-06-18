package roth.lib.map.json;

import java.lang.reflect.Type;
import java.util.LinkedList;

import roth.lib.map.Generic;
import roth.lib.map.PropertyField;
import roth.lib.map.util.MapperUtil;

public class GenericTest
{
	
	public static void main(String[] args)
	{
		JsonConfig config = new JsonConfig().setPrettyPrinting(false).setSerializeNulls(true).setTimeFormat("yyyy-MM-dd HH:mm:ss");
		JsonMapper mapper = new JsonMapper();
		//serialize(config, mapper);
		//deserialize(config, mapper);
		test(config, mapper);
	}
	
	protected static void serialize(JsonConfig config, JsonMapper mapper)
	{
		GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>> genericModel = new GenericModelOne<>();
		genericModel.setValue(true).setModel(new GenericModelTwo<GenericModelA, GenericModelB>().setValue("test").setModel1(new GenericModelA().setValue(1234)).setModel2(new GenericModelB().setValue(1.234)));
		mapper.serialize(genericModel, System.out, config);
	}
	
	protected static void deserialize(JsonConfig config, JsonMapper mapper)
	{
		String data = "{\"value\":true,\"model\":{\"value\":\"test\",\"model1\":{\"value\":1234},\"model2\":{\"value\":1.234}}}";
		GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>> model = mapper.deserialize(data, new Generic<GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>>>(){});
		System.out.println(model);
	}
	
	protected static void test(JsonConfig config, JsonMapper mapper)
	{
		Generic<?> generic = new Generic<GenericModelOne<GenericModelTwo<GenericModelA, GenericModelB>>>(){};
		Type type = generic.getType();
		LinkedList<PropertyField> propertyFields = mapper.getPropertyFields(type);
		for(PropertyField propertyField : propertyFields)
		{
			Type fieldType = propertyField.getFieldType();
			Class<?> fieldKlass = MapperUtil.getClass(fieldType);
			System.out.println(fieldKlass);
		}
	}
	
}
