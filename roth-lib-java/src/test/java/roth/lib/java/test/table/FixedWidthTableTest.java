package roth.lib.java.test.table;

import java.io.File;

import roth.lib.java.lang.List;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.table.FixedWidthTableMapper;

public class FixedWidthTableTest
{
	
	public static void main(String[] args) throws Exception
	{
		deserializeList();
	}
	
	public static void deserializeList()
	{
		List<List<String>> rows = new FixedWidthTableMapper(new MapperConfig().setTableHeader(false)).deserializeList(new File("dev/testFixed.txt"), new List<Integer>(7, 8, 1));
		for(List<String> row : rows)
		{
			System.out.println(row);
		}
	}
	
}
