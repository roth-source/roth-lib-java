package roth.lib.java.code.java;

import roth.lib.java.lang.List;

@SuppressWarnings("serial")
public class JavaCompilationUnit extends JavaCode
{
	protected JavaDocComment docComment;
	protected JavaPackage _package;
	protected List<JavaImport> imports = new List<JavaImport>();
	protected List<JavaEntity> entities = new List<JavaEntity>();
	
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
	
	public List<JavaImport> getImports()
	{
		return imports;
	}
	
	public void setImports(List<JavaImport> imports)
	{
		this.imports = imports;
	}
	
	public List<JavaEntity> getEntities()
	{
		return entities;
	}
	
	public void setEntities(List<JavaEntity> entities)
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
