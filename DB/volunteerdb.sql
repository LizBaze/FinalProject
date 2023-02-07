-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema volunteerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `volunteerdb` ;

-- -----------------------------------------------------
-- Schema volunteerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `volunteerdb` DEFAULT CHARACTER SET utf8 ;
USE `volunteerdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(45) NULL,
  `img_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NULL,
  `bio` TEXT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `organization` ;

CREATE TABLE IF NOT EXISTS `organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `logo` VARCHAR(2000) NULL,
  `description` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `volunteer_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `volunteer_event` ;

CREATE TABLE IF NOT EXISTS `volunteer_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `created_date` DATETIME NULL,
  `start_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  `organization_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_organization1_idx` (`organization_id` ASC),
  INDEX `fk_volunteer_event_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_event_organization1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_volunteer_event_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `participant` ;

CREATE TABLE IF NOT EXISTS `participant` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `description` TEXT NULL,
  `img_url` VARCHAR(2000) NULL,
  `rating` INT NULL,
  `date_joined` DATETIME NULL,
  PRIMARY KEY (`user_id`, `event_id`),
  INDEX `fk_user_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_user_has_event_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `volunteer_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

CREATE TABLE IF NOT EXISTS `member` (
  `user_id` INT NOT NULL,
  `organization_id` INT NOT NULL,
  `admin` TINYINT NULL,
  `date_joined` DATETIME NULL,
  PRIMARY KEY (`user_id`, `organization_id`),
  INDEX `fk_user_has_organization_organization1_idx` (`organization_id` ASC),
  INDEX `fk_user_has_organization_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_organization_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_organization_organization1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_message` ;

CREATE TABLE IF NOT EXISTS `group_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NULL,
  `date_posted` DATETIME NULL,
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `in_reply_to` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_message_user1_idx` (`user_id` ASC),
  INDEX `fk_group_message_event1_idx` (`event_id` ASC),
  INDEX `fk_group_message_group_message1_idx` (`in_reply_to` ASC),
  CONSTRAINT `fk_group_message_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_message_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `volunteer_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_message_group_message1`
    FOREIGN KEY (`in_reply_to`)
    REFERENCES `group_message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cause`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cause` ;

CREATE TABLE IF NOT EXISTS `cause` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `icon_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_img`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_img` ;

CREATE TABLE IF NOT EXISTS `event_img` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `img_url` VARCHAR(2000) NULL,
  `caption` VARCHAR(2000) NULL,
  `volunteer_event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_img_volunteer_event1_idx` (`volunteer_event_id` ASC),
  CONSTRAINT `fk_event_img_volunteer_event1`
    FOREIGN KEY (`volunteer_event_id`)
    REFERENCES `volunteer_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_cause`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_cause` ;

CREATE TABLE IF NOT EXISTS `user_has_cause` (
  `user_id` INT NOT NULL,
  `cause_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `cause_id`),
  INDEX `fk_user_has_cause_cause1_idx` (`cause_id` ASC),
  INDEX `fk_user_has_cause_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_cause_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_cause_cause1`
    FOREIGN KEY (`cause_id`)
    REFERENCES `cause` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization_has_cause`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `organization_has_cause` ;

CREATE TABLE IF NOT EXISTS `organization_has_cause` (
  `organization_id` INT NOT NULL,
  `cause_id` INT NOT NULL,
  PRIMARY KEY (`organization_id`, `cause_id`),
  INDEX `fk_organization_has_cause_cause1_idx` (`cause_id` ASC),
  INDEX `fk_organization_has_cause_organization1_idx` (`organization_id` ASC),
  CONSTRAINT `fk_organization_has_cause_organization1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_organization_has_cause_cause1`
    FOREIGN KEY (`cause_id`)
    REFERENCES `cause` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `volunteer_event_has_cause`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `volunteer_event_has_cause` ;

CREATE TABLE IF NOT EXISTS `volunteer_event_has_cause` (
  `volunteer_event_id` INT NOT NULL,
  `cause_id` INT NOT NULL,
  PRIMARY KEY (`volunteer_event_id`, `cause_id`),
  INDEX `fk_volunteer_event_has_cause_cause1_idx` (`cause_id` ASC),
  INDEX `fk_volunteer_event_has_cause_volunteer_event1_idx` (`volunteer_event_id` ASC),
  CONSTRAINT `fk_volunteer_event_has_cause_volunteer_event1`
    FOREIGN KEY (`volunteer_event_id`)
    REFERENCES `volunteer_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_volunteer_event_has_cause_cause1`
    FOREIGN KEY (`cause_id`)
    REFERENCES `cause` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS volunteer@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'volunteer'@'localhost' IDENTIFIED BY 'volunteer';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'volunteer'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, 'Broadway', 'Hanover', 'Pennsylvania', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `img_url`, `enabled`, `bio`, `address_id`) VALUES (1, 'John', 'Johnson', 'john@gmail.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'ADMIN', NULL, NULL, NULL, 1);

COMMIT;

