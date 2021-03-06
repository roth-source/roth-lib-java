package roth.lib.java.test.json;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.Map;
import roth.lib.java.mapper.MapperConfig;

public class JsonTest
{
	protected static MapperConfig mapperConfig = new MapperConfig().setSerializeNulls(true);
	
	public static void main(String[] args) throws Exception
	{
		//serializeModel();
		//serializeMap();
		//deserializeModel();
		deserializeMap();
	}
	
	protected static void serializeModel()
	{
		JsonModel model = new JsonModel();
		new JsonMapper(mapperConfig).setPrettyPrint(true).serialize(model, System.out);
	}
	
	protected static void serializeMap()
	{
		Map<String, Object> map = new Map<String, Object>();
		map.put("test_boolean", true);
		map.put("test_byte", 123);
		map.put("test_short", 12345);
		map.put("test_integer", 12345678);
		map.put("test_long", 123456789L);
		map.put("test_float", 1.2345679f);
		map.put("test_double", 1.2345679d);
		map.put("test_character", 'a');
		map.put("test_string", "test");
		map.put("test_date", new Date());
		map.put("test_calendar", new GregorianCalendar());
		new JsonMapper(mapperConfig).serialize(map, System.out);
	}
	
	protected static void deserializeModel()
	{
		String data = "{\"null_primitive_boolean\":false,\"null_primitive_byte\":0,\"null_primitive_short\":0,\"null_primitive_integer\":0,\"null_primitive_long\":0,\"null_primitive_float\":0.0,\"null_primitive_double\":0.0,\"null_primitive_character\":\" \",\"null_wrapper_boolean\":null,\"null_wrapper_byte\":null,\"null_wrapper_short\":null,\"null_wrapper_integer\":null,\"null_wrapper_long\":null,\"null_wrapper_float\":null,\"null_wrapper_double\":null,\"null_wrapper_character\":null,\"null_string\":null,\"null_date\":null,\"null_calendar\":null,\"test_boolean\":true,\"test_byte\":123,\"test_short\":12345,\"test_integer\":12345678,\"test_long\":123456789,\"test_float\":1.2345679,\"test_double\":1.23456789,\"test_character\":\"a\",\"test_string\":\"test\",\"test_date\":\"2015-01-12 22:48:34\",\"test_calendar\":\"2015-01-12 22:48:34\",\"null_string_array\":null,\"empty_string_array\":[],\"test_string_array\":[\"one\",\"two\",\"three\"],\"null_string_list\":null,\"empty_string_list\":[],\"test_string_list\":[\"one\",\"two\",\"three\"],\"null_string_map\":null,\"empty_string_map\":{},\"test_string_map\":{\"one\":\"value one\",\"two\":\"value two\",\"three\":\"value three\"},\"test_model_list\":[{\"test_boolean\":true,\"test_byte\":123,\"test_short\":12345,\"test_integer\":12345678,\"test_long\":123456789,\"test_float\":1.2345679,\"test_double\":1.23456789,\"test_character\":\"a\",\"test_string\":\"test\",\"test_date\":\"2015-01-12 22:48:34\",\"test_calendar\":\"2015-01-12 22:48:34\"},{\"test_boolean\":true,\"test_byte\":123,\"test_short\":12345,\"test_integer\":12345678,\"test_long\":123456789,\"test_float\":1.2345679,\"test_double\":1.23456789,\"test_character\":\"a\",\"test_string\":\"test\",\"test_date\":\"2015-01-12 22:48:34\",\"test_calendar\":\"2015-01-12 22:48:34\"}],\"test_model_map\":{\"one\":{\"test_boolean\":true,\"test_byte\":123,\"test_short\":12345,\"test_integer\":12345678,\"test_long\":123456789,\"test_float\":1.2345679,\"test_double\":1.23456789,\"test_character\":\"a\",\"test_string\":\"test\",\"test_date\":\"2015-01-12 22:48:34\",\"test_calendar\":\"2015-01-12 22:48:34\"},\"two\":{\"test_boolean\":true,\"test_byte\":123,\"test_short\":12345,\"test_integer\":12345678,\"test_long\":123456789,\"test_float\":1.2345679,\"test_double\":1.23456789,\"test_character\":\"a\",\"test_string\":\"test\",\"test_date\":\"2015-01-12 22:48:34\",\"test_calendar\":\"2015-01-12 22:48:34\"}}}";
		JsonModel model = new JsonMapper(mapperConfig).setPrettyPrint(true).deserialize(data, JsonModel.class);
		System.out.println(model);
	}
	
	protected static void deserializeMap()
	{
		Map<String, Object> map = new JsonMapper(mapperConfig).deserialize(new File("dev/testMap2.json")); 
		System.out.println(new JsonMapper(mapperConfig).setPrettyPrint(true).serialize(map));
	}
	
}
