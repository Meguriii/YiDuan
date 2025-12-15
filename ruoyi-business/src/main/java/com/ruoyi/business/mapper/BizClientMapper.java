package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizClient;

/**
 * 业务用户Mapper接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface BizClientMapper 
{
    /**
     * 查询业务用户
     * 
     * @param clientId 业务用户主键
     * @return 业务用户
     */
    public BizClient selectBizClientByClientId(String clientId);

    /**
     * 查询业务用户列表
     * 
     * @param bizClient 业务用户
     * @return 业务用户集合
     */
    public List<BizClient> selectBizClientList(BizClient bizClient);

    /**
     * 新增业务用户
     * 
     * @param bizClient 业务用户
     * @return 结果
     */
    public int insertBizClient(BizClient bizClient);

    /**
     * 修改业务用户
     * 
     * @param bizClient 业务用户
     * @return 结果
     */
    public int updateBizClient(BizClient bizClient);

    /**
     * 删除业务用户
     * 
     * @param clientId 业务用户主键
     * @return 结果
     */
    public int deleteBizClientByClientId(String clientId);

    /**
     * 批量删除业务用户
     * 
     * @param clientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizClientByClientIds(String[] clientIds);
}
