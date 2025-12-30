package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizClientMapper;
import com.ruoyi.business.domain.BizClient;
import com.ruoyi.business.service.IBizClientService;

/**
 * 业务用户Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizClientServiceImpl implements IBizClientService 
{
    @Autowired
    private BizClientMapper bizClientMapper;

    /**
     * 查询业务用户
     * 
     * @param clientId 业务用户主键
     * @return 业务用户
     */
    @Override
    public BizClient selectBizClientByClientId(String clientId)
    {
        return bizClientMapper.selectBizClientByClientId(clientId);
    }

    /**
     * 查询业务用户列表
     * 
     * @param bizClient 业务用户
     * @return 业务用户
     */
    @Override
    public List<BizClient> selectBizClientList(BizClient bizClient)
    {
        return bizClientMapper.selectBizClientList(bizClient);
    }

    /**
     * 新增业务用户
     * 
     * @param bizClient 业务用户
     * @return 结果
     */
    @Override
    public int insertBizClient(BizClient bizClient)
    {
        return bizClientMapper.insertBizClient(bizClient);
    }

    /**
     * 修改业务用户
     * 
     * @param bizClient 业务用户
     * @return 结果
     */
    @Override
    public int updateBizClient(BizClient bizClient)
    {
        return bizClientMapper.updateBizClient(bizClient);
    }

    /**
     * 批量删除业务用户
     * 
     * @param clientIds 需要删除的业务用户主键
     * @return 结果
     */
    @Override
    public int deleteBizClientByClientIds(String[] clientIds)
    {
        return bizClientMapper.deleteBizClientByClientIds(clientIds);
    }

    /**
     * 删除业务用户信息
     * 
     * @param clientId 业务用户主键
     * @return 结果
     */
    @Override
    public int deleteBizClientByClientId(String clientId)
    {
        return bizClientMapper.deleteBizClientByClientId(clientId);
    }
}
