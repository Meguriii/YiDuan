package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizPackFlow;

/**
 * 包裹流转记录Service接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface IBizPackFlowService 
{
    /**
     * 查询包裹流转记录
     * 
     * @param flowId 包裹流转记录主键
     * @return 包裹流转记录
     */
    public BizPackFlow selectBizPackFlowByFlowId(String flowId);

    /**
     * 查询包裹流转记录列表
     * 
     * @param bizPackFlow 包裹流转记录
     * @return 包裹流转记录集合
     */
    public List<BizPackFlow> selectBizPackFlowList(BizPackFlow bizPackFlow);

    /**
     * 新增包裹流转记录
     * 
     * @param bizPackFlow 包裹流转记录
     * @return 结果
     */
    public int insertBizPackFlow(BizPackFlow bizPackFlow);

    /**
     * 修改包裹流转记录
     * 
     * @param bizPackFlow 包裹流转记录
     * @return 结果
     */
    public int updateBizPackFlow(BizPackFlow bizPackFlow);

    /**
     * 批量删除包裹流转记录
     * 
     * @param flowIds 需要删除的包裹流转记录主键集合
     * @return 结果
     */
    public int deleteBizPackFlowByFlowIds(String[] flowIds);

    /**
     * 删除包裹流转记录信息
     * 
     * @param flowId 包裹流转记录主键
     * @return 结果
     */
    public int deleteBizPackFlowByFlowId(String flowId);
}
