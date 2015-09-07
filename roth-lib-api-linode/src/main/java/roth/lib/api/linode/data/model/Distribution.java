package roth.lib.api.linode.data.model;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Distribution implements Serializable
{
	@Property(name = "DISTRIBUTIONID")
	protected Integer distributionId;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "CREATE_DT")
	protected Calendar createDt;
	
	@Property(name = "IS64BIT")
	protected Integer _64bit;
	
	@Property(name = "MINIMAGESIZE")
	protected Integer minImageSize;
	
	@Property(name = "REQUIRESPVOPSKERNEL")
	protected Boolean requiresPvopsKernel;
	
	public Distribution()
	{
		
	}
	
	public Integer getDistributionId()
	{
		return distributionId;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public Calendar getCreateDt()
	{
		return createDt;
	}
	
	public Integer get64bit()
	{
		return _64bit;
	}
	
	public boolean is64bit()
	{
		return 1 == _64bit;
	}
	
	public Integer getMinImageSize()
	{
		return minImageSize;
	}
	
	public Boolean getRequiresPvopsKernel()
	{
		return requiresPvopsKernel;
	}
	
	public Distribution setDistributionId(Integer distributionId)
	{
		this.distributionId = distributionId;
		return this;
	}
	
	public Distribution setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public Distribution setCreateDt(Calendar createDt)
	{
		this.createDt = createDt;
		return this;
	}
	
	public Distribution set64bit(Integer _64bit)
	{
		this._64bit = _64bit;
		return this;
	}
	
	public Distribution setMinImageSize(Integer minImageSize)
	{
		this.minImageSize = minImageSize;
		return this;
	}
	
	public Distribution setRequiresPvopsKernel(Boolean requiresPvopsKernel)
	{
		this.requiresPvopsKernel = requiresPvopsKernel;
		return this;
	}
	
}
