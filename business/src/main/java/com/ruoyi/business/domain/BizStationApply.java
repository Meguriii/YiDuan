package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驿站申请对象 biz_station_apply
 * 
 * @author ruoyi
 * @date 2025-12-29
 */
public class BizStationApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请ID */
    private Long applyId;

    /** 驿站名称 */
    @Excel(name = "驿站名称")
    private String stationName;

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

    /** 申请人用户ID */
    @Excel(name = "申请人用户ID")
    private Long contactUserId;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private String applyStatus;

    /** 审核人 */
    @Excel(name = "审核人")
    private Long auditBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String auditRemark;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }

    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
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

    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }

    public void setApplyStatus(String applyStatus) 
    {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() 
    {
        return applyStatus;
    }

    public void setAuditBy(Long auditBy) 
    {
        this.auditBy = auditBy;
    }

    public Long getAuditBy() 
    {
        return auditBy;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    public void setAuditRemark(String auditRemark) 
    {
        this.auditRemark = auditRemark;
    }

    public String getAuditRemark() 
    {
        return auditRemark;
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
            .append("applyId", getApplyId())
            .append("stationName", getStationName())
            .append("stationProv", getStationProv())
            .append("stationCity", getStationCity())
            .append("stationDist", getStationDist())
            .append("stationAddr", getStationAddr())
            .append("contactUserId", getContactUserId())
            .append("tel", getTel())
            .append("applyStatus", getApplyStatus())
            .append("auditBy", getAuditBy())
            .append("auditTime", getAuditTime())
            .append("auditRemark", getAuditRemark())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
