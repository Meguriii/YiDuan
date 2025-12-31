package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BizAddr;
import org.apache.ibatis.annotations.Param;

/**
 * 用户地址簿Mapper接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface BizAddrMapper 
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
     * 删除用户地址簿
     * 
     * @param addrId 用户地址簿主键
     * @return 结果
     */
    public int deleteBizAddrByAddrId(String addrId);

    /**
     * 批量删除用户地址簿
     * 
     * @param addrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizAddrByAddrIds(String[] addrIds);


    public void updateOthersToOne(@Param("clientId") String clientId,
                                  @Param("addrId") String addrId);
}
