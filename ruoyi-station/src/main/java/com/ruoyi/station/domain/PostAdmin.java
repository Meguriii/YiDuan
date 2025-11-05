package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName Post_Admin
 */
@TableName(value ="Post_Admin")
@Data
public class PostAdmin {
    /**
     * 
     */
    @TableId(value = "Post_Admin_ID")
    private Integer postAdminId;

    /**
     * 
     */
    @TableField(value = "Post_Admin_Name")
    private String postAdminName;
}