package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizPackFlow;

/**
 * 包裹流转记录Mapper接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface BizPackFlowMapper 
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
     * 删除包裹流转记录
     * 
     * @param flowId 包裹流转记录主键
     * @return 结果
     */
    public int deleteBizPackFlowByFlowId(String flowId);

    /**
     * 批量删除包裹流转记录
     * 
     * @param flowIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizPackFlowByFlowIds(String[] flowIds);
}
