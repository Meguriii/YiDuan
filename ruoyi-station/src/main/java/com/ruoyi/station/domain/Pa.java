package com.ruoyi.station.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName PA
 */
@TableName(value ="PA")
@Data
public class Pa {
    /**
     * 
     */
    @TableId(value = "Post_Admin_ID")
    private Integer postAdminId;

    /**
     * 
     */
    @TableId(value = "Post_ID")
    private Integer postId;
}