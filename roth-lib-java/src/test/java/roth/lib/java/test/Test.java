package roth.lib.java.test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import roth.lib.java.json.JsonMapper;
import roth.lib.java.mapper.Mapper;

public class Test
{
	
	
	public static void main(String[] args)
	{
		TestModel model = new TestModel();
		model.setName("Jared");
		model.put("number", 1234);
		model.put("active", true);
		model.put("email", "jared@asdf.com");
		model.put("emails", Arrays.asList("a@a.com", "b@b.com", "c@c.com"));
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("name1", "value1");
		map.put("name2", "value2");
		map.put("name3", "value3");
		model.put("map", map);
		model.put("maps", Arrays.asList(map, map));
		LinkedList<List<String>> arrays = new LinkedList<List<String>>();
		arrays.add(Arrays.asList("a@a.com", "b@b.com", "c@c.com"));
		arrays.add(Arrays.asList("a@a.com", "b@b.com", "c@c.com"));
		arrays.add(Arrays.asList("a@a.com", "b@b.com", "c@c.com"));
		model.put("arrays", arrays);
		
		Mapper mapper = new JsonMapper().setPrettyPrint(true);
		String json = mapper.serialize(model);
		System.out.println(json);
		
		TestModel deserializedModel = mapper.deserialize(json, TestModel.class);
		mapper.serialize(deserializedModel, System.out);
	}
	
}
