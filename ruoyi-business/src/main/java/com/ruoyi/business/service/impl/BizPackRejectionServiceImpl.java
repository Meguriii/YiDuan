package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizPackRejectionMapper;
import com.ruoyi.business.domain.BizPackRejection;
import com.ruoyi.business.service.IBizPackRejectionService;

/**
 * 拒收/退件记录Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizPackRejectionServiceImpl implements IBizPackRejectionService 
{
    @Autowired
    private BizPackRejectionMapper bizPackRejectionMapper;

    /**
     * 查询拒收/退件记录
     * 
     * @param rejectId 拒收/退件记录主键
     * @return 拒收/退件记录
     */
    @Override
    public BizPackRejection selectBizPackRejectionByRejectId(String rejectId)
    {
        return bizPackRejectionMapper.selectBizPackRejectionByRejectId(rejectId);
    }

    /**
     * 查询拒收/退件记录列表
     * 
     * @param bizPackRejection 拒收/退件记录
     * @return 拒收/退件记录
     */
    @Override
    public List<BizPackRejection> selectBizPackRejectionList(BizPackRejection bizPackRejection)
    {
        return bizPackRejectionMapper.selectBizPackRejectionList(bizPackRejection);
    }

    /**
     * 新增拒收/退件记录
     * 
     * @param bizPackRejection 拒收/退件记录
     * @return 结果
     */
    @Override
    public int insertBizPackRejection(BizPackRejection bizPackRejection)
    {
        return bizPackRejectionMapper.insertBizPackRejection(bizPackRejection);
    }

    /**
     * 修改拒收/退件记录
     * 
     * @param bizPackRejection 拒收/退件记录
     * @return 结果
     */
    @Override
    public int updateBizPackRejection(BizPackRejection bizPackRejection)
    {
        return bizPackRejectionMapper.updateBizPackRejection(bizPackRejection);
    }

    /**
     * 批量删除拒收/退件记录
     * 
     * @param rejectIds 需要删除的拒收/退件记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPackRejectionByRejectIds(String[] rejectIds)
    {
        return bizPackRejectionMapper.deleteBizPackRejectionByRejectIds(rejectIds);
    }

    /**
     * 删除拒收/退件记录信息
     * 
     * @param rejectId 拒收/退件记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPackRejectionByRejectId(String rejectId)
    {
        return bizPackRejectionMapper.deleteBizPackRejectionByRejectId(rejectId);
    }
}
