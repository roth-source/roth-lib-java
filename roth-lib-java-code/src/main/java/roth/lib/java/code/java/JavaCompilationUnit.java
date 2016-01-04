package roth.lib.java.code.java;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class JavaCompilationUnit extends JavaCode
{
	protected JavaDocComment docComment;
	protected JavaPackage _package;
	protected LinkedList<JavaImport> imports = new LinkedList<JavaImport>();
	protected LinkedList<JavaEntity> entities = new LinkedList<JavaEntity>();
	
	public JavaCompilationUnit()
	{
		
	}
	
	public JavaDocComment getDocComment()
	{
		return docComment;
	}
	
	public void setDocComment(JavaDocComment docComment)
	{
		this.docComment = docComment;
	}
	
	public JavaPackage getPackage()
	{
		return _package;
	}
	
	public void setPackage(JavaPackage _package)
	{
		this._package = _package;
	}
	
	public LinkedList<JavaImport> getImports()
	{
		return imports;
	}
	
	public void setImports(LinkedList<JavaImport> imports)
	{
		this.imports = imports;
	}
	
	public LinkedList<JavaEntity> getEntities()
	{
		return entities;
	}
	
	public void setEntities(LinkedList<JavaEntity> entities)
	{
		this.entities = entities;
	}
	
	@Override
	public String toString()
	{
		return toSource(0);
	}
	
	@Override
	public String toSource(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		if(docComment != null)
		{
			builder.append(docComment);
			builder.append(NEW_LINE);
		}
		if(_package != null)
		{
			builder.append(_package);
			builder.append(NEW_LINE);
			builder.append(NEW_LINE);
		}
		if(!imports.isEmpty())
		{
			for(JavaImport _import : imports)
			{
				if(_import != null)
				{
					builder.append(_import);
					builder.append(NEW_LINE);
				}
				else
				{
					builder.append(NEW_LINE);
				}
			}
		}
		for(JavaEntity entity : entities)
		{
			builder.append(entity);
			builder.append(NEW_LINE);
		}
		return builder.toString();
	}
	
}
