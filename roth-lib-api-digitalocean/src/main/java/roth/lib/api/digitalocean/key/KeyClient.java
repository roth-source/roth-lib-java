package roth.lib.api.digitalocean.key;

import roth.lib.api.digitalocean.DigitalOceanClient;

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
	
	public GetKeysResponse getKeys()
	{
		return get(url(ACCOUNT_KEYS), GetKeysResponse.class);
	}
	
	public GetKeysResponse getKeys(int page)
	{
		return get(url(ACCOUNT_KEYS, page), GetKeysResponse.class);
	}
	
	public GetKeyResponse getKey(String fingerprint)
	{
		return get(url(ACCOUNT_KEYS + fingerprint), GetKeyResponse.class);
	}
	
	public CreateKeyResponse createKey(CreateKeyRequest keyRequest)
	{
		return post(url(ACCOUNT_KEYS), keyRequest, CreateKeyResponse.class);
	}
	
	public UpdateKeyResponse updateKey(String fingerprint, String name)
	{
		return put(url(ACCOUNT_KEYS + fingerprint), new UpdateKeyRequest(name), UpdateKeyResponse.class);
	}
	
	public void deleteKey(String fingerprint)
	{
		delete(url(ACCOUNT_KEYS + fingerprint));
	}
	
}
