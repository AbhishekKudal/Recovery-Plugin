-- -----------------------------------------------------
-- Schema ecommerce-plugin
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecommerce-plugin`;

CREATE SCHEMA `ecommerce-plugin`;
USE `ecommerce-plugin` ;

-- -----------------------------------------------------
-- Table `ecommerce-plugin`.`abandoned_cart_details`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `ecommerce-plugin`.`abandoned_cart_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT(20) DEFAULT NULL,
  `cart_token` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `contact_number` VARCHAR(255) DEFAULT NULL,
  `email_marketing` BOOLEAN DEFAULT false,
  `sms_marketing` BOOLEAN DEFAULT false,
  `created_at` DATETIME DEFAULT NULL,
  `current_status` BOOLEAN DEFAULT false,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- current_status: False represents order is incomplete and true represents order is complete