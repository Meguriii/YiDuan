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
import com.ruoyi.business.domain.BizStationAdminMap;
import com.ruoyi.business.service.IBizStationAdminMapService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 驿站-管理员映射Controller
 * 
 * @author ruoyi
 * @date 2025-12-09
 */
@RestController
@RequestMapping("/business/map")
public class BizStationAdminMapController extends BaseController
{
    @Autowired
    private IBizStationAdminMapService bizStationAdminMapService;

    /**
     * 查询驿站-管理员映射列表
     */
    @PreAuthorize("@ss.hasPermi('business:map:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizStationAdminMap bizStationAdminMap)
    {
        startPage();
        List<BizStationAdminMap> list = bizStationAdminMapService.selectBizStationAdminMapList(bizStationAdminMap);
        return getDataTable(list);
    }

    /**
     * 导出驿站-管理员映射列表
     */
    @PreAuthorize("@ss.hasPermi('business:map:export')")
    @Log(title = "驿站-管理员映射", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizStationAdminMap bizStationAdminMap)
    {
        List<BizStationAdminMap> list = bizStationAdminMapService.selectBizStationAdminMapList(bizStationAdminMap);
        ExcelUtil<BizStationAdminMap> util = new ExcelUtil<BizStationAdminMap>(BizStationAdminMap.class);
        util.exportExcel(response, list, "驿站-管理员映射数据");
    }

    /**
     * 获取驿站-管理员映射详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:map:query')")
    @GetMapping(value = "/{stationId}")
    public AjaxResult getInfo(@PathVariable("stationId") String stationId)
    {
        return success(bizStationAdminMapService.selectBizStationAdminMapByStationId(stationId));
    }

    /**
     * 新增驿站-管理员映射
     */
    @PreAuthorize("@ss.hasPermi('business:map:add')")
    @Log(title = "驿站-管理员映射", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizStationAdminMap bizStationAdminMap)
    {
        return toAjax(bizStationAdminMapService.insertBizStationAdminMap(bizStationAdminMap));
    }

    /**
     * 修改驿站-管理员映射
     */
    @PreAuthorize("@ss.hasPermi('business:map:edit')")
    @Log(title = "驿站-管理员映射", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizStationAdminMap bizStationAdminMap)
    {
        return toAjax(bizStationAdminMapService.updateBizStationAdminMap(bizStationAdminMap));
    }

    /**
     * 删除驿站-管理员映射
     */
    @PreAuthorize("@ss.hasPermi('business:map:remove')")
    @Log(title = "驿站-管理员映射", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationIds}")
    public AjaxResult remove(@PathVariable String[] stationIds)
    {
        return toAjax(bizStationAdminMapService.deleteBizStationAdminMapByStationIds(stationIds));
    }
}
