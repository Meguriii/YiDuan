package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizStationMapper;
import com.ruoyi.business.domain.BizStation;
import com.ruoyi.business.service.IBizStationService;

/**
 * 驿站信息Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-15
 */
@Service
public class BizStationServiceImpl implements IBizStationService 
{
    @Autowired
    private BizStationMapper bizStationMapper;

    /**
     * 查询驿站信息
     * 
     * @param stationId 驿站信息主键
     * @return 驿站信息
     */
    @Override
    public BizStation selectBizStationByStationId(String stationId)
    {
        return bizStationMapper.selectBizStationByStationId(stationId);
    }

    /**
     * 查询驿站信息列表
     * 
     * @param bizStation 驿站信息
     * @return 驿站信息
     */
    @Override
    public List<BizStation> selectBizStationList(BizStation bizStation)
    {
        return bizStationMapper.selectBizStationList(bizStation);
    }

    /**
     * 新增驿站信息
     * 
     * @param bizStation 驿站信息
     * @return 结果
     */
    @Override
    public int insertBizStation(BizStation bizStation)
    {
        return bizStationMapper.insertBizStation(bizStation);
    }

    /**
     * 修改驿站信息
     * 
     * @param bizStation 驿站信息
     * @return 结果
     */
    @Override
    public int updateBizStation(BizStation bizStation)
    {
        return bizStationMapper.updateBizStation(bizStation);
    }

    /**
     * 批量删除驿站信息
     * 
     * @param stationIds 需要删除的驿站信息主键
     * @return 结果
     */
    @Override
    public int deleteBizStationByStationIds(String[] stationIds)
    {
        return bizStationMapper.deleteBizStationByStationIds(stationIds);
    }

    /**
     * 删除驿站信息信息
     * 
     * @param stationId 驿站信息主键
     * @return 结果
     */
    @Override
    public int deleteBizStationByStationId(String stationId)
    {
        return bizStationMapper.deleteBizStationByStationId(stationId);
    }
}
