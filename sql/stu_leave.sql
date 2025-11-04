SET GLOBAL time_zone = '+8:00';
drop table if exists stu_leave;

create table stu_leave (
                         leave_id           int(11)         auto_increment    comment '请假ID',
                         leave_student      int(11)         not null          comment '学号',
                         leave_type         char(1)         not null          comment '请假类型（0病假1事假2公假3孕假4年假5其他）',
                         leave_reason       varchar(30)     default ''        comment '请假原因',
                         leave_begin        date            not null          comment '开始日期',
                         leave_end          date            not null          comment '结束日期',
                         leave_result       char(1)         default 0         comment '审核结果（0待审核1通过2不通过）',
                         leave_auditor      int(11)         default ''        comment '审核员ID',
                         primary key (leave_id)
) engine=innodb auto_increment=1 comment = '学生请假表';

insert into stu_leave (
    leave_student, leave_type, leave_reason, leave_begin,  leave_end, leave_result, leave_auditor
) values
      ();