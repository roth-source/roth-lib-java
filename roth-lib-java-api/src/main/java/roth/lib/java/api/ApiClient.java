package roth.lib.java.api;

import java.io.IOException;
import java.lang.reflect.Type;

import roth.lib.java.Generic;
import roth.lib.java.inputter.Inputter;
import roth.lib.java.inputter.MapperInputter;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.net.http.HttpClient;
import roth.lib.java.net.http.HttpHeader;
import roth.lib.java.net.http.HttpHeaders;
import roth.lib.java.net.http.HttpMethod;
import roth.lib.java.net.http.HttpRequest;
import roth.lib.java.net.http.HttpResponse;
import roth.lib.java.net.http.HttpUrl;
import roth.lib.java.outputter.MapperOutputter;
import roth.lib.java.outputter.Outputter;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.type.MimeType;

public abstract class ApiClient<ApiRequest, ApiResponse> extends HttpClient
{
	protected static String RAW_BODY		= "RAW BODY";
	protected static String MAPPED_ENTITY	= "MAPPED ENTITY";
	
	protected MapperType requestMapperType;
	protected MapperType responseMapperType;
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected boolean debug;
	
	public ApiClient(boolean debug, MapperType requestMapperType, MapperType responseMapperType)
	{
		this.debug = debug;
		this.requestMapperType = requestMapperType;
		this.responseMapperType = responseMapperType;
		mapperReflector = MapperReflector.get();
		mapperConfig = MapperConfig.get();
	}
	
	public MapperType getRequestMapperType()
	{
		return requestMapperType;
	}
	
	public MapperType getResponseMapperType()
	{
		return responseMapperType;
	}
	
	public MapperReflector getMapperReflector()
	{
		return mapperReflector;
	}
	
	public MapperConfig getMapperConfig()
	{
		return mapperConfig;
	}
	
	public boolean isDebug()
	{
		return debug;
	}
	
	public ApiClient<ApiRequest, ApiResponse> setDebug(boolean debug)
	{
		this.debug = debug;
		return this;
	}
	
	public void setTimeFormat(String timeFormat)
	{
		getMapperConfig().setTimeFormat(timeFormat);
	}
	
	protected MimeType getRequestContentType()
	{
		MimeType contentType = null;
		switch(getRequestMapperType())
		{
			case JSON:
			{
				contentType = MimeType.APPLICATION_JSON;
				break;
			}
			case XML:
			{
				contentType = MimeType.APPLICATION_XML;
				break;
			}
			case FORM:
			{
				contentType = MimeType.APPLICATION_X_WWW_FORM_URLENCODED;
				break;
			}
			case TABLE:
			case MYSQL:
			{
				break;
			}
		}
		return contentType;
	}
	
	protected MimeType getResponseContentType()
	{
		MimeType contentType = null;
		switch(getResponseMapperType())
		{
			case JSON:
			{
				contentType = MimeType.APPLICATION_JSON;
				break;
			}
			case XML:
			{
				contentType = MimeType.APPLICATION_XML;
				break;
			}
			case FORM:
			{
				contentType = MimeType.APPLICATION_X_WWW_FORM_URLENCODED;
				break;
			}
			case TABLE:
			case MYSQL:
			{
				break;
			}
		}
		return contentType;
	}
	
	protected abstract HttpUrl url();
	
	protected Outputter getOutputter(ApiRequest apiRequest)
	{
		return new MapperOutputter<ApiRequest>(getRequestMapperType(), getMapperReflector(), getMapperConfig(), apiRequest);
	}
	
	protected <T extends ApiResponse> Inputter<T> getInputter(Type type)
	{
		return new MapperInputter<T>(getResponseMapperType(), getMapperReflector(), getMapperConfig(), type);
	}
	
	protected String debugRequest(ApiRequest apiRequest)
	{
		return getMapperReflector().getMapper(getRequestMapperType(), getMapperConfig()).setPrettyPrint(true).serialize(apiRequest);
	}
	
	protected String debugResponse(ApiResponse apiResponse)
	{
		return getMapperReflector().getMapper(getResponseMapperType(), getMapperConfig()).setPrettyPrint(true).serialize(apiResponse);
	}
	
	protected String debugBody(String body)
	{
		return getMapperReflector().getMapper(getResponseMapperType(), getMapperConfig()).prettyPrint(body);
	}
	
