-- ------------------------------------------------------
-- 创建并使用数据库-商品管理模块数据库
-- ------------------------------------------------------
set charset utf8;
create database if not exists totoro_product character set UTF8;
use totoro_product;

-- 
-- 创建商品分类表
--

CREATE TABLE IF NOT EXISTS `ps_product_category` (
	id                   bigint not null auto_increment,	
	parent_id            bigint comment '上级分类的编号：0表示一级分类',
	name                 varchar(64) comment '名称',
	level                int(1) comment '分类级别：0->1级；1->2级',
	product_count        int comment '商品数量',
	product_unit         varchar(64) comment '商品单位',
	nav_status           int(1) comment '是否显示在导航栏：0->不显示；1->显示',
	show_status          int(1) comment '显示状态：0->不显示；1->显示',
	sort                 int comment '排序',
	icon                 varchar(255) comment '图标',
	keywords             varchar(255) comment '关键字',
	description          text comment '描述',
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- 创建商品品牌表
--
CREATE TABLE IF NOT EXISTS `ps_product_brand` (
   id                   bigint not null auto_increment,
   name                 varchar(64) comment '名称',
   first_letter         varchar(8) comment '首字母',
   sort                 int comment '排序',
   factory_status       int(1) comment '是否为品牌制造商：0->不是；1->是',
   show_status          int(1) comment '是否显示',
   product_count        int comment '产品数量',
   product_comment_count int comment '产品评论数量',
   logo                 varchar(255) comment '品牌logo',
   big_pic              varchar(255) comment '专区大图',
   brand_story          text comment '品牌故事',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
