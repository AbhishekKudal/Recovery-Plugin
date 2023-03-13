-- -----------------------------------------------------
-- Table `ecommerce-plugin`.`scheduler`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `ecommerce-plugin`.`scheduler` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cart_token` VARCHAR(255) DEFAULT NULL,
  `scheduled_time` DATETIME DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `contact_number` VARCHAR(255) DEFAULT NULL,
  `current_status` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;