package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizPackRejection;

/**
 * 拒收/退件记录Mapper接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface BizPackRejectionMapper 
{
    /**
     * 查询拒收/退件记录
     * 
     * @param rejectId 拒收/退件记录主键
     * @return 拒收/退件记录
     */
    public BizPackRejection selectBizPackRejectionByRejectId(String rejectId);

    /**
     * 查询拒收/退件记录列表
     * 
     * @param bizPackRejection 拒收/退件记录
     * @return 拒收/退件记录集合
     */
    public List<BizPackRejection> selectBizPackRejectionList(BizPackRejection bizPackRejection);

    /**
     * 新增拒收/退件记录
     * 
     * @param bizPackRejection 拒收/退件记录
     * @return 结果
     */
    public int insertBizPackRejection(BizPackRejection bizPackRejection);

    /**
     * 修改拒收/退件记录
     * 
     * @param bizPackRejection 拒收/退件记录
     * @return 结果
     */
    public int updateBizPackRejection(BizPackRejection bizPackRejection);

    /**
     * 删除拒收/退件记录
     * 
     * @param rejectId 拒收/退件记录主键
     * @return 结果
     */
    public int deleteBizPackRejectionByRejectId(String rejectId);

    /**
     * 批量删除拒收/退件记录
     * 
     * @param rejectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizPackRejectionByRejectIds(String[] rejectIds);
}
