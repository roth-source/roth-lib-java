package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class UpdateNodeRequest extends NodeIdRequest
{
	@Property(name = "Label")
	protected String label;
	
	@Property(name = "lpm_displayGroup")
	protected String lpmDisplayGroup;
	
	@Property(name = "Alert_cpu_enabled")
	protected Boolean alertCpuEnabled;
	
	@Property(name = "Alert_cpu_threshold")
	protected Boolean alertCpuThreshold;
	
	@Property(name = "Alert_diskio_enabled")
	protected Boolean alertDiskioEnabled;
	
	@Property(name = "Alert_diskio_threshold")
	protected Boolean alertDiskioThreshold;
	
	@Property(name = "Alert_bwin_enabled")
	protected Boolean alertBwinEnabled;
	
	@Property(name = "Alert_bwin_threshold")
	protected Boolean alertBwinThreshold;
	
	@Property(name = "Alert_bwout_enabled")
	protected Boolean alertBwoutEnabled;
	
	@Property(name = "Alert_bwout_threshold")
	protected Boolean alertBwoutThreshold;
	
	@Property(name = "Alert_bwquota_enabled")
	protected Boolean alertBwquotaEnabled;
	
	@Property(name = "Alert_bwquota_threshold")
	protected Boolean alertBwquotaThreshold;
	
	@Property(name = "backupWindow")
	protected Integer backupWindow;
	
	@Property(name = "backupWeeklyDay")
	protected Integer backupWeeklyDay;
	
	@Property(name = "watchdog")
	protected Boolean watchdog;
	
	@Property(name = "ms_ssh_disabled")
	protected Boolean msSshDisabled;
	
	@Property(name = "ms_ssh_user")
	protected String msSshUser;
	
	@Property(name = "ms_ssh_ip")
	protected String msSshIp;
	
	@Property(name = "ms_ssh_port")
	protected Integer msSshPort;
	
	public UpdateNodeRequest(Integer linodeId)
	{
		super();
		setLinodeId(linodeId);
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getLpmDisplayGroup()
	{
		return lpmDisplayGroup;
	}
	
	public Boolean getAlertCpuEnabled()
	{
		return alertCpuEnabled;
	}
	
	public Boolean getAlertCpuThreshold()
	{
		return alertCpuThreshold;
	}
	
	public Boolean getAlertDiskioEnabled()
	{
		return alertDiskioEnabled;
	}
	
	public Boolean getAlertDiskioThreshold()
	{
		return alertDiskioThreshold;
	}
	
	public Boolean getAlertBwinEnabled()
	{
		return alertBwinEnabled;
	}
	
	public Boolean getAlertBwinThreshold()
	{
		return alertBwinThreshold;
	}
	
	public Boolean getAlertBwoutEnabled()
	{
		return alertBwoutEnabled;
	}
	
	public Boolean getAlertBwoutThreshold()
	{
		return alertBwoutThreshold;
	}
	
	public Boolean getAlertBwquotaEnabled()
	{
		return alertBwquotaEnabled;
	}
	
	public Boolean getAlertBwquotaThreshold()
	{
		return alertBwquotaThreshold;
	}
	
	public Integer getBackupWindow()
	{
		return backupWindow;
	}
	
	public Integer getBackupWeeklyDay()
	{
		return backupWeeklyDay;
	}
	
	public Boolean getWatchdog()
	{
		return watchdog;
	}
	
	public Boolean getMsSshDisabled()
	{
		return msSshDisabled;
	}
	
	public String getMsSshUser()
	{
		return msSshUser;
	}
	
	public String getMsSshIp()
	{
		return msSshIp;
	}
	
	public Integer getMsSshPort()
	{
		return msSshPort;
	}
	
	public UpdateNodeRequest setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public UpdateNodeRequest setLpmDisplayGroup(String lpmDisplayGroup)
	{
		this.lpmDisplayGroup = lpmDisplayGroup;
		return this;
	}
	
	public UpdateNodeRequest setAlertCpuEnabled(Boolean alertCpuEnabled)
	{
		this.alertCpuEnabled = alertCpuEnabled;
		return this;
	}
	
	public UpdateNodeRequest setAlertCpuThreshold(Boolean alertCpuThreshold)
	{
		this.alertCpuThreshold = alertCpuThreshold;
		return this;
	}
	
	public UpdateNodeRequest setAlertDiskioEnabled(Boolean alertDiskioEnabled)
	{
		this.alertDiskioEnabled = alertDiskioEnabled;
		return this;
	}
	
	public UpdateNodeRequest setAlertDiskioThreshold(Boolean alertDiskioThreshold)
	{
		this.alertDiskioThreshold = alertDiskioThreshold;
		return this;
	}
	
	public UpdateNodeRequest setAlertBwinEnabled(Boolean alertBwinEnabled)
	{
		this.alertBwinEnabled = alertBwinEnabled;
		return this;
	}
	
	public UpdateNodeRequest setAlertBwinThreshold(Boolean alertBwinThreshold)
	{
		this.alertBwinThreshold = alertBwinThreshold;
		return this;
	}
	
	public UpdateNodeRequest setAlertBwoutEnabled(Boolean alertBwoutEnabled)
	{
		this.alertBwoutEnabled = alertBwoutEnabled;
		return this;
	}
	
	public UpdateNodeRequest setAlertBwoutThreshold(Boolean alertBwoutThreshold)
	{
		this.alertBwoutThreshold = alertBwoutThreshold;
		return this;
	}
	
	public UpdateNodeRequest setAlertBwquotaEnabled(Boolean alertBwquotaEnabled)
	{
		this.alertBwquotaEnabled = alertBwquotaEnabled;
		return this;
	}
	
	public UpdateNodeRequest setAlertBwquotaThreshold(Boolean alertBwquotaThreshold)
	{
		this.alertBwquotaThreshold = alertBwquotaThreshold;
		return this;
	}
	
	public UpdateNodeRequest setBackupWindow(Integer backupWindow)
	{
		this.backupWindow = backupWindow;
		return this;
	}
	
	public UpdateNodeRequest setBackupWeeklyDay(Integer backupWeeklyDay)
	{
		this.backupWeeklyDay = backupWeeklyDay;
		return this;
	}
	
	public UpdateNodeRequest setWatchdog(Boolean watchdog)
	{
		this.watchdog = watchdog;
		return this;
	}
	
	public UpdateNodeRequest setMsSshDisabled(Boolean msSshDisabled)
	{
		this.msSshDisabled = msSshDisabled;
		return this;
	}
	
	public UpdateNodeRequest setMsSshUser(String msSshUser)
	{
		this.msSshUser = msSshUser;
		return this;
	}
	
	public UpdateNodeRequest setMsSshIp(String msSshIp)
	{
		this.msSshIp = msSshIp;
		return this;
	}
	
	public UpdateNodeRequest setMsSshPort(Integer msSshPort)
	{
		this.msSshPort = msSshPort;
		return this;
	}
	
}
