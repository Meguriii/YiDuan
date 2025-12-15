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
import com.ruoyi.business.domain.BizPackRejection;
import com.ruoyi.business.service.IBizPackRejectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 拒收/退件记录Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/rejection")
public class BizPackRejectionController extends BaseController
{
    @Autowired
    private IBizPackRejectionService bizPackRejectionService;

    /**
     * 查询拒收/退件记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:rejection:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizPackRejection bizPackRejection)
    {
        startPage();
        List<BizPackRejection> list = bizPackRejectionService.selectBizPackRejectionList(bizPackRejection);
        return getDataTable(list);
    }

    /**
     * 导出拒收/退件记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:rejection:export')")
    @Log(title = "拒收/退件记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizPackRejection bizPackRejection)
    {
        List<BizPackRejection> list = bizPackRejectionService.selectBizPackRejectionList(bizPackRejection);
        ExcelUtil<BizPackRejection> util = new ExcelUtil<BizPackRejection>(BizPackRejection.class);
        util.exportExcel(response, list, "拒收/退件记录数据");
    }

    /**
     * 获取拒收/退件记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:rejection:query')")
    @GetMapping(value = "/{rejectId}")
    public AjaxResult getInfo(@PathVariable("rejectId") String rejectId)
    {
        return success(bizPackRejectionService.selectBizPackRejectionByRejectId(rejectId));
    }

    /**
     * 新增拒收/退件记录
     */
    @PreAuthorize("@ss.hasPermi('business:rejection:add')")
    @Log(title = "拒收/退件记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizPackRejection bizPackRejection)
    {
        return toAjax(bizPackRejectionService.insertBizPackRejection(bizPackRejection));
    }

    /**
     * 修改拒收/退件记录
     */
    @PreAuthorize("@ss.hasPermi('business:rejection:edit')")
    @Log(title = "拒收/退件记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizPackRejection bizPackRejection)
    {
        return toAjax(bizPackRejectionService.updateBizPackRejection(bizPackRejection));
    }

    /**
     * 删除拒收/退件记录
     */
    @PreAuthorize("@ss.hasPermi('business:rejection:remove')")
    @Log(title = "拒收/退件记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rejectIds}")
    public AjaxResult remove(@PathVariable String[] rejectIds)
    {
        return toAjax(bizPackRejectionService.deleteBizPackRejectionByRejectIds(rejectIds));
    }
}
