package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 业务用户对象 biz_client
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务用户ID */
    private String clientId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String clientName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String clientTel;

    /** 用户状态 */
    @Excel(name = "用户状态")
    private String clientStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setClientId(String clientId) 
    {
        this.clientId = clientId;
    }

    public String getClientId() 
    {
        return clientId;
    }

    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }

    public String getClientName() 
    {
        return clientName;
    }

    public void setClientTel(String clientTel) 
    {
        this.clientTel = clientTel;
    }

    public String getClientTel() 
    {
        return clientTel;
    }

    public void setClientStatus(String clientStatus) 
    {
        this.clientStatus = clientStatus;
    }

    public String getClientStatus() 
    {
        return clientStatus;
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
            .append("clientId", getClientId())
            .append("clientName", getClientName())
            .append("clientTel", getClientTel())
            .append("clientStatus", getClientStatus())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
