# 大学快递驿站后台系统

## 1 项目介绍

### 1.1 项目背景

随着高校校园的规模不断扩大和科技发展，师生的网络购物、文件寄送等需求日益增长，快递业务量呈持续上升趋势。传统校园快递服务信息化程度低、管理效率差、包裹分拣与核验环节容易出错，也常常因为管理原因发生学生与驿站的冲突。尤其在高峰期（如开学季、双十一、节假日前后），驿站内包裹堆积严重，取件排队时间长、管理混乱、包裹丢失或误取事件时有发生，严重影响了学生与管理人员的使用体验与工作效率。

为了解决上述问题，本项目拟开发一套大学快递系统，实现校园快递业务的数字化与智能化管理。通过完善的权限分级设计与数据库支持，系统能够实现驿站信息统一管理、包裹全流程追踪、用户取件码自动生成与验证等核心功能，从而提高快递流转效率、规范化驿站管理、保障数据安全与业务透明化。

### 1.2 项目目标

本项目旨在开发一个功能完善、操作便捷、安全可靠的大学快递管理系统，实现以下目标：
•	构建一套可扩展的校园快递信息管理平台；
•	实现驿站注册与审核、包裹核验与追踪、取件码自动生成与验证等核心功能；
•	通过权限分级机制保障数据安全与操作规范；
•	提供良好的界面交互体验与系统可维护性。

### 1.3 项目建设环境与使用范围

应用环境：高校校园内各类快递驿站

用户群体：
•	超级管理员（负责系统与驿站全局管理）
•	驿站管理员（负责本驿站包裹的接收、核验与取件码生成）
•	普通用户（学生、教师等收/寄件个体）

运行平台：Web前后台系统

数据库环境：MySQL

开发环境：采用迭代模型的软件开发流程，支持系统的持续优化与版本更新

## 2 软件需求规格框架

### 2.1 项目用户需求

•	**超级管理员：**

**需求描述1：驿站管理**

详细说明：可以登录后台系统，访问数据库驿站相关的信息，管理驿站信息，审核新注册的驿站，处罚或封禁有异常行为的驿站

**需求描述2：用户管理**

详细说明：可以登录后台系统，访问数据库用户相关的信息，管理用户信息，处理用户的异常行为和信息

•	**驿站管理员：**

**需求描述1：注册**

详细说明：驿站管理员注册驿站，注册时需要填写负责人姓名，驿站地址等信息，等待超级管理员对驿站进行审核以确认信息真实性

**需求描述2：驿站管理**

详细说明：可以登录后台系统，访问数据库内本驿站所有库存包裹信息

**需求描述3：发货核验**

详细说明：对用户提供的包裹进行核验，检查快递是否为易碎品、危险品等并打上特殊标签，退回无法寄出的物品，确认用户是否需要加急，检验合格的快递更改为合格状态，可以出库给快递员进行运送

**需求描述4：收货核验**

详细说明：对快递员送达的包裹进行核验，检查快递是否破损等问题，合格的快递更改为合格状态，代表这个快递已经核验通过，等待对快递进行分类上架，不合格的快递更改为不合格状态，联系收货人进行退货等操作

**需求描述5：生成取件码**

详细说明：对核验状态为合格包裹进行分类，检查站内存储空间，生成合适的取件码，人工上架驿站

•	**业务用户：**

**需求描述1：注册**

详细说明：用户进入前台系统注册用户账号

**需求描述2：登记个人信息**

详细说明：用户填写以及修改姓名，地址，手机号等个人信息，可以增加多个地址

**需求描述3：登记寄送快递**

详细说明：用户在驿站寄出包裹，填写寄件人，收件地址，货物类型等信息，包裹在核验通过后自动入库

**需求描述4：查看快递运输环节**

详细说明：用户在前台查看和自己有关的包裹当前所在环节和包裹所在位置

**需求描述5：取快递**

详细说明：用户在包裹到站时收到提醒，获得取件码，在驿站进行取件

