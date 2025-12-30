package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizStation;

/**
 * 驿站信息Service接口
 * 
 * @author weiyi
 * @date 2025-12-15
 */
public interface IBizStationService 
{
    /**
     * 查询驿站信息
     * 
     * @param stationId 驿站信息主键
     * @return 驿站信息
     */
    public BizStation selectBizStationByStationId(String stationId);

    /**
     * 查询驿站信息列表
     * 
     * @param bizStation 驿站信息
     * @return 驿站信息集合
     */
    public List<BizStation> selectBizStationList(BizStation bizStation);

    /**
     * 新增驿站信息
     * 
     * @param bizStation 驿站信息
     * @return 结果
     */
    public int insertBizStation(BizStation bizStation);

    /**
     * 修改驿站信息
     * 
     * @param bizStation 驿站信息
     * @return 结果
     */
    public int updateBizStation(BizStation bizStation);

    /**
     * 批量删除驿站信息
     * 
     * @param stationIds 需要删除的驿站信息主键集合
     * @return 结果
     */
    public int deleteBizStationByStationIds(String[] stationIds);

    /**
     * 删除驿站信息信息
     * 
     * @param stationId 驿站信息主键
     * @return 结果
     */
    public int deleteBizStationByStationId(String stationId);
}