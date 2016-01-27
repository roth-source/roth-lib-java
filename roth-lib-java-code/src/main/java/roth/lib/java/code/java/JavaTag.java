package roth.lib.java.code.java;

import java.util.regex.Pattern;

import roth.lib.java.lang.List;

public enum JavaTag
{
	// GROUPS
	UNIT,
	BRACES,
	PARENS,
	BRACKETS,
	ANGLE_BRACKETS,
	
	// ENTITY
	CLASS,
	INTERFACE,
	ENUM,
	
	// ACCESS
	PUBLIC,
	PROTECTED,
	PRIVATE,
	
	// MODIFIER
	STATIC,
	FINAL,
	ABSTRACT,
	SYNCHRONIZED,
	VOLATILE,
	TRANSIENT,
	NATIVE,
	STRICTFP,
	DEFAULT,
	
	// PRIMITIVE TYPE
	BOOLEAN,
	BYTE,
	SHORT,
	INT,
	LONG,
	FLOAT,
	DOUBLE,
	CHAR,
	VOID,
	
	// PRIMITIVE LITERAL
	NULL,
	TRUE,
	FALSE,
	
	// DECLARATION
	PACKAGE,
	IMPORT,
	EXTENDS,
	IMPLEMENTS,
	THROWS,
	SUPER,
	
	// STATEMENT
	IF,
	ELSE,
	FOR,
	WHILE,
	DO,
	SWITCH,
	CASE,
	BREAK,
	CONTINUE,
	TRY,
	CATCH,
	FINALLY,
	
	// EXPRESSION
	NEW,
	THIS,
	RETURN,
	THROW,
	INSTANCEOF,
	ASSERT,
	
	// PRIMITIVE VALUE
	STRING,
	CHARACTER,
	NUMBER,
		
	// OTHER
	END,
	SPACE,
	NAME,
	COMMENT,
	OPERATOR,
	SELECTOR,
	SEPERATOR,
	REFERENCE,
	LAMBDA,
	WILDCARD,
	ANNOTATION,
	VARARGS,
	
	;
	
	public static final Pattern INTEGER_PATTERN = Pattern.compile("^[-]?[\\d][xb]?[0-9a-fA-F_]*[Ll]?$");
	public static final Pattern FLOATING_PATTERN = Pattern.compile("^[-]?[\\d_]+[\\.][\\d_]+(?:[Ee][-]?[\\d_]+)?[FfDd]?$");
	
	public static final List<JavaTag> GROUP_TAGS = new List<JavaTag>(UNIT, BRACES, PARENS, BRACKETS, ANGLE_BRACKETS);
	public static final List<JavaTag> ENTITY_TAGS = new List<JavaTag>(CLASS, INTERFACE, ENUM);
	public static final List<JavaTag> ACCESS_TAGS = new List<JavaTag>(PUBLIC, PROTECTED, PRIVATE);
	public static final List<JavaTag> MODIFIER_TAGS = new List<JavaTag>(STATIC, FINAL, ABSTRACT, SYNCHRONIZED, VOLATILE, TRANSIENT, NATIVE, STRICTFP, DEFAULT);
	public static final List<JavaTag> PRIMITIVE_TYPE_TAGS = new List<JavaTag>(BOOLEAN, BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, CHAR, VOID);
	public static final List<JavaTag> PRIMITIVE_LITERAL_TAGS = new List<JavaTag>(NULL, TRUE, FALSE);
	public static final List<JavaTag> DECLARATION_TAGS = new List<JavaTag>(PACKAGE, IMPORT, EXTENDS, IMPLEMENTS, THROWS, SUPER);
	public static final List<JavaTag> STATEMENT_TAGS = new List<JavaTag>(IF, ELSE, FOR, WHILE, DO, SWITCH, CASE, BREAK, CONTINUE, TRY, CATCH, FINALLY);
	public static final List<JavaTag> EXPRESSION_TAGS = new List<JavaTag>(NEW, THIS, RETURN, THROW, INSTANCEOF, ASSERT);
	public static final List<JavaTag> RESERVED_TAGS = new List<JavaTag>();
	public static final List<JavaTag> PRIMITIVE_VALUE_TAGS = new List<JavaTag>(STRING, CHARACTER, NUMBER);
	
	static
	{
		RESERVED_TAGS.addAll(ENTITY_TAGS);
		RESERVED_TAGS.addAll(ACCESS_TAGS);
		RESERVED_TAGS.addAll(MODIFIER_TAGS);
		RESERVED_TAGS.addAll(PRIMITIVE_TYPE_TAGS);
		RESERVED_TAGS.addAll(PRIMITIVE_LITERAL_TAGS);
		RESERVED_TAGS.addAll(DECLARATION_TAGS);
		RESERVED_TAGS.addAll(STATEMENT_TAGS);
		RESERVED_TAGS.addAll(EXPRESSION_TAGS);
		PRIMITIVE_VALUE_TAGS.addAll(PRIMITIVE_LITERAL_TAGS);
	}
	
	public boolean isGroup()
	{
		return GROUP_TAGS.contains(this);
	}
	
	public boolean isEntity()
	{
		return ENTITY_TAGS.contains(this);
	}
	
	public boolean isAccess()
	{
		return ACCESS_TAGS.contains(this);
	}
	
	public boolean isModifier()
	{
		return MODIFIER_TAGS.contains(this);
	}
	
	public boolean isPrimitiveType()
	{
		return PRIMITIVE_TYPE_TAGS.contains(this);
	}
	
	public boolean isPrimitiveLiteral()
	{
		return PRIMITIVE_LITERAL_TAGS.contains(this);
	}
	
	public boolean isDeclaration()
	{
		return DECLARATION_TAGS.contains(this);
	}
	
	public boolean isStatement()
	{
		return STATEMENT_TAGS.contains(this);
	}
	
	public boolean isExpression()
	{
		return EXPRESSION_TAGS.contains(this);
	}
	
	public boolean isReservered()
	{
		return RESERVED_TAGS.contains(this);
	}
	
	public boolean isPrimitiveValue()
	{
		return PRIMITIVE_VALUE_TAGS.contains(this);
	}
	
	public static JavaToken check(JavaToken token)
	{
		if(token != null && !token.getTag().isGroup())
		{
			String value = token.getValue();
			for(JavaTag reservedTag : RESERVED_TAGS)
			{
				if(reservedTag.name().toLowerCase().equals(value))
				{
					token.setTag(reservedTag);
					return null;
				}
			}
			if(INTEGER_PATTERN.matcher(value).find() || FLOATING_PATTERN.matcher(value).find())
			{
				token.setTag(NUMBER);
			}
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
	
}
