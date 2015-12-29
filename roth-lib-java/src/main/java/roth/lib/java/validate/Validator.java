package roth.lib.java.validate;

import roth.lib.java.reflector.EntityReflector;

public interface Validator
{
	public void validate(Object value, Object request, EntityReflector entityReflector) throws ValidatorException;
	
}
