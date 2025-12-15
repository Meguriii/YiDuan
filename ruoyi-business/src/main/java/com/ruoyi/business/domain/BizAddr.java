package com.ruoyi.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户地址簿对象 biz_addr
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizAddr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地址ID */
    private String addrId;

    /** 所属用户(biz_client) */
    @Excel(name = "所属用户(biz_client)")
    private String clientId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String addrName;

    /** 电话 */
    @Excel(name = "电话")
    private String addrTel;

    /** 省 */
    @Excel(name = "省")
    private String addrProv;

    /** 市 */
    @Excel(name = "市")
    private String addrCity;

    /** 区县 */
    @Excel(name = "区县")
    private String addrDist;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addrDetail;

    /** 是否默认地址 */
    @Excel(name = "是否默认地址")
    private Integer isDefault;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setAddrId(String addrId) 
    {
        this.addrId = addrId;
    }

    public String getAddrId() 
    {
        return addrId;
    }

    public void setClientId(String clientId) 
    {
        this.clientId = clientId;
    }

    public String getClientId() 
    {
        return clientId;
    }

    public void setAddrName(String addrName) 
    {
        this.addrName = addrName;
    }

    public String getAddrName() 
    {
        return addrName;
    }

    public void setAddrTel(String addrTel) 
    {
        this.addrTel = addrTel;
    }

    public String getAddrTel() 
    {
        return addrTel;
    }

    public void setAddrProv(String addrProv) 
    {
        this.addrProv = addrProv;
    }

    public String getAddrProv() 
    {
        return addrProv;
    }

    public void setAddrCity(String addrCity) 
    {
        this.addrCity = addrCity;
    }

    public String getAddrCity() 
    {
        return addrCity;
    }

    public void setAddrDist(String addrDist) 
    {
        this.addrDist = addrDist;
    }

    public String getAddrDist() 
    {
        return addrDist;
    }

    public void setAddrDetail(String addrDetail) 
    {
        this.addrDetail = addrDetail;
    }

    public String getAddrDetail() 
    {
        return addrDetail;
    }

    public void setIsDefault(Integer isDefault) 
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault() 
    {
        return isDefault;
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
            .append("addrId", getAddrId())
            .append("clientId", getClientId())
            .append("addrName", getAddrName())
            .append("addrTel", getAddrTel())
            .append("addrProv", getAddrProv())
            .append("addrCity", getAddrCity())
            .append("addrDist", getAddrDist())
            .append("addrDetail", getAddrDetail())
            .append("isDefault", getIsDefault())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
