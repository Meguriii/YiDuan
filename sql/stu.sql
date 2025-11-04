SET GLOBAL time_zone = '+8:00';

drop table if exists student;

create table student (
                             student_id           int(11)         auto_increment    comment '学号',
                             student_name         varchar(30)     default ''        comment '姓名',
                             student_sex          char(1)         default '2'       comment '性别（0男 1女 2未知）',
                             student_birth        date            not null          comment '生日',
                             student_phone        varchar(30)     default ''        comment '电话',
                             student_update       datetime
                             default current_timestamp on update current_timestamp  comment '更新时间',
                             student_status       char(1)         default 0         comment '状态（0正常 1停用）',
                             primary key (student_id)
) engine=innodb auto_increment=1 comment = '学生信息表';

insert into student (
    student_name, student_sex, student_birth, student_phone,  student_status
) values
      ('张三', '0', '2007-03-15', '13862543189','0'),
      ('李四', '1', '2005-06-22', '15603796273','0'),
      ('王五', '0', '2006-01-05', '18973425501','1'),
      ('赵六', '0', '2004-09-30', '17791834062','0');