package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StuLeaveMapper;
import com.ruoyi.system.domain.StuLeave;
import com.ruoyi.system.service.IStuLeaveService;

/**
 * 学生请假Service业务层处理
 * 
 * @author weiyi
 * @date 2025-07-17
 */
@Service
public class StuLeaveServiceImpl implements IStuLeaveService 
{
    @Autowired
    private StuLeaveMapper stuLeaveMapper;

    /**
     * 查询学生请假
     * 
     * @param leaveId 学生请假主键
     * @return 学生请假
     */
    @Override
    public StuLeave selectStuLeaveByLeaveId(Long leaveId)
    {
        return stuLeaveMapper.selectStuLeaveByLeaveId(leaveId);
    }

    /**
     * 查询学生请假列表
     * 
     * @param stuLeave 学生请假
     * @return 学生请假
     */
    @Override
    public List<StuLeave> selectStuLeaveList(StuLeave stuLeave)
    {
        return stuLeaveMapper.selectStuLeaveList(stuLeave);
    }

    /**
     * 新增学生请假
     * 
     * @param stuLeave 学生请假
     * @return 结果
     */
    @Override
    public int insertStuLeave(StuLeave stuLeave)
    {
        return stuLeaveMapper.insertStuLeave(stuLeave);
    }

    /**
     * 修改学生请假
     * 
     * @param stuLeave 学生请假
     * @return 结果
     */
    @Override
    public int updateStuLeave(StuLeave stuLeave)
    {
        return stuLeaveMapper.updateStuLeave(stuLeave);
    }

    /**
     * 批量删除学生请假
     * 
     * @param leaveIds 需要删除的学生请假主键
     * @return 结果
     */
    @Override
    public int deleteStuLeaveByLeaveIds(Long[] leaveIds)
    {
        return stuLeaveMapper.deleteStuLeaveByLeaveIds(leaveIds);
    }

    /**
     * 删除学生请假信息
     * 
     * @param leaveId 学生请假主键
     * @return 结果
     */
    @Override
    public int deleteStuLeaveByLeaveId(Long leaveId)
    {
        return stuLeaveMapper.deleteStuLeaveByLeaveId(leaveId);
    }
}
