要运行配置中心，需要创建表：

CREATE TABLE `auth_application` (
  `app_id` VARCHAR(50) NOT NULL,
  `app_code` VARCHAR(50) NOT NULL,
  `app_name` VARCHAR(50) NULL,
  `app_secret` VARCHAR(100) NULL,
  `app_secret_text` VARCHAR(100) NULL,
  `ticketlivetimes` DECIMAL(10) NULL,
  PRIMARY KEY (`app_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = '应用表';
