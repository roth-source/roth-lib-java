package roth.lib.java.web.compile.test;

import java.io.File;

import roth.lib.java.util.FileUtil;

public class CompileTest
{
	
	public static void main(String[] args)
	{
		File file = new File("/Users/User/Workspaces/roth-lib-js/roth-lib-js-client-dev/resources/style/select.css");
		String value = FileUtil.toString(file).replaceAll("[\\r\\n\\t]", "").replaceAll("\\\\n", "\\\\\\\\n").replaceAll("\\\\\\\"", "\\\\\\\\\"").replaceAll("\"", "\\\\\"");
		System.out.println("\"" + value + "\"");
	}
	
}
