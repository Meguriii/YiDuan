package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName User
 */
@TableName(value ="User")
@Data
public class User {
    /**
     * 
     */
    @TableId(value = "User_ID")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "User_Name")
    private String userName;

    /**
     * 
     */
    @TableField(value = "User_Tel")
    private Integer userTel;

    /**
     * 
     */
    @TableField(value = "User_Sta")
    private String userSta;

    /**
     * 
     */
    @TableField(value = "User_Pin")
    private String userPin;
}