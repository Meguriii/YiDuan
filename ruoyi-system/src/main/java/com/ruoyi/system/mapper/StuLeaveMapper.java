package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.StuLeave;

/**
 * 学生请假Mapper接口
 * 
 * @author weiyi
 * @date 2025-07-17
 */
public interface StuLeaveMapper 
{
    /**
     * 查询学生请假
     * 
     * @param leaveId 学生请假主键
     * @return 学生请假
     */
    public StuLeave selectStuLeaveByLeaveId(Long leaveId);

    /**
     * 查询学生请假列表
     * 
     * @param stuLeave 学生请假
     * @return 学生请假集合
     */
    public List<StuLeave> selectStuLeaveList(StuLeave stuLeave);

    /**
     * 新增学生请假
     * 
     * @param stuLeave 学生请假
     * @return 结果
     */
    public int insertStuLeave(StuLeave stuLeave);

    /**
     * 修改学生请假
     * 
     * @param stuLeave 学生请假
     * @return 结果
     */
    public int updateStuLeave(StuLeave stuLeave);

    /**
     * 删除学生请假
     * 
     * @param leaveId 学生请假主键
     * @return 结果
     */
    public int deleteStuLeaveByLeaveId(Long leaveId);

    /**
     * 批量删除学生请假
     * 
     * @param leaveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuLeaveByLeaveIds(Long[] leaveIds);
}
