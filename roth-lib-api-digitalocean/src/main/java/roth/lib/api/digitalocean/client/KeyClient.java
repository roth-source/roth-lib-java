package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.KeyRequest;
import roth.lib.api.digitalocean.data.response.KeyResponse;
import roth.lib.api.digitalocean.data.response.KeysResponse;

public class KeyClient extends DigitalOceanClient
{
	
	public KeyClient(String token)
	{
		this(token, false);
	}
	
	public KeyClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public KeysResponse getKeys()
	{
		return get(url(ACCOUNT_KEYS), KeysResponse.class);
	}
	
	public KeysResponse getKeys(int page)
	{
		return get(url(ACCOUNT_KEYS, page), KeysResponse.class);
	}
	
	public KeyResponse getKey(String fingerprint)
	{
		return get(url(ACCOUNT_KEYS + fingerprint), KeyResponse.class);
	}
	
	public KeyResponse createKey(KeyRequest keyRequest)
	{
		return post(url(ACCOUNT_KEYS), keyRequest, KeyResponse.class);
	}
	
	public KeyResponse updateKey(String fingerprint, String name)
	{
		return put(url(ACCOUNT_KEYS + fingerprint), new KeyRequest(name), KeyResponse.class);
	}
	
	public void deleteKey(String fingerprint)
	{
		delete(url(ACCOUNT_KEYS + fingerprint));
	}
	
}
