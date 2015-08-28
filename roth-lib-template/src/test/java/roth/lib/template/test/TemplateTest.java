package roth.lib.template.test;

import java.util.LinkedHashMap;

import roth.lib.template.Template;
import roth.lib.template.TemplateConfig;
import roth.lib.util.ResourceUtil;

public class TemplateTest
{
	
	public static void main(String[] args) throws Exception
	{
		test();
	}
	
	protected static void test() throws Exception
	{
		String html = ResourceUtil.toString("template.html");
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("test", new TestModel());
		
		Template template = new Template(new TemplateConfig().setOpenUnescapedExpression("{{-").setCloseUnescapedExpression("-}}"));
		String result = template.render(html, data);
		System.out.println(result);
		System.out.println(template.getVersion());
	}
	
}
