-- 字典表
CREATE TABLE dir(
id varchar(4) not null comment '字典id',
cid varchar(4) not null COMMENT '字典分类id',
NAME VARCHAR(20) NOT NULL COMMENT '字典名',
cname varchar(50) null comment '分类名称',
create_date DATE NULL COMMENT '创建时间',
cname varchar(50) not null comment '分类名称',
constraint pk_dir_id primary key (id,cid)
)CHARSET=utf8 COMMENT '字典表';

CREATE TABLE USER(
    user_id VARCHAR(50) PRIMARY KEY COMMENT '用户主键',
    user_name VARCHAR(50) NOT NULL COMMENT '用姓名名',
    -- 密码初始为123456
    pass_word VARCHAR(50) NOT NULL DEFAULT 'e10adc3949ba59abbe56e057f20f883e' COMMENT '初始密码',
    age INT NULL COMMENT '年龄',
    address VARCHAR(50) NULL COMMENT '住址',
    sex VARCHAR(2) NULL COMMENT '性别:0-男;1-女',
    phone VARCHAR(11) NULL COMMENT '联系电话',
    id_no VARCHAR(19) NULL COMMENT '身份证号',
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
    user_name VARCHAR(50) not null COMMENT '用户名',
    access_type VARCHAR(20) NOT NULL COMMENT '登录类型:登入或登出',
    access_ip VARCHAR(50) COMMENT '用户访问ip',
    access_date timestamp COMMENT '访问时间'
)CHARSET=utf8 COMMENT='用户登录登出记录表';

 -- 用户操作记录表
CREATE TABLE operation_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录id',
    user_id VARCHAR(50) NOT NULL COMMENT '用户id',
    user_name varchar(40) not null comment '用户名',
    operation_name VARCHAR(50) NOT NULL COMMENT '操作的名称',
    operation_result VARCHAR(50) NOT NULL COMMENT '操作结果',
    operation_date timestamp COMMENT '操作时间'
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
goods_id VARCHAR(50) NOT NULL COMMENT '型号',
goods_type varchar(4) NOT NULL COMMENT '货物类型',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
goods_size VARCHAR(50) NOT NULL COMMENT '货物尺寸',
current_num INT NOT NULL COMMENT '当前库存',
init_num int not null comment '初始库存',
repository_id varchar(20) NOT NULL COMMENT '仓库id',
create_date timestamp NULL COMMENT '创建时间',
update_date timestamp NULL COMMENT '更新时间',
storage_status varchar(2) not null default '0' comment '库存状态:0:正常;1:该库存数量为0时,进行软删除'
)CHARSET=utf8 COMMENT '库存表';

-- 出入库表
create table stock_operator(
record_id varchar(30) primary key comment '入库记录id',
goods_id VARCHAR(50) NOT NULL COMMENT '型号',
goods_type varchar(4) NOT NULL COMMENT '货物类型',
stock_type varchar(4) not null comment '入库类型:采购入库,生成入库,本厂维修入库,外发入库,出货类型:生成出库,本厂维修出库,外发出库,报废',
goods_size VARCHAR(50) NOT NULL COMMENT '货物尺寸',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
goods_number int not null comment '入库数量',
repository_id varchar(20) not null comment '库位id',
operator varchar(50) not null comment '操作人',
customer_id int null comment '客户id',
sale_man varchar(30) null comment '业务员id',
remark varchar(200) null comment '备注',
operator_date timestamp not null comment '出入库时间',
CONSTRAINT fk_stockIn_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id),
constraint fk_stockIn_operator foreign key (operator) references user(user_id)
)charset=utf8 comment '出入库表操作';

-- 出库表(已废弃)
create table stock_out(
record_id varchar(30) primary key comment '出库记录id',
goods_id varchar(50) not null comment '型号',
goods_type varchar(4) not null comment '货物类型',
out_type varchar(4) not null comment '出货类型:生成出库,本厂维修出库,外发出库,报废',
goods_quality varchar(4) NOT NULL COMMENT '货物成色',
goods_number int not null comment '出库数量',
operator varchar(50) not null comment '操作人',
repository_id varchar(20) not null comment '库位id',
remark varchar(200) null comment '备注',
create_date date not null comment '出库时间',
update_date date not null comment '修改时间',
constraint fk_stockOut_operator foreign key (operator) references user(user_id),
CONSTRAINT fk_stockOut_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id)
)charset=utf8 comment '出库表';

-- 销售出库表(已废弃)
create table sale(
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
constraint fk_sale_operator foreign key (operator) references user(user_id),
CONSTRAINT fk_sale_goods_id FOREIGN KEY (goods_id) REFERENCES goods(id)
)charset=utf8 comment '销售出库';

-- 售后入库表(已废弃)
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



insert into user values('admin','admin','123456','0',null,null);
insert into user values ('tony','tony','123456','0',null,null);

insert into role values('admin','管理员','0',sysdate(),sysdate());

insert into user_role values('admin','commonsAdmin',null,null);
insert into user_role values('tony','commonsAdmin',null,null);
insert into user_role values('admin','systemAdmin',null,null);
insert into user_role values('admin','admin',null,null);
insert into user_role values('tony','systemAdmin',null,null);

insert into dir values('0001','00','玻璃',null);
insert into dir values('0002','00','背光',null);
insert into dir values('0003','00','模组',null);
insert into dir values('0004','00','胶纸',null);

alter table dir add cname varchar(50) not null;

