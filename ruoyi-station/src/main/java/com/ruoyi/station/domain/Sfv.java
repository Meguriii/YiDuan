package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName SFV
 */
@TableName(value ="SFV")
@Data
public class Sfv {
    /**
     * 
     */
    @TableId(value = "Post_Admin_ID")
    private Integer postAdminId;

    /**
     * 
     */
    @TableId(value = "Pack_ID")
    private Integer packId;

    /**
     * 
     */
    @TableField(value = "SFV_Sta")
    private String sfvSta;

    /**
     * 
     */
    @TableField(value = "SFV_Note")
    private String sfvNote;
}