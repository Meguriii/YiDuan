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
import com.ruoyi.business.domain.BizAddr;
import com.ruoyi.business.service.IBizAddrService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户地址簿Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/addr")
public class BizAddrController extends BaseController
{
    @Autowired
    private IBizAddrService bizAddrService;

    /**
     * 查询用户地址簿列表
     */
    @PreAuthorize("@ss.hasPermi('business:addr:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizAddr bizAddr)
    {
        startPage();
        List<BizAddr> list = bizAddrService.selectBizAddrList(bizAddr);
        return getDataTable(list);
    }

    /**
     * 导出用户地址簿列表
     */
    @PreAuthorize("@ss.hasPermi('business:addr:export')")
    @Log(title = "用户地址簿", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizAddr bizAddr)
    {
        List<BizAddr> list = bizAddrService.selectBizAddrList(bizAddr);
        ExcelUtil<BizAddr> util = new ExcelUtil<BizAddr>(BizAddr.class);
        util.exportExcel(response, list, "用户地址簿数据");
    }

    /**
     * 获取用户地址簿详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:addr:query')")
    @GetMapping(value = "/{addrId}")
    public AjaxResult getInfo(@PathVariable("addrId") String addrId)
    {
        return success(bizAddrService.selectBizAddrByAddrId(addrId));
    }

    /**
     * 新增用户地址簿
     */
    @PreAuthorize("@ss.hasPermi('business:addr:add')")
    @Log(title = "用户地址簿", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizAddr bizAddr)
    {
        return toAjax(bizAddrService.insertBizAddr(bizAddr));
    }

    /**
     * 修改用户地址簿
     */
    @PreAuthorize("@ss.hasPermi('business:addr:edit')")
    @Log(title = "用户地址簿", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizAddr bizAddr)
    {
        return toAjax(bizAddrService.updateBizAddr(bizAddr));
    }

    /**
     * 删除用户地址簿
     */
    @PreAuthorize("@ss.hasPermi('business:addr:remove')")
    @Log(title = "用户地址簿", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addrIds}")
    public AjaxResult remove(@PathVariable String[] addrIds)
    {
        return toAjax(bizAddrService.deleteBizAddrByAddrIds(addrIds));
    }
}