## 3 软件项目计划与设计

### 3.1 项目开发模型

迭代模型中的一次开发迭代是一次完整地经过所有工作流程的过程：（至少包括）需求工作流程、分析设计工作流程、实施工作流程和测试工作流程。实质上，迭代模型是类似小型的瀑布式项目。迭代模型描述软件产品的不同阶段是按产品深度或细化的程度来划分。先将产品的整个框架建立起来，在系统初期，已经具有用户需要的全部功能，然后不断细化和完善已有功能。

具体描述：
a.	一次迭代过程包括了所有软件开发流程。
b.	每一次迭代均产生一个可发布的产品。
c.	该产品为最终产品的一个子集。

我们项目开发的过程中将采用迭代模型。原因主要有以下几点因素：
a.	开发人员缺少项目管理经验，很难将产品一次做好
b.	项目泛用性大，无法完整实现所有商业级功能，必须有选择性地优先满足核心需求
c.	迭代模型作为一种业界常用的软件开发模型，具有较大的学习价值

### 3.2 项目用例建模

![1](image\1.png)

**主要用例1：**

**用例名称**：发货核验

**前置条件**:驿站管理员完成驿站注册并登录系统

**描述：**
1. 当驿站管理员点击"发货核验"进入该用例
2. 驿站管理员核验代发货快递是否为危险品和易碎品
3. 驿站管理员确认该快递是否为加急快递
4. 驿站管理员确认提交
5. 系统更新数据库中的快递状态（是否核验通过，是否为危险品，是否加急等）
6. 系统保存该信息，等待快递员寄送，用例结束

**异常情况**：若第5步中快递核验状态为不合格，则将包裹进行退回操作

**后置条件**：包裹检验状态为合格，状态为待发送

**主成功场景**:驿站管理员登陆系统，点击"发送核验"，人工核验待发送包裹更新数据库状态，核验通过的包裹等待发送

**主要用例2：**

**用例名称**：生成取件码

**前置条件：**
1. 驿站管理员完成驿站注册并登录系统
2. 驿站管理员完成收货核验，收货核验的结果为合格

**描述：**
1. 驿站管理员点击"生成取件码"进入该用例
2. 系统自动生成取件码
3. 驿站管理员点击确认
4. 为包裹信息中增加取件码，更新该取件码为占用状态
5. 系统确认信息后，向用户和驿站管理员显示取件码信息，用例结束

**异常情况：**
1. 第2步中若该快递为核验通过，则系统弹出"快递收货核验未通过"，用例结束
2. 第3步中若驿站管理员超时不确认则重新生成取件码

**后置情况：**系统更新带取出包裹的取件码信息，该取件码为占用状态

**主成功场景：**驿站管理员点击生成取件码，系统自动生成取件码，点击确认，等待系统结果

**主要用例3：**

**用例名称：** 取快递

**前置条件：**
1. 用户完成注册并登录系统
2. 快递已生成取件码

**描述：**
1. 用户在快递成功生成取件码时收到提示并获得取件码
2. 用户扫描手机的取件码和包裹上的取件码取出快递
3. 系统更新快递状态为"取出"，释放对应取件码

**异常状态：** 第2步中若未能正确识别取件码则弹出"未能识别取件码"，并开始重新扫描

**后置状态：** 系统更新快递状态为取出，取件码释放

**主成功场景：** 用户获得取件码后扫描取件码取出包裹，系统更新对应的包裹和取件码状态

### 3.3 UML状态图建模

![2](image\2.png)

![3](image\3.png)

![4](image\4.png)

![5](image\5.png)

![6](image\6.png)

### 3.4 UML活动图建模
![7](image\7.png)

## 4 数据库设计

### 4.1 ER图

![er](image\er.png)

### 4.2 数据库表结构

