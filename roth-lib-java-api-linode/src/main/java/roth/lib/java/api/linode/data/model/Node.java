package roth.lib.java.api.linode.data.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Node implements Serializable
{
	@Property(name = "LINODEID")
	protected Integer linodeId;
	
	@Property(name = "DATACENTERID")
	protected Integer datacenterId;
	
	@Property(name = "PLANID")
	protected Integer planId;
	
	@Property(name = "CREATE_DT")
	protected Calendar createDt;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "DISTRIBUTIONVENDOR")
	protected String distributionVendor;
	
	@Property(name = "TOTALXFER")
	protected Integer totalXfer;
	
	@Property(name = "TOTALHD")
	protected Integer totalHd;
	
	@Property(name = "TOTALRAM")
	protected Integer totalRam;
	
	@Property(name = "STATUS")
	protected Integer status;
	
	@Property(name = "BACKUPSENABLED")
	protected Boolean backupsEnabled;
	
	@Property(name = "LPM_DISPLAYGROUP")
	protected String lpmDisplayGroup;
	
	@Property(name = "ALERT_CPU_ENABLED")
	protected Boolean alertCpuEnabled;
	
	@Property(name = "ALERT_CPU_THRESHOLD")
	protected Integer alertCpuThreshold;
	
	@Property(name = "ALERT_DISKIO_ENABLED")
	protected Boolean alertDiskioEnabled;
	
	@Property(name = "ALERT_DISKIO_THRESHOLD")
	protected Integer alertDiskioThreshold;
	
	@Property(name = "ALERT_BWIN_ENABLED")
	protected Boolean alertBwinEnabled;
	
	@Property(name = "ALERT_BWIN_THRESHOLD")
	protected Integer alertBwinThreshold;
	
	@Property(name = "ALERT_BWOUT_ENABLED")
	protected Boolean alertBwoutEnabled;
	
	@Property(name = "ALERT_BWOUT_THRESHOLD")
	protected Integer alertBwoutThreshold;
	
	@Property(name = "ALERT_BWQUOTA_ENABLED")
	protected Boolean alertBwquotaEnabled;
	
	@Property(name = "ALERT_BWQUOTA_THRESHOLD")
	protected Integer alertBwquotaThreshold;
	
	@Property(name = "BACKUPWINDOW")
	protected Integer backupWindow;
	
	@Property(name = "BACKUPWEEKLYDAY")
	protected Integer backupWeeklyDay;
	
	@Property(name = "WATCHDOG")
	protected Boolean watchdog;
	
	public Node()
	{
		
	}
	
	public Integer getLinodeId()
	{
		return linodeId;
	}
	
	public Integer getDataccenterId()
	{
		return datacenterId;
	}
	
	public Integer getPlanId()
	{
		return planId;
	}
	
	public Calendar getCreateDt()
	{
		return createDt;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getDistributionVendor()
	{
		return distributionVendor;
	}
	
	public Integer getTotalXfer()
	{
		return totalXfer;
	}
	
	public Integer getTotalHd()
	{
		return totalHd;
	}
	
	public Integer getTotalRam()
	{
		return totalRam;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public Boolean getBackupsEnabled()
	{
		return backupsEnabled;
	}
	
	public String getLpmDisplayGroup()
	{
		return lpmDisplayGroup;
	}
	
	public Boolean getAlertCpuEnabled()
	{
		return alertCpuEnabled;
	}
	
	public Integer getAlertCpuThreshold()
	{
		return alertCpuThreshold;
	}
	
	public Boolean getAlertDiskioEnabled()
	{
		return alertDiskioEnabled;
	}
	
	public Integer getAlertDiskioThreshold()
	{
		return alertDiskioThreshold;
	}
	
	public Boolean getAlertBwinEnabled()
	{
		return alertBwinEnabled;
	}
	
	public Integer getAlertBwinThreshold()
	{
		return alertBwinThreshold;
	}
	
	public Boolean getAlertBwoutEnabled()
	{
		return alertBwoutEnabled;
	}
	
	public Integer getAlertBwoutThreshold()
	{
		return alertBwoutThreshold;
	}
	
	public Boolean getAlertBwquotaEnabled()
	{
		return alertBwquotaEnabled;
	}
	
	public Integer getAlertBwquotaThreshold()
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
	
	public Node setLinodeId(Integer linodeId)
	{
		this.linodeId = linodeId;
		return this;
	}
	
	public Node setDatacenterId(Integer datacenterId)
	{
		this.datacenterId = datacenterId;
		return this;
	}
	
	public Node setPlanId(Integer planId)
	{
		this.planId = planId;
		return this;
	}
	
	public Node setCreateDt(Calendar createDt)
	{
		this.createDt = createDt;
		return this;
	}
	
	public Node setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public Node setDistributionVendor(String distributionVendor)
	{
		this.distributionVendor = distributionVendor;
		return this;
	}
	
	public Node setTotalXfer(Integer totalXfer)
	{
		this.totalXfer = totalXfer;
		return this;
	}
	
	public Node setTotalHd(Integer totalHd)
	{
		this.totalHd = totalHd;
		return this;
	}
	
	public Node setTotalRam(Integer totalRam)
	{
		this.totalRam = totalRam;
		return this;
	}
	
	public Node setStatus(Integer status)
	{
		this.status = status;
		return this;
	}
	
	public Node setBackupsEnabled(Boolean backupsEnabled)
	{
		this.backupsEnabled = backupsEnabled;
		return this;
	}
	
	public Node setLpmDisplayGroup(String lpmDisplayGroup)
	{
		this.lpmDisplayGroup = lpmDisplayGroup;
		return this;
	}
	
	public Node setAlertCpuEnabled(Boolean alertCpuEnabled)
	{
		this.alertCpuEnabled = alertCpuEnabled;
		return this;
	}
	
	public Node setAlertCpuThreshold(Integer alertCpuThreshold)
	{
		this.alertCpuThreshold = alertCpuThreshold;
		return this;
	}
	
	public Node setAlertDiskioEnabled(Boolean alertDiskioEnabled)
	{
		this.alertDiskioEnabled = alertDiskioEnabled;
		return this;
	}
	
	public Node setAlertDiskioThreshold(Integer alertDiskioThreshold)
	{
		this.alertDiskioThreshold = alertDiskioThreshold;
		return this;
	}
	
	public Node setAlertBwinEnabled(Boolean alertBwinEnabled)
	{
		this.alertBwinEnabled = alertBwinEnabled;
		return this;
	}
	
	public Node setAlertBwinThreshold(Integer alertBwinThreshold)
	{
		this.alertBwinThreshold = alertBwinThreshold;
		return this;
	}
	
	public Node setAlertBwoutEnabled(Boolean alertBwoutEnabled)
	{
		this.alertBwoutEnabled = alertBwoutEnabled;
		return this;
	}
	
	public Node setAlertBwoutThreshold(Integer alertBwoutThreshold)
	{
		this.alertBwoutThreshold = alertBwoutThreshold;
		return this;
	}
	
	public Node setAlertBwquotaEnabled(Boolean alertBwquotaEnabled)
	{
		this.alertBwquotaEnabled = alertBwquotaEnabled;
		return this;
	}
	
	public Node setAlertBwquotaThreshold(Integer alertBwquotaThreshold)
	{
		this.alertBwquotaThreshold = alertBwquotaThreshold;
		return this;
	}
	
	public Node setBackupWindow(Integer backupWindow)
	{
		this.backupWindow = backupWindow;
		return this;
	}
	
	public Node setBackupWeeklyDay(Integer backupWeeklyDay)
	{
		this.backupWeeklyDay = backupWeeklyDay;
		return this;
	}
	
	public Node setWatchdog(Boolean watchdog)
	{
		this.watchdog = watchdog;
		return this;
	}
	
}
