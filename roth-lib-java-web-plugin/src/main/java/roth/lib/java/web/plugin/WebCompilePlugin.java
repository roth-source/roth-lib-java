package roth.lib.java.web.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import roth.lib.java.web.compile.WebCompile;

@Mojo(name = "webcompile")
public class WebCompilePlugin extends AbstractMojo
{
	
	public WebCompilePlugin()
	{
		
	}
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException
	{
		try
		{
			WebCompile webCompile = new WebCompile();
			webCompile.compile();
		}
		catch(Exception e)
		{
			throw new MojoFailureException(e.getMessage(), e);
		}
	}
	
}
