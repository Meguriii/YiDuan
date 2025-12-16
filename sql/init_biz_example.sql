SET FOREIGN_KEY_CHECKS = 0;

/* ========= 清空数据 ========= */
DELETE FROM biz_pack_rejection;
DELETE FROM biz_pack_verify;
DELETE FROM biz_pack_flow;
DELETE FROM biz_pack;
DELETE FROM biz_addr;
DELETE FROM biz_station_admin_map;
DELETE FROM biz_station;
DELETE FROM biz_client;

SET FOREIGN_KEY_CHECKS = 1;

/* ========= biz_client ========= */
INSERT INTO biz_client (client_name, client_tel, client_status) VALUES
                                                                    ('张三', '13800000001', '正常'),
                                                                    ('李四', '13800000002', '正常'),
                                                                    ('王五', '13800000003', '正常'),
                                                                    ('赵六', '13800000004', '正常');

/* ========= biz_station（上师大驿站） ========= */
INSERT INTO biz_station
(dept_id, station_prov, station_city, station_dist, station_addr, contact_user_id, status, tel)
VALUES
    (201, '上海市', '上海市', '奉贤区', '上师大奉贤校区学生服务中心一楼', 101, '正常', '021-57120001'),
    (202, '上海市', '上海市', '徐汇区', '上师大徐汇校区东部驿站楼', 102, '正常', '021-64320002');

/* ========= biz_station_admin_map ========= */
INSERT INTO biz_station_admin_map
(station_id, admin_user_id, role_note)
VALUES
    (1, 101, '站长'),
    (2, 102, '站长');

/* ========= biz_addr（上海 / 北京 / 广州） ========= */
INSERT INTO biz_addr
(client_id, addr_name, addr_tel, addr_prov, addr_city, addr_dist, addr_detail, is_default)
VALUES
    (1, '张三', '13800000001', '上海市', '上海市', '徐汇区', '桂林路100号', 1),
    (1, '张三', '13800000001', '上海市', '上海市', '奉贤区', '海思路500号', 0),
    (2, '李四', '13800000002', '北京市', '北京市', '海淀区', '中关村南大街27号', 1),
    (3, '王五', '13800000003', '广东省', '广州市', '天河区', '珠江新城花城大道', 1),
    (4, '赵六', '13800000004', '上海市', '上海市', '浦东新区', '世纪大道88号', 1);

/* ========= biz_pack ========= */
INSERT INTO biz_pack
(sender_id,
 sender_prov, sender_city, sender_dist, sender_addr_detail,
 receiver_id, receiver_name, receiver_tel,
 receiver_prov, receiver_city, receiver_dist, receiver_addr_detail,
 weight, volume, note,
 is_dangerous, is_fragile, is_urgent,
 status, pickup_code)
VALUES
    (1,
     '上海市','上海市','徐汇区','桂林路100号',
     2,'李四','13800000002',
     '北京市','北京市','海淀区','中关村南大街27号',
     1.850, 0.0105, '书籍资料',
     0,0,0,
     '寄出已核验', NULL),

    (2,
     '北京市','北京市','海淀区','中关村南大街27号',
     4,'赵六','13800000004',
     '上海市','上海市','奉贤区','上师大奉贤校区',
     3.200, 0.0320, '生活用品',
     0,1,0,
     '入库已核验', '1001'),

    (3,
     '广东省','广州市','天河区','珠江新城花城大道',
     1,'张三','13800000001',
     '上海市','上海市','徐汇区','上师大徐汇校区',
     0.950, 0.0042, '文件袋',
     0,0,1,
     '待取件', '2002');

/* ========= biz_pack_flow ========= */
INSERT INTO biz_pack_flow
(pack_id, station_id, admin_user_id, action, action_note)
VALUES
    (1, 2, 102, '寄出核验', '徐汇驿站寄出核验完成'),
    (1, 1, 101, '到站', '包裹到达奉贤驿站'),

    (2, 1, 101, '入库核验', '奉贤驿站入库完成'),
    (2, 1, 101, '上架', '奉贤A区2号货架'),

    (3, 2, 102, '入库核验', '徐汇驿站入库完成');

/* ========= biz_pack_verify ========= */
INSERT INTO biz_pack_verify
(pack_id, station_id, admin_user_id, verify_type, verify_status, verify_note, verified_at)
VALUES
    (1, 2, 102, '寄出核验', '通过', '寄件信息正确', NOW()),
    (2, 1, 101, '入站核验', '通过', '包裹完好', NOW()),
    (3, 2, 102, '入站核验', '通过', '加急件优先处理', NOW());

/* ========= biz_pack_rejection ========= */
INSERT INTO biz_pack_rejection
(pack_id, reject_by, reject_reason)
VALUES
    (2, 101, '收件人临时外出，申请退件');
