package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName Sup_Admin
 */
@TableName(value ="Sup_Admin")
@Data
public class SupAdmin {
    /**
     * 
     */
    @TableId(value = "Sup_Admin_ID")
    private Integer supAdminId;

    /**
     * 
     */
    @TableField(value = "Sup_Admin_Name")
    private String supAdminName;
}