**驿站表 biz_station**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| station_id | 驿站ID | BIGINT UNSIGNED | NOT NULL AUTO_INCREMENT PRIMARY KEY | 1 |
| station_name | 驿站名称 | VARCHAR(200) | NOT NULL | 上师大奉贤驿站 |
| dept_id | 对应部门ID（一对一） | BIGINT(20) | NOT NULL UNIQUE KEY FOREIGN KEY | 201 |
| station_prov | 省 | VARCHAR(64) | NOT NULL | 上海市 |
| station_city | 市 | VARCHAR(64) | NOT NULL | 上海市 |
| station_dist | 区县 | VARCHAR(64) | NOT NULL | 奉贤区 |
| station_addr | 详细地址 | VARCHAR(255) | NOT NULL | 上师大奉贤校区学生服务中心一楼 |
| contact_user_id | 负责人ID | BIGINT | DEFAULT NULL FOREIGN KEY | 101 |
| status | 驿站状态 | ENUM | '待审核','正常','停用' | 正常 |
| tel | 联系电话 | VARCHAR(20) | DEFAULT NULL | 021-57120001 |
| created_at | 创建时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 00:00:00 |
| updated_at | 更新时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 2025-12-31 16:05:40 |

**驿站-管理员映射表 biz_station_admin_map**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| station_id | 驿站ID | BIGINT UNSIGNED | NOT NULL PRIMARY KEY FOREIGN KEY | 1 |
| admin_user_id | 管理员ID(sys_user) | BIGINT | NOT NULL PRIMARY KEY FOREIGN KEY | 101 |
| role_note | 角色说明，例如站长/核验员 | VARCHAR(100) | DEFAULT NULL | 张站长 |
| assigned_at | 分配时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 23:21:05 |

**用户地址表 biz_addr**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| addr_id | 地址ID | BIGINT UNSIGNED | NOT NULL AUTO_INCREMENT PRIMARY KEY | 1 |
| user_id | 所属用户 | BIGINT | NOT NULL FOREIGN KEY | 1 |
| addr_name | 姓名 | VARCHAR(64) | NOT NULL | 张三 |
| addr_tel | 电话 | VARCHAR(20) | NOT NULL | 13800000001 |
| addr_prov | 省 | VARCHAR(64) | NOT NULL | 上海市 |
| addr_city | 市 | VARCHAR(64) | NOT NULL | 上海市 |
| addr_dist | 区县 | VARCHAR(64) | NOT NULL | 徐汇区 |
| addr_detail | 详细地址 | VARCHAR(255) | NOT NULL | 桂林路100号 |
| is_default | 是否默认地址 | TINYINT(1) | NOT NULL DEFAULT 0 | 0 |
| created_at | 创建时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 00:00:00 |
| updated_at | 更新时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 2025-12-31 00:00:00 |

**包裹 biz_pack**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| pack_id | 包裹ID | BIGINT UNSIGNED | NOT NULL AUTO_INCREMENT PRIMARY KEY | 1 |
| sender_id | 寄件用户 | BIGINT | NOT NULL FOREIGN KEY | 1 |
| sender_prov | 寄件省 | VARCHAR(64) | NOT NULL | 上海市 |
| sender_city | 寄件市 | VARCHAR(64) | NOT NULL | 上海市 |
| sender_dist | 寄件区县 | VARCHAR(64) | NOT NULL | 徐汇区 |
| sender_addr_detail | 寄件详细地址 | VARCHAR(255) | NOT NULL | 桂林路100号 |
| receiver_id | 收件用户ID，可为空 | BIGINT UNSIGNED | DEFAULT NULL | 2 |
| receiver_name | 收件人姓名 | VARCHAR(64) | NOT NULL | 李四 |
| receiver_tel | 收件人手机号 | VARCHAR(20) | NOT NULL | 13800000002 |
| receiver_prov | 收件省 | VARCHAR(64) | NOT NULL | 北京市 |
| receiver_city | 收件市 | VARCHAR(64) | NOT NULL | 北京市 |
| receiver_dist | 收件区县 | VARCHAR(64) | NOT NULL | 海淀区 |
| receiver_addr_detail | 收件详细地址 | VARCHAR(255) | NOT NULL | 中关村南大街27号 |
| weight | 重量 | DECIMAL(8,3) | DEFAULT NULL | 1.850 |
| volume | 体积 | DECIMAL(10,4) | DEFAULT NULL | 0.0105 |
| note | 备注 | VARCHAR(500) | DEFAULT NULL | 书籍资料 |
| is_dangerous | 是否危险品 | TINYINT(1) | NOT NULL DEFAULT 0 | 0 |
| is_fragile | 是否易碎 | TINYINT(1) | NOT NULL DEFAULT 0 | 0 |
| is_urgent | 是否加急 | TINYINT(1) | NOT NULL DEFAULT 0 | 0 |
| status | 包裹状态 | ENUM | '待揽收','寄出待核验','寄出已核验','运输中','到站待核验','入库已核验','待上架','待取件','已取件','拒收','退件','异常' | 入库已核验 |
| pickup_code | 取件码（入库后生成） | VARCHAR(16) | DEFAULT NULL UNIQUE KEY | null |
| created_at | 创建时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 23:21:05 |
| updated_at | 更新时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 2025-12-30 00:20:31 |

