package roth.lib.java.code.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import roth.lib.java.lang.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;

public class JavaParser implements Characters
{
	public static final Pattern NEW_LINE_PATTERN = Pattern.compile(String.valueOf(NEW_LINE));
	
	protected File file;
	protected JavaCompilationUnit compilationUnit;
	
	public JavaParser(File file) throws FileNotFoundException
	{
		this.file = file;
		parse();
	}
	
	public JavaCompilationUnit getCompilationUnit()
	{
		return compilationUnit;
	}
	
	protected void parse() throws FileNotFoundException
	{
		JavaTokenizer sourceTokenizer = new JavaTokenizer(file);
		JavaToken unitToken = sourceTokenizer.getUnitToken();
		if(unitToken != null)
		{
			compilationUnit = new JavaCompilationUnit();
			List<JavaComment> comments = new List<JavaComment>();
			List<JavaToken> queuedTokens = new List<JavaToken>();
			List<JavaToken> tokens = unitToken.getTokens();
			JavaToken token = null;
			while((token = tokens.pollFirst()) != null)
			{
				if(tagEquals(JavaTag.COMMENT, token))
				{
					JavaComment comment = parseComment(token);
					if(comment instanceof JavaDocComment && compilationUnit.getPackage() == null)
					{
						compilationUnit.setDocComment((JavaDocComment) comment);
					}
					else
					{
						comments.add(comment);
					}
					queuedTokens.clear();
				}
				else if(tagEquals(JavaTag.PACKAGE, token))
				{
					JavaPackage _package = parsePackage(tokens);
					if(_package != null)
					{
						_package.getComments().addAll(comments);
						comments.clear();
						compilationUnit.setPackage(_package);
					}
					queuedTokens.clear();
				}
				else if(tagEquals(JavaTag.IMPORT, token))
				{
					JavaImport _import = parseImport(tokens);
					if(_import != null)
					{
						comments.clear();
						compilationUnit.getImports().add(_import);
					}
					JavaToken peekToken = tokens.peekFirst();
					if(tagEquals(JavaTag.SPACE, peekToken))
					{
						Matcher newLineMatcher = NEW_LINE_PATTERN.matcher(peekToken.getValue());
						int lines = 0;
						while(newLineMatcher.find())
						{
							if(lines++ > 0)
							{
								compilationUnit.getImports().add(null);
							}
						}
					}
					queuedTokens.clear();
				}
				else if(tagEquals(JavaTag.BRACES, token))
				{
					queuedTokens.add(token);
					JavaEntity entity = parseEntity(queuedTokens);
					if(entity != null)
					{
						entity.setComments(comments);
						comments.clear();
						compilationUnit.getEntities().add(entity);
					}
					queuedTokens.clear();
				}
				else
				{
					queuedTokens.add(token);
				}
			}
		}
	}
	
	protected JavaComment parseComment(JavaToken token)
	{
		JavaComment comment = null;
		if(token != null)
		{
			String value = token.getValue();
			if(value.startsWith(OPEN_BLOCK_COMMENT))
			{
				comment = (value.startsWith(OPEN_DOC_COMMENT) ? new JavaDocComment() : new JavaBlockComment());
				String[] lines = value.split("\n");
				for(int i = 0; i < lines.length; i++)
				{
					comment.getLines().add(lines[i].trim());
				}
			}
			else if(value.startsWith(INLINE_COMMENT))
			{
				comment = new JavaInlineComment();
				comment.getLines().add(value.trim());
			}
		}
		return comment;
	}
	
	protected JavaInlineComment parseInlineComment(List<JavaToken> tokens)
	{
		JavaInlineComment inlineComment = null;
		int peeks = 0;
		String space = BLANK;
		JavaToken token = null;
		while((token = tokens.peekFirst()) != null)
		{
			peeks++;
			if(tagEquals(JavaTag.SPACE, token))
			{
				if(!token.hasNewLine())
				{
					space = token.getValue();
				}
				else
				{
					break;
				}
			}
			else if(tagEquals(JavaTag.COMMENT, token))
			{
				String value = token.getValue();
				if(value.startsWith(INLINE_COMMENT))
				{
					inlineComment = new JavaInlineComment();
					inlineComment.setSpace(space);
					inlineComment.getLines().add(value);
					for(int i = 0; i < peeks; i++)
					{
						tokens.pollFirst();
					}
					break;
				}
			}
			else
			{
				break;
			}
		}
		return inlineComment;
	}
	
