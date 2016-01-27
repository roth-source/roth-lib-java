package roth.lib.java.template.test;

import roth.lib.java.lang.Map;

import roth.lib.java.template.Template;
import roth.lib.java.util.ResourceUtil;

public class TemplateTest
{
	
	public static void main(String[] args) throws Exception
	{
		test();
	}
	
	protected static void test() throws Exception
	{
		String html = ResourceUtil.toString("template.html");
		Map<String, Object> data = new Map<String, Object>();
		data.put("from", "From");
		data.put("width", 600);
		data.put("showResident", true);
		data.put("row", new RowHelper());
		data.put("fineprint", null);
		data.put("title", "Login for " + "Jared");
		data.put("companyName", "company");
		data.put("profileUrl", null);
		data.put("loginUrl", "" + "/login");
		data.put("contact", "555-555-5555");
		data.put("name", "user");
		data.put("username", "username");
		data.put("password", "password");
		
		Template template = new Template();
		String result = template.render(html, data);
		System.out.println(result);
		System.out.println(template.getVersion());
	}
	
	public static class RowHelper
	{
		private int row = 0;
		
		public String stripe()
		{
			return stripe("#f4f4f4");
		}
		
		public String stripe(String backgroundColor)
		{
			return (++row % 2 != 0) ? "background-color:" + backgroundColor : "";
		}
		
		public void reset()
		{
			row = 0;
		}
		
	}
	
}
