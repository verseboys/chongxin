CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  `open_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `clientid` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `unionid` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `platform` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `version` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `systemversion` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `checked` int(11) NOT NULL DEFAULT '0' COMMENT '手机号码是否验证，0，未验证，1验证',
  `lastlogin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116424 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `isfinished` int(11) DEFAULT '0' COMMENT '活动结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `activity_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `aid` int(11) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `mobile` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `beused` int(11) DEFAULT '0' COMMENT '已体检',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '0' COMMENT '设置默认地址 1为默认地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8;

CREATE TABLE `admin_menu` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `fid` varchar(255) DEFAULT NULL COMMENT '父id',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `state` bit(1) DEFAULT b'0' COMMENT '是否删除，1为删除',
  `type` int(11) DEFAULT '0' COMMENT '会员或后台用户',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `album` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `dog_id` varchar(6) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `view_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL DEFAULT '0',
  `created` varchar(30) NOT NULL,
  `content` varchar(500) NOT NULL,
  `ask_id` int(11) NOT NULL DEFAULT '0',
  `to_uid` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `answer_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL DEFAULT '0',
  `photo` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `ask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` int(11) NOT NULL DEFAULT '0',
  `score` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  `created` varchar(30) NOT NULL,
  `content` varchar(500) NOT NULL,
  `uid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `ask_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL DEFAULT '0',
  `photo` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `title` varchar(30) DEFAULT NULL,
  `content` text,
  `uid` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `buy` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `uid` int(11) DEFAULT '0' COMMENT '用户id',
  `addressid` int(11) DEFAULT '0' COMMENT '地址id',
  `state` int(11) DEFAULT '0' COMMENT '购买状态 0待付款 1付款成功 2已发货 3已收货 -1关闭',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货地址',
  `buytime` datetime DEFAULT NULL COMMENT '下单时间',
  `paytype` int(11) DEFAULT '0' COMMENT '付款方式 0为微信1为支付宝',
  `paycount` float NOT NULL DEFAULT '0' COMMENT '总金额',
  `noncestr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '随机字符串 判断微信回调',
  `gold` int(11) DEFAULT '0' COMMENT '订单使用金币数',
  `deleted` int(11) DEFAULT '0',
  `express` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '快递',
  `oddnumbers` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `buy_copy` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `uid` int(11) DEFAULT '0' COMMENT '用户id',
  `productid` int(11) DEFAULT '0' COMMENT '产品id',
  `addressid` int(11) DEFAULT '0' COMMENT '地址id',
  `state` int(11) DEFAULT '0' COMMENT '购买状态 0待付款 1付款成功 2已发货 3已收货 -1关闭',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货地址',
  `number` int(11) DEFAULT '0' COMMENT '购买份数',
  `buytime` datetime DEFAULT NULL COMMENT '下单时间',
  `starttime` datetime DEFAULT NULL COMMENT '生效时间',
  `endtime` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '失效时间',
  `paytype` int(11) DEFAULT '0' COMMENT '付款方式 0为微信1为支付宝',
  `paycount` float NOT NULL DEFAULT '0' COMMENT '总金额',
  `dogid` int(11) DEFAULT '0',
  `noncestr` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '随机字符串 判断微信回调',
  `gold` int(11) DEFAULT '0' COMMENT '订单使用金币数',
  `deleted` int(11) DEFAULT '0',
  `express` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '快递',
  `oddnumbers` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `buy_infor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyid` varchar(255) DEFAULT '0' COMMENT '购买id',
  `productid` int(11) DEFAULT '0' COMMENT '产品id',
  `number` int(11) DEFAULT '0' COMMENT '购买数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT '0',
  `forum_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `city` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(120) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '1',
  `pid` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型名称',
  `pid` int(11) DEFAULT '0' COMMENT '父id',
  `type` int(11) DEFAULT '0' COMMENT '1 宠物经历',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '犬舍名称',
  `banner` varchar(150) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度',
  `longtitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `introduction` text COMMENT '犬舍介绍',
  `created` datetime DEFAULT NULL,
  `open_time` varchar(255) DEFAULT NULL COMMENT '营业时间',
  `deleted` int(11) DEFAULT '0',
  `type` int(11) DEFAULT '1' COMMENT '1是犬舍 2是宠物店',
  `notice` varchar(255) DEFAULT NULL COMMENT '公告',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116327 DEFAULT CHARSET=utf8;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(90) NOT NULL,
  `number` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `video` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `custom` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `qq` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `content` text,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `dog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '0',
  `blood` varchar(255) DEFAULT NULL,
  `ear_no` varchar(25) DEFAULT NULL,
  `sex` int(11) DEFAULT '1',
  `name` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `father_blood` varchar(255) DEFAULT NULL,
  `mather_blood` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `height` float DEFAULT '0',
  `weight` float DEFAULT '0',
  `owner_id` int(11) DEFAULT '0',
  `sire_id` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `viewed` int(11) DEFAULT '0',
  `zan` int(11) DEFAULT '0',
  `comment_count` int(11) DEFAULT '0',
  `favorite_count` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `command` int(11) DEFAULT '0',
  `classify` int(11) DEFAULT NULL COMMENT '0狗1猫2其他',
  `chip` varchar(255) DEFAULT NULL COMMENT '芯片号',
  `isok` int(11) DEFAULT '0' COMMENT '宠物芯片是否确认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101009 DEFAULT CHARSET=utf8;

