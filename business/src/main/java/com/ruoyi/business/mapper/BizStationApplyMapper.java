package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizStationApply;

/**
 * 驿站申请Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-29
 */
public interface BizStationApplyMapper 
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
     * 修改驿站申请
     * 
     * @param bizStationApply 驿站申请
     * @return 结果
     */
    public int updateBizStationApply(BizStationApply bizStationApply);

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
     * @param applyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizStationApplyByApplyIds(Long[] applyIds);
}
