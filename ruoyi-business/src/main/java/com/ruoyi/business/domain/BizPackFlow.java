package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 包裹流转记录对象 biz_pack_flow
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizPackFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流转记录ID */
    private String flowId;

    /** 包裹ID */
    @Excel(name = "包裹ID")
    private String packId;

    /** 驿站ID（在哪个站点发生） */
    @Excel(name = "驿站ID", readConverterExp = "在=哪个站点发生")
    private String stationId;

    /** 操作管理员(sys_user) */
    @Excel(name = "操作管理员(sys_user)")
    private Long adminUserId;

    /** 流转动作 */
    @Excel(name = "流转动作")
    private String action;

    /** 备注 */
    @Excel(name = "备注")
    private String actionNote;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setFlowId(String flowId) 
    {
        this.flowId = flowId;
    }

    public String getFlowId() 
    {
        return flowId;
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

    public void setAction(String action) 
    {
        this.action = action;
    }

    public String getAction() 
    {
        return action;
    }

    public void setActionNote(String actionNote) 
    {
        this.actionNote = actionNote;
    }

    public String getActionNote() 
    {
        return actionNote;
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
            .append("flowId", getFlowId())
            .append("packId", getPackId())
            .append("stationId", getStationId())
            .append("adminUserId", getAdminUserId())
            .append("action", getAction())
            .append("actionNote", getActionNote())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
