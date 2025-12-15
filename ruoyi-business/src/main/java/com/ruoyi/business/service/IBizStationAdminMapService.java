package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizStationAdminMap;

/**
 * 驿站-管理员映射Service接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface IBizStationAdminMapService 
{
    /**
     * 查询驿站-管理员映射
     * 
     * @param stationId 驿站-管理员映射主键
     * @return 驿站-管理员映射
     */
    public BizStationAdminMap selectBizStationAdminMapByStationId(String stationId);

    /**
     * 查询驿站-管理员映射列表
     * 
     * @param bizStationAdminMap 驿站-管理员映射
     * @return 驿站-管理员映射集合
     */
    public List<BizStationAdminMap> selectBizStationAdminMapList(BizStationAdminMap bizStationAdminMap);

    /**
     * 新增驿站-管理员映射
     * 
     * @param bizStationAdminMap 驿站-管理员映射
     * @return 结果
     */
    public int insertBizStationAdminMap(BizStationAdminMap bizStationAdminMap);

    /**
     * 修改驿站-管理员映射
     * 
     * @param bizStationAdminMap 驿站-管理员映射
     * @return 结果
     */
    public int updateBizStationAdminMap(BizStationAdminMap bizStationAdminMap);

    /**
     * 批量删除驿站-管理员映射
     * 
     * @param stationIds 需要删除的驿站-管理员映射主键集合
     * @return 结果
     */
    public int deleteBizStationAdminMapByStationIds(String[] stationIds);

    /**
     * 删除驿站-管理员映射信息
     * 
     * @param stationId 驿站-管理员映射主键
     * @return 结果
     */
    public int deleteBizStationAdminMapByStationId(String stationId);
}
