create table red_bag_config (
    id bigint(11) unsigned auto_increment primary key comment '主键',
    type tinyint(11) unsigned not null comment '红包类型：1 普通红包 2 拼手气红包',
    total_num int(11) unsigned not null comment '红包总个数',
    used_num int(11) unsigned not null comment '红包已经发放的个数',
    total_amount int(11)  unsigned not null comment '总金额，单位分',
    used_amount int(11)  unsigned not null comment '已经使用的金额，单位分',
    creator varchar(256) not null comment '创建人',
    expire_time bigint(11) unsigned not null comment '红包过期时间',
    create_time bigint(11) unsigned not null comment '创建时间',
    update_time bigint(11) unsigned not null comment '更新时间'
) comment '发红包配置详情' engine = innodb default char set = utf8;

create table red_bag_allocate_detail (
   id bigint(11) unsigned auto_increment primary key comment '主键',
   amount int(11) unsigned not null comment '单个红包金额，单位分',
   red_bag_config_id bigint(11) unsigned not null comment '红包配置Id',
   status tinyint(11) unsigned not null default 1 comment '领取状态：1 未领取 2 已领取',
   receiver varchar(256) not null comment '领取人',
   receive_time bigint(11) unsigned not null comment '领取时间',
   create_time bigint(11) unsigned not null comment '创建时间',
   update_time bigint(11) unsigned not null comment '更新时间'
) comment '红包分配明细' engine = innodb default char set = utf8;