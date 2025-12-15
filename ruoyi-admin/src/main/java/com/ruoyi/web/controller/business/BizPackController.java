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
import com.ruoyi.business.domain.BizPack;
import com.ruoyi.business.service.IBizPackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 包裹表Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/pack")
public class BizPackController extends BaseController
{
    @Autowired
    private IBizPackService bizPackService;

    /**
     * 查询包裹表列表
     */
    @PreAuthorize("@ss.hasPermi('business:pack:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizPack bizPack)
    {
        startPage();
        List<BizPack> list = bizPackService.selectBizPackList(bizPack);
        return getDataTable(list);
    }

    /**
     * 导出包裹表列表
     */
    @PreAuthorize("@ss.hasPermi('business:pack:export')")
    @Log(title = "包裹表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizPack bizPack)
    {
        List<BizPack> list = bizPackService.selectBizPackList(bizPack);
        ExcelUtil<BizPack> util = new ExcelUtil<BizPack>(BizPack.class);
        util.exportExcel(response, list, "包裹表数据");
    }

    /**
     * 获取包裹表详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:pack:query')")
    @GetMapping(value = "/{packId}")
    public AjaxResult getInfo(@PathVariable("packId") String packId)
    {
        return success(bizPackService.selectBizPackByPackId(packId));
    }

    /**
     * 新增包裹表
     */
    @PreAuthorize("@ss.hasPermi('business:pack:add')")
    @Log(title = "包裹表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizPack bizPack)
    {
        return toAjax(bizPackService.insertBizPack(bizPack));
    }

    /**
     * 修改包裹表
     */
    @PreAuthorize("@ss.hasPermi('business:pack:edit')")
    @Log(title = "包裹表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizPack bizPack)
    {
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 删除包裹表
     */
    @PreAuthorize("@ss.hasPermi('business:pack:remove')")
    @Log(title = "包裹表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{packIds}")
    public AjaxResult remove(@PathVariable String[] packIds)
    {
        return toAjax(bizPackService.deleteBizPackByPackIds(packIds));
    }
}
