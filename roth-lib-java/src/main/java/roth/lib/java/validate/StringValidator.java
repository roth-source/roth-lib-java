package roth.lib.java.validate;

import roth.lib.java.reflector.EntityReflector;

public abstract class StringValidator implements Validator
{
	
	@Override
	public void validate(Object value, Object request, EntityReflector entityReflector) throws ValidatorException
	{
		boolean valid = false;
		if(value instanceof String)
		{
			valid = isValid((String) value);
		}
		if(!valid)
		{
			throw new ValidatorException();
		}
	}
	
	public abstract boolean isValid(String value);
	
}