CREATE TABLE `dog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dog_id` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL DEFAULT '0',
  `zan` int(11) NOT NULL DEFAULT '0',
  `content` varchar(350) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dog_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `dog_id` int(11) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`,`dog_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `dog_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `dog_id` int(11) NOT NULL DEFAULT '0',
  `content` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT '0',
  `url` varchar(255) NOT NULL,
  `created` datetime DEFAULT NULL,
  `viewed` int(11) DEFAULT '0',
  `replied` int(11) DEFAULT '0',
  `good` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100144 DEFAULT CHARSET=utf8;

CREATE TABLE `feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `content` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photo` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pid` int(11) DEFAULT '0',
  `reply_count` int(11) DEFAULT '0',
  `good_count` int(11) DEFAULT '0',
  `created` datetime NOT NULL,
  `region` int(11) DEFAULT '0',
  `relation_id` int(11) DEFAULT '0',
  `latitude` double DEFAULT '0',
  `longtitude` double DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  `address` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `card` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `feed_index` (`uid`,`created`,`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2263 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `feed_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) NOT NULL DEFAULT '0' COMMENT '动态id',
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '发布者id',
  `comment` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
  `created` datetime NOT NULL COMMENT '发信时间',
  `zan` int(11) NOT NULL DEFAULT '0',
  `to_uid` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `feed_comment_index` (`fid`,`uid`,`created`,`zan`,`to_uid`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=28966 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `feed_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) NOT NULL DEFAULT '0' COMMENT '动态id',
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '用户',
  `url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7324 DEFAULT CHARSET=utf8;

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '0',
  `content` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `relation_id` int(11) DEFAULT '0',
  `uid` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `intro` varchar(500) DEFAULT NULL,
  `pid` int(11) NOT NULL,
  `topics` int(11) NOT NULL,
  `today_topics` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  `orders` int(11) NOT NULL,
  `viewed` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

CREATE TABLE `forum_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT '0' COMMENT '用户id',
  `fid` int(11) DEFAULT '0' COMMENT '好友id',
  `status` int(11) DEFAULT '0' COMMENT '状态(0关注，1互相关注)',
  `created` datetime DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1393 DEFAULT CHARSET=utf8;

CREATE TABLE `goldrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT '0' COMMENT '用户id',
  `fid` int(11) DEFAULT '0' COMMENT '动态id',
  `type` int(11) DEFAULT '0' COMMENT '0为评论 1为赞',
  `gold` int(11) DEFAULT '0' COMMENT '领取金币数',
  `createdtime` datetime DEFAULT NULL COMMENT '领取时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13864 DEFAULT CHARSET=utf8;

CREATE TABLE `ip` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `start_ip` double DEFAULT NULL,
  `end_ip` double DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `isp` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `to_uid` int(11) NOT NULL DEFAULT '0',
  `from_uid` int(11) NOT NULL DEFAULT '0',
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14334 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `notice_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) DEFAULT '0',
  `notice` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `online_user` (
  `id` varchar(50) NOT NULL,
  `uid` int(11) NOT NULL,
  `updated` datetime NOT NULL,
  `platform` varchar(10) NOT NULL DEFAULT 'mobile',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `orderoperation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `statefrom` int(255) DEFAULT '0',
  `stateto` int(255) DEFAULT '0',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL DEFAULT '' COMMENT '订单号',
  `uid` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '0',
  `deleted` int(11) DEFAULT '0',
  `remark` text COMMENT '订单说明',
  `adminremark` text COMMENT '后台备注',
  `created` datetime DEFAULT NULL COMMENT '下单时间',
  `address_id` int(11) DEFAULT '0' COMMENT '联系地址',
  `doctor_id` int(11) DEFAULT '0' COMMENT '医师',
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `dog_id` varchar(255) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品名',
  `type` int(11) DEFAULT '1' COMMENT '产品类型 1为宠信宝',
  `price` float NOT NULL DEFAULT '0' COMMENT '价格',
  `unit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单位',
  `count` int(11) DEFAULT '0' COMMENT '销量',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品详情链接',
  `deleted` int(11) DEFAULT '0',
  `createdtime` datetime DEFAULT NULL COMMENT '上市时间',
  `imgurl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `detail` longtext COLLATE utf8mb4_unicode_ci COMMENT '商品详情',
  `marketprice` float NOT NULL DEFAULT '0' COMMENT '市场价',
  `ismall` int(11) DEFAULT '0' COMMENT '1 商城产品',
  `img_small` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '小图地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '产品类型名',
  `pinyin` varchar(255) DEFAULT NULL COMMENT '名字的拼音（产品详情 h5展示时要用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `sex` int(11) NOT NULL DEFAULT '1',
  `truename` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `qq` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `feeds_Count` int(11) DEFAULT '0',
  `topics_Count` int(11) DEFAULT '0',
  `friends_Count` int(11) DEFAULT '0',
  `view_Count` int(11) DEFAULT '0',
  `level` int(11) DEFAULT '0',
  `score` int(11) DEFAULT '0',
  `prestige` int(11) DEFAULT '0',
  `gold_Count` int(11) DEFAULT '0',
  `domain` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `intro` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `company` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idcard` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city_id` int(11) DEFAULT '1',
  `latitude` float NOT NULL DEFAULT '0',
  `longtitude` float NOT NULL DEFAULT '0',
  `is_city` int(11) NOT NULL DEFAULT '0',
  `birthday` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT '1',
  `topic_pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主题图片',
  `from_id` int(11) DEFAULT '0' COMMENT '推荐人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `profit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT '0' COMMENT '0 不可使用 1可使用 2(推荐的时候才会出现，只是推荐成功但购买金额还未达标)',
  `uid` int(11) DEFAULT '0' COMMENT '用户id',
  `buy_id` varchar(255) DEFAULT NULL COMMENT '订单id',
  `type` int(11) DEFAULT '0' COMMENT '0 推荐 1购买返利 2提现',
  `fid` int(11) DEFAULT '0' COMMENT '推荐人id',
  `profit` float NOT NULL DEFAULT '0' COMMENT '收入',
  `created` datetime DEFAULT NULL COMMENT '记录生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feedid` int(11) DEFAULT '0' COMMENT '动态id',
  `classifyid` int(11) DEFAULT '0' COMMENT '记录类型',
  `weight` double DEFAULT '0' COMMENT '体重',
  `height` double DEFAULT '0' COMMENT '肩高',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `record_time` datetime DEFAULT NULL COMMENT '疫苗时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `dogid` int(11) DEFAULT '0' COMMENT '宠物id',
  `uid` int(11) DEFAULT '0' COMMENT '用户id',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1170 DEFAULT CHARSET=utf8;

CREATE TABLE `record_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL DEFAULT '0' COMMENT '记录id',
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '用户',
  `url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6708 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `scheduled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `qq` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dogclass` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '犬种',
  `subjectid` int(11) DEFAULT '0' COMMENT '专题id',
  `state` int(11) DEFAULT '0' COMMENT '状态',
  `createdtime` datetime DEFAULT NULL COMMENT '预定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `server` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) DEFAULT '0',
  `classifyid` int(11) DEFAULT '0',
  `price` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `units` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `sharenotes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `to_uid` int(11) NOT NULL DEFAULT '0',
  `from_uid` int(11) NOT NULL DEFAULT '0',
  `notes` varchar(255) NOT NULL COMMENT '分享的哪篇',
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=454 DEFAULT CHARSET=utf8;

CREATE TABLE `shows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `dog_count` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `year` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专题名',
  `logo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专题标志',
  `banner` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标语',
  `summary` text COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `qrcode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '二维码',
  `domain` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '域名',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0',
  `fid` int(11) NOT NULL DEFAULT '0',
  `pid` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '1',
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `last_uid` int(11) DEFAULT '0',
  `replied` int(11) DEFAULT '0',
  `viewed` int(11) DEFAULT '0',
  `deleted` int(11) DEFAULT '0',
  `topest` int(11) DEFAULT '0',
  `best` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `last_replied` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `topic_index` (`uid`,`fid`,`type`,`deleted`,`topest`,`updated`,`last_replied`,`created`,`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=utf8;

CREATE TABLE `toutiao_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT '0' COMMENT '素材id',
  `published` datetime DEFAULT NULL COMMENT '发布时间',
  `viewCount` int(11) DEFAULT '0',
  `qrCode` varchar(255) DEFAULT NULL COMMENT '二维码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

CREATE TABLE `toutiao_sucai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `logo` varchar(100) DEFAULT NULL COMMENT '图片',
  `intro` varchar(300) DEFAULT NULL COMMENT '内容简介',
  `content` longtext COMMENT '内容',
  `link` varchar(100) DEFAULT NULL COMMENT '原文链接',
  `auth` varchar(50) DEFAULT NULL COMMENT '作者',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `viewed` int(11) DEFAULT '0' COMMENT '阅读次数',
  `deleted` int(11) DEFAULT '0' COMMENT '状态(0 显示，1删除，不显示)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

CREATE TABLE `trans_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dogid` int(11) DEFAULT '0' COMMENT 'dog id',
  `from_uid` int(11) DEFAULT '0',
  `to_uid` int(11) DEFAULT '0',
  `transtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=839 DEFAULT CHARSET=utf8;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL COMMENT '用户id',
  `profit` float NOT NULL DEFAULT '0' COMMENT '可使用金额',
  `no_profit` float NOT NULL DEFAULT '0' COMMENT '不可使用金额',
  `alipay` varchar(255) DEFAULT NULL COMMENT '支付宝帐号',
  `name` varchar(255) DEFAULT NULL COMMENT '用户的名字',
  `qrCode` varchar(255) DEFAULT NULL COMMENT '推荐二维码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

CREATE TABLE `voucher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT '0',
  `typeid` int(11) DEFAULT '0' COMMENT '代金券类型',
  `price` int(11) DEFAULT '0' COMMENT '代金券面值',
  `beused` int(11) DEFAULT '0' COMMENT '1 为已经使用',
  `created` datetime DEFAULT NULL COMMENT '代金券生成时间',
  `endtime` datetime DEFAULT NULL COMMENT '有效期到',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `voucher_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '代金券名称',
  `intro` varchar(255) DEFAULT NULL COMMENT '代金券说明',
  `effectivetime` int(11) DEFAULT '0' COMMENT '优惠券有效时间（天）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `warranty` (
  `id` varchar(255) NOT NULL,
  `blood` varchar(255) DEFAULT NULL COMMENT '宠物芯片号',
  `uid` int(11) DEFAULT '0' COMMENT '购买者',
  `price` int(11) DEFAULT '0' COMMENT '价格',
  `checked` int(11) DEFAULT '0' COMMENT '1 为已确认保单',
  `deleted` int(11) DEFAULT '0' COMMENT '1 删除',
  `created` datetime DEFAULT NULL COMMENT '下单时间',
  `paytime` datetime DEFAULT NULL COMMENT '收费时间',
  `starttime` datetime DEFAULT NULL COMMENT '生效时间',
  `endtime` datetime DEFAULT NULL COMMENT '失效时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `warranty_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wid` varchar(255) DEFAULT NULL COMMENT '保单id',
  `uid` int(11) DEFAULT '0' COMMENT '操作人id',
  `money` int(11) DEFAULT '0' COMMENT '收款金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '操作说明',
  `created` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;