**包裹流转记录 biz_pack_flow**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| flow_id | 流转记录ID | BIGINT UNSIGNED | NOT NULL AUTO_INCREMENT PRIMARY KEY | 1 |
| pack_id | 包裹ID | BIGINT UNSIGNED | NOT NULL FOREIGN KEY | 1 |
| station_id | 驿站ID | BIGINT UNSIGNED | NOT NULL FOREIGN KEY | 2 |
| admin_user_id | 操作管理员(sys_user) | BIGINT | DEFAULT NULL FOREIGN KEY | 102 |
| action | 流转动作 | ENUM | '寄出核验','入库核验','上架','出库','分拣','到站','取件','其他' | 寄出核验 |
| action_note | 备注 | VARCHAR(500) | DEFAULT NULL | 徐汇驿站寄出核验完成 |
| created_at | 操作时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 23:21:05 |

**包裹审核 biz_pack_verify**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| verify_id | 审核记录ID | BIGINT UNSIGNED | NOT NULL AUTO_INCREMENT PRIMARY KEY | 1 |
| pack_id | 包裹ID | BIGINT UNSIGNED | NOT NULL FOREIGN KEY | 1 |
| station_id | 驿站ID | BIGINT UNSIGNED | NOT NULL FOREIGN KEY | 2 |
| admin_user_id | 审核管理员ID | BIGINT | DEFAULT NULL FOREIGN KEY | 102 |
| verify_type | 审核类型 | ENUM | '寄出核验','入站核验' | 寄出核验 |
| verify_status | 审核状态 | ENUM | '待审核','通过','不通过' | 通过 |
| verify_note | 审核备注 | VARCHAR(500) | DEFAULT NULL | 寄件信息正确 |
| verified_at | 审核时间 | DATETIME | DEFAULT NULL | 2025-12-29 23:21:05 |
| created_at | 创建时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 23:21:05 |

**退货/拒收记录 biz_pack_rejection**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| reject_id | 记录ID | BIGINT UNSIGNED | NOT NULL AUTO_INCREMENT PRIMARY KEY | 1 |
| pack_id | 包裹ID | BIGINT UNSIGNED | NOT NULL FOREIGN KEY | 2 |
| reject_by | 操作人（sys_user） | BIGINT | DEFAULT NULL FOREIGN KEY | 101 |
| reject_reason | 拒收原因 | VARCHAR(255) | NOT NULL | 收件人临时外出，申请退件 |
| created_at | 记录时间 | DATETIME | NOT NULL DEFAULT CURRENT_TIMESTAMP | 2025-12-29 23:21:05 |

