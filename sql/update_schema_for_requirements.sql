-- 1. 完善驿站表 - 添加驿站名称字段（如果已存在会报错，可忽略）
ALTER TABLE biz_station ADD COLUMN station_name VARCHAR(200) NOT NULL DEFAULT '' COMMENT '驿站名称' AFTER station_id;

-- 1.1 创建驿站申请表
CREATE TABLE IF NOT EXISTS biz_station_apply (
  apply_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  station_name VARCHAR(200) NOT NULL COMMENT '驿站名称',
  station_prov VARCHAR(64) NOT NULL COMMENT '省',
  station_city VARCHAR(64) NOT NULL COMMENT '市',
  station_dist VARCHAR(64) NOT NULL COMMENT '区县',
  station_addr VARCHAR(255) NOT NULL COMMENT '详细地址',
  contact_user_id BIGINT NOT NULL COMMENT '申请人用户ID',
  tel VARCHAR(20) NOT NULL COMMENT '联系电话',
  apply_status ENUM('待审核', '审核通过', '审核不通过') NOT NULL DEFAULT '待审核' COMMENT '申请状态',
  audit_by BIGINT COMMENT '审核人',
  audit_time DATETIME COMMENT '审核时间',
  audit_remark VARCHAR(500) COMMENT '审核备注',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (apply_id),
  KEY idx_apply_user (contact_user_id),
  KEY idx_apply_status (apply_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='驿站申请表';

-- 2. 添加字典数据 - 驿站状态
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, create_time, remark) 
VALUES ('驿站状态', 'biz_station_status', '0', 'admin', NOW(), '驿站审核状态列表')
ON DUPLICATE KEY UPDATE dict_name = dict_name;

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, create_time, remark)
VALUES 
(1, '待审核', '待审核', 'biz_station_status', '0', 'admin', NOW(), '驿站注册后待超级管理员审核'),
(2, '正常', '正常', 'biz_station_status', '0', 'admin', NOW(), '驿站审核通过正常运营'),
(3, '停用', '停用', 'biz_station_status', '0', 'admin', NOW(), '驿站被封禁或停用')
ON DUPLICATE KEY UPDATE dict_label = dict_label;

-- 3. 添加字典数据 - 包裹状态
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, create_time, remark) 
VALUES ('包裹状态', 'biz_pack_status', '0', 'admin', NOW(), '包裹流转状态')
ON DUPLICATE KEY UPDATE dict_name = dict_name;

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, create_time, remark)
VALUES 
(1, '待揽收', '待揽收', 'biz_pack_status', '0', 'admin', NOW(), '包裹等待寄出'),
(2, '寄出待核验', '寄出待核验', 'biz_pack_status', '0', 'admin', NOW(), '包裹寄出待驿站核验'),
(3, '寄出已核验', '寄出已核验', 'biz_pack_status', '0', 'admin', NOW(), '包裹寄出核验通过'),
(4, '运输中', '运输中', 'biz_pack_status', '0', 'admin', NOW(), '包裹正在运输'),
(5, '到站待核验', '到站待核验', 'biz_pack_status', '0', 'admin', NOW(), '包裹到达目的驿站待核验'),
(6, '入库已核验', '入库已核验', 'biz_pack_status', '0', 'admin', NOW(), '包裹入库核验通过'),
(7, '待上架', '待上架', 'biz_pack_status', '0', 'admin', NOW(), '包裹等待上架'),
(8, '待取件', '待取件', 'biz_pack_status', '0', 'admin', NOW(), '包裹已上架等待取件'),
(9, '已取件', '已取件', 'biz_pack_status', '0', 'admin', NOW(), '包裹已被收件人取走'),
(10, '拒收', '拒收', 'biz_pack_status', '0', 'admin', NOW(), '收件人拒收包裹'),
(11, '退件', '退件', 'biz_pack_status', '0', 'admin', NOW(), '包裹正在退回'),
(12, '异常', '异常', 'biz_pack_status', '0', 'admin', NOW(), '包裹出现异常')
ON DUPLICATE KEY UPDATE dict_label = dict_label;

