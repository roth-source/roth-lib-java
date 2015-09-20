package roth.lib.java.api.rackspace.test;

import java.io.File;

import roth.lib.java.net.http.type.MimeType;

public class FileTest extends Test
{
	
	public static void main(String[] args)
	{
		//createContainer();
		//deleteContainer();
		uploadFile();
	}
	
	protected static void createContainer()
	{
		clientFactory.getFileClient().createContainer("test");
	}
	
	protected static void deleteContainer()
	{
		clientFactory.getFileClient().createContainer("test");
	}
	
	protected static void uploadFile()
	{
		clientFactory.getFileClient().uploadFile("test", "test.tit", MimeType.IMAGE_TIFF.toString(), new File("/Users/User/Downloads/test.tif"));
	}
	
}
