SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS biz_pack_rejection;
DROP TABLE IF EXISTS biz_pack_verify;
DROP TABLE IF EXISTS biz_pack_flow;
DROP TABLE IF EXISTS biz_pack;
DROP TABLE IF EXISTS biz_addr;
DROP TABLE IF EXISTS biz_station_admin_map;
DROP TABLE IF EXISTS biz_station;
DROP TABLE IF EXISTS biz_client;
SET FOREIGN_KEY_CHECKS = 1;


/* ===========================
   表：biz_client（业务用户表）
=========================== */
DROP TABLE IF EXISTS biz_client;
CREATE TABLE biz_client (
                            client_id         BIGINT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '业务用户ID',
                            client_name       VARCHAR(64)         NOT NULL                COMMENT '用户姓名',
                            client_tel        VARCHAR(20)         NOT NULL                COMMENT '手机号',
                            client_status     ENUM('正常','冻结','注销')
                                                                  NOT NULL DEFAULT '正常'  COMMENT '用户状态',
                            created_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            updated_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                            PRIMARY KEY(client_id),
                            UNIQUE KEY uk_client_tel(client_tel)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务用户表';


/* ===========================
   表：biz_station（驿站表）
=========================== */
DROP TABLE IF EXISTS biz_station;
CREATE TABLE biz_station (
                             station_id      BIGINT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '驿站ID',
                             station_name    VARCHAR(200)        NOT NULL DEFAULT ''     COMMENT '驿站名称',
                             dept_id         BIGINT(20)          NOT NULL                COMMENT '对应部门ID（一对一）',

                             station_prov    VARCHAR(64)         NOT NULL                COMMENT '省',
                             station_city    VARCHAR(64)         NOT NULL                COMMENT '市',
                             station_dist    VARCHAR(64)         NOT NULL                COMMENT '区县',
                             station_addr    VARCHAR(255)        NOT NULL                COMMENT '详细地址',
                             contact_user_id BIGINT              DEFAULT NULL            COMMENT '负责人ID',
                             status          ENUM('待审核','正常','停用')
                                                                 NOT NULL DEFAULT '待审核'COMMENT '驿站状态',
                             tel             VARCHAR(20)         DEFAULT NULL            COMMENT '联系电话',
                             created_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             updated_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                             PRIMARY KEY (station_id),
                             UNIQUE KEY uk_station_dept (dept_id),

                             CONSTRAINT fk_station_dept
                                 FOREIGN KEY (dept_id) REFERENCES sys_dept(dept_id)
                                     ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT fk_station_contact_user
                                 FOREIGN KEY (contact_user_id) REFERENCES sys_user(user_id)
                                     ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='驿站信息表';


/* ============================================
   表：biz_station_admin_map（驿站管理员映射表）
============================================ */
DROP TABLE IF EXISTS biz_station_admin_map;
CREATE TABLE biz_station_admin_map (
                                       station_id      BIGINT UNSIGNED     NOT NULL                COMMENT '驿站ID',
                                       admin_user_id   BIGINT              NOT NULL                COMMENT '管理员ID(sys_user)',
                                       role_note       VARCHAR(100)        DEFAULT NULL            COMMENT '角色说明，例如站长/核验员',
                                       assigned_at     DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '分配时间',

                                       PRIMARY KEY (station_id, admin_user_id),

                                       CONSTRAINT fk_pam_station
                                           FOREIGN KEY (station_id) REFERENCES biz_station(station_id)
                                               ON DELETE CASCADE ON UPDATE CASCADE,
                                       CONSTRAINT fk_pam_admin
                                           FOREIGN KEY (admin_user_id) REFERENCES sys_user(user_id)
                                               ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='驿站-管理员映射表';


/* ===========================
   表：biz_addr（用户地址表）
=========================== */
DROP TABLE IF EXISTS biz_addr;
CREATE TABLE biz_addr (
                          addr_id         BIGINT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '地址ID',
                          client_id       BIGINT UNSIGNED     NOT NULL                COMMENT '所属用户(biz_client)',
                          addr_name       VARCHAR(64)         NOT NULL                COMMENT '姓名',
                          addr_tel        VARCHAR(20)         NOT NULL                COMMENT '电话',
                          addr_prov       VARCHAR(64)         NOT NULL                COMMENT '省',
                          addr_city       VARCHAR(64)         NOT NULL                COMMENT '市',
                          addr_dist       VARCHAR(64)         NOT NULL                COMMENT '区县',
                          addr_detail     VARCHAR(255)        NOT NULL                COMMENT '详细地址',
                          is_default      TINYINT(1)          NOT NULL DEFAULT 0      COMMENT '是否默认地址',
                          created_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          updated_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                          PRIMARY KEY (addr_id),
                          INDEX idx_addr_client (client_id),

                          CONSTRAINT fk_addr_client
                              FOREIGN KEY (client_id) REFERENCES biz_client(client_id)
                                  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户地址簿';


/* ===========================
   表：biz_pack（包裹）
=========================== */
DROP TABLE IF EXISTS biz_pack;
CREATE TABLE biz_pack (
                          pack_id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '包裹ID',

                          sender_id           BIGINT UNSIGNED NOT NULL                COMMENT '寄件用户(biz_client)',
                          sender_prov         VARCHAR(64)     NOT NULL                COMMENT '寄件省',
                          sender_city         VARCHAR(64)     NOT NULL                COMMENT '寄件市',
                          sender_dist         VARCHAR(64)     NOT NULL                COMMENT '寄件区县',
                          sender_addr_detail  VARCHAR(255)    NOT NULL                COMMENT '寄件详细地址',

                          receiver_id         BIGINT UNSIGNED DEFAULT NULL            COMMENT '收件用户ID，可为空',
                          receiver_name       VARCHAR(64)     NOT NULL                COMMENT '收件人姓名',
                          receiver_tel        VARCHAR(20)     NOT NULL                COMMENT '收件人手机号',
                          receiver_prov       VARCHAR(64)     NOT NULL                COMMENT '收件省',
                          receiver_city       VARCHAR(64)     NOT NULL                COMMENT '收件市',
                          receiver_dist       VARCHAR(64)     NOT NULL                COMMENT '收件区县',
                          receiver_addr_detail VARCHAR(255)   NOT NULL                COMMENT '收件详细地址',

                          weight              DECIMAL(8,3)    DEFAULT NULL            COMMENT '重量',
                          volume              DECIMAL(10,4)   DEFAULT NULL            COMMENT '体积',
                          note                VARCHAR(500)    DEFAULT NULL            COMMENT '备注',
                          is_dangerous        TINYINT(1)      NOT NULL DEFAULT 0      COMMENT '是否危险品',
                          is_fragile          TINYINT(1)      NOT NULL DEFAULT 0      COMMENT '是否易碎',
                          is_urgent           TINYINT(1)      NOT NULL DEFAULT 0      COMMENT '是否加急',
                          status  ENUM('待揽收','寄出待核验','寄出已核验','运输中','到站待核验','入库已核验','待上架','待取件','已取件','拒收','退件','异常')
                                                              NOT NULL DEFAULT '待揽收'COMMENT '包裹状态',
                          pickup_code         VARCHAR(16)     DEFAULT NULL            COMMENT '取件码（入库后生成）',

                          created_at          DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          updated_at          DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                          PRIMARY KEY (pack_id),
                          UNIQUE KEY uk_pickup_code (pickup_code),
                          INDEX idx_pack_client (sender_id),
                          INDEX idx_pack_status (status),

                          CONSTRAINT fk_pack_client
                              FOREIGN KEY (sender_id) REFERENCES biz_client(client_id)
                                  ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='包裹表（地址文本化，不关联地址表）';


/* ==========================================
   表：biz_pack_flow（包裹流转/到站记录）
========================================== */
DROP TABLE IF EXISTS biz_pack_flow;
CREATE TABLE biz_pack_flow (
                               flow_id         BIGINT UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '流转记录ID',
                               pack_id         BIGINT UNSIGNED     NOT NULL                COMMENT '包裹ID',
                               station_id      BIGINT UNSIGNED     NOT NULL                COMMENT '驿站ID（在哪个站点发生）',
                               admin_user_id   BIGINT              DEFAULT NULL            COMMENT '操作管理员(sys_user)',
                               action          ENUM('寄出核验','入库核验','上架','出库','分拣','到站','取件','其他')
                                                                   NOT NULL                COMMENT '流转动作',
                               action_note     VARCHAR(500)        DEFAULT NULL            COMMENT '备注',
                               created_at      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',

                               PRIMARY KEY (flow_id),
                               INDEX idx_flow_pack (pack_id),
                               INDEX idx_flow_station (station_id),
                               INDEX idx_flow_admin(admin_user_id),

                               CONSTRAINT fk_flow_pack
                                   FOREIGN KEY (pack_id) REFERENCES biz_pack(pack_id)
                                       ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT fk_flow_station
                                   FOREIGN KEY (station_id) REFERENCES biz_station(station_id)
                                       ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT fk_flow_admin
                                   FOREIGN KEY (admin_user_id) REFERENCES sys_user(user_id)
                                       ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='包裹流转记录';


/* ===================================
   表：biz_pack_verify（包裹审核）
=================================== */
DROP TABLE IF EXISTS biz_pack_verify;
CREATE TABLE biz_pack_verify (
                                 verify_id      BIGINT UNSIGNED      NOT NULL AUTO_INCREMENT COMMENT '审核记录ID',
                                 pack_id        BIGINT UNSIGNED      NOT NULL                COMMENT '包裹ID',
                                 station_id     BIGINT UNSIGNED      NOT NULL                COMMENT '驿站ID',
                                 admin_user_id  BIGINT               DEFAULT NULL            COMMENT '审核管理员ID',
                                 verify_type    ENUM('寄出核验','入站核验') NOT NULL            COMMENT '审核类型',
                                 verify_status  ENUM('待审核','通过','不通过')
                                     NOT NULL DEFAULT '待审核'COMMENT '审核状态',
                                 verify_note    VARCHAR(500)         DEFAULT NULL            COMMENT '审核备注',
                                 verified_at    DATETIME             DEFAULT NULL            COMMENT '审核时间',
                                 created_at     DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

                                 PRIMARY KEY (verify_id),
                                 INDEX idx_verify_pack(pack_id),
                                 INDEX idx_verify_post(station_id),

                                 CONSTRAINT fk_verify_pack
                                     FOREIGN KEY (pack_id) REFERENCES biz_pack(pack_id)
                                         ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT fk_verify_station
                                     FOREIGN KEY (station_id) REFERENCES biz_station(station_id)
                                         ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT fk_verify_admin
                                     FOREIGN KEY (admin_user_id) REFERENCES sys_user(user_id)
                                         ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='包裹审核记录';


/* =======================================
   表：biz_pack_rejection（拒收/退件记录）
======================================= */
DROP TABLE IF EXISTS biz_pack_rejection;
CREATE TABLE biz_pack_rejection (
                                    reject_id      BIGINT UNSIGNED      NOT NULL AUTO_INCREMENT COMMENT '记录ID',
                                    pack_id        BIGINT UNSIGNED      NOT NULL                COMMENT '包裹ID',
                                    reject_by      BIGINT               DEFAULT NULL            COMMENT '操作人（sys_user）',
                                    reject_reason  VARCHAR(255)         NOT NULL                COMMENT '拒收原因',
                                    created_at     DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '记录时间',

                                    PRIMARY KEY (reject_id),
                                    INDEX idx_reject_pack(pack_id),

                                    CONSTRAINT fk_reject_pack
                                        FOREIGN KEY (pack_id) REFERENCES biz_pack(pack_id)
                                            ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT fk_reject_user
                                        FOREIGN KEY (reject_by) REFERENCES sys_user(user_id)
                                            ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='拒收/退件记录';