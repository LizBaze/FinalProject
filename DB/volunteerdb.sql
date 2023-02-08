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
  `address_id` INT NULL,
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
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, '1275 First Street', 'Washington', 'D.C.', '20002');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `img_url`, `enabled`, `bio`, `address_id`) VALUES (1, 'John', 'Johnson', 'john@email.com', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'ADMIN', NULL, 1, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `organization`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `organization` (`id`, `name`, `logo`, `description`) VALUES (1, 'Peace Corps', NULL, 'The Peace Corps is an independent agency and program of the United States government that trains and deploys volunteers to provide international development assistance');

COMMIT;


-- -----------------------------------------------------
-- Data for table `volunteer_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `volunteer_event` (`id`, `name`, `description`, `created_date`, `start_date`, `end_date`, `organization_id`, `address_id`) VALUES (1, 'Teacher', 'English Teacher', '2022-01-01', '2023-01-01', '2023-04-04', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `participant`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `participant` (`user_id`, `event_id`, `description`, `img_url`, `rating`, `date_joined`) VALUES (1, 1, 'Taught english', NULL, 7, '2023-1-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `member`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `member` (`user_id`, `organization_id`, `admin`, `date_joined`) VALUES (1, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `group_message` (`id`, `description`, `date_posted`, `user_id`, `event_id`, `in_reply_to`) VALUES (1, 'Very excited for the event', '2022-02-02', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cause`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (1, 'Advocacy & Human Rights', 'Human rights are about a person-centred way of working – making sure that a person\'s views, needs and wishes are fully considered. Advocacy similarly strives to make sure that people are heard and involved in plans for their care and treatment.', 'https://www.mercyworld.org/45074/1600x1067/55a6a0c18c/human-trafficking-word-cloud-510860690_4500x3000.jpeg?q=60&w=1000&lossless=1&fm=jpg&ixlib=js-1.1.1&s=7d48526e65bb0561d87e94f149e9c807');
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (2, 'Animals', 'Animal rights advocates want to distinguish animals from inanimate objects, as they are so often considered by exploitative industries and the law. The animal rights movement strives to make the public aware of the fact that animals are sensitive, emotional, and intelligent beings who deserve dignity and respect.', 'https://i.natgeofe.com/k/c022030e-f1aa-4ab3-ad56-fdcdd4a1d08b/125-animals-tiger.jpg');
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (3, 'Arts & Culture', 'Volunteer for culture and art projects and use all your creativity to do something meaningful whilst you travel the world. Whether you play an instrument, are gifted in arts and crafts or have a passion for photography, there are many cultural projects worldwide focusing on these topics.', 'https://sloanreview.mit.edu/wp-content/uploads/2021/04/MAG-Shantz-1290x860-1.jpg');
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (4, 'Board Development', 'Board service is a form of volunteerism that can have a huge impact on the organization, but if you think that your sense of personal fulfillment requires a more hands-on volunteer opportunity, you might want to inquire about direct-service volunteering opportunities in the organization instead of board service.', 'https://gerandaprojects.com/wp-content/uploads/2018/10/HeaderBoardDevelopment.jpg');
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (5, 'Children & Youth', '\nOur volunteer youth workers play an integral role in helping to facilitate our youth work sessions, including supporting activities such as cooking, media and music workshops, and providing general support and guidance for the young people', 'https://cms-tc.pbskids.org/parents/articles/Tips-for-Volunteering-With-Kids.jpg');
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (6, 'Community', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (7, 'Computers & Technology', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (8, 'Crisis Support', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (9, 'Disaster Relief', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (10, 'Education & Literacy', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (11, 'Emergency & Safety', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (12, 'Employment ', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (13, 'Environment', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (14, 'Faith-Based', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (15, 'Health & Medicine ', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (16, 'Homeless & Housing', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (17, 'Hunger', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (18, 'Immigrants & Refugees', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (19, 'International', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (20, 'Justice & Legal', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (21, 'LGBTQ+', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (22, 'Media & Broadcasting', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (23, 'People With Disabilities ', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (24, 'Politics', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (25, 'Race & Ethnicity ', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (26, 'Seniors', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (27, 'Sports & Recreation ', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (28, 'Veterans & Military Families', NULL, NULL);
INSERT INTO `cause` (`id`, `name`, `description`, `icon_url`) VALUES (29, 'Women', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_img`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `event_img` (`id`, `img_url`, `caption`, `volunteer_event_id`) VALUES (1, NULL, 'teaching english', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_cause`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `user_has_cause` (`user_id`, `cause_id`) VALUES (1, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `organization_has_cause`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `organization_has_cause` (`organization_id`, `cause_id`) VALUES (1, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `volunteer_event_has_cause`
-- -----------------------------------------------------
START TRANSACTION;
USE `volunteerdb`;
INSERT INTO `volunteer_event_has_cause` (`volunteer_event_id`, `cause_id`) VALUES (1, 6);

COMMIT;