**部门表 sys_dept**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| dept_id | 部门id | BIGINT(20) | NOT NULL AUTO_INCREMENT PRIMARY KEY | 201 |
| parent_id | 父部门id | BIGINT(20) | DEFAULT 0 | 200 |
| ancestors | 祖级列表 | VARCHAR(50) | DEFAULT '' | 0,100,200 |
| dept_name | 部门名称 | VARCHAR(30) | DEFAULT '' | 上师大奉贤驿站 |
| order_num | 显示顺序 | INT(4) | DEFAULT 0 | 1 |
| leader | 负责人 | VARCHAR(20) | DEFAULT NULL |  |
| phone | 联系电话 | VARCHAR(11) | DEFAULT NULL |  |
| email | 邮箱 | VARCHAR(50) | DEFAULT NULL |  |
| status | 部门状态 | CHAR(1) | DEFAULT '0' | 0 |
| del_flag | 删除标志 | CHAR(1) | DEFAULT '0' | 0 |
| create_by | 创建者 | VARCHAR(64) | DEFAULT '' | admin |
| create_time | 创建时间 | DATETIME |  | 2025-12-15 15:17:21 |
| update_by | 更新者 | VARCHAR(64) | DEFAULT '' | admin |
| update_time | 更新时间 | DATETIME |  | 2025-12-15 15:17:53 |

**用户信息表 sys_user**

| 字段名称 | 字段含义 | 类型 | 约束条件 | 样本值 |
|----------|----------|------|----------|--------|
| user_id | 用户ID | BIGINT(20) | NOT NULL AUTO_INCREMENT PRIMARY KEY | 109 |
| dept_id | 部门ID | BIGINT(20) | DEFAULT NULL | 215 |
| user_name | 用户账号 | VARCHAR(30) | NOT NULL | 不知道取什么名字 |
| nick_name | 用户昵称 | VARCHAR(30) | NOT NULL | 不知道取什么名字 |
| user_type | 用户类型（00系统用户） | VARCHAR(2) | DEFAULT '00' | 00 |
| email | 用户邮箱 | VARCHAR(50) | DEFAULT '' | aaa@smail.shnu.edu.cn |
| phonenumber | 手机号码 | VARCHAR(11) | DEFAULT '' | 18300000001 |
| sex | 用户性别 | CHAR(1) | DEFAULT '0' | 0 |
| avatar | 头像地址 | VARCHAR(100) | DEFAULT '' | /profile/avatar/2025/12/31/8c1a1e367a0b414b872b0f0fc16fced3.jpg |
| password | 密码 | VARCHAR(100) | DEFAULT '' | $2a$10$l.LqdK0ZhFhL9AfTSC2Cyubhpme26.4vsUPHjrdTjdOR/zh85BRxa |
| status | 账号状态 | CHAR(1) | DEFAULT '0' | 0 |
| del_flag | 删除标志 | CHAR(1) | DEFAULT '0' | 0 |
| pwd_update_date | 密码最后更新时间 | DATETIME |  | 2025-12-30 16:57:04 |
| create_by | 创建者 | VARCHAR(64) | DEFAULT '' |  |
| create_time | 创建时间 | DATETIME |  | 2025-12-30 16:57:04 |
| update_by | 更新者 | VARCHAR(64) | DEFAULT '' | admin |
| update_time | 更新时间 | DATETIME |  | 2025-12-31 15:34:15 |
| remark | 备注 | VARCHAR(500) | DEFAULT NULL |  |

## 5 体系架构

### 5.1 开发平台

•	JetBrains IntelliJ IDEA
•	Github
•	Alibaba Cloud

### 5.2 环境选择

1、系统环境
•	Java EE 8
•	Servlet 3.0
•	Apache Maven 3

2、主框架
•	Spring Boot 2.2
•	Spring Framework 5.2
•	Spring Security 5.2

3、持久层
•	Apache MyBatis 3.5
•	Hibernate Validation 6.0
•	Alibaba Druid 1.2

4、视图层
•	Vue 2.6
•	Axios 0.21
•	Element 2.15

### 5.3 系统架构设计

![se](image\se.png)