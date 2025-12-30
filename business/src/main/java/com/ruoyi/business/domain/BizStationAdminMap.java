package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驿站-管理员映射对象 biz_station_admin_map
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizStationAdminMap extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 驿站ID */
    private String stationId;

    /** 管理员ID(sys_user) */
    private Long adminUserId;

    /** 角色说明，例如站长/核验员 */
    @Excel(name = "角色说明，例如站长/核验员")
    private String roleNote;

    /** 分配时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date assignedAt;

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

    public void setRoleNote(String roleNote) 
    {
        this.roleNote = roleNote;
    }

    public String getRoleNote() 
    {
        return roleNote;
    }

    public void setAssignedAt(Date assignedAt) 
    {
        this.assignedAt = assignedAt;
    }

    public Date getAssignedAt() 
    {
        return assignedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stationId", getStationId())
            .append("adminUserId", getAdminUserId())
            .append("roleNote", getRoleNote())
            .append("assignedAt", getAssignedAt())
            .toString();
    }
}