	protected void setHeaders(HttpHeaders headers)
	{
		
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, HttpHeader... headers)
	{
		return get(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return get(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, Type type, HttpHeader... headers)
	{
		return get(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return get(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return get(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.GET, false, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, HttpHeader... headers)
	{
		return post(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return post(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, Type type, HttpHeader... headers)
	{
		return post(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return post(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return post(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.POST, false, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, HttpHeader... headers)
	{
		return put(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return put(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, Type type, HttpHeader... headers)
	{
		return put(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return put(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return put(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.PUT, false, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, HttpHeader... headers)
	{
		return delete(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return delete(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, Type type, HttpHeader... headers)
	{
		return delete(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return delete(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return delete(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.DELETE, false, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, HttpHeader... headers)
	{
		return postGzip(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return postGzip(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, Type type, HttpHeader... headers)
	{
		return postGzip(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return postGzip(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return postGzip(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.POST, true, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, HttpHeader... headers)
	{
		return putGzip(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return putGzip(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, Type type, HttpHeader... headers)
	{
		return putGzip(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return putGzip(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return putGzip(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.PUT, true, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, HttpHeader... headers)
	{
		return deleteGzip(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return deleteGzip(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, Type type, HttpHeader... headers)
	{
		return deleteGzip(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return deleteGzip(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return deleteGzip(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connect(url, apiRequest, type, HttpMethod.DELETE, true, headers);
	}
	
	protected <T extends ApiResponse> T connect(HttpUrl url, ApiRequest apiRequest, Type type, HttpMethod method, boolean gzip, HttpHeader... headers)
	{
		HttpResponse<T> response = connectResponse(url, apiRequest, type, method, gzip, headers);
		return response.getEntity();
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, HttpHeader... headers)
	{
		return getResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return getResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return getResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return getResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return getResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.GET, false, headers);
	}

	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, HttpHeader... headers)
	{
		return postResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return postResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return postResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return postResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return postResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.POST, false, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, HttpHeader... headers)
	{
		return putResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return putResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return putResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return putResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return putResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.PUT, false, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, HttpHeader... headers)
	{
		return deleteResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return deleteResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return deleteResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return deleteResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return deleteResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.DELETE, false, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, HttpHeader... headers)
	{
		return postGzipResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return postGzipResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return postGzipResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return postGzipResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return postGzipResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.POST, true, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, HttpHeader... headers)
	{
		return putGzipResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return putGzipResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return putGzipResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return putGzipResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return putGzipResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.PUT, true, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, HttpHeader... headers)
	{
		return deleteGzipResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, Generic<T> generic, HttpHeader... headers)
	{
		return deleteGzipResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, Type type, HttpHeader... headers)
	{
		return deleteGzipResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, ApiRequest apiRequest, HttpHeader... headers)
	{
		return deleteGzipResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpHeader... headers)
	{
		return deleteGzipResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.DELETE, true, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> connectResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpMethod method, boolean gzip, HttpHeader...headers)
	{
		try
		{
			HttpRequest<ApiRequest> request = new HttpRequest<ApiRequest>();
			request.setMethod(method);
			request.setUrl(url);
			HttpHeaders httpHeaders = url.getHeaders().setHeaders(headers);
			MimeType requestContentType = getRequestContentType();
			if(requestContentType != null)
			{
				httpHeaders.setContentType(requestContentType);
			}
			MimeType responseContentType = getResponseContentType();
			if(requestContentType != null)
			{
				httpHeaders.setAccept(responseContentType);
			}
			setHeaders(httpHeaders);
			request.setHeaders(httpHeaders);
			if(apiRequest != null)
			{
				if(apiRequest instanceof Outputter)
				{
					request.setOutputter(((Outputter) apiRequest).setGzip(gzip));
				}
				else
				{
					request.setOutputter(getOutputter(apiRequest).setGzip(gzip));
				}
			}
			if(isDebug())
			{
				System.out.print(request);
				if(apiRequest != null && !(apiRequest instanceof Serializer))
				{
					System.out.println(debugRequest(apiRequest));
				}
			}
			Inputter<T> inputter = null;
			if(type != null)
			{
				inputter = getInputter(type);
			}
			HttpResponse<T> response = connect(request, inputter, isDebug());
			T apiResponse = response.getEntity();
			if(isDebug())
			{
				System.out.print(response);
				String body = response.getBody();
				if(body != null)
				{
					System.out.println(RAW_BODY);
					System.out.println(debugBody(body));
				}
				if(apiResponse != null)
				{
					System.out.println(MAPPED_ENTITY);
					System.out.println(debugResponse(apiResponse));
				}
			}
			checkResponse(response);
			return response;
		}
		catch(IOException e)
		{
			throw new ApiException(e);
		}
	}
	
	protected <T extends ApiResponse> void checkResponse(HttpResponse<T> response)
	{
		
	}
	
}
