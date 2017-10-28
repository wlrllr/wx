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


CREATE TABLE `WX_USER_TAG` (
`USER_ID`  integer(10) NOT NULL ,
`TAG_NAME`  varchar(100) NOT NULL ,
PRIMARY KEY (`USER_ID`, `TAG_NAME`)
)
;

