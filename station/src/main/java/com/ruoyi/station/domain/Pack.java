package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName Pack
 */
@TableName(value ="Pack")
@Data
public class Pack {
    /**
     * 
     */
    @TableId(value = "Pack_ID")
    private Integer packId;

    /**
     * 
     */
    @TableField(value = "User_ID")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "Pack_Sen")
    private Integer packSen;

    /**
     * 
     */
    @TableField(value = "Pack_Rec")
    private Integer packRec;

    /**
     * 
     */
    @TableField(value = "Pack_Dg")
    private Integer packDg;

    /**
     * 
     */
    @TableField(value = "Pack_Fra")
    private Integer packFra;

    /**
     * 
     */
    @TableField(value = "Pack_Urg")
    private Integer packUrg;

    /**
     * 
     */
    @TableField(value = "Pack_Fin")
    private String packFin;

    /**
     * 
     */
    @TableField(value = "Pack_Sta")
    private String packSta;

    /**
     * 
     */
    @TableField(value = "Pack_Note")
    private String packNote;
}