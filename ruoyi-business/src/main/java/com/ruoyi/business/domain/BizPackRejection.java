package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 拒收/退件记录对象 biz_pack_rejection
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizPackRejection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private String rejectId;

    /** 包裹ID */
    @Excel(name = "包裹ID")
    private String packId;

    /** 操作人（sys_user） */
    @Excel(name = "操作人", readConverterExp = "s=ys_user")
    private Long rejectBy;

    /** 拒收原因 */
    @Excel(name = "拒收原因")
    private String rejectReason;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setRejectId(String rejectId) 
    {
        this.rejectId = rejectId;
    }

    public String getRejectId() 
    {
        return rejectId;
    }

    public void setPackId(String packId) 
    {
        this.packId = packId;
    }

    public String getPackId() 
    {
        return packId;
    }

    public void setRejectBy(Long rejectBy) 
    {
        this.rejectBy = rejectBy;
    }

    public Long getRejectBy() 
    {
        return rejectBy;
    }

    public void setRejectReason(String rejectReason) 
    {
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() 
    {
        return rejectReason;
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
            .append("rejectId", getRejectId())
            .append("packId", getPackId())
            .append("rejectBy", getRejectBy())
            .append("rejectReason", getRejectReason())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