-- 4. 添加字典数据 - 是否标记
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, create_time, remark) 
VALUES ('是否', 'biz_yes_no', '0', 'admin', NOW(), '是否标记')
ON DUPLICATE KEY UPDATE dict_name = dict_name;

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, status, create_by, create_time)
VALUES 
(1, '否', '0', 'biz_yes_no', '', 'default', '0', 'admin', NOW()),
(2, '是', '1', 'biz_yes_no', '', 'primary', '0', 'admin', NOW())
ON DUPLICATE KEY UPDATE dict_label = dict_label;

-- 5. 添加字典数据 - 用户状态
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, create_time, remark) 
VALUES ('用户状态', 'biz_client_status', '0', 'admin', NOW(), '业务用户状态')
ON DUPLICATE KEY UPDATE dict_name = dict_name;

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, create_time)
VALUES 
(1, '正常', '正常', 'biz_client_status', '0', 'admin', NOW()),
(2, '冻结', '冻结', 'biz_client_status', '0', 'admin', NOW()),
(3, '注销', '注销', 'biz_client_status', '0', 'admin', NOW())
ON DUPLICATE KEY UPDATE dict_label = dict_label;

-- 6. 添加字典数据 - 审核状态
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, create_time, remark) 
VALUES ('审核状态', 'biz_verify_status', '0', 'admin', NOW(), '审核状态')
ON DUPLICATE KEY UPDATE dict_name = dict_name;

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, status, create_by, create_time)
VALUES 
(1, '待审核', '待审核', 'biz_verify_status', '', 'info', '0', 'admin', NOW()),
(2, '通过', '通过', 'biz_verify_status', '', 'success', '0', 'admin', NOW()),
(3, '不通过', '不通过', 'biz_verify_status', '', 'danger', '0', 'admin', NOW())
ON DUPLICATE KEY UPDATE dict_label = dict_label;

-- 7. 添加菜单 - 包裹核验页面（驿站管理员使用）
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2033, '包裹核验', 2032, 2, 'verify', 'business/pack/verify', 'C', '0', '0', 'business:pack:verify', 'check', 'admin', NOW(), '包裹核验页面 - 发货核验、收货核验、上架管理')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 8. 添加菜单 - 我的包裹（用户端使用）
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2034, '我的包裹', 2030, 1, 'myPacks', 'business/pack/myPacks', 'C', '0', '0', '', 'shopping', 'admin', NOW(), '用户查看自己的包裹流转信息')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 9. 添加包裹核验相关的按钮权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2035, '发货核验', 2033, 1, '', '', 'F', '0', '0', 'business:pack:verify', '#', 'admin', NOW(), ''),
(2036, '收货核验', 2033, 2, '', '', 'F', '0', '0', 'business:pack:verify', '#', 'admin', NOW(), ''),
(2037, '生成取件码', 2033, 3, '', '', 'F', '0', '0', 'business:pack:shelve', '#', 'admin', NOW(), ''),
(2038, '取件', 2034, 1, '', '', 'F', '0', '0', 'business:pack:pickup', '#', 'admin', NOW(), '')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 10. 添加驿站审核权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2039, '驿站审核', 2028, 1, '', '', 'F', '0', '0', 'business:station:audit', '#', 'admin', NOW(), '超级管理员审核驿站'),
(2040, '查看库存', 2028, 2, '', '', 'F', '0', '0', 'business:station:inventory', '#', 'admin', NOW(), '查看驿站库存包裹')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 11. 添加用户管理权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2041, '冻结用户', 2027, 1, '', '', 'F', '0', '0', 'business:client:freeze', '#', 'admin', NOW(), '超级管理员冻结/解冻用户')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 12. 添加驿站注册申请菜单（任何登录用户可见）
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2042, '驿站注册', 0, 4, 'stationRegister', 'business/station/register', 'C', '0', '0', '', 'edit', 'admin', NOW(), '驿站注册申请页面')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 13. 添加驿站申请管理菜单（超级管理员审核）
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2043, '驿站申请管理', 2007, 2, 'stationApply', 'business/stationApply/index', 'C', '0', '0', 'business:stationApply:list', 'form', 'admin', NOW(), '驿站申请审核管理')
ON DUPLICATE KEY UPDATE menu_name = menu_name;

-- 14. 添加驿站申请审核权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, create_by, create_time, remark)
VALUES 
(2044, '申请审核', 2043, 1, '', '', 'F', '0', '0', 'business:stationApply:audit', '#', 'admin', NOW(), '审核驿站申请')
ON DUPLICATE KEY UPDATE menu_name = menu_name;
