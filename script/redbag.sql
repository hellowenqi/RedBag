CREATE TABLE `red_bag_allocate_detail` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT ''主键'',
  `amount` int(11) unsigned NOT NULL COMMENT ''单个红包金额，单位分'',
  `red_bag_config_id` bigint(11) unsigned NOT NULL COMMENT ''红包配置Id'',
  `status` tinyint(11) unsigned NOT NULL DEFAULT ''1'' COMMENT ''领取状态：1 未领取 2 已领取'',
  `receiver` varchar(256) DEFAULT NULL COMMENT ''领取人'',
  `receive_time` bigint(11) unsigned NOT NULL COMMENT ''领取时间'',
  `create_time` bigint(11) unsigned NOT NULL COMMENT ''创建时间'',
  `update_time` bigint(11) unsigned NOT NULL COMMENT ''更新时间'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_config_id_receiver` (`red_bag_config_id`,`receiver`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包分配明细';

CREATE TABLE `red_bag_config` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(11) unsigned NOT NULL COMMENT '红包类型：1 普通红包 2 拼手气红包',
  `total_num` int(11) unsigned NOT NULL COMMENT '红包总个数',
  `used_num` int(11) unsigned NOT NULL COMMENT '红包已经发放的个数',
  `total_amount` int(11) unsigned NOT NULL COMMENT '总金额，单位分',
  `used_amount` int(11) unsigned NOT NULL COMMENT '已经使用的金额，单位分',
  `creator` varchar(256) NOT NULL COMMENT '创建人',
  `expire_time` bigint(11) unsigned NOT NULL COMMENT '红包过期时间',
  `create_time` bigint(11) unsigned NOT NULL COMMENT '创建时间',
  `update_time` bigint(11) unsigned NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发红包配置详情'