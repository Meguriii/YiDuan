package com.ruoyi.business.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.business.domain.BizPack;
import com.ruoyi.business.domain.BizPackWithSender;

/**
 * 包裹表Service接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface IBizPackService 
{
    /**
     * 查询包裹表
     * 
     * @param packId 包裹表主键
     * @return 包裹表
     */
    public BizPack selectBizPackByPackId(String packId);

    /**
     * 查询包裹表(包含寄件人信息)
     * 
     * @param packId 包裹表主键
     * @return 包裹表(包含寄件人信息)
     */
    public BizPackWithSender selectBizPackWithSenderByPackId(String packId);

    /**
     * 根据用户ID查询相关包裹（寄件或收件）
     * 
     * @param userId 用户ID
     * @return 包裹表集合
     */
    public List<BizPack> selectBizPackByUserId(Long userId);

    /**
     * 根据用户ID查询相关包裹（寄件或收件）包含寄件人信息
     * 
     * @param userId 用户ID
     * @return 包裹表集合(包含寄件人信息)
     */
    public List<BizPackWithSender> selectBizPackWithSenderByUserId(Long userId);

    /**
     * 查询包裹表列表
     * 
     * @param bizPack 包裹表
     * @return 包裹表集合
     */
    public List<BizPack> selectBizPackList(BizPack bizPack);

    /**
     * 根据收件人ID和状态查询包裹表列表
     * 
     * @param receiverId 收件人ID
     * @param status 状态
     * @return 包裹表集合
     */
    public List<BizPack> selectBizPackByReceiverIdAndStatus(@Param("receiverId") Long receiverId, @Param("status") String status);

    /**
     * 查询包裹表列表(包含寄件人信息)
     * 
     * @param bizPack 包裹表
     * @return 包裹表集合(包含寄件人信息)
     */
    public List<BizPackWithSender> selectBizPackListWithSender(BizPack bizPack);

    /**
     * 新增包裹表
     * 
     * @param bizPack 包裹表
     * @return 结果
     */
    public int insertBizPack(BizPack bizPack);

    /**
     * 修改包裹表
     * 
     * @param bizPack 包裹表
     * @return 结果
     */
    public int updateBizPack(BizPack bizPack);

    /**
     * 批量删除包裹表
     * 
     * @param packIds 需要删除的包裹表主键集合
     * @return 结果
     */
    public int deleteBizPackByPackIds(String[] packIds);

    /**
     * 删除包裹表信息
     * 
     * @param packId 包裹表主键
     * @return 结果
     */
    public int deleteBizPackByPackId(String packId);
}
