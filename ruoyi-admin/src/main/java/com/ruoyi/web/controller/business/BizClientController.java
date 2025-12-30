package com.ruoyi.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.BizClient;
import com.ruoyi.business.service.IBizClientService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务用户Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/client")
public class BizClientController extends BaseController
{
    @Autowired
    private IBizClientService bizClientService;

    /**
     * 查询业务用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:client:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizClient bizClient)
    {
        startPage();
        List<BizClient> list = bizClientService.selectBizClientList(bizClient);
        return getDataTable(list);
    }

    /**
     * 导出业务用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:client:export')")
    @Log(title = "业务用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizClient bizClient)
    {
        List<BizClient> list = bizClientService.selectBizClientList(bizClient);
        ExcelUtil<BizClient> util = new ExcelUtil<BizClient>(BizClient.class);
        util.exportExcel(response, list, "业务用户数据");
    }

    /**
     * 获取业务用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:client:query')")
    @GetMapping(value = "/{clientId}")
    public AjaxResult getInfo(@PathVariable("clientId") String clientId)
    {
        return success(bizClientService.selectBizClientByClientId(clientId));
    }

    /**
     * 新增业务用户
     */
    @PreAuthorize("@ss.hasPermi('business:client:add')")
    @Log(title = "业务用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizClient bizClient)
    {
        return toAjax(bizClientService.insertBizClient(bizClient));
    }

    /**
     * 修改业务用户
     */
    @PreAuthorize("@ss.hasPermi('business:client:edit')")
    @Log(title = "业务用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizClient bizClient)
    {
        return toAjax(bizClientService.updateBizClient(bizClient));
    }

    /**
     * 删除业务用户
     */
    @PreAuthorize("@ss.hasPermi('business:client:remove')")
    @Log(title = "业务用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clientIds}")
    public AjaxResult remove(@PathVariable String[] clientIds)
    {
        return toAjax(bizClientService.deleteBizClientByClientIds(clientIds));
    }

    /**
     * 用户注册 - 不需要权限
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody BizClient bizClient)
    {
        // 检查手机号是否已存在
        BizClient query = new BizClient();
        query.setClientTel(bizClient.getClientTel());
        List<BizClient> list = bizClientService.selectBizClientList(query);
        if (!list.isEmpty()) {
            return error("该手机号已注册");
        }
        
        // 设置默认状态为正常
        bizClient.setClientStatus("正常");
        return toAjax(bizClientService.insertBizClient(bizClient));
    }

    /**
     * 冻结/解冻用户 - 超级管理员功能
     */
    @PreAuthorize("@ss.hasPermi('business:client:freeze')")
    @Log(title = "冻结用户", businessType = BusinessType.UPDATE)
    @PutMapping("/freeze/{clientId}")
    public AjaxResult freezeClient(@PathVariable String clientId, @RequestBody BizClient bizClient)
    {
        bizClient.setClientId(clientId);
        return toAjax(bizClientService.updateBizClient(bizClient));
    }
}
