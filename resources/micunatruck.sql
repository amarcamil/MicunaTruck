-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema micunatruck
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema micunatruck
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `micunatruck` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `micunatruck` ;

-- -----------------------------------------------------
-- Table `micunatruck`.`user_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`user_types` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');


-- -----------------------------------------------------
-- Table `micunatruck`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`users` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `user_type_id` INT NOT NULL COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `lastname` VARCHAR(50) NULL COMMENT '',
  `legal_name` VARCHAR(100) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `photo` TEXT NULL COMMENT '',
  `address` VARCHAR(250) NULL COMMENT '',
  `telephone` VARCHAR(50) NULL COMMENT '',
  `email` VARCHAR(50) NULL COMMENT '',
  `password` VARCHAR(300) NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_users_user_types1_idx` (`user_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_users_user_types1`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `micunatruck`.`user_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `micunatruck`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`admins` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `lastname` VARCHAR(50) NULL COMMENT '',
  `photo` TEXT NULL COMMENT '',
  `email` VARCHAR(50) NULL COMMENT '',
  `password` VARCHAR(300) NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');


-- -----------------------------------------------------
-- Table `micunatruck`.`event_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`event_status` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');


-- -----------------------------------------------------
-- Table `micunatruck`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`events` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` INT NOT NULL COMMENT '',
  `event_status_id` INT NOT NULL COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `image` TEXT NULL COMMENT '',
  `date` TIMESTAMP NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_events_event_status_idx` (`event_status_id` ASC)  COMMENT '',
  INDEX `fk_events_users1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_events_event_status`
    FOREIGN KEY (`event_status_id`)
    REFERENCES `micunatruck`.`event_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_events_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `micunatruck`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `micunatruck`.`applicants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`applicants` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` INT NOT NULL COMMENT '',
  `event_id` INT NOT NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 0 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_applicants_users1_idx` (`user_id` ASC)  COMMENT '',
  INDEX `fk_applicants_events1_idx` (`event_id` ASC)  COMMENT '',
  CONSTRAINT `fk_applicants_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `micunatruck`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_applicants_events1`
    FOREIGN KEY (`event_id`)
    REFERENCES `micunatruck`.`events` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `micunatruck`.`ads`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`ads` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `admin_id` INT NOT NULL COMMENT '',
  `user_type_id` INT NOT NULL COMMENT '',
  `code_space` INT NOT NULL COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `image` TEXT NULL COMMENT '',
  `price` DECIMAL(11,2) NULL COMMENT '',
  `start_date` TIMESTAMP NULL COMMENT '',
  `end_date` TIMESTAMP NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_ads_admins1_idx` (`admin_id` ASC)  COMMENT '',
  INDEX `fk_ads_user_types1_idx` (`user_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_ads_user_types1`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `micunatruck`.`user_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ads_admins1`
    FOREIGN KEY (`admin_id`)
    REFERENCES `micunatruck`.`admins` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `micunatruck`.`subscriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`subscriptions` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `user_id` INT NOT NULL COMMENT '',
  `price` DECIMAL(11,2) NULL COMMENT '',
  `start_date` TIMESTAMP NULL COMMENT '',
  `end_date` TIMESTAMP NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_subscriptions_users1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_subscriptions_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `micunatruck`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `micunatruck`.`origin_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`origin_types` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');


-- -----------------------------------------------------
-- Table `micunatruck`.`incomes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `micunatruck`.`incomes` (
  `id` INT  NOT NULL AUTO_INCREMENT COMMENT '',
  `origin_id` INT NOT NULL COMMENT '',
  `origin_type_id` INT NOT NULL COMMENT '',
  `amount` DECIMAL(11,2) NULL COMMENT '',
  `start_date` TIMESTAMP NULL COMMENT '',
  `end_date` TIMESTAMP NULL COMMENT '',
  `flag_active` TINYINT(1)  NULL DEFAULT 1 COMMENT '',
  `updated_at` TIMESTAMP NULL COMMENT '',
  `deleted_at` TIMESTAMP NULL COMMENT '',
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_incomes_origin_types1_idx` (`origin_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_incomes_origin_types1`
    FOREIGN KEY (`origin_type_id`)
    REFERENCES `micunatruck`.`origin_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- INSERTING DATA
-- -----------------------------------------------------



-- -----------------------------------------------------
-- Inserting Table `micunatruck`.`user_types`
-- -----------------------------------------------------
INSERT INTO user_types (
	id,
    name,
    description,
    flag_active,
    created_at
)
VALUES (
	1,
	'FoodTruck',
    'Usuario food truck',
    1,
    NOW()
	);

INSERT INTO user_types (
	id,
    name,
    description,
    flag_active,
    created_at
)
VALUES (
	2,
	'Promotor',
    'Usuario organizador de eventos',
    1,
    NOW()
	);

INSERT INTO event_status (id,name,description, flag_active, updated_at) VALUES
(1, 'Pendiente', 'Evento Pendiente', 1, NOW()),
(2, 'Finalizado', 'Evento Finalizado', 1, NOW()),
(3, 'Cancelado', 'Evento Cancelado', 1, NOW())
;

COMMIT;
