-- ========= 清空数据 =========
DELETE FROM biz_pack_rejection;
DELETE FROM biz_pack_verify;
DELETE FROM biz_pack_flow;
DELETE FROM biz_pack;
DELETE FROM biz_addr;
DELETE FROM biz_station_admin_map;
DELETE FROM biz_station;

/* ========= sys_user（管理员） =========
INSERT INTO sys_user (user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, pwd_update_date, create_by, create_time, update_by, update_time, remark) VALUES
(3, 101, 'admin03', '张三', '00', 'admin03@163.com', '13800000003', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '上海转运中心管理员'),
(4, 102, 'admin04', '李四', '00', 'admin04@163.com', '13800000004', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '北京转运中心管理员'),
(5, 103, 'admin05', '王五', '00', 'admin05@163.com', '13800000005', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '广州转运中心管理员'),
(6, 104, 'admin06', '赵六', '00', 'admin06@163.com', '13800000006', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '武汉转运中心管理员'),
(7, 106, 'wzc', '王支撑', '00', 'zhichengw@163.com', '13800000007', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '上师大奉贤驿站管理员'),
(8, 107, 'sam', 'Sam Bridges', '00', 'sam@163.com', '13800000008', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '上师大徐汇驿站管理员');

/* ========= sys_user（业务用户） =========
INSERT INTO sys_user (user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, pwd_update_date, create_by, create_time, update_by, update_time, remark) VALUES
(101, null, 'user01', '小宇', '00', 'user01@163.com', '13800000101', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '业务用户01'),
(102, null, 'user02', '小杰', '00', 'user02@163.com', '13800000102', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '业务用户02'),
(103, null, 'user03', '小佳', '00', 'user03@163.com', '13800000103', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '业务用户03'),
(104, null, 'user04', '小涛', '00', 'user04@163.com', '13800000104', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), sysdate(), 'admin', sysdate(), '', sysdate(), '业务用户04');

/* ========= sys_dept（部门表） =========
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES
   (108, 0,0,'华理奉贤驿站', 0,NULL,'021-111111111',NULL,0, 0, 'admin', NOW(), 'admin', NOW()),
   (109, 0,0,'应技大奉贤驿站', 0,NULL,'021-22222222',NULL,0,0,  'admin', NOW(), 'admin', NOW()),
   (110, 0,0,'复旦大学驿站', 0,NULL,'021-33333333',NULL,0,0,  'admin', NOW(), 'admin', NOW()),
   (111, 0,0,'上海交大驿站', 0,NULL,'021-44444444',NULL,0,0,  'admin', NOW(), 'admin', NOW()),
   (112, 0,0,'清华大学驿站', 0,NULL,'021-55555555',NULL,0,0,  'admin', NOW(), 'admin', NOW()),
   (113, 0,0,'北京大学驿站', 0,NULL,'021-666666666',NULL,0,0, 'admin', NOW(), 'admin', NOW()),
   (114, 0,0,'中山大学驿站', 0,NULL,'021-77777777',NULL,0,0,  'admin', NOW(), 'admin', NOW()),
   (115, 0,0,'武汉大学驿站', 0,NULL,'021-88888888',NULL,0,0,  'admin', NOW(), 'admin', NOW()),
   (116, 0,0,'重庆大学驿站', 0,NULL,'021-99999999',NULL,0,0,  'admin', NOW(), 'admin', NOW());
*/

SET FOREIGN_KEY_CHECKS = 0;

