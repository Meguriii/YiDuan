package com.ruoyi.business.controller;

import java.util.List;
import java.util.Map;
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
import com.ruoyi.business.domain.BizStationApply;
import com.ruoyi.business.service.IBizStationApplyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 驿站申请Controller
 * 
 * @author ruoyi
 * @date 2025-12-29
 */
@RestController
@RequestMapping("/business/stationApply")
public class BizStationApplyController extends BaseController
{
    @Autowired
    private IBizStationApplyService bizStationApplyService;

    /**
     * 查询驿站申请列表
     */
    @PreAuthorize("@ss.hasPermi('business:stationApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizStationApply bizStationApply)
    {
        startPage();
        List<BizStationApply> list = bizStationApplyService.selectBizStationApplyList(bizStationApply);
        return getDataTable(list);
    }

    /**
     * 获取驿站申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:stationApply:query')")
    @GetMapping(value = "/{applyId}")
    public AjaxResult getInfo(@PathVariable("applyId") Long applyId)
    {
        return success(bizStationApplyService.selectBizStationApplyByApplyId(applyId));
    }

    /**
     * 新增驿站申请（无需权限）
     */
    @Log(title = "驿站申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizStationApply bizStationApply)
    {
        return toAjax(bizStationApplyService.insertBizStationApply(bizStationApply));
    }

    /**
     * 审核驿站申请（超级管理员）
     */
    @PreAuthorize("@ss.hasPermi('business:stationApply:audit')")
    @Log(title = "驿站申请审核", businessType = BusinessType.UPDATE)
    @PutMapping("/audit/{applyId}")
    public AjaxResult audit(@PathVariable Long applyId, @RequestBody Map<String, Object> params)
    {
        boolean pass = (Boolean) params.get("pass");
        Long parentDeptId = params.get("parentDeptId") != null ? Long.valueOf(params.get("parentDeptId").toString()) : null;
        String auditRemark = (String) params.get("auditRemark");
        return toAjax(bizStationApplyService.auditStationApply(applyId, pass, parentDeptId, auditRemark));
    }

    /**
     * 删除驿站申请
     */
    @PreAuthorize("@ss.hasPermi('business:stationApply:remove')")
    @Log(title = "驿站申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applyIds}")
    public AjaxResult remove(@PathVariable Long[] applyIds)
    {
        return toAjax(bizStationApplyService.deleteBizStationApplyByApplyIds(applyIds));
    }
}
