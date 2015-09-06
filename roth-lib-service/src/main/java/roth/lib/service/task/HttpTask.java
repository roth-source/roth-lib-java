package roth.lib.service.task;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import roth.lib.service.util.TaskUtil;
import roth.lib.util.ThreadUtil;

public abstract class HttpTask implements Runnable, Callable<Boolean>
{
	protected static String NEWLINE_INDENT	= "\n     ";
	
	protected HttpSession session;
	protected String taskId;
	protected long startTime;
	
	public HttpTask(HttpSession session, String taskId)
	{
		this.session = session;
		this.taskId = taskId;
		this.startTime = System.currentTimeMillis();
	}
	
	@Override
	public Boolean call()
	{
		run();
		return true;
	}
	
	protected String getElapsedTime()
	{
		long milliseconds = System.currentTimeMillis() - startTime;
		long minutes = (int) ((milliseconds / (1000 * 60)) % 60);
		int seconds = (int) (milliseconds / 1000) % 60 ;
		return String.format("%02d:%02d", minutes, seconds);
	}
	
	protected void startAction(String section)
	{
		addProgress(getElapsedTime() + " " + section + NEWLINE_INDENT);
	}
	
	protected void updateAction(String action)
	{
		updateAction(action, false);
	}
	
	protected void updateAction(String action, boolean newLine)
	{
		addProgress((newLine ? NEWLINE_INDENT : "") + " > " + action);
	}

	protected void waitAction(int seconds)
	{
		updateAction(String.format("waiting %d secs", seconds));
		ThreadUtil.waitSeconds(seconds);
	}
	
	protected void endAction()
	{
		addProgress(" > done\n\n");
	}
	
	protected void addProgress(String progress)
	{
		TaskUtil.addProgress(session, taskId, progress);
	}
	
	protected void completeTask()
	{
		addProgress(getElapsedTime() + " COMPLETE");
		ThreadUtil.waitSeconds(10);
		TaskUtil.completeProgress(session, taskId);
	}
	
}
