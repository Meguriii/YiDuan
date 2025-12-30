package com.ruoyi.business.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.business.mapper.BizStationApplyMapper;
import com.ruoyi.business.mapper.BizStationMapper;
import com.ruoyi.business.domain.BizStationApply;
import com.ruoyi.business.domain.BizStation;
import com.ruoyi.business.service.IBizStationApplyService;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.common.utils.SecurityUtils;

/**
 * 驿站申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-29
 */
@Service
public class BizStationApplyServiceImpl implements IBizStationApplyService 
{
    @Autowired
    private BizStationApplyMapper bizStationApplyMapper;

    @Autowired
    private BizStationMapper bizStationMapper;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询驿站申请
     * 
     * @param applyId 驿站申请主键
     * @return 驿站申请
     */
    @Override
    public BizStationApply selectBizStationApplyByApplyId(Long applyId)
    {
        return bizStationApplyMapper.selectBizStationApplyByApplyId(applyId);
    }

    /**
     * 查询驿站申请列表
     * 
     * @param bizStationApply 驿站申请
     * @return 驿站申请
     */
    @Override
    public List<BizStationApply> selectBizStationApplyList(BizStationApply bizStationApply)
    {
        return bizStationApplyMapper.selectBizStationApplyList(bizStationApply);
    }

    /**
     * 新增驿站申请
     * 
     * @param bizStationApply 驿站申请
     * @return 结果
     */
    @Override
    public int insertBizStationApply(BizStationApply bizStationApply)
    {
        // 设置申请人为当前登录用户
        bizStationApply.setContactUserId(SecurityUtils.getUserId());
        // 设置申请状态为待审核
        bizStationApply.setApplyStatus("待审核");
        return bizStationApplyMapper.insertBizStationApply(bizStationApply);
    }

    /**
     * 审核驿站申请（审核通过后创建驿站和部门）
     * 
     * @param applyId 申请ID
     * @param pass 是否通过
     * @param parentDeptId 父部门ID
     * @param auditRemark 审核备注
     * @return 结果
     */
    @Override
    @Transactional
    public int auditStationApply(Long applyId, boolean pass, Long parentDeptId, String auditRemark)
    {
        // 1. 查询申请信息
        BizStationApply apply = bizStationApplyMapper.selectBizStationApplyByApplyId(applyId);
        if (apply == null) {
            throw new RuntimeException("申请不存在");
        }

        if (!"待审核".equals(apply.getApplyStatus())) {
            throw new RuntimeException("该申请已处理");
        }

        // 2. 更新申请状态
        apply.setApplyStatus(pass ? "审核通过" : "审核不通过");
        apply.setAuditBy(SecurityUtils.getUserId());
        apply.setAuditTime(new Date());
        apply.setAuditRemark(auditRemark);
        bizStationApplyMapper.updateBizStationApply(apply);

        // 3. 如果审核通过，创建部门和驿站
        if (pass) {
            // 查询父部门信息
            SysDept parentDept = deptService.selectDeptById(parentDeptId);
            if (parentDept == null) {
                throw new RuntimeException("父部门不存在");
            }

            // 创建新部门
            SysDept newDept = new SysDept();
            newDept.setParentId(parentDeptId);
            newDept.setAncestors(parentDept.getAncestors() + "," + parentDeptId);
            newDept.setDeptName(apply.getStationName());
            newDept.setOrderNum(0);
            newDept.setLeader(apply.getContactUserId().toString());
            newDept.setPhone(apply.getTel());
            newDept.setStatus("0"); // 0正常
            newDept.setCreateBy(SecurityUtils.getUsername());

            int deptResult = deptService.insertDept(newDept);
            if (deptResult <= 0) {
                throw new RuntimeException("创建部门失败");
            }

            // 创建驿站
            BizStation station = new BizStation();
            station.setStationName(apply.getStationName());
            station.setDeptId(newDept.getDeptId());
            station.setStationProv(apply.getStationProv());
            station.setStationCity(apply.getStationCity());
            station.setStationDist(apply.getStationDist());
            station.setStationAddr(apply.getStationAddr());
            station.setContactUserId(apply.getContactUserId());
            station.setStatus("正常");
            station.setTel(apply.getTel());

            int stationResult = bizStationMapper.insertBizStation(station);
            if (stationResult <= 0) {
                throw new RuntimeException("创建驿站失败");
            }
        }

        return 1;
    }

    /**
     * 批量删除驿站申请
     * 
     * @param applyIds 需要删除的驿站申请主键
     * @return 结果
     */
    @Override
    public int deleteBizStationApplyByApplyIds(Long[] applyIds)
    {
        return bizStationApplyMapper.deleteBizStationApplyByApplyIds(applyIds);
    }

    /**
     * 删除驿站申请信息
     * 
     * @param applyId 驿站申请主键
     * @return 结果
     */
    @Override
    public int deleteBizStationApplyByApplyId(Long applyId)
    {
        return bizStationApplyMapper.deleteBizStationApplyByApplyId(applyId);
    }
}
