package com.ruoyi.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizPackVerifyMapper;
import com.ruoyi.business.domain.BizPackVerify;
import com.ruoyi.business.service.IBizPackVerifyService;

/**
 * 包裹审核记录Service业务层处理
 * 
 * @author weiyi
 * @date 2025-12-09
 */
@Service
public class BizPackVerifyServiceImpl implements IBizPackVerifyService 
{
    @Autowired
    private BizPackVerifyMapper bizPackVerifyMapper;

    /**
     * 查询包裹审核记录
     * 
     * @param verifyId 包裹审核记录主键
     * @return 包裹审核记录
     */
    @Override
    public BizPackVerify selectBizPackVerifyByVerifyId(String verifyId)
    {
        return bizPackVerifyMapper.selectBizPackVerifyByVerifyId(verifyId);
    }

    /**
     * 查询包裹审核记录列表
     * 
     * @param bizPackVerify 包裹审核记录
     * @return 包裹审核记录
     */
    @Override
    public List<BizPackVerify> selectBizPackVerifyList(BizPackVerify bizPackVerify)
    {
        return bizPackVerifyMapper.selectBizPackVerifyList(bizPackVerify);
    }

    /**
     * 新增包裹审核记录
     * 
     * @param bizPackVerify 包裹审核记录
     * @return 结果
     */
    @Override
    public int insertBizPackVerify(BizPackVerify bizPackVerify)
    {
        return bizPackVerifyMapper.insertBizPackVerify(bizPackVerify);
    }

    /**
     * 修改包裹审核记录
     * 
     * @param bizPackVerify 包裹审核记录
     * @return 结果
     */
    @Override
    public int updateBizPackVerify(BizPackVerify bizPackVerify)
    {
        return bizPackVerifyMapper.updateBizPackVerify(bizPackVerify);
    }

    /**
     * 批量删除包裹审核记录
     * 
     * @param verifyIds 需要删除的包裹审核记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPackVerifyByVerifyIds(String[] verifyIds)
    {
        return bizPackVerifyMapper.deleteBizPackVerifyByVerifyIds(verifyIds);
    }

    /**
     * 删除包裹审核记录信息
     * 
     * @param verifyId 包裹审核记录主键
     * @return 结果
     */
    @Override
    public int deleteBizPackVerifyByVerifyId(String verifyId)
    {
        return bizPackVerifyMapper.deleteBizPackVerifyByVerifyId(verifyId);
    }
}
