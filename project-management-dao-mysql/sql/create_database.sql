
CREATE SCHEMA IF NOT EXISTS `project_management_db` DEFAULT CHARACTER SET utf8 ;
USE `project_management_db` ;


CREATE TABLE IF NOT EXISTS `project_management_db`.`accounts` (
    `account_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `surname` VARCHAR(30) NOT NULL,
    `patronymic` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    `photo` BLOB NULL,
    PRIMARY KEY (`account_id`)
) ENGINE = InnoDB;

CREATE UNIQUE INDEX `email_unique_idx` ON `project_management_db`.`accounts` (`email` ASC) INVISIBLE;
CREATE INDEX `full_name_idx` ON `project_management_db`.`accounts` (`surname` ASC, `name` ASC, `patronymic` ASC) VISIBLE;


CREATE TABLE IF NOT EXISTS `project_management_db`.`users` (
    `user_id` INT NOT NULL,
    `position` VARCHAR(45) NOT NULL,
    `team_id` INT NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `phone` VARCHAR(20) NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`actions` (
    `action_id` INT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `reporter` INT NOT NULL,
    PRIMARY KEY (`action_id`)
) ENGINE = InnoDB;

CREATE INDEX `date_time_idx` ON `project_management_db`.`actions` (`date_time` ASC) INVISIBLE;


CREATE TABLE IF NOT EXISTS `project_management_db`.`projects` (
    `project_id` INT NOT NULL AUTO_INCREMENT,
    `project_code` VARCHAR(20) NOT NULL,
    `summary` VARCHAR(50) NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME NOT NULL,
    `due_date` DATETIME NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `team_id` INT NULL,
    PRIMARY KEY (`project_id`)
) ENGINE = InnoDB;

CREATE INDEX `summary_idx` ON `project_management_db`.`projects` (`summary` ASC) INVISIBLE;
CREATE INDEX `due_date_idx` ON `project_management_db`.`projects` (`due_date` ASC) INVISIBLE;


CREATE TABLE IF NOT EXISTS `project_management_db`.`tasks` (
    `task_id` INT NOT NULL AUTO_INCREMENT,
    `task_code` VARCHAR(30) NOT NULL,
    `project_id` INT NOT NULL,
    `priority` VARCHAR(20) NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `due_date` DATETIME NOT NULL,
    `estimation_time` INT NOT NULL,
    `reporter` INT NOT NULL,
    `assignee` INT NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`task_id`)
) ENGINE = InnoDB;

CREATE UNIQUE INDEX `task_code_idx` ON `project_management_db`.`tasks` (`task_code` ASC) INVISIBLE;
CREATE INDEX `due_date_idx` ON `project_management_db`.`tasks` (`due_date` ASC) INVISIBLE;
CREATE INDEX `project_id_idx` ON `project_management_db`.`tasks` (`project_id` ASC) VISIBLE;


CREATE TABLE IF NOT EXISTS `project_management_db`.`task_actions` (
    `action_id` INT NOT NULL,
    `task_id` INT NOT NULL,
    PRIMARY KEY (`action_id`, `task_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`comments` (
    `comment_id` INT NOT NULL,
    `title` VARCHAR(20) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `author` INT NOT NULL,
    `task_id` INT NOT NULL,
    PRIMARY KEY (`comment_id`)
) ENGINE = InnoDB;

CREATE INDEX `date_time_idx` ON `project_management_db`.`comments` (`date_time` ASC) VISIBLE;


CREATE TABLE IF NOT EXISTS `project_management_db`.`attachments` (
    `attachment_id` INT NOT NULL,
    `file` BLOB NOT NULL,
    `task_id` INT NOT NULL,
    PRIMARY KEY (`attachment_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`project_actions` (
    `action_id` INT NOT NULL,
    `project_id` INT NOT NULL,
    PRIMARY KEY (`action_id`, `project_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`account_actions` (
    `action_id` INT NOT NULL,
    `account_id` INT NOT NULL,
    `reason` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`action_id`, `account_id`)
) ENGINE = InnoDB;

USE `project_management_db`;

DELIMITER $$
USE `project_management_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `project_management_db`.`tasks_BEFORE_INSERT` BEFORE INSERT ON `tasks` FOR EACH ROW
BEGIN
	SET NEW.task_code = CONCAT(
		(SELECT project_code FROM `projects` WHERE `projects`.project_id = NEW.project_id), "-",
        ((SELECT count(*) FROM `tasks` WHERE `tasks`.project_id = NEW.project_id) + 1));
END$$


DELIMITER ;