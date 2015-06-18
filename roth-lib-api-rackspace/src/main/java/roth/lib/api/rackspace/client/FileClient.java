package roth.lib.api.rackspace.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import roth.lib.api.rackspace.data.type.ServiceType;
import roth.lib.map.serializer.BytesSerializer;
import roth.lib.map.serializer.FileSerializer;
import roth.lib.net.http.HttpHeader;
import roth.lib.net.http.HttpRequestHeaders;
import roth.lib.net.http.HttpUrl;
import roth.lib.util.IoUtil;

public class FileClient extends RackspaceClient
{
	
	public FileClient(IdentityClient identityClient, boolean debug)
	{
		super(identityClient, debug);
	}
	
	@Override
	protected HttpUrl url()
	{
		return new HttpUrl(getEndpoint(ServiceType.CLOUD_FILES).getPublicUrl());
	}
	
	protected HttpUrl url(String containerName)
	{
		return url().addPath(SLASH + containerName);
	}
	
	protected HttpUrl url(String containerName, String objectName)
	{
		return url().addPath(SLASH + containerName + SLASH + objectName);
	}
	
	@Override
	protected HttpRequestHeaders getRequestHeaders(HttpUrl url)
	{
		return url.getHeaders().setHeader("X-Auth-Token", getAutoTokenId());
	}
	
	public void createContainer(String name)
	{
		put(url(name));
	}
	
	public void deleteContainer(String name)
	{
		delete(url(name));
	}
	
	public void uploadFile(String container, String filename, String contentType, File file)
	{
		put(url(container, filename), new FileSerializer(file), new HttpHeader("Content-Type", contentType));
	}
	
	public void uploadBytes(String container, String filename, String contentType, byte[] bytes)
	{
		put(url(container, filename), new BytesSerializer(bytes), new HttpHeader("Content-Type", contentType));
	}
	
	public InputStream downloadFile(String container, String filename)
	{
		return getResponse(url(container, filename)).getInput();
	}
	
	public void downloadFile(String container, String filename, File file)
	{
		try(InputStream input = downloadFile(container, filename);
		OutputStream output = new FileOutputStream(file);)
		{
			IoUtil.copy(input, output);
		}
		catch(IOException e)
		{
			throw new RackspaceException(e);
		}
	}
	
	public byte[] downloadBytes(String container, String filename)
	{
		try
		{
			return IoUtil.toBytes(downloadFile(container, filename));
		}
		catch(IOException e)
		{
			throw new RackspaceException(e);
		}
	}
	
	public void deleteFileContainer(String name, String filename)
	{
		delete(url(name, filename));
	}
	
}
