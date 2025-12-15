package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizAddr;

/**
 * 用户地址簿Service接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface IBizAddrService 
{
    /**
     * 查询用户地址簿
     * 
     * @param addrId 用户地址簿主键
     * @return 用户地址簿
     */
    public BizAddr selectBizAddrByAddrId(String addrId);

    /**
     * 查询用户地址簿列表
     * 
     * @param bizAddr 用户地址簿
     * @return 用户地址簿集合
     */
    public List<BizAddr> selectBizAddrList(BizAddr bizAddr);

    /**
     * 新增用户地址簿
     * 
     * @param bizAddr 用户地址簿
     * @return 结果
     */
    public int insertBizAddr(BizAddr bizAddr);

    /**
     * 修改用户地址簿
     * 
     * @param bizAddr 用户地址簿
     * @return 结果
     */
    public int updateBizAddr(BizAddr bizAddr);

    /**
     * 批量删除用户地址簿
     * 
     * @param addrIds 需要删除的用户地址簿主键集合
     * @return 结果
     */
    public int deleteBizAddrByAddrIds(String[] addrIds);

    /**
     * 删除用户地址簿信息
     * 
     * @param addrId 用户地址簿主键
     * @return 结果
     */
    public int deleteBizAddrByAddrId(String addrId);
}
