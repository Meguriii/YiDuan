package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName Addr
 */
@TableName(value ="Addr")
@Data
public class Addr {
    /**
     * 
     */
    @TableId(value = "Addr_ID")
    private Integer addrId;

    /**
     * 
     */
    @TableField(value = "Addr_Prov")
    private String addrProv;

    /**
     * 
     */
    @TableField(value = "Addr_City")
    private String addrCity;

    /**
     * 
     */
    @TableField(value = "Addr_Dist")
    private String addrDist;

    /**
     * 
     */
    @TableField(value = "Addr_Addr")
    private String addrAddr;

    /**
     * 
     */
    @TableField(value = "Addr_Name")
    private String addrName;

    /**
     * 
     */
    @TableField(value = "Addr_Tel")
    private Integer addrTel;

    /**
     * 
     */
    @TableField(value = "User_ID")
    private Integer userId;
}