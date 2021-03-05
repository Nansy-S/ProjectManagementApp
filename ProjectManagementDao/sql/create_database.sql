
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


CREATE TABLE IF NOT EXISTS `project_management_db`.`teams` (
    `team_id` INT NOT NULL AUTO_INCREMENT,
    `team_name` VARCHAR(45) NOT NULL,
    `participants_number` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`team_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`users` (
    `user_id` INT NOT NULL,
    `position` VARCHAR(45) NOT NULL,
    `team_id` INT NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `phone` VARCHAR(20) NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`account_actions` (
    `account_action_id` INT NOT NULL AUTO_INCREMENT,
    `admin_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `reason` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`account_action_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`projects` (
    `project_code` VARCHAR(20) NOT NULL,
    `summary` VARCHAR(50) NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME NOT NULL,
    `due_date` DATETIME NOT NULL,
    `status` VARCHAR(20) NOT NULL,
    `team_id` INT NULL,
    PRIMARY KEY (`project_code`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`tasks` (
    `task_code` INT NOT NULL AUTO_INCREMENT,
    `project_code` VARCHAR(20) NOT NULL,
    `priority` VARCHAR(20) NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `due_date` DATETIME NOT NULL,
    `estimation_time` INT NOT NULL,
    `assignee` INT NOT NULL,
    `reporter` INT NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`task_code`, `project_code`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`task_actions` (
    `task_action_id` INT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `reporter_action` INT NOT NULL,
    `task_code` INT NULL,
    `project_code` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`task_action_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`comments` (
    `comment_id` INT NOT NULL,
    `title` VARCHAR(20) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `project_code` VARCHAR(20) NOT NULL,
    `task_code` INT NOT NULL,
    `author` INT NOT NULL,
    PRIMARY KEY (`comment_id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `project_management_db`.`attachments` (
    `attachment_id` INT NOT NULL,
    `file` BLOB NOT NULL,
    `task_code` INT NOT NULL,
    `project_code` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`attachment_id`)
) ENGINE = InnoDB;