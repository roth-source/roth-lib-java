package roth.lib.java.web.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

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
			MavenProject project = (MavenProject) getPluginContext().get("project");
			WebCompile webCompile = new WebCompile(false);
			webCompile.updateProjectDir(project.getBasedir());
			webCompile.compile();
		}
		catch(Exception e)
		{
			throw new MojoFailureException(e.getMessage(), e);
		}
	}
	
}
