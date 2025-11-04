package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.StuLeave;
import com.ruoyi.system.service.IStuLeaveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生请假Controller
 * 
 * @author weiyi
 * @date 2025-07-17
 */
@RestController
@RequestMapping("/system/leave")
public class StuLeaveController extends BaseController
{
    @Autowired
    private IStuLeaveService stuLeaveService;

    /**
     * 查询学生请假列表
     */
    @PreAuthorize("@ss.hasPermi('system:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(StuLeave stuLeave)
    {
        startPage();
        List<StuLeave> list = stuLeaveService.selectStuLeaveList(stuLeave);
        return getDataTable(list);
    }

    /**
     * 导出学生请假列表
     */
    @PreAuthorize("@ss.hasPermi('system:leave:export')")
    @Log(title = "学生请假", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StuLeave stuLeave)
    {
        List<StuLeave> list = stuLeaveService.selectStuLeaveList(stuLeave);
        ExcelUtil<StuLeave> util = new ExcelUtil<StuLeave>(StuLeave.class);
        util.exportExcel(response, list, "学生请假数据");
    }

    /**
     * 获取学生请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:leave:query')")
    @GetMapping(value = "/{leaveId}")
    public AjaxResult getInfo(@PathVariable("leaveId") Long leaveId)
    {
        return success(stuLeaveService.selectStuLeaveByLeaveId(leaveId));
    }

    /**
     * 新增学生请假
     */
    @PreAuthorize("@ss.hasPermi('system:leave:add')")
    @Log(title = "学生请假", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StuLeave stuLeave)
    {
        return toAjax(stuLeaveService.insertStuLeave(stuLeave));
    }

    /**
     * 审核学生请假
     */
    @PreAuthorize("@ss.hasPermi('system:leave:edit')")
    @Log(title = "学生请假", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StuLeave stuLeave)
    {
        return toAjax(stuLeaveService.updateStuLeave(stuLeave));
    }

    /**
     * 删除学生请假
     */
    @PreAuthorize("@ss.hasPermi('system:leave:remove')")
    @Log(title = "学生请假", businessType = BusinessType.DELETE)
	@DeleteMapping("/{leaveIds}")
    public AjaxResult remove(@PathVariable Long[] leaveIds)
    {
        return toAjax(stuLeaveService.deleteStuLeaveByLeaveIds(leaveIds));
    }
}