package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizPack;

/**
 * 包裹表Mapper接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface BizPackMapper 
{
    /**
     * 查询包裹表
     * 
     * @param packId 包裹表主键
     * @return 包裹表
     */
    public BizPack selectBizPackByPackId(String packId);

    /**
     * 查询包裹表列表
     * 
     * @param bizPack 包裹表
     * @return 包裹表集合
     */
    public List<BizPack> selectBizPackList(BizPack bizPack);

    /**
     * 新增包裹表
     * 
     * @param bizPack 包裹表
     * @return 结果
     */
    public int insertBizPack(BizPack bizPack);

    /**
     * 修改包裹表
     * 
     * @param bizPack 包裹表
     * @return 结果
     */
    public int updateBizPack(BizPack bizPack);

    /**
     * 删除包裹表
     * 
     * @param packId 包裹表主键
     * @return 结果
     */
    public int deleteBizPackByPackId(String packId);

    /**
     * 批量删除包裹表
     * 
     * @param packIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizPackByPackIds(String[] packIds);
}
