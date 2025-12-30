package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizStationApply;

/**
 * 驿站申请Service接口
 * 
 * @author ruoyi
 * @date 2025-12-29
 */
public interface IBizStationApplyService 
{
    /**
     * 查询驿站申请
     * 
     * @param applyId 驿站申请主键
     * @return 驿站申请
     */
    public BizStationApply selectBizStationApplyByApplyId(Long applyId);

    /**
     * 查询驿站申请列表
     * 
     * @param bizStationApply 驿站申请
     * @return 驿站申请集合
     */
    public List<BizStationApply> selectBizStationApplyList(BizStationApply bizStationApply);

    /**
     * 新增驿站申请
     * 
     * @param bizStationApply 驿站申请
     * @return 结果
     */
    public int insertBizStationApply(BizStationApply bizStationApply);

    /**
     * 审核驿站申请（审核通过后创建驿站和部门）
     * 
     * @param applyId 申请ID
     * @param pass 是否通过
     * @param parentDeptId 父部门ID
     * @param auditRemark 审核备注
     * @return 结果
     */
    public int auditStationApply(Long applyId, boolean pass, Long parentDeptId, String auditRemark);

    /**
     * 删除驿站申请
     * 
     * @param applyId 驿站申请主键
     * @return 结果
     */
    public int deleteBizStationApplyByApplyId(Long applyId);

    /**
     * 批量删除驿站申请
     * 
     * @param applyIds 需要删除的驿站申请主键集合
     * @return 结果
     */
    public int deleteBizStationApplyByApplyIds(Long[] applyIds);
}
