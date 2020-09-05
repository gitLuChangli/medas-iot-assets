drop database if exists medas_iot_assets_inventory;

create database medas_iot_assets_inventory charset=utf8;
use medas_iot_assets_inventory;

-- ----------------------------
-- Table structure for ums_company
-- ----------------------------
DROP TABLE IF EXISTS `ums_company`;
CREATE TABLE `ums_company` (
	`id` bigint(20) not null,
    `fee_code` varchar(32) default null comment '费用代码',
    `name` varchar(200) default null comment '部门名称',
    `area` varchar(200) default null comment '厂区',
    `note` varchar(200) default null comment '备注',
    `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
    primary key (`id`),
    unique key (`fee_code`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='部门表';

insert into `ums_company` (`id`, `fee_code`, `name`, `status`, `create_time`) values (1, 'C', 'C次集團',  1, '2020-08-19 08:24:20');
insert into `ums_company` (`id`, `fee_code`, `name`, `status`, `create_time`) values (2, 'EBA0001', '華南檢測中心', 1, '2020-08-19 08:24:21');
insert into `ums_company` (`id`, `fee_code`, `name`, `status`, `create_time`) values (3, 'EBA0002', '智能量測-物聯網', 1, '2020-09-18 08:31:10');

-- ----------------------------
-- Table structure for ums_company_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_company_relation`;
CREATE TABLE `ums_company_relation` (
	`id` bigint(20) not null,
    `ancestor` bigint(20) not null comment '上级部门编号',
    `descendant` bigint(20) not null comment '部门编号',
    `depth` int not null comment '部门之间层级深度',
    primary key (`id`),
    unique key (`ancestor`, `descendant`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='部门关系表';

insert into `ums_company_relation` (`id`, `ancestor`, `descendant`, `depth`) values (1, 1, 1, 0);
insert into `ums_company_relation` (`id`, `ancestor`, `descendant`, `depth`) values (2, 1, 2, 1);
insert into `ums_company_relation` (`id`, `ancestor`, `descendant`, `depth`) values (3, 2, 2, 0);
insert into `ums_company_relation` (`id`, `ancestor`, `descendant`, `depth`) values (4, 1, 3, 2);
insert into `ums_company_relation` (`id`, `ancestor`, `descendant`, `depth`) values (5, 2, 3, 1);
insert into `ums_company_relation` (`id`, `ancestor`, `descendant`, `depth`) values (6, 3, 3, 0);

-- --------------------------------------
-- Table structure for usm_admin
-- --------------------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
	`id` bigint(20) not null,
    `username` varchar(64) default null comment '工号',
    `password` varchar(64) default null comment '密码',
    `nickname` varchar(64) default null comment '昵称',
    `icon` varchar(200) default null comment '图像',
    `email` varchar(200) default null comment '邮箱',
    `ext` varchar(32) default null comment '分机',
    `phone` varchar(32) default null comment '手机号',
    `openid` varchar(32) default null comment '微信公众号openid',
    `company_id` bigint(20) default null,
    `note` varchar(200) default null comment '备注',
    `create_time` datetime default null comment '创建时间',
    `login_time` datetime default null comment '最后登录时间',
    `status` int(1) default '1' comment '账户启用状态：0->禁用，1->启用',
    primary key (`id`),
    unique key (`username`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='用户表';

insert into ums_admin values (1, 'W0515366', '$2a$10$HZkGqaLPIUyupdUjI/NOPeCYjCm.nc6DVWFuNiPVlrQ5gUwSJcpKG', '盧昌利', null, 'hzlh-cmc-rd1system@mail.foxconn.com', '560+73766', '13249466549', null, 3, null, '2020-06-04 16:56:24', null, 1);

-- --------------------------------------
-- Table structure for usm_admin_login_log
-- --------------------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log` (
	`id` bigint(20) not null auto_increment,
    `admin_id` bigint(20) default null,
    `create_time` datetime default null,
    `ip` varchar(64) default null,
    `user_agent` varchar(100) default null comment '登录类型',
    primary key (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台用户登录日志表';

-- --------------------------------------
-- Table structure for usm_role
-- --------------------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint(20)not null auto_increment,
  `name` varchar(100) default null comment '名称',
  `description` varchar(500) default null comment '描述',
  `admin_count` int(11) default null comment '后台用户数量',
  `create_time` datetime default null comment '创建时间',
  `status` int(1) default '1' comment '启用状态：0->禁用；1->启用',
  `sort` int(11) default '0',
  PRIMARY KEY (`id`),
  unique key (`name`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台用户角色表';

INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (1,'超級管理員','role_super',0,'2020-07-09 11:09:55',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (2,'系統管理員-部門','role_admin_company',0,'2020-08-06 09:17:40',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (3,'通用用戶-盤點','role_user_inv',0,'2020-08-06 09:18:26',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (4,'系统管理员-角色','role_admin_role',0,'2020-08-06 09:37:30',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (5,'部門管理員-盤點','role_admin2_inv',0,'2020-08-06 09:47:21',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (6,'系統管理員-菜單','role_admin_menu',0,'2020-08-06 09:48:38',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (7,'系統管理員-資源','role_admin_resource',0,'2020-08-06 09:48:53',1,0);
INSERT INTO `ums_role` (`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) VALUES (8,'系統管理員-用戶','role_admin_user',0,'2020-08-06 09:49:14',1,0);

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台用户和角色关系表';

insert into `ums_admin_role_relation` (`id`, `admin_id`, `role_id`) values (1, 1, 1);

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`),
  unique key (`parent_id`, `title`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台菜单表';

INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (1,0,'2020-07-09 15:56:22','系統管理',0,0,'/ums','el-icon-s-tools',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (2,1,'2020-07-10 17:44:35','角色列表',1,1,'/ums/role','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (3,1,'2020-07-11 10:50:56','菜單列表',1,2,'/ums/menu','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (4,1,'2020-07-11 10:51:10','資源列表',1,3,'/ums/resource','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (5,1,'2020-07-11 10:51:23','部門列表',1,4,'/ums/company','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (6,1,'2020-07-11 10:51:40','用戶列表',1,5,'/ums/admin','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (7,1,'2020-07-11 10:52:24','分配菜單',1,6,'分配菜單','',0);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (8,1,'2020-07-11 10:52:39','分配資源',1,7,'分配資源','',0);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (9,1,'2020-07-11 10:53:13','資源分類',1,8,'資源分類','',0);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (10,0,'2020-07-11 10:58:52','固定資產',0,1,'/assets','el-icon-coin',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (11,10,'2020-07-11 10:59:26','資產列表',1,2,'/assets/list','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (12,10,'2020-07-11 10:59:42','盤點工單',1,3,'/assets/work','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (13,10,'2020-08-06 09:43:47','導入資產',1,1,'/assets/import','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (14,10,'2020-08-06 09:44:21','我的工單',1,4,'/assets/mine','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (15,0,'2020-08-06 09:53:53','個人中心',0,2,'/mine','el-icon-s-help',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (16,15,'2020-08-06 09:54:17','操作日誌',1,2,'/mine/log','',1);
INSERT INTO `ums_menu` (`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) VALUES (17,15,'2020-08-06 09:55:26','個人信息',1,1,'/mine/index','',1);

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台角色菜单关系表';

insert into `ums_role_menu_relation` values (1, 1, 1);
insert into `ums_role_menu_relation` values (2, 1, 2);
insert into `ums_role_menu_relation` values (3, 1, 3);
insert into `ums_role_menu_relation` values (4, 1, 4);
insert into `ums_role_menu_relation` values (5, 1, 5);
insert into `ums_role_menu_relation` values (6, 1, 6);
insert into `ums_role_menu_relation` values (7, 1, 7);
insert into `ums_role_menu_relation` values (8, 1, 8);
insert into `ums_role_menu_relation` values (9, 1, 9);
insert into `ums_role_menu_relation` values (10, 1, 10);
insert into `ums_role_menu_relation` values (11, 1, 11);
insert into `ums_role_menu_relation` values (12, 1, 12);
insert into `ums_role_menu_relation` values (13, 1, 13);
insert into `ums_role_menu_relation` values (14, 1, 14);
insert into `ums_role_menu_relation` values (15, 1, 15);
insert into `ums_role_menu_relation` values (16, 1, 16);
insert into `ums_role_menu_relation` values (17, 1, 17);

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  unique key (`name`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='资源分类表';

INSERT INTO `ums_resource_category` (`id`,`create_time`,`name`,`sort`) VALUES (1,'2020-07-11 09:21:57','權限模塊',0);
INSERT INTO `ums_resource_category` (`id`,`create_time`,`name`,`sort`) VALUES (2,'2020-08-06 09:13:51','通用',1);

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',  
  PRIMARY KEY (`id`),
  unique key (`name`, `category_id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台资源表';

INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (1,'2020-07-11 10:04:17','res_admin_user','/api/admin/user/**','包含後台用戶的增刪改查',1);
INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (2,'2020-08-06 09:06:57','res_admin_role','/api/admin/role/**','系統角色信息的增刪改查',1);
INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (3,'2020-08-06 09:07:57','res_admin_res','/api/admin/resource/**','系統資源的增刪改查',1);
INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (4,'2020-08-06 09:08:51','res_admin_company','/api/admin/company/**','系統部門的增刪改查',1);
INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (5,'2020-08-06 09:09:51','res_admin_menu','/api/admin/menu/**','後台菜單增刪改查',1);
INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (6,'2020-08-06 09:14:29','res_admin2','/api/admin2/**','使用部門管理員操作',2);
INSERT INTO `ums_resource` (`id`,`create_time`,`name`,`url`,`description`,`category_id`) VALUES (7,'2020-08-06 09:15:25','res_user','/api/user/**','所有用戶登錄後都可以使用的資源',2);

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台角色资源关系表';

insert into `ums_role_resource_relation` values (1, 1, 1);
insert into `ums_role_resource_relation` values (2, 1, 2);
insert into `ums_role_resource_relation` values (3, 1, 3);
insert into `ums_role_resource_relation` values (4, 1, 4);
insert into `ums_role_resource_relation` values (5, 1, 5);
insert into `ums_role_resource_relation` values (6, 1, 6);
insert into `ums_role_resource_relation` values (7, 1, 7);

-- ----------------------------
-- Table structure for epc_prefix
-- ----------------------------
DROP TABLE IF EXISTS `epc_prefix`;
CREATE TABLE `epc_prefix`(
	`id` bigint(20) not null,
    `k` varchar(100) not null,
    `v` varchar(100) not null,
    primary key (`id`),
    unique key (`k`),
    unique key (`v`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='电子标签前缀表';

-- ----------------------------
-- Table structure for epc_prefix_company
-- ----------------------------
DROP TABLE IF EXISTS `epc_prefix_company`;
CREATE TABLE `epc_prefix_company` (
	`id` bigint(20) not null,
    `prefix_id` bigint(20) not null,
    `company_id` bigint(20) not null,
    primary key (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='电子标签与部门之间的关系表';
