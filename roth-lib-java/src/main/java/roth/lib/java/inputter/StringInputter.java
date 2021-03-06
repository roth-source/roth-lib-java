package roth.lib.java.inputter;

import java.io.BufferedReader;
import java.io.IOException;

import roth.lib.java.util.IoUtil;


public class StringInputter extends Inputter<String>
{
	
	public StringInputter()
	{
		super();
	}
	
	@Override
	public String input(BufferedReader reader) throws IOException
	{
		return IoUtil.toString(reader);
	}
	
}
