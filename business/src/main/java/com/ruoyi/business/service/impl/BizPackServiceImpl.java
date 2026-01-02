package com.ruoyi.business.service.impl;

import java.util.List;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizPackMapper;
import com.ruoyi.business.mapper.BizAddrMapper;
import com.ruoyi.business.domain.BizPack;
import com.ruoyi.business.domain.BizPackWithSender;
import com.ruoyi.business.domain.BizAddr;
import com.ruoyi.business.service.IBizPackService;
import com.ruoyi.common.utils.SecurityUtils;

/**
 * 包裹表Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizPackServiceImpl implements IBizPackService 
{
    @Autowired
    private BizPackMapper bizPackMapper;

    /**
     * 查询包裹表
     * 
     * @param packId 包裹表主键
     * @return 包裹表
     */
    @Override
    public BizPack selectBizPackByPackId(String packId)
    {
        return bizPackMapper.selectBizPackByPackId(packId);
    }

    @Override
    public BizPackWithSender selectBizPackWithSenderByPackId(String packId)
    {
        return bizPackMapper.selectBizPackWithSenderByPackId(packId);
    }

    @Override
    public List<BizPack> selectBizPackByUserId(Long userId)
    {
        return bizPackMapper.selectBizPackByUserId(userId);
    }

    @Override
    public List<BizPack> selectBizPackByReceiverIdAndStatus(@Param("receiverId") Long receiverId, @Param("status") String status)
    {
        return bizPackMapper.selectBizPackByReceiverIdAndStatus(receiverId, status);
    }

    @Override
    public List<BizPackWithSender> selectBizPackWithSenderByUserId(Long userId)
    {
        return bizPackMapper.selectBizPackWithSenderByUserId(userId);
    }

    /**
     * 查询包裹表列表
     * 
     * @param bizPack 包裹表
     * @return 包裹表
     */
    @Override
    public List<BizPack> selectBizPackList(BizPack bizPack)
    {
        return bizPackMapper.selectBizPackList(bizPack);
    }

    @Override
    public List<BizPackWithSender> selectBizPackListWithSender(BizPack bizPack)
    {
        return bizPackMapper.selectBizPackListWithSender(bizPack);
    }

    /**
     * 新增包裹表
     * 
     * @param bizPack 包裹表
     * @return 结果
     */
    @Override
    public int insertBizPack(BizPack bizPack)
    {
        // 如果前端没有传递senderId,则使用当前登录用户的ID
        if (bizPack.getSenderId() == null) {
            Long userId = SecurityUtils.getUserId();
            bizPack.setSenderId(userId);
        }
        return bizPackMapper.insertBizPack(bizPack);
    }

    /**
     * 修改包裹表
     * 
     * @param bizPack 包裹表
     * @return 结果
     */
    @Override
    public int updateBizPack(BizPack bizPack)
    {
        return bizPackMapper.updateBizPack(bizPack);
    }

    /**
     * 批量删除包裹表
     * 
     * @param packIds 需要删除的包裹表主键
     * @return 结果
     */
    @Override
    public int deleteBizPackByPackIds(String[] packIds)
    {
        return bizPackMapper.deleteBizPackByPackIds(packIds);
    }

    /**
     * 删除包裹表信息
     * 
     * @param packId 包裹表主键
     * @return 结果
     */
    @Override
    public int deleteBizPackByPackId(String packId)
    {
        return bizPackMapper.deleteBizPackByPackId(packId);
    }
}
