package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizPackFlowMapper;
import com.ruoyi.business.domain.BizPackFlow;
import com.ruoyi.business.service.IBizPackFlowService;

/**
 * 包裹流转记录Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizPackFlowServiceImpl implements IBizPackFlowService 
{
    @Autowired
    private BizPackFlowMapper bizPackFlowMapper;

    /**
     * 查询包裹流转记录
     * 
     * @param flowId 包裹流转记录主键
     * @return 包裹流转记录
     */
    @Override
    public BizPackFlow selectBizPackFlowByFlowId(String flowId)
    {
        return bizPackFlowMapper.selectBizPackFlowByFlowId(flowId);
    }

    /**
     * 查询包裹流转记录列表
     * 
     * @param bizPackFlow 包裹流转记录
     * @return 包裹流转记录
     */
    @Override
    public List<BizPackFlow> selectBizPackFlowList(BizPackFlow bizPackFlow)
    {
        return bizPackFlowMapper.selectBizPackFlowList(bizPackFlow);
    }

    /**
     * 新增包裹流转记录
     * 
     * @param bizPackFlow 包裹流转记录
     * @return 结果
     */
    @Override
    public int insertBizPackFlow(BizPackFlow bizPackFlow)
    {
        return bizPackFlowMapper.insertBizPackFlow(bizPackFlow);
    }

    /**
     * 修改包裹流转记录
     * 
     * @param bizPackFlow 包裹流转记录
     * @return 结果
     */
    @Override
    public int updateBizPackFlow(BizPackFlow bizPackFlow)
    {
        return bizPackFlowMapper.updateBizPackFlow(bizPackFlow);
    }

    /**
     * 批量删除包裹流转记录
     * 
     * @param flowIds 需要删除的包裹流转记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPackFlowByFlowIds(String[] flowIds)
    {
        return bizPackFlowMapper.deleteBizPackFlowByFlowIds(flowIds);
    }

    /**
     * 删除包裹流转记录信息
     * 
     * @param flowId 包裹流转记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPackFlowByFlowId(String flowId)
    {
        return bizPackFlowMapper.deleteBizPackFlowByFlowId(flowId);
    }
}
