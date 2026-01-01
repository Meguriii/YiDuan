package com.ruoyi.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 包裹表对象 biz_pack
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public class BizPack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 包裹ID */
    private String packId;

    /** 寄件用户(sys_user) */
    @Excel(name = "寄件用户")
    private Long senderId;

    /** 寄件省 */
    @Excel(name = "寄件省")
    private String senderProv;

    /** 寄件市 */
    @Excel(name = "寄件市")
    private String senderCity;

    /** 寄件区县 */
    @Excel(name = "寄件区县")
    private String senderDist;

    /** 寄件详细地址 */
    @Excel(name = "寄件详细地址")
    private String senderAddrDetail;

    /** 收件用户ID，可为空 */
    @Excel(name = "收件用户ID，可为空")
    private Long receiverId;

    /** 收件人姓名 */
    @Excel(name = "收件人姓名")
    private String receiverName;

    /** 收件人手机号 */
    @Excel(name = "收件人手机号")
    private String receiverTel;

    /** 收件省 */
    @Excel(name = "收件省")
    private String receiverProv;

    /** 收件市 */
    @Excel(name = "收件市")
    private String receiverCity;

    /** 收件区县 */
    @Excel(name = "收件区县")
    private String receiverDist;

    /** 收件详细地址 */
    @Excel(name = "收件详细地址")
    private String receiverAddrDetail;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal weight;

    /** 体积 */
    @Excel(name = "体积")
    private BigDecimal volume;

    /** 备注 */
    @Excel(name = "备注")
    private String note;

    /** 是否危险品 */
    @Excel(name = "是否危险品")
    private Integer isDangerous;

    /** 是否易碎 */
    @Excel(name = "是否易碎")
    private Integer isFragile;

    /** 是否加急 */
    @Excel(name = "是否加急")
    private Integer isUrgent;

    /** 包裹状态 */
    @Excel(name = "包裹状态")
    private String status;

    /** 取件码（入库后生成） */
    @Excel(name = "取件码", readConverterExp = "入=库后生成")
    private String pickupCode;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setPackId(String packId) 
    {
        this.packId = packId;
    }

    public String getPackId() 
    {
        return packId;
    }

    public void setSenderId(Long senderId) 
    {
        this.senderId = senderId;
    }

    public Long getSenderId() 
    {
        return senderId;
    }

    public void setSenderProv(String senderProv) 
    {
        this.senderProv = senderProv;
    }

    public String getSenderProv() 
    {
        return senderProv;
    }

    public void setSenderCity(String senderCity) 
    {
        this.senderCity = senderCity;
    }

    public String getSenderCity() 
    {
        return senderCity;
    }

    public void setSenderDist(String senderDist) 
    {
        this.senderDist = senderDist;
    }

    public String getSenderDist() 
    {
        return senderDist;
    }

    public void setSenderAddrDetail(String senderAddrDetail) 
    {
        this.senderAddrDetail = senderAddrDetail;
    }

    public String getSenderAddrDetail() 
    {
        return senderAddrDetail;
    }

    public void setReceiverId(Long receiverId) 
    {
        this.receiverId = receiverId;
    }

    public Long getReceiverId() 
    {
        return receiverId;
    }

    public void setReceiverName(String receiverName) 
    {
        this.receiverName = receiverName;
    }

    public String getReceiverName() 
    {
        return receiverName;
    }

    public void setReceiverTel(String receiverTel) 
    {
        this.receiverTel = receiverTel;
    }

    public String getReceiverTel() 
    {
        return receiverTel;
    }

    public void setReceiverProv(String receiverProv) 
    {
        this.receiverProv = receiverProv;
    }

    public String getReceiverProv() 
    {
        return receiverProv;
    }

    public void setReceiverCity(String receiverCity) 
    {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity() 
    {
        return receiverCity;
    }

    public void setReceiverDist(String receiverDist) 
    {
        this.receiverDist = receiverDist;
    }

    public String getReceiverDist() 
    {
        return receiverDist;
    }

    public void setReceiverAddrDetail(String receiverAddrDetail) 
    {
        this.receiverAddrDetail = receiverAddrDetail;
    }

    public String getReceiverAddrDetail() 
    {
        return receiverAddrDetail;
    }

    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }

    public void setVolume(BigDecimal volume) 
    {
        this.volume = volume;
    }

    public BigDecimal getVolume() 
    {
        return volume;
    }

    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }

    public void setIsDangerous(Integer isDangerous) 
    {
        this.isDangerous = isDangerous;
    }

    public Integer getIsDangerous() 
    {
        return isDangerous;
    }

    public void setIsFragile(Integer isFragile) 
    {
        this.isFragile = isFragile;
    }

    public Integer getIsFragile() 
    {
        return isFragile;
    }

    public void setIsUrgent(Integer isUrgent) 
    {
        this.isUrgent = isUrgent;
    }

    public Integer getIsUrgent() 
    {
        return isUrgent;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setPickupCode(String pickupCode) 
    {
        this.pickupCode = pickupCode;
    }

    public String getPickupCode() 
    {
        return pickupCode;
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
            .append("packId", getPackId())
            .append("senderId", getSenderId())
            .append("senderProv", getSenderProv())
            .append("senderCity", getSenderCity())
            .append("senderDist", getSenderDist())
            .append("senderAddrDetail", getSenderAddrDetail())
            .append("receiverId", getReceiverId())
            .append("receiverName", getReceiverName())
            .append("receiverTel", getReceiverTel())
            .append("receiverProv", getReceiverProv())
            .append("receiverCity", getReceiverCity())
            .append("receiverDist", getReceiverDist())
            .append("receiverAddrDetail", getReceiverAddrDetail())
            .append("weight", getWeight())
            .append("volume", getVolume())
            .append("note", getNote())
            .append("isDangerous", getIsDangerous())
            .append("isFragile", getIsFragile())
            .append("isUrgent", getIsUrgent())
            .append("status", getStatus())
            .append("pickupCode", getPickupCode())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
