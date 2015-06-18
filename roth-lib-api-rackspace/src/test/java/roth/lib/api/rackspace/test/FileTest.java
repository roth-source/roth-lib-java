package roth.lib.api.rackspace.test;

import java.io.File;

import roth.lib.net.http.header.type.MimeType;

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
		clientFactory.getFileClient().uploadFile("test", "test.tit", MimeType.IMAGE_TIFF.getValue(), new File("/Users/User/Downloads/test.tif"));
	}
	
}
