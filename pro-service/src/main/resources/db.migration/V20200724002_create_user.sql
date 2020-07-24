USE springcloud;

CREATE TABLE IF NOT EXISTS `user`
(
    `user_id`      INT(11)      NOT NULL AUTO_INCREMENT,
    `user_name`    VARCHAR(100) NOT NULL COMMENT '用户姓名',
    `age`          INT(3)       NOT NULL COMMENT '年龄',
    `created_time` BIGINT(13)   NOT NULL DEFAULT 1595485550000,
    `created_by`   INT(3)       NOT NULL DEFAULT 0,
    `updated_time` BIGINT(13)   NOT NULL DEFAULT 1595485550000,
    `updated_by`   INT(3)       NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;