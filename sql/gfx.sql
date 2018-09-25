-- 字典表
CREATE TABLE dir(
id varchar(4) not null comment '字典id'
cid varchar(4) not null COMMENT '字典分类id',
NAME VARCHAR(20) NOT NULL COMMENT '字典名',
create_date DATE NULL COMMENT '创建时间',
constraint pk_dir_id primary key (id,cid)
)CHARSET=utf8 COMMENT '字典表';

CREATE TABLE USER(
    user_id VARCHAR(50) PRIMARY KEY COMMENT '用户主键',
    user_name VARCHAR(50) NOT NULL COMMENT '用姓名名',
    pass_word VARCHAR(50) NOT NULL COMMENT '密码',
    user_status varchar(2) not null comment '用户状态：0-正常；1-禁用',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='用户表';

CREATE TABLE role(
    role_id VARCHAR(50) PRIMARY KEY COMMENT '角色主键',
    role_desc VARCHAR(50) NOT NULL COMMENT '角色描述',
    role_status varchar(2) not null comment '角色状态：0-正常；1-禁用',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='角色表';

CREATE TABLE permission(
    permission_id VARCHAR(50) PRIMARY KEY COMMENT '权限id',
    permission_name VARCHAR(50) NOT NULL COMMENT '权限名称',
    permission_url varchar(50) null comment '权限对应的url',
    p_id VARCHAR(50) NULL COMMENT '父id',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='权限表';

CREATE TABLE user_role(
    u_id VARCHAR(50) NOT NULL COMMENT '用户id',
    r_id VARCHAR(50) NOT NULL COMMENT '角色id',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间',
    CONSTRAINT pk_id PRIMARY KEY (u_id,r_id),
    CONSTRAINT fk__u_id FOREIGN KEY (u_id) REFERENCES USER(user_id),
    CONSTRAINT fk_fav_r_id FOREIGN KEY (r_id) REFERENCES role(role_id)
)CHARSET=utf8 COMMENT='用户角色表';

 CREATE TABLE role_permission(
    p_id VARCHAR(50) NOT NULL COMMENT '权限id',
    r_id VARCHAR(50) NOT NULL COMMENT '角色id',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间',
    CONSTRAINT pk_rp_id PRIMARY KEY (p_id,r_id),
    CONSTRAINT fk_rp_p_id FOREIGN KEY (p_id) REFERENCES permission(permission_id),
    CONSTRAINT fk_rp_r_id FOREIGN KEY (r_id) REFERENCES role(role_id)
 )CHARSET=utf8 COMMENT='角色权限表';

 -- 用户登录登出表
 CREATE TABLE access_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id VARCHAR(50) NOT NULL COMMENT '登录用户id',
    access_type VARCHAR(20) NOT NULL COMMENT '登录类型:登入或登出',
    access_ip VARCHAR(50) COMMENT '用户访问ip',
    access_date DATE COMMENT '访问时间'
)CHARSET=utf8 COMMENT='用户登录登出记录表';

 -- 用户操作记录表
CREATE TABLE operation_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录id',
    user_id VARCHAR(50) NOT NULL COMMENT '用户id',
    operation_name VARCHAR(50) NOT NULL COMMENT '操作的名称',
    operation_reslut VARCHAR(50) NOT NULL COMMENT '操作结果',
    operation_date DATE COMMENT '操作时间'
)CHARSET=utf8 COMMENT '用户操作记录表';

-- 货物信息表
CREATE TABLE goods(
id varchar(50) not null COMMENT '型号',
brand VARCHAR(50) NOT NULL COMMENT '品牌',
TYPE varchar(4) NOT NULL COMMENT '货物类型',
size VARCHAR(50) NOT NULL COMMENT '尺寸',
VALUE DOUBLE NULL COMMENT '货物价值',
constraint pk_goods primary key (id,type),
CONSTRAINT fk_goods_type FOREIGN KEY (TYPE) REFERENCES dir(id)
)CHARSET=utf8 COMMENT '货物信息表';

-- 仓库信息表
CREATE TABLE repository(
id varchar(20) PRIMARY KEY COMMENT '仓库代码id',
address VARCHAR(50) NULL COMMENT '仓库地址',
STATUS VARCHAR(2) NOT NULL COMMENT '仓库状态:0 正常;1 异常',
description VARCHAR(100) NULL COMMENT '仓库描述',
AREA FLOAT null COMMENT '仓库面积:单位平方米'
)CHARSET=utf8 COMMENT '仓库信息表';
-- 客户表
CREATE TABLE customer(
id INT PRIMARY KEY AUTO_INCREMENT COMMENT '客户id',
NAME VARCHAR(100) NOT NULL COMMENT '客户名',
link_man VARCHAR(50) NULL COMMENT '客户方联系人',
phone VARCHAR(11) NULL COMMENT '联系电话',
email VARCHAR(30) NULL COMMENT '电子邮箱',
address VARCHAR(200) NULL COMMENT '地址',
sale_man VARCHAR(50) NULL COMMENT '业务员id',
CONSTRAINT fk_cs FOREIGN KEY (sale_man) REFERENCES USER(user_id)
)CHARSET=utf8 COMMENT '客户表';

-- 库存表
CREATE TABLE STORAGE(
id varchar(50) PRIMARY KEY COMMENT '库存主键',
model_num VARCHAR(50) NOT NULL COMMENT '型号',
TYPE varchar(4) NOT NULL COMMENT '货物类型',
quality varchar(4) NOT NULL COMMENT '货物成色',
size VARCHAR(50) NOT NULL COMMENT '货物尺寸',
current_num INT NOT NULL COMMENT '当前库存',
init_num int not null comment '初始库存',
repository_id INT NOT NULL COMMENT '仓库id',
create_date DATE NULL COMMENT '创建时间',
update_date DATE NULL COMMENT '更新时间',
)CHARSET=utf8 COMMENT '库存表';

-- 入库表
create table stockIn(
record_id varchar(30) primary key comment '入库记录id',
goods_id VARCHAR(50) NOT NULL COMMENT '型号',
goods_type varchar(4) NOT NULL COMMENT '货物类型',
in_type varchar(4) not null comment '入库类型:采购入库,生成入库,本厂维修入库,外发入库,',
goods_size VARCHAR(50) NOT NULL COMMENT '货物尺寸',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
goods_number int not null comment '入库数量',
repository_id int not null comment '库位id',
operator varchar(50) not null comment '操作人',
remark varchar(200) null comment '备注',
create_date date not null comment '入库时间',
update_date date not null comment '修改时间'
CONSTRAINT fk_stockIn_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id),
constraint fk_stockout_operator foreign key (operator) references user(user_id)
)charset=utf8 comment '入库表';

