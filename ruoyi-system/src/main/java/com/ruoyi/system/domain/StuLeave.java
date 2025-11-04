package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生请假对象 stu_leave
 * 
 * @author weiyi
 * @date 2025-07-17
 */
public class StuLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 请假ID */
    private Long leaveId;

    /** 学号 */
    @Excel(name = "学号")
    private Long leaveStudent;

    /** 学生姓名 */
    @Excel(name = "姓名")
    private String leaveName;

    /** 请假类型（0病假1事假2公假3孕假4年假5其他） */
    @Excel(name = "请假类型", readConverterExp = "0=病假1事假2公假3孕假4年假5其他")
    private String leaveType;

    /** 请假原因 */
    @Excel(name = "请假原因")
    private String leaveReason;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveBegin;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveEnd;

    /** 审核结果（0待审核1通过2不通过） */
    @Excel(name = "审核结果", readConverterExp = "0=待审核1通过2不通过")
    private String leaveResult;

    /** 审核员ID */
    @Excel(name = "审核员ID")
    private Long leaveAuditor;

    /** 审核员名称 */
    @Excel(name = "审核员名称")
    private String leaveAuditorName;

    public void setLeaveId(Long leaveId) 
    {
        this.leaveId = leaveId;
    }

    public Long getLeaveId() 
    {
        return leaveId;
    }

    public void setLeaveStudent(Long leaveStudent) 
    {
        this.leaveStudent = leaveStudent;
    }

    public Long getLeaveStudent() 
    {
        return leaveStudent;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveType(String leaveType) 
    {
        this.leaveType = leaveType;
    }

    public String getLeaveType() 
    {
        return leaveType;
    }

    public void setLeaveReason(String leaveReason) 
    {
        this.leaveReason = leaveReason;
    }

    public String getLeaveReason() 
    {
        return leaveReason;
    }

    public void setLeaveBegin(Date leaveBegin) 
    {
        this.leaveBegin = leaveBegin;
    }

    public Date getLeaveBegin() 
    {
        return leaveBegin;
    }

    public void setLeaveEnd(Date leaveEnd) 
    {
        this.leaveEnd = leaveEnd;
    }

    public Date getLeaveEnd() 
    {
        return leaveEnd;
    }

    public void setLeaveResult(String leaveResult) 
    {
        this.leaveResult = leaveResult;
    }

    public String getLeaveResult() 
    {
        return leaveResult;
    }

    public void setLeaveAuditor(Long leaveAuditor) 
    {
        this.leaveAuditor = leaveAuditor;
    }

    public Long getLeaveAuditor() 
    {
        return leaveAuditor;
    }

    public void setLeaveAuditorName(String leaveAuditorName)
    {
        this.leaveAuditorName = leaveAuditorName;
    }

    public String getLeaveAuditorName()
    {
        return leaveAuditorName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("leaveId", getLeaveId())
            .append("leaveStudent", getLeaveStudent())
            .append("leaveName", getLeaveName())
            .append("leaveType", getLeaveType())
            .append("leaveReason", getLeaveReason())
            .append("leaveBegin", getLeaveBegin())
            .append("leaveEnd", getLeaveEnd())
            .append("leaveResult", getLeaveResult())
            .append("leaveAuditor", getLeaveAuditor())
            .toString();
    }
}
