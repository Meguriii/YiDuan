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
import com.ruoyi.business.domain.BizPackFlow;
import com.ruoyi.business.service.IBizPackFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 包裹流转记录Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/flow")
public class BizPackFlowController extends BaseController
{
    @Autowired
    private IBizPackFlowService bizPackFlowService;

    /**
     * 查询包裹流转记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizPackFlow bizPackFlow)
    {
        startPage();
        List<BizPackFlow> list = bizPackFlowService.selectBizPackFlowList(bizPackFlow);
        return getDataTable(list);
    }

    /**
     * 导出包裹流转记录列表
     */
    @PreAuthorize("@ss.hasPermi('business:flow:export')")
    @Log(title = "包裹流转记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizPackFlow bizPackFlow)
    {
        List<BizPackFlow> list = bizPackFlowService.selectBizPackFlowList(bizPackFlow);
        ExcelUtil<BizPackFlow> util = new ExcelUtil<BizPackFlow>(BizPackFlow.class);
        util.exportExcel(response, list, "包裹流转记录数据");
    }

    /**
     * 获取包裹流转记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:flow:query')")
    @GetMapping(value = "/{flowId}")
    public AjaxResult getInfo(@PathVariable("flowId") String flowId)
    {
        return success(bizPackFlowService.selectBizPackFlowByFlowId(flowId));
    }

    /**
     * 新增包裹流转记录
     */
    @PreAuthorize("@ss.hasPermi('business:flow:add')")
    @Log(title = "包裹流转记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizPackFlow bizPackFlow)
    {
        return toAjax(bizPackFlowService.insertBizPackFlow(bizPackFlow));
    }

    /**
     * 修改包裹流转记录
     */
    @PreAuthorize("@ss.hasPermi('business:flow:edit')")
    @Log(title = "包裹流转记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizPackFlow bizPackFlow)
    {
        return toAjax(bizPackFlowService.updateBizPackFlow(bizPackFlow));
    }

    /**
     * 删除包裹流转记录
     */
    @PreAuthorize("@ss.hasPermi('business:flow:remove')")
    @Log(title = "包裹流转记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{flowIds}")
    public AjaxResult remove(@PathVariable String[] flowIds)
    {
        return toAjax(bizPackFlowService.deleteBizPackFlowByFlowIds(flowIds));
    }
}
