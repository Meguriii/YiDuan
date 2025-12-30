package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName Post
 */
@TableName(value ="Post")
@Data
public class Post {
    /**
     * 
     */
    @TableId(value = "Post_ID")
    private Integer postId;

    /**
     * 
     */
    @TableField(value = "Post_Name")
    private String postName;

    /**
     * 
     */
    @TableField(value = "Post_Prov")
    private String postProv;

    /**
     * 
     */
    @TableField(value = "Post_Dist")
    private String postDist;

    /**
     * 
     */
    @TableField(value = "Post_City")
    private String postCity;

    /**
     * 
     */
    @TableField(value = "Post_Addr")
    private String postAddr;
}