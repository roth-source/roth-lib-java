package roth.lib.java.service.util;

import javax.servlet.http.HttpSession;

import roth.lib.java.service.HttpServiceResponse;
import roth.lib.java.util.IdUtil;

public class TaskUtil
{
	protected static String PROGRESS 	= "progress";
	protected static String RESPONSE 	= "response";
	
	protected TaskUtil()
	{
		
	}
	
	public static String initProgress(HttpSession session)
	{
		String taskId = IdUtil.random(10);
		String name = PROGRESS + "-" + taskId;
		session.setAttribute(name, "");
		return taskId;
	}
	
	public static void addProgress(HttpSession session, String taskId, String progress)
	{
		String name = PROGRESS + "-" + taskId;
		Object currentProgress = session.getAttribute(name);
		if(currentProgress != null && currentProgress instanceof String)
		{
			currentProgress += progress;
		}
		else
		{
			currentProgress = progress;
		}
		session.setAttribute(name, currentProgress);
	}
	
	public static String getProgress(HttpSession session, String taskId)
	{
		String progress = null;
		String name = PROGRESS + "-" + taskId;
		Object object = session.getAttribute(name);
		session.setAttribute(name, "");
		if(object != null && object instanceof String)
		{
			progress = (String) object;
		}
		return progress;
	}
	
	public static void completeProgress(HttpSession session, String taskId)
	{
		String name = PROGRESS + "-" + taskId;
		session.removeAttribute(name);
	}
	
	public static void setResponse(HttpSession session, String taskId, HttpServiceResponse response)
	{
		String name = RESPONSE + "-" + taskId;
		session.setAttribute(name, response);
	}
	
	public static HttpServiceResponse getResponse(HttpSession session, String taskId)
	{
		HttpServiceResponse response = null;
		String name = RESPONSE + "-" + taskId;
		Object object = session.getAttribute(name);
		if(object != null && object instanceof HttpServiceResponse)
		{
			response = (HttpServiceResponse) object;
		}
		return response;
	}
	
}
