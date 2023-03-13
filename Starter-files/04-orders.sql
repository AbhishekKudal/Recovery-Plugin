-- -----------------------------------------------------
-- Table `ecommerce-plugin`.`orders`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `ecommerce-plugin`.`orders` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cart_token` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;