insert into goods values ('NV156FHM-N61','熊猫','0001','15.6',null);
insert into goods values ('NV156FHM-N48','熊猫','0001','15.6',null);
insert into goods values ('NV133FHM-N52','熊猫','0001','13.3',null);

insert into repository values ('A1','A区1号','0',null,null);
insert into repository values ('A2','A区2号','0',null,null);
insert into repository values ('A3','A区3号','0',null,null);
insert into repository values ('A4','A区4号','0',null,null);

insert into customer values (null,'华为','任总','10086',null,null,'admin');
insert into customer values (null,'中兴','刘总','10000',null,null,'admin');
insert into customer values (null,'京东','刘总','102369',null,null,'admin');

-- 货物成色插入
insert into dir values ('0101','01','ok',null,'货物成色');
insert into dir values ('0102','01','A规',null,'货物成色');
insert into dir values ('0103','01','B规',null,'货物成色');
insert into dir values ('0104','01','线屏',null,'货物成色');
insert into dir values ('0105','01','破',null,'货物成色');
insert into dir values ('0106','01','花屏',null,'货物成色');
insert into dir values ('0107','01','短路',null,'货物成色');

-- 入库类型插入
insert into dir values ('0201','02','采购入库',null,'入库类型');
insert into dir values ('0202','02','生产入库',null,'入库类型');
insert into dir values ('0203','02','维修入库',null,'入库类型');
insert into dir values ('0204','02','外发入库',null,'入库类型');

-- 插入售后入库类型
insert into dir values ('0205','02','售后入库',null,'入库类型');

-- 2018/9/28
insert into role values ('saleMan','业务员','0',null,null);
insert into user_role values ('tony','saleMan',null,null);

insert into dir values ('0301','03','销售出库',null,'出库类型');
insert into dir values ('0302','03','样品出库',null,'出库类型');
insert into dir values ('0303','03','维修出库',null,'出库类型');
insert into dir values ('0304','03','外发出库',null,'出库类型');
insert into dir values ('0305','03','生产出库',null,'出库类型');

-- 2018/9/29
alter table storage add storage_status varchar(2) not null default '0' comment '库存状态:0:正常;1:该库存数量为0时,进行软删除';

-- 2018/10/08
ALTER TABLE access_record MODIFY COLUMN access_date TIMESTAMP;
-- 2018/10/09
ALTER TABLE USER ADD COLUMN age INT NULL COMMENT '年龄';
ALTER TABLE USER ADD COLUMN address VARCHAR(50) NULL COMMENT '住址';
ALTER TABLE USER ADD COLUMN sex VARCHAR(2) NULL COMMENT '性别:0-男;1-女';
ALTER TABLE USER ADD COLUMN phone VARCHAR(11) NULL COMMENT '联系电话';
ALTER TABLE USER ADD COLUMN id_no VARCHAR(19) NULL COMMENT '身份证号';
-- 2018/10/10
insert into role values ('storer','仓管员','0',sysdate(),sysdate());
insert into role values ('buyer','采购员','0',sysdate(),sysdate());
insert into role values ('governor','主管','0',sysdate(),sysdate());
insert into permission values ('user:update','员工更新','/updateUserAdmin',null,sysdate(),sysdate());
insert into permission values ('user:delete','员工删除','/deleteUserAdmin',null,sysdate(),sysdate());
insert into permission values ('user:add','员工新增','/addUserAdmin',null,sysdate(),sysdate());
insert into permission values ('goods:add','货物新增','/addGoods',null,sysdate(),sysdate());
insert into permission values ('goods:update','货物更新','/updateGoods',null,sysdate(),sysdate());
insert into permission values ('customer:add','客户新增','/addCustomer',null,sysdate(),sysdate());
insert into permission values ('customer:update','客户更新','/addCustomer',null,sysdate(),sysdate());
insert into permission values ('stock:in','入库操作','stockIn',null,sysdate(),sysdate());
insert into permission values ('stock:out','出库操作','stockOut',null,sysdate(),sysdate());
insert into permission values ('stock:select','出入库查询','searchStockRecord',null,sysdate(),sysdate());
insert into permission values ('storage:update','库存更新','updateStorage',null,sysdate(),sysdate());
insert into permission values ('storage:delete','库存更新','deleteStorage',null,sysdate(),sysdate());

insert into role_permission values ('user:update','admin',sysdate(),sysdate());
insert into role_permission values ('user:delete','admin',sysdate(),sysdate());
insert into role_permission values ('user:add','admin',sysdate(),sysdate());
insert into role_permission values ('goods:add','admin',sysdate(),sysdate());
insert into role_permission values ('customer:update','admin',sysdate(),sysdate());
insert into role_permission values ('customer:add','admin',sysdate(),sysdate());
insert into role_permission values ('stock:in','admin',sysdate(),sysdate());
insert into role_permission values ('stock:out','admin',sysdate(),sysdate());
insert into role_permission values ('stock:select','admin',sysdate(),sysdate());
insert into role_permission values ('storage:update','admin',sysdate(),sysdate());
insert into role_permission values ('storage:delete','admin',sysdate(),sysdate());

-- 2018/10/11
insert into role_permission values ('stock:in','storer',sysdate(),sysdate());
insert into role_permission values ('stock:out','storer',sysdate(),sysdate());
insert into role_permission values ('stock:select','storer',sysdate(),sysdate());
insert into role_permission values ('storage:update','storer',sysdate(),sysdate());