package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 包裹审核记录对象 biz_pack_verify
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizPackVerify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核记录ID */
    private String verifyId;

    /** 包裹ID */
    @Excel(name = "包裹ID")
    private String packId;

    /** 驿站ID */
    @Excel(name = "驿站ID")
    private String stationId;

    /** 审核管理员ID */
    @Excel(name = "审核管理员ID")
    private Long adminUserId;

    /** 审核类型 */
    @Excel(name = "审核类型")
    private String verifyType;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String verifyStatus;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String verifyNote;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verifiedAt;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setVerifyId(String verifyId) 
    {
        this.verifyId = verifyId;
    }

    public String getVerifyId() 
    {
        return verifyId;
    }

    public void setPackId(String packId) 
    {
        this.packId = packId;
    }

    public String getPackId() 
    {
        return packId;
    }

    public void setStationId(String stationId) 
    {
        this.stationId = stationId;
    }

    public String getStationId() 
    {
        return stationId;
    }

    public void setAdminUserId(Long adminUserId) 
    {
        this.adminUserId = adminUserId;
    }

    public Long getAdminUserId() 
    {
        return adminUserId;
    }

    public void setVerifyType(String verifyType) 
    {
        this.verifyType = verifyType;
    }

    public String getVerifyType() 
    {
        return verifyType;
    }

    public void setVerifyStatus(String verifyStatus) 
    {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyStatus() 
    {
        return verifyStatus;
    }

    public void setVerifyNote(String verifyNote) 
    {
        this.verifyNote = verifyNote;
    }

    public String getVerifyNote() 
    {
        return verifyNote;
    }

    public void setVerifiedAt(Date verifiedAt) 
    {
        this.verifiedAt = verifiedAt;
    }

    public Date getVerifiedAt() 
    {
        return verifiedAt;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("verifyId", getVerifyId())
            .append("packId", getPackId())
            .append("stationId", getStationId())
            .append("adminUserId", getAdminUserId())
            .append("verifyType", getVerifyType())
            .append("verifyStatus", getVerifyStatus())
            .append("verifyNote", getVerifyNote())
            .append("verifiedAt", getVerifiedAt())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