/* ========= biz_station（驿站） ========= */
INSERT INTO biz_station
(station_id,dept_id, station_name, station_prov, station_city, station_dist, station_addr, contact_user_id, status, tel, created_at, updated_at)
VALUES
    (1,106, '上师大奉贤驿站', '上海市', '上海市', '奉贤区', '海思路500号', 7, '正常', '021-12345678', NOW(), NOW()),
    (2,107, '上师大徐汇驿站', '上海市', '上海市', '徐汇区', '桂林路100号', 8, '正常', '021-87654321', NOW(), NOW()),
    (3,108, '华理奉贤驿站', '上海市', '上海市', '奉贤区', '海伦路99号', 3, '正常', '021-11111111', NOW(), NOW()),
    (4,109, '应技大奉贤驿站', '上海市', '上海市', '奉贤区', '海泉路100号', 3, '正常', '021-22222222', NOW(), NOW()),
    (5,110, '复旦大学驿站', '上海市', '上海市', '杨浦区', '邯郸路220号', 3, '正常', '021-33333333', NOW(), NOW()),
    (6,111, '上海交大驿站', '上海市', '上海市', '闵行区', '东川路800号', 3, '正常', '021-44444444', NOW(), NOW()),
    (7,112, '清华大学驿站', '北京市', '北京市', '海淀区', '清华园1号', 4, '正常', '010-55555555', NOW(), NOW()),
    (8,113, '北京大学驿站', '北京市', '北京市', '海淀区', '颐和园路5号', 4, '正常', '010-66666666', NOW(), NOW()),
    (9,114, '中山大学驿站', '广东省', '广州市', '海珠区', '新港西路135号', 5, '正常', '020-77777777', NOW(), NOW()),
    (10,115, '武汉大学驿站', '湖北省', '武汉市', '武昌区', '珞珈山路16号', 6, '正常', '027-88888888', NOW(), NOW()),
    (11,116, '重庆大学驿站', '重庆市', '重庆市', '沙坪坝区', '沙坪坝正街174号', NULL, '正常', '023-99999999', NOW(), NOW());


/* ========= biz_addr（用户地址簿） ========= */
INSERT INTO biz_addr
(addr_id,user_id, addr_name, addr_tel, addr_prov, addr_city, addr_dist, addr_detail, is_default, created_at, updated_at)
VALUES
    (1,101, '小宇家', '13800000101', '上海市', '上海市', '奉贤区', '海思路500号', 0, NOW(), NOW()),
    (2,101, '小宇学校', '13800000101', '上海市', '上海市', '徐汇区', '桂林路100号', 1, NOW(), NOW()),
    (3,101, '小宇父母', '13900000101', '上海市', '上海市', '浦东新区', '世纪大道88号', 1, NOW(), NOW()),
    (4,3  , '张三家', '13800000003', '上海市', '上海市', '静安区', '南京西路1266号', 0, NOW(), NOW()),
    (5,102, '小杰家', '13800000102', '北京市', '北京市', '朝阳区', '建国门外大街1号', 0, NOW(), NOW()),
    (6,102, '小杰学校', '13800000102', '北京市', '北京市', '海淀区', '中关村南大街27号', 1, NOW(), NOW()),
    (7,102, '小杰父母', '13900000102', '北京市', '北京市', '西城区', '西单北大街', 1, NOW(), NOW()),
    (8,103, '小佳家', '13800000103', '广东省', '广州市', '天河区', '珠江新城花城大道', 0, NOW(), NOW()),
    (9,103, '小佳学校', '13800000103', '广东省', '广州市', '越秀区', '北京路步行街', 1, NOW(), NOW()),
    (10,103, '小佳父母', '13900000103', '广东省', '广州市', '荔湾区', '上下九步行街', 1, NOW(), NOW());


