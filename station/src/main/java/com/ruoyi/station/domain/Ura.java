package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName URA
 */
@TableName(value ="URA")
@Data
public class Ura {
    /**
     * 
     */
    @TableId(value = "User_ID")
    private Integer userId;

    /**
     * 
     */
    @TableId(value = "Pack_ID")
    private Integer packId;

    /**
     * 
     */
    @TableField(value = "URA_Rsn")
    private String uraRsn;

    /**
     * 
     */
    @TableField(value = "URA_Ver")
    private String uraVer;
}