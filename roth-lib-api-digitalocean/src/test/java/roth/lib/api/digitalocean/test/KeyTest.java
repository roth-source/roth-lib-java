package roth.lib.api.digitalocean.test;

import roth.lib.api.digitalocean.key.CreateKeyRequest;

public class KeyTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getKeys();
		//getKey();
		//createKey();
		//updateKey();
		//deleteKey();
	}
	
	public static void getKeys()
	{
		clientFactory.getKeyClient().getKeys(1);
	}
	
	public static void getKey()
	{
		clientFactory.getKeyClient().getKey("d6:66:d4:f2:c5:96:82:e8:37:53:df:e7:c7:e5:4f:38");
	}
	
	public static void createKey()
	{
		clientFactory.getKeyClient().createKey(new CreateKeyRequest("Test", "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC2HMlIKl3SbqE4LZ96Kre34sU8joGaHWUIWb3EcqMc3Vf/JY8c+jYxtxtXdJN7MKr5as38JElsP+flvdnDVekv9CZg0aLwp0I3sa3pbPHIVxu6+q95OzouGnk22iVJpJwkMgQ8uTVZWlqLUwl1ehH3OPEGmnVcX1u8Lrm39uRfqf2wPxtlH2RZW9xGmluLfwVZ05NtXmQdm8kcfqLkxikX9u4i3m8LUdZiiTQ2gKJ0XVT+YZ00K9/r9IZoPBcvO6M1sCG+FmRl60zVwMt8UcsKdrY0Zv5yV1SQHykc/Y+5U0TU6nUQN8+kKRsC4J05QzwkU9RhhZewj5JRokxX3dbj User@office-mini.local"));
	}
	
	public static void updateKey()
	{
		clientFactory.getKeyClient().updateKey("d6:66:d4:f2:c5:96:82:e8:37:53:df:e7:c7:e5:4f:38", "test");
	}
	
	public static void deleteKey()
	{
		clientFactory.getKeyClient().deleteKey("d6:66:d4:f2:c5:96:82:e8:37:53:df:e7:c7:e5:4f:38");
	}
	
}
