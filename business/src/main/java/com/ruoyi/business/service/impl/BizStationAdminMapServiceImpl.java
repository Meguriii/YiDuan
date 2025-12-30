package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizStationAdminMapMapper;
import com.ruoyi.business.domain.BizStationAdminMap;
import com.ruoyi.business.service.IBizStationAdminMapService;

/**
 * 驿站-管理员映射Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizStationAdminMapServiceImpl implements IBizStationAdminMapService 
{
    @Autowired
    private BizStationAdminMapMapper bizStationAdminMapMapper;

    /**
     * 查询驿站-管理员映射
     * 
     * @param stationId 驿站-管理员映射主键
     * @return 驿站-管理员映射
     */
    @Override
    public BizStationAdminMap selectBizStationAdminMapByStationId(String stationId)
    {
        return bizStationAdminMapMapper.selectBizStationAdminMapByStationId(stationId);
    }

    /**
     * 查询驿站-管理员映射列表
     * 
     * @param bizStationAdminMap 驿站-管理员映射
     * @return 驿站-管理员映射
     */
    @Override
    public List<BizStationAdminMap> selectBizStationAdminMapList(BizStationAdminMap bizStationAdminMap)
    {
        return bizStationAdminMapMapper.selectBizStationAdminMapList(bizStationAdminMap);
    }

    /**
     * 新增驿站-管理员映射
     * 
     * @param bizStationAdminMap 驿站-管理员映射
     * @return 结果
     */
    @Override
    public int insertBizStationAdminMap(BizStationAdminMap bizStationAdminMap)
    {
        return bizStationAdminMapMapper.insertBizStationAdminMap(bizStationAdminMap);
    }

    /**
     * 修改驿站-管理员映射
     * 
     * @param bizStationAdminMap 驿站-管理员映射
     * @return 结果
     */
    @Override
    public int updateBizStationAdminMap(BizStationAdminMap bizStationAdminMap)
    {
        return bizStationAdminMapMapper.updateBizStationAdminMap(bizStationAdminMap);
    }

    /**
     * 批量删除驿站-管理员映射
     * 
     * @param stationIds 需要删除的驿站-管理员映射主键
     * @return 结果
     */
    @Override
    public int deleteBizStationAdminMapByStationIds(String[] stationIds)
    {
        return bizStationAdminMapMapper.deleteBizStationAdminMapByStationIds(stationIds);
    }

    /**
     * 删除驿站-管理员映射信息
     * 
     * @param stationId 驿站-管理员映射主键
     * @return 结果
     */
    @Override
    public int deleteBizStationAdminMapByStationId(String stationId)
    {
        return bizStationAdminMapMapper.deleteBizStationAdminMapByStationId(stationId);
    }
}
