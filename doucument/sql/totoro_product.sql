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
	id                   bigint NOT NULL auto_increment,
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
   id                   bigint NOT NULL auto_increment,
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

--
-- 商品表
--
CREATE TABLE IF NOT EXISTS `ps_product` (
    id                      bigint NOT NULL auto_increment,
    name                    varchar(100) comment '商品名称',
    cateid                  varchar(64) DEFAULT NULL comment '类别Id',
    subtitle                varchar(200) DEFAULT NULL comment '商品副标题',
    mainimage               varchar(500) DEFAULT NULL comment '产品主图,url相对地址',
    subimages               text comment '图片地址,json格式',
    detail text             comment '商品详情',
    price                   decimal(20,2) NOT NULL comment '价格,单位-元保留两位小数',
    stock                   int(11) NOT NULL comment '库存数量',
    status                  int(6) DEFAULT '1' comment '商品状态.1-在售 2-下架 3-删除',
    createtime datetime     DEFAULT NULL comment '创建时间',
    updatetime datetime     DEFAULT NULL comment '更新时间',
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
