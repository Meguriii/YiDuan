package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizPackVerify;

/**
 * 包裹审核记录Service接口
 * 
 * @author weiyi
 * @date 2025-12-09
 */
public interface IBizPackVerifyService 
{
    /**
     * 查询包裹审核记录
     * 
     * @param verifyId 包裹审核记录主键
     * @return 包裹审核记录
     */
    public BizPackVerify selectBizPackVerifyByVerifyId(String verifyId);

    /**
     * 查询包裹审核记录列表
     * 
     * @param bizPackVerify 包裹审核记录
     * @return 包裹审核记录集合
     */
    public List<BizPackVerify> selectBizPackVerifyList(BizPackVerify bizPackVerify);

    /**
     * 新增包裹审核记录
     * 
     * @param bizPackVerify 包裹审核记录
     * @return 结果
     */
    public int insertBizPackVerify(BizPackVerify bizPackVerify);

    /**
     * 修改包裹审核记录
     * 
     * @param bizPackVerify 包裹审核记录
     * @return 结果
     */
    public int updateBizPackVerify(BizPackVerify bizPackVerify);

    /**
     * 批量删除包裹审核记录
     * 
     * @param verifyIds 需要删除的包裹审核记录主键集合
     * @return 结果
     */
    public int deleteBizPackVerifyByVerifyIds(String[] verifyIds);

    /**
     * 删除包裹审核记录信息
     * 
     * @param verifyId 包裹审核记录主键
     * @return 结果
     */
    public int deleteBizPackVerifyByVerifyId(String verifyId);
}
