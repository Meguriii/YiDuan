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
import com.ruoyi.business.domain.BizPackVerify;
import com.ruoyi.business.service.IBizPackVerifyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 包裹审核记录Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/verify")
public class BizPackVerifyController extends BaseController
{
    @Autowired
    private IBizPackVerifyService bizPackVerifyService;

    /**
     * 查询包裹审核记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:verify:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizPackVerify bizPackVerify)
    {
        startPage();
        List<BizPackVerify> list = bizPackVerifyService.selectBizPackVerifyList(bizPackVerify);
        return getDataTable(list);
    }

    /**
     * 导出包裹审核记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:verify:export')")
    @Log(title = "包裹审核记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizPackVerify bizPackVerify)
    {
        List<BizPackVerify> list = bizPackVerifyService.selectBizPackVerifyList(bizPackVerify);
        ExcelUtil<BizPackVerify> util = new ExcelUtil<BizPackVerify>(BizPackVerify.class);
        util.exportExcel(response, list, "包裹审核记录数据");
    }

    /**
     * 获取包裹审核记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:verify:query')")
    @GetMapping(value = "/{verifyId}")
    public AjaxResult getInfo(@PathVariable("verifyId") String verifyId)
    {
        return success(bizPackVerifyService.selectBizPackVerifyByVerifyId(verifyId));
    }

    /**
     * 新增包裹审核记录
     */
    @PreAuthorize("@ss.hasPermi('business:verify:add')")
    @Log(title = "包裹审核记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizPackVerify bizPackVerify)
    {
        return toAjax(bizPackVerifyService.insertBizPackVerify(bizPackVerify));
    }

    /**
     * 修改包裹审核记录
     */
    @PreAuthorize("@ss.hasPermi('business:verify:edit')")
    @Log(title = "包裹审核记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizPackVerify bizPackVerify)
    {
        return toAjax(bizPackVerifyService.updateBizPackVerify(bizPackVerify));
    }

    /**
     * 删除包裹审核记录
     */
    @PreAuthorize("@ss.hasPermi('business:verify:remove')")
    @Log(title = "包裹审核记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{verifyIds}")
    public AjaxResult remove(@PathVariable String[] verifyIds)
    {
        return toAjax(bizPackVerifyService.deleteBizPackVerifyByVerifyIds(verifyIds));
    }
}
