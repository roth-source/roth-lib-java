package roth.lib.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import roth.lib.Characters;

public abstract class Inputter<T>
{
	
	protected Inputter()
	{
		
	}
	
	public T input(InputStream input) throws IOException
	{
		return input(new InputStreamReader(input, Characters.UTF_8));
	}
	
	public T input(Reader reader) throws IOException
	{
		return input(new BufferedReader(reader));
	}
	
	public T input(BufferedReader reader) throws IOException
	{
		return null;
	}
	
}