-- 出库表
create table stockOut(
record_id varchar(30) primary key comment '出库记录id',
goods_id varchar(50) not null comment '型号',
goods_type varchar(4) not null comment '货物类型',
out_type varchar(4) not null comment '出货类型:生成出库,本厂维修出库,外发出库,报废',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
goods_number int not null comment '出库数量',
operator varchar(50) not null comment '操作人',
remark varchar(200) null comment '备注',
create_date date not null comment '出库时间',
update_date date not null comment '修改时间',
constraint fk_stockout_operator foreign key (operator) references user(user_id),
CONSTRAINT fk_stockIn_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id)
)charset=utf8 comment '出库表'

-- 销售出库表
create table saleOut(
record_id varchar(30) primary key comment '出库记录id',
goods_id varchar(50) not null comment '型号',
goods_type varchar(4) not null comment '货物类型',
goods_number int not null comment '出库数量',
type varchar(4) not null comment '00:出货;01:样品',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
operator varchar(50) not null comment '操作人',
salesman varchar(50) not null comment '业务员',
customer varchar(50) not null comment '客户',
remark varchar(200) null comment '备注',
create_date date not null comment '出库时间',
update_date date not null comment '修改时间',
constraint fk_stockout_operator foreign key (operator) references user(user_id),
CONSTRAINT fk_stockIn_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id)
)charset=utf8 comment '销售出库';

-- 售后入库表
create table afterSale(
record_id varchar(30) primary key comment '出库记录id',
goods_id varchar(50) not null comment '型号',
goods_type varchar(4) not null comment '货物类型',
goods_number int not null comment '入库数量',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
operator varchar(50) not null comment '操作人',
salesman varchar(50) not null comment '业务员',
customer varchar(50) not null comment '客户',
remark varchar(200) null comment '备注',
create_date date not null comment '出库时间',
update_date date not null comment '修改时间',
constraint fk_afterSale_operator foreign key (operator) references user(user_id),
CONSTRAINT fk_afterSale_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id)
)charset=utf8 comment '销售入库';



insert into user values('admin','admin','123456','0',null,null)
insert into role values('commonsAdmin','通用管理员','0',null,null);
insert into role values('systemAdmin','系统管理员','0',null,null);
insert into role values('admin','管理员','0',null,null);

insert into user_role values('admin','commonsAdmin',null,null);
insert into user_role values('admin','systemAdmin',null,null);
insert into user_role values('admin','admin',null,null);

insert into dir values(null,'玻璃','00',null);
insert into dir values(null,'背光','00',null);
insert into dir values(null,'模组','00',null);
insert into dir values(null,'胶纸','00',null);