	protected JavaPackage parsePackage(List<JavaToken> tokens)
	{
		JavaPackage _package = new JavaPackage();
		StringBuilder builder = new StringBuilder();
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null && !tagEquals(JavaTag.END, token))
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				builder.append(token.getValue());
			}
			else if(tagEquals(JavaTag.SELECTOR, token))
			{
				builder.append(DOT);
			}
		}
		_package.setName(builder.toString());
		_package.setInlineComment(parseInlineComment(tokens));
		return _package;
	}
	
	protected JavaImport parseImport(List<JavaToken> tokens)
	{
		JavaImport _import = new JavaImport();
		StringBuilder builder = new StringBuilder();
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null && !JavaTag.END.equals(token.getTag()))
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				builder.append(token.getValue());
			}
			else if(tagEquals(JavaTag.SELECTOR, token))
			{
				builder.append(DOT);
			}
			else if(tagEquals(JavaTag.STATIC, token))
			{
				_import.setStatic(true);
			}
		}
		_import.setName(builder.toString());
		_import.setInlineComment(parseInlineComment(tokens));
		return _import;
	}
	
	protected JavaEntity parseEntity(List<JavaToken> tokens)
	{
		JavaEntity entity = null;
		List<JavaAnnotation> annotations = new List<JavaAnnotation>();
		JavaAccess access = null;
		List<JavaModifier> modifiers = new List<JavaModifier>();
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(tagEquals(JavaTag.ANNOTATION, token))
			{
				JavaToken peekToken = tokens.peekFirst();
				if(tagEquals(JavaTag.INTERFACE, peekToken))
				{
					tokens.pollFirst();
					JavaAnnotationInterface annotationInterface = new JavaAnnotationInterface();
					annotationInterface.setAnnotations(annotations);
					annotationInterface.setAccess(access);
					annotationInterface.setModifiers(modifiers);
					parseAnnotationInterface(annotationInterface, tokens);
					entity = annotationInterface;
					break;
				}
				else
				{
					annotations.add(parseAnnotation(tokens));
				}
			}
			else if(tagsContain(JavaTag.ACCESS_TAGS, token))
			{
				access = JavaAccess.parse(token.getTag().name());
			}
			else if(tagsContain(JavaTag.MODIFIER_TAGS, token))
			{
				JavaModifier modifier = JavaModifier.parse(token.getTag().name());
				if(modifier != null)
				{
					modifiers.add(modifier);
				}
			}
			else if(tagEquals(JavaTag.CLASS, token))
			{
				JavaClass _class = new JavaClass();
				_class.setAnnotations(annotations);
				_class.setAccess(access);
				_class.setModifiers(modifiers);
				parseClass(_class, tokens);
				entity = _class;
				break;
			}
			else if(tagEquals(JavaTag.INTERFACE, token))
			{
				JavaInterface _interface = new JavaInterface();
				_interface.setAnnotations(annotations);
				_interface.setAccess(access);
				_interface.setModifiers(modifiers);
				parseInterface(_interface, tokens);
				entity = _interface;
				break;
			}
			else if(tagEquals(JavaTag.ENUM, token))
			{
				JavaEnum _enum = new JavaEnum();
				_enum.setAnnotations(annotations);
				_enum.setAccess(access);
				_enum.setModifiers(modifiers);
				parseEnum(_enum, tokens);
				entity = _enum;
				break;
			}
		}
		return entity;
	}
	
	protected void parseClass(JavaClass klass, List<JavaToken> tokens)
	{
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				tokens.offerFirst(token);
				klass.setName(parseName(tokens));
			}
			else if(tagEquals(JavaTag.ANGLE_BRACKETS, token))
			{
				klass.setDefinitions(parseDefinitions(token.getTokens()));
			}
			else if(tagEquals(JavaTag.EXTENDS, token))
			{
				klass.setSuperType(parseType(tokens));
			}
			else if(tagEquals(JavaTag.IMPLEMENTS, token))
			{
				klass.setInterfaceTypes(parseTypes(tokens));
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				parseClassMembers(klass, token.getTokens());
				break;
			}
		}
	}
	
	protected void parseClassMembers(JavaClass klass, List<JavaToken> tokens)
	{
		boolean reset = false;
		List<JavaComment> comments = new List<JavaComment>();
		List<JavaAnnotation> annotations = new List<JavaAnnotation>();
		JavaAccess access = null;
		List<JavaModifier> modifiers = new List<JavaModifier>();
		List<JavaDefinition> definitions = new List<JavaDefinition>();
		JavaType type = null;
		String name = null;
		JavaToken previousToken = null;
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(tagEquals(JavaTag.COMMENT, token))
			{
				comments.add(parseComment(token));
			}
			else if(tagEquals(JavaTag.ANNOTATION, token))
			{
				JavaToken peekToken = tokens.peekFirst();
				if(tagEquals(JavaTag.INTERFACE, peekToken))
				{
					tokens.pollFirst();
					JavaAnnotationInterface memberAnnotationInterface = new JavaAnnotationInterface();
					memberAnnotationInterface.setComments(comments);
					memberAnnotationInterface.setAnnotations(annotations);
					memberAnnotationInterface.setAccess(access);
					memberAnnotationInterface.setModifiers(modifiers);
					parseAnnotationInterface(memberAnnotationInterface, tokens);
					reset = true;
				}
				else
				{
					annotations.add(parseAnnotation(tokens));
				}
			}
			else if(tagsContain(JavaTag.ACCESS_TAGS, token))
			{
				access = JavaAccess.parse(token.getTag().name());
			}
			else if(tagsContain(JavaTag.MODIFIER_TAGS, token))
			{
				modifiers.add( JavaModifier.parse(token.getTag().name()));
			}
			else if(tagEquals(JavaTag.ANGLE_BRACKETS, token))
			{
				definitions.addAll(parseDefinitions(token.getTokens()));
			}
			else if(tagEquals(JavaTag.NAME, token))
			{
				if(type == null)
				{
					tokens.offerFirst(token);
					type = parseType(tokens);
				}
				else if(name == null)
				{
					tokens.offerFirst(token);
					name = parseName(tokens);
				}
			}
			else if(tagEquals(JavaTag.END, token))
			{
				JavaField field = new JavaField();
				field.setComments(comments);
				field.setAnnotations(annotations);
				field.setAccess(access);
				field.setModifiers(modifiers);
				field.setType(type);
				field.setName(name);
				if(field.isStatic())
				{
					klass.getStaticFields().add(field);
				}
				else
				{
					klass.getFields().add(field);
				}
				reset = true;
			}
			else if(tagEquals(JavaTag.OPERATOR, token))
			{
				JavaField field = new JavaField();
				field.setComments(comments);
				field.setAnnotations(annotations);
				field.setAccess(access);
				field.setModifiers(modifiers);
				field.setType(type);
				field.setName(name);
				StringBuilder builder = new StringBuilder();
				if(tagEquals(JavaTag.SPACE, previousToken))
				{
					builder.append(previousToken.getValue());
				}
				builder.append(token.getValue());
				JavaToken nextToken = tokens.peekFirst();
				if(tagEquals(JavaTag.SPACE, nextToken))
				{
					builder.append(nextToken.getValue());
					tokens.pollFirst();
				}
				field.setAssignment(builder.toString());
				field.setInitializer(parseInitializer(tokens));
				if(field.isStatic())
				{
					klass.getStaticFields().add(field);
				}
				else
				{
					klass.getFields().add(field);
				}
				reset = true;
			}
			else if(tagEquals(JavaTag.PARENS, token))
			{
				if(klass.getName().equals(type.getName()))
				{
					JavaConstructor constructor = new JavaConstructor();
					constructor.setComments(comments);
					constructor.setAnnotations(annotations);
					constructor.setAccess(access);
					tokens.add(0, token);
					parseConstructor(constructor, tokens);
				}
				else
				{
					JavaMethod method = new JavaMethod();
					method.setComments(comments);
					method.setAnnotations(annotations);
					method.setAccess(access);
					method.setModifiers(modifiers);
					tokens.add(0, token);
					parseMethod(method, tokens);
					if(method.isStatic())
					{
						klass.getStaticMethods().add(method);
					}
					else
					{
						klass.getMethods().add(method);
					}
				}
				reset = true;
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				JavaInitializer initializer = new JavaInitializer();
				initializer.setStatic(modifiers.contains(JavaModifier.STATIC));
				initializer.setBlock(parseBlock(token.getTokens()));
				if(initializer.isStatic())
				{
					klass.getStaticInitializers().add(initializer);
				}
				else
				{
					klass.getInitializers().add(initializer);
				}
				reset = true;
			}
			else if(tagEquals(JavaTag.CLASS, token))
			{
				JavaClass memberClass = new JavaClass();
				memberClass.setComments(comments);
				memberClass.setAnnotations(annotations);
				memberClass.setAccess(access);
				memberClass.setModifiers(modifiers);
				parseClass(memberClass, tokens);
				reset = true;
			}
			else if(tagEquals(JavaTag.INTERFACE, token))
			{
				JavaInterface memberInterface = new JavaInterface();
				memberInterface.setComments(comments);
				memberInterface.setAnnotations(annotations);
				memberInterface.setAccess(access);
				memberInterface.setModifiers(modifiers);
				parseInterface(memberInterface, tokens);
				reset = true;
			}
			else if(tagEquals(JavaTag.ENUM, token))
			{
				JavaEnum memberEnum = new JavaEnum();
				memberEnum.setComments(comments);
				memberEnum.setAnnotations(annotations);
				memberEnum.setAccess(access);
				memberEnum.setModifiers(modifiers);
				parseEnum(memberEnum, tokens);
				reset = true;
			}
			if(reset)
			{
				comments.clear();
				annotations.clear();
				access = null;
				modifiers.clear();
				type = null;
				name = null;
				reset = false;
			}
			previousToken = token;
		}
	}
	
	protected void parseInterface(JavaInterface _interface, List<JavaToken> tokens)
	{
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				tokens.offerFirst(token);
				_interface.setName(parseName(tokens));
			}
			else if(tagEquals(JavaTag.ANGLE_BRACKETS, token))
			{
				_interface.setDefinitions(parseDefinitions(token.getTokens()));
			}
			else if(tagEquals(JavaTag.EXTENDS, token))
			{
				_interface.setSuperTypes(parseTypes(tokens));
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				parseInterfaceMembers(_interface, token.getTokens());
				break;
			}
		}
	}
	
	protected void parseInterfaceMembers(JavaInterface _interface, List<JavaToken> tokens)
	{
		
	}
	
	protected void parseEnum(JavaEnum _enum, List<JavaToken> tokens)
	{
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				tokens.offerFirst(token);
				_enum.setName(parseName(tokens));
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				parseEnumMembers(_enum, token.getTokens());
				break;
			}
		}
	}
	
	protected void parseEnumMembers(JavaEnum _enum, List<JavaToken> tokens)
	{
		
	}
	
	protected void parseAnnotationInterface(JavaAnnotationInterface annotationInterface, List<JavaToken> tokens)
	{
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				tokens.offerFirst(token);
				annotationInterface.setName(parseName(tokens));
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				parseAnnotationInterfaceMembers(annotationInterface, token.getTokens());
				break;
			}
		}
	}
	
	protected void parseAnnotationInterfaceMembers(JavaAnnotationInterface annotationInterface, List<JavaToken> tokens)
	{
		
	}
	
	protected void parseConstructor(JavaConstructor constructor, List<JavaToken> tokens)
	{
		JavaToken token = null;
		while((token = peekAfterSpace(tokens)) != null)
		{
			if(tagEquals(JavaTag.PARENS, token))
			{
				tokens.pollFirst();
				constructor.setParameters(parseParameters(token.getTokens()));
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				tokens.pollFirst();
				constructor.setBlock(parseBlock(token.getTokens()));
			}
			else if(tagEquals(JavaTag.COMMENT, token))
			{
				
			}
			else
			{
				break;
			}
		}
	}
	
	protected void parseMethod(JavaMethod method, List<JavaToken> tokens)
	{
		JavaToken token = null;
		while((token = peekAfterSpace(tokens)) != null)
		{
			if(tagEquals(JavaTag.PARENS, token))
			{
				tokens.pollFirst();
				method.setParameters(parseParameters(token.getTokens()));
			}
			else if(tagEquals(JavaTag.BRACES, token))
			{
				tokens.pollFirst();
				method.setBlock(parseBlock(token.getTokens()));
			}
			else if(tagEquals(JavaTag.COMMENT, token))
			{
				
			}
			else
			{
				break;
			}
		}
	}
	
	protected String parseName(List<JavaToken> tokens)
	{
		StringBuilder builder = new StringBuilder();
		JavaToken token = peekAfterSpace(tokens);
		if(tagEquals(JavaTag.NAME, token))
		{
			token = tokens.pollFirst();
			builder.append(token.getValue());
		}
		return builder.toString();
	}
	
	protected String parseQualifiedName(List<JavaToken> tokens)
	{
		StringBuilder builder = new StringBuilder();
		JavaToken token = peekAfterSpace(tokens);
		while(token != null)
		{
			if(tagEquals(JavaTag.NAME, token))
			{
				token = tokens.pollFirst();
				builder.append(token.getValue());
				token = tokens.peekFirst();
			}
			else if(tagEquals(JavaTag.SELECTOR, token))
			{
				token = tokens.pollFirst();
				builder.append(DOT);
				token = peekAfterSpace(tokens);
			}
			else
			{
				break;
			}
		}
		return builder.toString();
	}
	
	protected List<JavaType> parseTypes(List<JavaToken> tokens)
	{
		List<JavaType> types = new List<JavaType>();
		JavaToken token = null;
		JavaType type = null;
		while((type = parseType(tokens)) != null)
		{
			types.add(type);
			token = peekAfterSpace(tokens);
			if(tagEquals(JavaTag.SEPERATOR, token))
			{
				tokens.pollFirst();
			}
			else
			{
				break;
			}
		}
		return types;
	}
	
	protected JavaType parseType(List<JavaToken> tokens)
	{
		JavaType type = new JavaType();
		type.setName(parseQualifiedName(tokens));
		JavaToken token = null;
		while((token = peekAfterSpace(tokens)) != null)
		{
			if(tagEquals(JavaTag.ANGLE_BRACKETS, token))
			{
				token = tokens.pollFirst();
				type.getGenerics().addAll(parseGenerics(token.getTokens()));
			}
			else if(tagEquals(JavaTag.BRACKETS, token))
			{
				token = tokens.pollFirst();
				type.addDimension();
			}
			else
			{
				break;
			}
		}
		return type;
	}
	
	protected List<JavaGeneric> parseGenerics(List<JavaToken> tokens)
	{
		List<JavaGeneric> generics = new List<JavaGeneric>();
		JavaToken token = null;
		JavaGeneric generic = null;
		while((generic = parseGeneric(tokens)) != null)
		{
			generics.add(generic);
			token = peekAfterSpace(tokens);
			if(!tagEquals(JavaTag.SEPERATOR, token))
			{
				break;
			}
		}
		return generics;
	}
	
	protected JavaGeneric parseGeneric(List<JavaToken> tokens)
	{
		JavaGeneric generic = null;
		JavaToken token = peekAfterSpace(tokens);
		if(tagEquals(JavaTag.NAME, token))
		{
			generic = parseType(tokens);
		}
		else if(tagEquals(JavaTag.WILDCARD, token))
		{
			token = tokens.pollFirst();
			generic = parseWildcard(tokens);
		}
		return generic;
	}
	
	protected JavaWildcard parseWildcard(List<JavaToken> tokens)
	{
		JavaWildcard wildcard = null;
		JavaToken token = peekAfterSpace(tokens);
		if(tagEquals(JavaTag.EXTENDS, token))
		{
			token = tokens.pollFirst();
			wildcard = new JavaExtendsWildcard(parseType(tokens));
		}
		else if(tagEquals(JavaTag.SUPER, token))
		{
			token = tokens.pollFirst();
			wildcard = new JavaSuperWildcard(parseType(tokens));
		}
		else
		{
			wildcard = new JavaWildcard();
		}
		return wildcard;
	}
	
	protected JavaValueExpression parseValueExpression(List<JavaToken> tokens)
	{
		JavaValueExpression valueExpression = new JavaValueExpression();
		valueExpression.getBuilder().append(parseValue(tokens, JavaTag.END));
		return valueExpression;
	}
	
	protected String parseValue(List<JavaToken> tokens, JavaTag... untils)
	{
		return parseValue(tokens, Arrays.asList(untils));
	}
	
	protected String parseValue(List<JavaToken> tokens, Collection<JavaTag> untils)
	{
		StringBuilder builder = new StringBuilder();
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null && !tagsContain(untils, token))
		{
			/*
			if(token instanceof ValueToken)
			{
				builder.append(token.getValue());
			}
			else if(token instanceof GroupToken)
			{
				GroupToken groupToken = (GroupToken) token;
				builder.append(groupToken.getOpen());
				builder.append(parseValue(token.getTokens()));
				builder.append(groupToken.getClose());
			}
			*/
		}
		return builder.toString();
	}
	
	protected JavaToken peekAfterSpace(List<JavaToken> tokens)
	{
		JavaToken token = tokens.peekFirst();
		if(tagEquals(JavaTag.SPACE, token))
		{
			token = tokens.pollFirst();
			token = tokens.peekFirst();
		}
		return token;
	}
	
	protected boolean tagsContain(Collection<JavaTag> tags, JavaToken token)
	{
		return tags != null && token != null && tags.contains(token.getTag());
	}
	
	protected boolean tagEquals(JavaTag tag, JavaToken token)
	{
		return tag != null && token != null && tag.equals(token.getTag());
	}
	
	protected JavaAnnotation parseAnnotation(List<JavaToken> tokens)
	{
		JavaAnnotation annotation = new JavaAnnotation();
		annotation.setType(parseType(tokens));
		JavaToken token = peekAfterSpace(tokens);
		if(tagEquals(JavaTag.PARENS, token))
		{
			tokens.pollFirst();
			annotation.setArguments(parseValueExpression(token.getTokens()));
		}
		return annotation;
	}
	
	protected JavaExpression parseInitializer(List<JavaToken> tokens)
	{
		
		return parseValueExpression(tokens);
	}
	
	protected List<JavaDefinition> parseDefinitions(List<JavaToken> tokens)
	{
		List<JavaDefinition> definitions = new List<JavaDefinition>();
		JavaToken token = null;
		JavaDefinition definition = null;
		while((definition = parseDefinition(tokens)) != null)
		{
			definitions.add(definition);
			token = peekAfterSpace(tokens);
			if(!tagEquals(JavaTag.SEPERATOR, token))
			{
				break;
			}
		}
		return definitions;
	}
	
	protected JavaDefinition parseDefinition(List<JavaToken> tokens)
	{
		JavaDefinition definition = null;
		JavaToken token = peekAfterSpace(tokens);
		if(tagEquals(JavaTag.NAME, token))
		{
			definition  = new JavaDefinition();
			definition.setName(parseName(tokens));
			token = peekAfterSpace(tokens);
			if(tagEquals(JavaTag.EXTENDS, token))
			{
				tokens.pollFirst();
				token = peekAfterSpace(tokens);
				if(tagEquals(JavaTag.NAME, token))
				{
					definition.getBoundTypes().add(parseType(tokens));
				}
				while((token = peekAfterSpace(tokens)) != null && "&".equals(token.getValue()))
				{
					token = peekAfterSpace(tokens);
					definition.getBoundTypes().add(parseType(tokens));
				}
			}
		}
		return definition;
	}
	
	protected List<JavaParameter> parseParameters(List<JavaToken> tokens)
	{
		List<JavaParameter> parameters = new List<JavaParameter>();
		JavaParameter parameter = null;
		JavaToken token = null;
		while((token = tokens.pollFirst()) != null)
		{
			if(parameter == null)
			{
				parameter = new JavaParameter();
			}
			if(tagEquals(JavaTag.ANNOTATION, token))
			{
				parameter.getAnnotations().add(parseAnnotation(tokens));
			}
			else if(tagEquals(JavaTag.NAME, token))
			{
				if(parameter.getType() == null)
				{
					parameter.setType(parseType(tokens));
				}
				else
				{
					parameter.setName(parseName(tokens));
				}
			}
			else if(tagEquals(JavaTag.SELECTOR, token))
			{
				if(tagEquals(JavaTag.SELECTOR, tokens.peekFirst()))
				{
					tokens.pollFirst();
					if(tagEquals(JavaTag.SELECTOR, tokens.peekFirst()))
					{
						tokens.pollFirst();
						parameter.setVarargs(true);
					}
				}
			}
			else if(tagEquals(JavaTag.SEPERATOR, token))
			{
				parameters.add(parameter);
				parameter = new JavaParameter();
			}
		}
		if(parameter != null)
		{
			parameters.add(parameter);
		}
		return parameters;
	}
	
	protected JavaBlock parseBlock(List<JavaToken> tokens)
	{
		JavaBlock block = new JavaBlock();
		
		return block;
	}
	
}
