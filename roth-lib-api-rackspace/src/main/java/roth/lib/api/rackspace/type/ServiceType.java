package roth.lib.api.rackspace.type;

public enum ServiceType
{
	CLOUD_FILES_CDN			("cloudFilesCDN"),
	CLOUD_FILES				("cloudFiles"),
	CLOUD_BLOCK_STORAGE		("cloudBlockStorage"),
	CLOUD_IMAGES			("cloudImages"),
	CLOUD_QUEUES			("cloudQueues"),
	CLOUD_BIGDATA			("cloudBigData"),
	CLOUD_ORCHESTRATION		("cloudOrchestration"),
	CLOUD_SERVERS_OPENSTACK	("cloudServersOpenStack"),
	AUTOSCALE				("autoscale"),
	CLOUD_DATABASES			("cloudDatabases"),
	CLOUD_BACKUP			("cloudBackup"),
	CLOUD_NETWORKS			("cloudNetworks"),
	CLOUD_METRICS			("cloudMetrics"),
	CLOUD_LOADBALANCERS		("cloudLoadBalancers"),
	CLOUD_FEEDS				("cloudFeeds"),
	CLOUD_MONITORING		("cloudMonitoring"),
	CLOUD_DNS				("cloudDNS"),
	CLOUD_SERVERS			("cloudServers"),
	;
	
	protected String name;
	
	ServiceType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
}
