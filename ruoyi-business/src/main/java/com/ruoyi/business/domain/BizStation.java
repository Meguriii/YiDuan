package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驿站信息对象 biz_station
 * 
 * @author weiyi
 * @date 2025-12-15
 */
public class BizStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 驿站ID */
    private String stationId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 省 */
    @Excel(name = "省")
    private String stationProv;

    /** 市 */
    @Excel(name = "市")
    private String stationCity;

    /** 区县 */
    @Excel(name = "区县")
    private String stationDist;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String stationAddr;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Long contactUserId;

    /** 驿站状态 */
    @Excel(name = "驿站状态")
    private String status;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setStationId(String stationId) 
    {
        this.stationId = stationId;
    }

    public String getStationId() 
    {
        return stationId;
    }

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setStationProv(String stationProv) 
    {
        this.stationProv = stationProv;
    }

    public String getStationProv() 
    {
        return stationProv;
    }

    public void setStationCity(String stationCity) 
    {
        this.stationCity = stationCity;
    }

    public String getStationCity() 
    {
        return stationCity;
    }

    public void setStationDist(String stationDist) 
    {
        this.stationDist = stationDist;
    }

    public String getStationDist() 
    {
        return stationDist;
    }

    public void setStationAddr(String stationAddr) 
    {
        this.stationAddr = stationAddr;
    }

    public String getStationAddr() 
    {
        return stationAddr;
    }

    public void setContactUserId(Long contactUserId) 
    {
        this.contactUserId = contactUserId;
    }

    public Long getContactUserId() 
    {
        return contactUserId;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stationId", getStationId())
            .append("deptId", getDeptId())
            .append("stationProv", getStationProv())
            .append("stationCity", getStationCity())
            .append("stationDist", getStationDist())
            .append("stationAddr", getStationAddr())
            .append("contactUserId", getContactUserId())
            .append("status", getStatus())
            .append("tel", getTel())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
