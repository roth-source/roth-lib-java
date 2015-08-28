package roth.lib.map.inputter;

import java.io.BufferedReader;
import java.io.IOException;


public class StringInputter extends Inputter<String>
{
	
	public StringInputter()
	{
		super();
	}
	
	@Override
	public String input(BufferedReader reader) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[1024];
		while(reader.read(buffer) > -1)
		{
			builder.append(buffer);
		}
		return builder.toString();
	}
	
}
