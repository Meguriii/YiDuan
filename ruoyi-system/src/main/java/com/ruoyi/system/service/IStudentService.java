package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.domain.Student;

/**
 * 学生信息Service接口
 * 
 * @author weiyi
 * @date 2025-07-15
 */
public interface IStudentService 
{
    /**
     * 查询学生信息
     * 
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    public Student selectStudentByStudentId(Long studentId);

    /**
     * 查询学生信息列表
     * 
     * @param student 学生信息
     * @return 学生信息集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生信息
     * 
     * @param student 学生信息
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 批量删除学生信息
     * 
     * @param studentIds 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteStudentByStudentIds(Long[] studentIds);

    /**
     * 删除学生信息信息
     * 
     * @param studentId 学生信息主键
     * @return 结果
     */
    public int deleteStudentByStudentId(Long studentId);

    /**
     * 修改角色状态
     *
     * @param student 角色信息
     * @return 结果
     */
    public int updateStudentStatus(Student student);
}
