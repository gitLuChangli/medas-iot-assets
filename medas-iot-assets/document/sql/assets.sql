drop database if exists medas_iot_assets;

create database medas_iot_assets charset=utf8;
use medas_iot_assets;

-- --------------------------------------
-- Table structure for usm_admin
-- --------------------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
	`id` bigint(20) not null auto_increment,
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

insert into ums_admin values (1, 'W0515366', '$2a$10$HZkGqaLPIUyupdUjI/NOPeCYjCm.nc6DVWFuNiPVlrQ5gUwSJcpKG', '盧昌利', null, 'hzlh-cmc-rd1system@mail.foxconn.com', '560+73766', '13249466549', null, null, null, '2020-06-04 16:56:24', null, 1);

alter table ums_admin change companyid company_id bigint(20) default null;
alter table ums_admin add unique key (`username`);

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

alter table ums_role add unique key (`name`);

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  unique key (`name`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台用户权限表';

alter table ums_permission add unique key (`name`);

-- ----------------------------
-- Table structure for ums_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission_relation`;
CREATE TABLE `ums_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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

alter table ums_menu add unique key (`parent_id`, `title`);

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',  
  PRIMARY KEY (`id`),
  unique key (`name`, `category_id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台资源表';

alter table ums_resource add unique key (`name`, `category_id`);

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

alter table ums_resource_category add unique key (`name`);

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='后台角色资源关系表';

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

alter table ums_company add unique key (`fee_code`);

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

alter table ums_company drop index fee_code_2;

