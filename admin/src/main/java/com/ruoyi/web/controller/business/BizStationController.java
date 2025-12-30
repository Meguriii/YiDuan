package com.ruoyi.web.controller.business;

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
import com.ruoyi.business.domain.BizStation;
import com.ruoyi.business.service.IBizStationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 驿站信息Controller
 * 
 * @author weiyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/business/station")
public class BizStationController extends BaseController
{
    @Autowired
    private IBizStationService bizStationService;

    /**
     * 查询驿站信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:station:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizStation bizStation)
    {
        startPage();
        List<BizStation> list = bizStationService.selectBizStationList(bizStation);
        return getDataTable(list);
    }

    /**
     * 获取驿站信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:station:query')")
    @GetMapping(value = "/{stationId}")
    public AjaxResult getInfo(@PathVariable("stationId") String stationId)
    {
        return success(bizStationService.selectBizStationByStationId(stationId));
    }

    /**
     * 新增驿站信息
     */
    @PreAuthorize("@ss.hasPermi('business:station:add')")
    @Log(title = "驿站信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizStation bizStation)
    {
        return toAjax(bizStationService.insertBizStation(bizStation));
    }

    /**
     * 修改驿站信息
     */
    @PreAuthorize("@ss.hasPermi('business:station:edit')")
    @Log(title = "驿站信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizStation bizStation)
    {
        return toAjax(bizStationService.updateBizStation(bizStation));
    }

    /**
     * 删除驿站信息
     */
    @PreAuthorize("@ss.hasPermi('business:station:remove')")
    @Log(title = "驿站信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationIds}")
    public AjaxResult remove(@PathVariable String[] stationIds)
    {
        return toAjax(bizStationService.deleteBizStationByStationIds(stationIds));
    }
}