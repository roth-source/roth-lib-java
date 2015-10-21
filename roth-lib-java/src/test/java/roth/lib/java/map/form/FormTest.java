package roth.lib.java.map.form;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import roth.lib.java.map.MapperConfig;
import roth.lib.java.map.MapperReflector;
import roth.lib.java.map.form.FormReflector;

public class FormTest
{
	protected static MapperReflector mapperReflector = new FormReflector();
	protected static MapperConfig mapperConfig = new MapperConfig().setPrettyPrinting(true).setSerializeNulls(true).setTimeFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args)
	{
		serializeModel();
		//serializeMap();
		//deserializeModel();
		//deserializeMap();
		//prettyPrint();
	}
	
	protected static void serializeModel()
	{
		FormModel model = new FormModel();
		mapperReflector.getMapper(mapperConfig).serialize(model, System.out);
	}
	
	protected static void serializeMap()
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
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
		mapperReflector.getMapper(mapperConfig).serialize(map, System.out);
	}
	
	protected static void deserializeModel()
	{
		String data = "null_primitive_boolean=false&null_primitive_byte=0&null_primitive_short=0&null_primitive_integer=0&null_primitive_long=0&null_primitive_float=0.0&null_primitive_double=0.0&null_primitive_character=%00&null_wrapper_boolean=&null_wrapper_byte=&null_wrapper_short=&null_wrapper_integer=&null_wrapper_long=&null_wrapper_float=&null_wrapper_double=&null_wrapper_character=&null_string=&null_date=&null_calendar=&test_boolean=true&test_byte=123&test_short=12345&test_integer=12345678&test_long=123456789&test_float=1.2345679&test_double=1.23456789&test_character=a&test_string=test&test_date=2015-01-12+12%3A15%3A03&test_calendar=2015-01-12+12%3A15%3A03";
		FormModel model = mapperReflector.getMapper(mapperConfig).deserialize(data, FormModel.class);
		System.out.println(model);
	}
	
	protected static void deserializeMap()
	{
		String data = "test_boolean=true&test_byte=123&test_short=12345&test_integer=12345678&test_long=123456789&test_float=1.2345679&test_double=1.2345679&test_character=a&test_string=test&test_date=2015-01-12+14%3A05%3A01&test_calendar=2015-01-12+14%3A05%3A01";
		LinkedHashMap<String, Object> map = mapperReflector.getMapper(mapperConfig).deserialize(data);
		System.out.println(map);
	}
	
	protected static void prettyPrint()
	{
		String data = "null_primitive_boolean=false&null_primitive_byte=0&null_primitive_short=0&null_primitive_integer=0&null_primitive_long=0&null_primitive_float=0.0&null_primitive_double=0.0&null_primitive_character=%00&null_wrapper_boolean=&null_wrapper_byte=&null_wrapper_short=&null_wrapper_integer=&null_wrapper_long=&null_wrapper_float=&null_wrapper_double=&null_wrapper_character=&null_string=&null_date=&null_calendar=&test_boolean=true&test_byte=123&test_short=12345&test_integer=12345678&test_long=123456789&test_float=1.2345679&test_double=1.23456789&test_character=a&test_string=test&test_date=2015-01-12+12%3A15%3A03&test_calendar=2015-01-12+12%3A15%3A03";
		System.out.println(mapperReflector.getMapper(mapperConfig).prettyPrint(data));
	}
}
