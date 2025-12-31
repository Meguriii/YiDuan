package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizAddrMapper;
import com.ruoyi.business.domain.BizAddr;
import com.ruoyi.business.service.IBizAddrService;

/**
 * 用户地址簿Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizAddrServiceImpl implements IBizAddrService 
{
    @Autowired
    private BizAddrMapper bizAddrMapper;

    /**
     * 查询用户地址簿
     * 
     * @param addrId 用户地址簿主键
     * @return 用户地址簿
     */
    @Override
    public BizAddr selectBizAddrByAddrId(String addrId)
    {
        return bizAddrMapper.selectBizAddrByAddrId(addrId);
    }

    /**
     * 查询用户地址簿列表
     * 
     * @param bizAddr 用户地址簿
     * @return 用户地址簿
     */
    @Override
    public List<BizAddr> selectBizAddrList(BizAddr bizAddr)
    {
        return bizAddrMapper.selectBizAddrList(bizAddr);
    }

    /**
     * 新增用户地址簿
     * 
     * @param bizAddr 用户地址簿
     * @return 结果
     */
    @Override
    public int insertBizAddr(BizAddr bizAddr)
    {
        return bizAddrMapper.insertBizAddr(bizAddr);
    }

    /**
     * 修改用户地址簿
     * 
     * @param bizAddr 用户地址簿
     * @return 结果
     */
    @Override
    public int updateBizAddr(BizAddr bizAddr)
    {
        Integer isDefault = bizAddr.getIsDefault();
        if (isDefault == null || (isDefault != 0 && isDefault != 1)) {
            throw new IllegalArgumentException("是否默认地址只能为 0 或 1");
        }

        // 只有当这条要设置为 0 时才处理
        if (isDefault == 0) {
            // 假设按 userId 维度唯一，你自己换成对应字段
            bizAddrMapper.updateOthersToOne(bizAddr.getClientId(), bizAddr.getAddrId());
        }

        return bizAddrMapper.updateBizAddr(bizAddr);
    }

    /**
     * 批量删除用户地址簿
     * 
     * @param addrIds 需要删除的用户地址簿主键
     * @return 结果
     */
    @Override
    public int deleteBizAddrByAddrIds(String[] addrIds)
    {
        return bizAddrMapper.deleteBizAddrByAddrIds(addrIds);
    }

    /**
     * 删除用户地址簿信息
     * 
     * @param addrId 用户地址簿主键
     * @return 结果
     */
    @Override
    public int deleteBizAddrByAddrId(String addrId)
    {
        return bizAddrMapper.deleteBizAddrByAddrId(addrId);
    }
}
