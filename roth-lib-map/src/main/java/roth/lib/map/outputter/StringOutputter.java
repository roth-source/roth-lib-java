package roth.lib.map.outputter;

import java.io.BufferedWriter;
import java.io.IOException;

public class StringOutputter extends Outputter
{
	protected String value;
	
	public StringOutputter()
	{
		
	}
	
	public StringOutputter(String value)
	{
		this.value = value;
	}
	
	public StringOutputter setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public void output(BufferedWriter writer) throws IOException
	{
		if(value != null)
		{
			writer.write(value);
		}
		writer.flush();
	}
	
}
