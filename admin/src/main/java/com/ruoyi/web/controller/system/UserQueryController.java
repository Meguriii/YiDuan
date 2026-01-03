package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户查询控制器，提供根据电话号码查询用户信息的API
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user")
public class UserQueryController
{
    @Autowired
    private ISysUserService userService;

    /**
     * 根据手机号码查询用户信息
     * 
     * @param phonenumber 手机号码
     * @return 用户信息
     */
    @GetMapping("/queryByPhonenumber")
    public AjaxResult queryUserByPhonenumber(@RequestParam String phonenumber)
    {
        SysUser user = userService.selectUserByPhonenumber(phonenumber);
        if (user != null) {
            return AjaxResult.success(user);
        } else {
            return AjaxResult.error("未找到对应用户");
        }
    }
}