package roth.lib.java.server;

import java.io.File;
import java.lang.reflect.Method;

public class Server
{
	
	public Server()
	{
		
	}
	
	
	
	public static void main(String[] args) throws Exception
	{
		try(ServerClassLoader classLoader = new ServerClassLoader(new File("war/test-war-1.war")))
		{
			Class<?> klass = classLoader.findClass("test.war.TestWar");
			Object test = klass.newInstance();
			Method method = klass.getDeclaredMethod("print");
			method.invoke(test);
		}
		try(ServerClassLoader classLoader = new ServerClassLoader(new File("war/test-war-2.war")))
		{
			Class<?> klass = classLoader.findClass("test.war.TestWar");
			Object test = klass.newInstance();
			Method method = klass.getDeclaredMethod("print");
			method.invoke(test);
		}
	}
	
}
