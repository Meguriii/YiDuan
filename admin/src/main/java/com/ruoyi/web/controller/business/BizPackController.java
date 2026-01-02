package com.ruoyi.business.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
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
import com.ruoyi.business.domain.BizPackWithSender;
import com.ruoyi.business.service.IBizPackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;

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
     * 查询包裹表列表(包含寄件人信息)
     */
    @PreAuthorize("@ss.hasPermi('business:pack:list')")
    @GetMapping("/listWithSender")
    public TableDataInfo listWithSender(BizPack bizPack)
    {
        startPage();
        List<BizPackWithSender> list = bizPackService.selectBizPackListWithSender(bizPack);
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
     * 导出包裹表列表(包含寄件人信息)
     */
    @PreAuthorize("@ss.hasPermi('business:pack:export')")
    @Log(title = "包裹表", businessType = BusinessType.EXPORT)
    @PostMapping("/exportWithSender")
    public void exportWithSender(HttpServletResponse response, BizPack bizPack)
    {
        List<BizPackWithSender> list = bizPackService.selectBizPackListWithSender(bizPack);
        ExcelUtil<BizPackWithSender> util = new ExcelUtil<BizPackWithSender>(BizPackWithSender.class);
        util.exportExcel(response, list, "包裹表数据(含寄件人信息)");
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
     * 获取包裹详细信息(包含寄件人信息)
     */
    @PreAuthorize("@ss.hasPermi('business:pack:query')")
    @GetMapping(value = "/detail/{packId}")
    public AjaxResult getDetailInfo(@PathVariable("packId") String packId)
    {
        return success(bizPackService.selectBizPackWithSenderByPackId(packId));
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

    /**
     * 发货核验 - 驿站管理员功能
     * 对用户提供的包裹进行核验，检查是否为易碎品、危险品等
     */
    @PreAuthorize("@ss.hasPermi('business:pack:verify')")
    @Log(title = "发货核验", businessType = BusinessType.UPDATE)
    @PutMapping("/verifySend/{packId}")
    public AjaxResult verifySend(@PathVariable String packId, @RequestBody BizPack bizPack)
    {
        bizPack.setPackId(packId);
        // 检验合格则更改状态为"寄出已核验"
        bizPack.setStatus("寄出已核验");
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 发货核验不通过 - 退回包裹
     */
    @PreAuthorize("@ss.hasPermi('business:pack:verify')")
    @Log(title = "发货核验不通过", businessType = BusinessType.UPDATE)
    @PutMapping("/rejectSend/{packId}")
    public AjaxResult rejectSend(@PathVariable String packId, @RequestBody Map<String, String> params)
    {
        BizPack bizPack = new BizPack();
        bizPack.setPackId(packId);
        bizPack.setStatus("异常");
        bizPack.setNote("核验不通过：" + params.get("reason"));
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 收货核验 - 驿站管理员功能
     * 对快递员送达的包裹进行核验
     */
    @PreAuthorize("@ss.hasPermi('business:pack:verify')")
    @Log(title = "收货核验", businessType = BusinessType.UPDATE)
    @PutMapping("/verifyReceive/{packId}")
    public AjaxResult verifyReceive(@PathVariable String packId, @RequestBody BizPack bizPack)
    {
        bizPack.setPackId(packId);
        // 检验合格则更改状态为"入库已核验"
        bizPack.setStatus("入库已核验");
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 收货核验不通过 - 包裹破损
     */
    @PreAuthorize("@ss.hasPermi('business:pack:verify')")
    @Log(title = "收货核验不通过", businessType = BusinessType.UPDATE)
    @PutMapping("/rejectReceive/{packId}")
    public AjaxResult rejectReceive(@PathVariable String packId, @RequestBody Map<String, String> params)
    {
        BizPack bizPack = new BizPack();
        bizPack.setPackId(packId);
        bizPack.setStatus("拒收");
        bizPack.setNote("入库核验不通过：" + params.get("reason"));
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 生成取件码并上架 - 驿站管理员功能
     */
    @PreAuthorize("@ss.hasPermi('business:pack:shelve')")
    @Log(title = "生成取件码并上架", businessType = BusinessType.UPDATE)
    @PutMapping("/generatePickupCode/{packId}")
    public AjaxResult generatePickupCode(@PathVariable String packId)
    {
        BizPack bizPack = new BizPack();
        bizPack.setPackId(packId);
        // 生成随机4位取件码
        String pickupCode = String.format("%04d", (int)(Math.random() * 10000));
        bizPack.setPickupCode(pickupCode);
        bizPack.setStatus("待取件");
        
        int result = bizPackService.updateBizPack(bizPack);
        if (result > 0) {
            Map<String, String> data = new HashMap<>();
            data.put("pickupCode", pickupCode);
            return success(data);
        }
        return error("生成取件码失败");
    }

    /**
     * 取件 - 用户取件功能
     */
    @Log(title = "取件", businessType = BusinessType.UPDATE)
    @PutMapping("/pickup/{pickupCode}")
    public AjaxResult pickup(@PathVariable String pickupCode)
    {
        // 根据取件码查找包裹
        BizPack query = new BizPack();
        query.setPickupCode(pickupCode);
        List<BizPack> list = bizPackService.selectBizPackList(query);
        
        if (list.isEmpty()) {
            return error("取件码不存在");
        }
        
        BizPack bizPack = list.get(0);
        if (!"待取件".equals(bizPack.getStatus())) {
            return error("包裹状态不正确，无法取件");
        }
        
        // 验证当前用户是否为收件人
        Long currentUserId = SecurityUtils.getUserId();
        if (!currentUserId.equals(bizPack.getReceiverId())) {
            return error("您不是该包裹的收件人，无法取件");
        }
        
        bizPack.setStatus("已取件");
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 超级管理员修改包裹状态
     */
    @PreAuthorize("@ss.hasPermi('business:pack:edit')")
    @Log(title = "修改包裹状态", businessType = BusinessType.UPDATE)
    @PutMapping("/updateStatus/{packId}")
    public AjaxResult updatePackStatus(@PathVariable String packId, @RequestBody Map<String, String> params)
    {
        BizPack bizPack = new BizPack();
        bizPack.setPackId(packId);
        String status = params.get("status");
        if (status == null || status.trim().isEmpty()) {
            return error("包裹状态不能为空");
        }
        bizPack.setStatus(status);
        String note = params.get("note");
        if (note != null) {
            bizPack.setNote(note);
        }
        return toAjax(bizPackService.updateBizPack(bizPack));
    }

    /**
     * 查询用户相关包裹 - 用户功能
     */
    @GetMapping("/myPacks/{userId}")
    public TableDataInfo getMyPacks(@PathVariable Long userId)
    {
        startPage();
        List<BizPack> list = bizPackService.selectBizPackByUserId(userId);
        return getDataTable(list);
    }
    
    /**
     * 根据收件人ID和状态查询包裹 - 用户功能
     */
    @GetMapping("/byReceiverIdAndStatus")
    public TableDataInfo getPacksByReceiverIdAndStatus(Long receiverId, String status)
    {
        // 验证用户只能查询自己的包裹
        if (receiverId != null && !receiverId.equals(SecurityUtils.getUserId())) {
            return getDataTable(new ArrayList<>());
        }
        startPage();
        List<BizPack> list = bizPackService.selectBizPackByReceiverIdAndStatus(receiverId, status);
        return getDataTable(list);
    }
    
    /**
     * 查询用户相关包裹(包含寄件人信息) - 用户功能
     */
    @GetMapping("/myPacksWithSender/{userId}")
    public TableDataInfo getMyPacksWithSender(@PathVariable Long userId)
    {
        startPage();
        List<BizPackWithSender> list = bizPackService.selectBizPackWithSenderByUserId(userId);
        return getDataTable(list);
    }
}
