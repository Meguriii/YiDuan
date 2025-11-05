package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName IFV
 */
@TableName(value ="IFV")
@Data
public class Ifv {
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
    @TableField(value = "IFV_Sta")
    private String ifvSta;

    /**
     * 
     */
    @TableField(value = "IFV_Note")
    private String ifvNote;
}