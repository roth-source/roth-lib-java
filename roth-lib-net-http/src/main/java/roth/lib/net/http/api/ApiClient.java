package roth.lib.net.http.api;

import java.io.IOException;
import java.lang.reflect.Type;

import roth.lib.map.Deserializer;
import roth.lib.map.Generic;
import roth.lib.map.Serializer;
import roth.lib.net.http.HttpClient;
import roth.lib.net.http.HttpHeader;
import roth.lib.net.http.HttpMethod;
import roth.lib.net.http.HttpRequest;
import roth.lib.net.http.HttpRequestHeaders;
import roth.lib.net.http.HttpResponse;
import roth.lib.net.http.HttpUrl;

public abstract class ApiClient<ApiRequest, ApiResponse> extends HttpClient
{
	protected boolean debug;
	
	public ApiClient(boolean debug)
	{
		this.debug = debug;
	}
	
	public boolean isDebug()
	{
		return debug;
	}
	
	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
	
	protected abstract HttpUrl url();
	protected abstract Serializer<ApiRequest> getSerializer(ApiRequest apiRequest);
	protected abstract <T extends ApiResponse> Deserializer<T> getDeserializer(Type type);
	protected abstract String debugRequest(ApiRequest apiRequest);
	protected abstract String debugResponse(ApiResponse apiResponse);
	protected abstract String debugBody(String body);
	
	protected HttpRequestHeaders getRequestHeaders(HttpUrl url)
	{
		return url.getHeaders();
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
	
	protected <T extends ApiResponse> HttpResponse<T> connectResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpMethod method, boolean gzip, HttpHeader... headers)
	{
		return connectResponse(url, apiRequest, type, method, gzip, getRequestHeaders(url).setHeaders(headers));
	}

	protected <T extends ApiResponse> T get(HttpUrl url, HttpRequestHeaders headers)
	{
		return get(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return get(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return get(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return get(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return get(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T get(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.GET, false, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, HttpRequestHeaders headers)
	{
		return post(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return post(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return post(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return post(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return post(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T post(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.POST, false, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, HttpRequestHeaders headers)
	{
		return put(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return put(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return put(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return put(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return put(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T put(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.PUT, false, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, HttpRequestHeaders headers)
	{
		return delete(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return delete(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return delete(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return delete(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return delete(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T delete(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.DELETE, false, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, HttpRequestHeaders headers)
	{
		return postGzip(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return postGzip(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return postGzip(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return postGzip(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return postGzip(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T postGzip(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.POST, true, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, HttpRequestHeaders headers)
	{
		return putGzip(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return putGzip(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return putGzip(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return putGzip(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return putGzip(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T putGzip(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.PUT, true, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, HttpRequestHeaders headers)
	{
		return deleteGzip(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return deleteGzip(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return deleteGzip(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return deleteGzip(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return deleteGzip(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> T deleteGzip(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connect(url, apiRequest, type, HttpMethod.DELETE, true, headers);
	}
	
	protected <T extends ApiResponse> T connect(HttpUrl url, ApiRequest apiRequest, Type type, HttpMethod method, boolean gzip, HttpRequestHeaders headers)
	{
		HttpResponse<T> response = connectResponse(url, apiRequest, type, method, gzip, headers);
		return response.getEntity();
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return getResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return getResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return getResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return getResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return getResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> getResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.GET, false, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return postResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return postResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return postResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return postResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return postResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.POST, false, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return putResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return putResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return putResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return putResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return putResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.PUT, false, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return deleteResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return deleteResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return deleteResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return deleteResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return deleteResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.DELETE, false, headers);
	}

	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return postGzipResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return postGzipResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return postGzipResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return postGzipResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return postGzipResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> postGzipResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.POST, true, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return putGzipResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return putGzipResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return putGzipResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return putGzipResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return putGzipResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> putGzipResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.PUT, true, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, HttpRequestHeaders headers)
	{
		return deleteGzipResponse(url, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, Generic<T> generic, HttpRequestHeaders headers)
	{
		return deleteGzipResponse(url, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, Type type, HttpRequestHeaders headers)
	{
		return deleteGzipResponse(url, null, type, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, ApiRequest apiRequest, HttpRequestHeaders headers)
	{
		return deleteGzipResponse(url, apiRequest, (Type) null, headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, ApiRequest apiRequest, Generic<T> generic, HttpRequestHeaders headers)
	{
		return deleteGzipResponse(url, apiRequest, generic.getType(), headers);
	}
	
	protected <T extends ApiResponse> HttpResponse<T> deleteGzipResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpRequestHeaders headers)
	{
		return connectResponse(url, apiRequest, type, HttpMethod.DELETE, true, headers);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends ApiResponse> HttpResponse<T> connectResponse(HttpUrl url, ApiRequest apiRequest, Type type, HttpMethod method, boolean gzip, HttpRequestHeaders headers)
	{
		try
		{
			HttpRequest<ApiRequest> request = new HttpRequest<ApiRequest>();
			request.setMethod(method);
			request.setUrl(url);
			request.setHeaders(headers);
			if(apiRequest != null)
			{
				if(apiRequest instanceof Serializer)
				{
					request.setSerializer(((Serializer<ApiRequest>) apiRequest).setGzip(gzip));
				}
				else
				{
					request.setSerializer(getSerializer(apiRequest).setGzip(gzip));
				}
			}
			if(debug)
			{
				System.out.print(request);
				if(apiRequest != null && !(apiRequest instanceof Serializer))
				{
					System.out.println(debugRequest(apiRequest));
				}
			}
			Deserializer<T> deserializer = null;
			if(type != null)
			{
				deserializer = getDeserializer(type);
			}
			HttpResponse<T> response = connect(request, deserializer, debug);
			T apiResponse = response.getEntity();
			if(debug)
			{
				System.out.print(response);
				String body = response.getBody();
				if(body != null)
				{
					System.out.println("BODY");
					System.out.println(debugBody(body));
				}
				if(apiResponse != null)
				{
					System.out.println("ENTITY");
					System.out.println(debugResponse(apiResponse));
				}
			}
			check(response);
			return response;
		}
		catch(IOException e)
		{
			throw new ApiException(e);
		}
	}
	
	protected abstract <T extends ApiResponse> void check(HttpResponse<T> response);
	
}
