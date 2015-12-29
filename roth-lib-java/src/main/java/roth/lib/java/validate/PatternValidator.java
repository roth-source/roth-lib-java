package roth.lib.java.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PatternValidator extends StringValidator
{
	
	public boolean isValid(String value)
	{
		Pattern pattern = Pattern.compile(getPattern());
		Matcher matcher = pattern.matcher((String) value);
		return matcher.find();
	}
	
	public abstract String getPattern();
	
}