/* ========= biz_pack（包裹） ========= */
INSERT INTO biz_pack
(pack_id,sender_id, sender_prov, sender_city, sender_dist, sender_addr_detail, receiver_id, receiver_name, receiver_tel, receiver_prov, receiver_city, receiver_dist, receiver_addr_detail, weight, volume, note, is_dangerous, is_fragile, is_urgent, status, pickup_code, created_at, updated_at)
VALUES
    (1,101, '上海市', '上海市', '徐汇区', '桂林路100号',102, '小杰', '13800000102', '北京市', '北京市', '海淀区', '中关村南大街27号', 1.5, 0.02, '摔一罚十', 0, 1, 0, '待揽收', NULL, NOW(), NOW()),
    (2,102, '北京市', '北京市', '海淀区', '中关村南大街27号', 101, '小宇', '13800000101', '上海市', '上海市', '徐汇区', '桂林路100号', 2.3, 0.03, '加急件', 0, 0, 1, '寄出待核验', NULL, NOW(), NOW()),
    (3,103, '广东省', '广州市', '天河区', '珠江新城花城大道', 3, '张三', '13800000003', '上海市', '上海市', '静安区', '南京西路1266号',0.8, 0.01, '普通包裹', 0, 0, 0, '到站待核验', NULL, NOW(), NOW()),
    (4,101, '上海市', '上海市', '徐汇区', '桂林路100号', NULL, '小宇父母', '13900000101', '上海市', '上海市', '浦东新区', '世纪大道88号',9, 0.10, '衣物', 0, 0, 0, '入库已核验', NULL, NOW(), NOW()),
    (5,3  , '上海市', '上海市', '静安区', '南京西路1266号', 101, '小宇', '13800000101', '上海市', '上海市', '徐汇区', '桂林路100号', 3.2, 0.05, '大件物品', 0, 0, 0, '待上架', 2887, NOW(), NOW()),
    (6,102, '北京市', '北京市', '朝阳区', '建国门外大街1号', 103, '小佳', '13800000103', '广东省', '广州市', '越秀区', '北京路步行街', 1.1, 0.015, '文件资料', 0, 1, 0, '入库已核验', NULL, NOW(), NOW()),
    (7,3  , '上海市', '上海市', '静安区', '南京西路1266号',  101, '小宇', '13800000101', '上海市', '上海市', '徐汇区', '桂林路100号', 2.0, 0.025, 'C4炸药', 1, 0, 0, '到站待核验', NULL, NOW(), NOW());


/* ========= biz_pack_flow（包裹流转记录） ========= */
INSERT INTO biz_pack_flow
(flow_id,pack_id, station_id, admin_user_id, action, action_note, created_at)
VALUES
    (1,1, 2, 8, '寄出核验', '徐汇驿站寄出核验完成', NOW()),
    (2,1, 7, 7, '到站', '包裹到达奉贤驿站', NOW()),
    (3,2, 3, 4, '入库核验', '海淀驿站入库完成', NOW()),
    (4,2, 3, 4, '上架', '海淀A区1号货架', NOW()),
    (5,3, 1, 8, '入库核验', '徐汇驿站入库完成', NOW()),
    (6,4, 5, 3, '到站', '包裹到达浦东驿站', NOW()),
    (7,5, 6, 4, '入库核验', '朝阳驿站入库完成', NOW()),
    (8,6, 7, 5, '入库核验', '越秀驿站入库完成', NOW()),
    (9,7, 8, 4, '上架', '静安驿站已上架', NOW()),
    (10,8, 9, 7, '取件', '收件人已取件', NOW());

/* ========= biz_pack_verify（包裹审核记录） ========= */
INSERT INTO biz_pack_verify
(verify_id,pack_id, station_id, admin_user_id, verify_type, verify_status, verify_note, verified_at, created_at)
VALUES
    (1,111, 1, 3, '寄出核验', '通过', '寄件信息正确', NOW(), NOW()),
    (2,112, 3, 3, '入站核验', '通过', '包裹完好', NOW(), NOW()),
    (3,113, 1, 3, '入站核验', '通过', '普通包裹无异常', NOW(), NOW()),
    (4,114, 5, 7, '入站核验', '通过', '大件物品已确认', NOW(), NOW()),
    (5,115, 6, 7, '入站核验', '通过', '文件资料无损坏', NOW(), NOW());

/* ========= biz_pack_rejection（拒收/退件记录） ========= */
INSERT INTO biz_pack_rejection
(reject_id,pack_id, reject_by, reject_reason, created_at)
VALUES
    (1,229, 3, '收件人地址异常，无法派送', NOW()),
    (2,221, 3, '收件人拒收，申请退件', NOW()),
    (3,223, 7, '收件人临时外出，申请退件', NOW()),
    (4,225, 7, '收件地址错误，无法送达', NOW());

/* ========= biz_station_admin_map（驿站管理员映射） ========= */
INSERT INTO biz_station_admin_map
(station_id, admin_user_id, role_note,assigned_at)
VALUES
    (1, 7, '站长', NOW()),
    (2, 8, '站长', NOW()),
    (3, 3, '站长', NOW()),
    (4, 3, '站长', NOW()),
    (5, 3, '站长', NOW()),
    (6, 4, '站长', NOW()),
    (7, 4, '站长', NOW()),
    (8, 5, '站长', NOW()),
    (9, 6, '站长', NOW());

SET FOREIGN_KEY_CHECKS = 1;