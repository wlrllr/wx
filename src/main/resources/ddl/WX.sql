CREATE TABLE `WX_USER` (
`ID`  integer(10) NOT NULL AUTO_INCREMENT ,
`NICK_NAME`  varchar(100) CHARACTER SET utf8mb4 NULL ,
`PHONE`  varchar(11) NULL ,
`OPEN_ID`  varchar(50) NULL ,
`SUBSCRIBE_TIME`  datetime NULL,
`UNSUBSCRIBE_TIME`  datetime NULL,
`forbidden`  varchar(2) NULL DEFAULT 'Y',
PRIMARY KEY (`ID`)
)
;
ALTER TABLE `wx_user`
ADD COLUMN `headimgurl`  varchar(200) NULL AFTER `PHONE`,
ADD COLUMN `country`  varchar(20) NULL AFTER `headimgurl`,
ADD COLUMN `province`  varchar(20) NULL AFTER `country`,
ADD COLUMN `city`  varchar(20) NULL AFTER `province`,
ADD COLUMN `remark`  varchar(500) NULL AFTER `forbidden`,
ADD COLUMN `app_id`  varchar(20) NOT NULL AFTER `remark`,
ADD COLUMN `sex`  int(2) NULL COMMENT '1 男，2女，0未知' AFTER `city`;


DROP TABLE IF EXISTS `wx_app`;
CREATE TABLE `wx_app` (
  `account` varchar(60) NOT NULL,
  `app_id` varchar(60) NOT NULL,
  `access_token` varchar(200) DEFAULT NULL,
  `token` varchar(50) NOT NULL,
  `app_secret` varchar(100) NOT NULL,
  `encoding_aes_key` varchar(200) DEFAULT NULL,
  `expires_time` int(4) DEFAULT '7100' COMMENT '默认7100s过期',
  `refresh_time` datetime DEFAULT NULL COMMENT '刷新token时间，保存每次刷新token的时间',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_app
-- ----------------------------
INSERT INTO `wx_app` VALUES ('gh_6197dceca93a', 'wx868081b662772ec0', '5z_uPJj0iYMIiVmG1BI8ZwfSL1wgwCQUUH5LnMjSvpZsRRE0K_oy62vP3ELLlYkwCHmsSQl5Kd7TD9q8wqK9Kch4EtL7pIhqCcW0mK04vrCAZ-nhyI_bEnxoVAq5s3BmOBLeAGADQA', 'wlrllrserver123789', '2ac3dc3b98c5a4f6e46ca10cb5635c1f', null, '7100', null);


