
CREATE SCHEMA IF NOT EXISTS `project_management_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `project_management_db` ;

-- -----------------------------------------------------
-- Table `project_management_db`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_management_db`.`accounts` (
    `account_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    `surname` VARCHAR(30) NOT NULL,
    `patronymic` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `password` VARCHAR(150) NOT NULL,
    `role` VARCHAR(20) NOT NULL,
    `photo` BLOB NULL DEFAULT NULL,
    PRIMARY KEY (`account_id`),
    UNIQUE INDEX `email_unique_idx` (`email` ASC) INVISIBLE,
    INDEX `full_name_idx` (`surname` ASC, `name` ASC, `patronymic` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 49
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`actions` (
    `action_id` INT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(20) NOT NULL,
    `date_time` TIMESTAMP NOT NULL,
    `reporter` INT NOT NULL,
    PRIMARY KEY (`action_id`),
    INDEX `fk_actions_accounts` (`reporter` ASC) VISIBLE,
    INDEX `date_time_idx` (`date_time` ASC) INVISIBLE,
    CONSTRAINT `fk_actions_accounts`
        FOREIGN KEY (`reporter`)
            REFERENCES `project_management_db`.`accounts` (`account_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 62
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`users` (
    `user_id` INT NOT NULL,
    `position` VARCHAR(45) NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `phone` VARCHAR(20) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    CONSTRAINT `fk_users_accounts`
        FOREIGN KEY (`user_id`)
            REFERENCES `project_management_db`.`accounts` (`account_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`account_actions` (
    `action_id` INT NOT NULL,
    `account_id` INT NOT NULL,
    `reason` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`action_id`, `account_id`),
    INDEX `fk_account_actions_users` (`account_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_actions_actions1`
        FOREIGN KEY (`action_id`)
            REFERENCES `project_management_db`.`actions` (`action_id`),
    CONSTRAINT `fk_account_actions_users`
        FOREIGN KEY (`account_id`)
            REFERENCES `project_management_db`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`projects` (
    `project_id` INT NOT NULL AUTO_INCREMENT,
    `project_code` VARCHAR(20) NOT NULL,
    `summary` VARCHAR(50) NOT NULL,
    `due_date` DATETIME NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`project_id`),
    INDEX `summary_idx` (`summary` ASC) INVISIBLE,
    INDEX `due_date_idx` (`due_date` ASC) INVISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 18
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`tasks` (
    `task_id` INT NOT NULL AUTO_INCREMENT,
    `task_code` VARCHAR(30) NOT NULL,
    `project_id` INT NOT NULL,
    `priority` VARCHAR(20) NOT NULL,
    `current_status` VARCHAR(20) NOT NULL,
    `due_date` DATETIME NOT NULL,
    `estimation_time` INT NOT NULL,
    `assignee` INT NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`task_id`),
    UNIQUE INDEX `task_code_idx` (`task_code` ASC) INVISIBLE,
    INDEX `fk_tasks_users_assignee` (`assignee` ASC) VISIBLE,
    INDEX `due_date_idx` (`due_date` ASC) INVISIBLE,
    INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
    CONSTRAINT `fk_tasks_projects`
        FOREIGN KEY (`project_id`)
            REFERENCES `project_management_db`.`projects` (`project_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_tasks_users_assignee`
        FOREIGN KEY (`assignee`)
            REFERENCES `project_management_db`.`users` (`user_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 26
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`attachments` (
    `attachment_id` INT NOT NULL,
    `file` BLOB NOT NULL,
    `task_id` INT NOT NULL,
    PRIMARY KEY (`attachment_id`),
    INDEX `fk_attachments_tasks` (`task_id` ASC) VISIBLE,
    CONSTRAINT `fk_attachments_tasks`
        FOREIGN KEY (`task_id`)
            REFERENCES `project_management_db`.`tasks` (`task_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`comments` (
    `comment_id` INT NOT NULL,
    `title` VARCHAR(20) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `date_time` DATETIME NOT NULL,
    `author` INT NOT NULL,
    `task_id` INT NOT NULL,
    PRIMARY KEY (`comment_id`),
    INDEX `fk_comments_tasks` (`task_id` ASC) VISIBLE,
    INDEX `fk_comments_users` (`author` ASC) VISIBLE,
    INDEX `date_time_idx` (`date_time` ASC) VISIBLE,
    CONSTRAINT `fk_comments_tasks`
        FOREIGN KEY (`task_id`)
            REFERENCES `project_management_db`.`tasks` (`task_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_comments_users`
        FOREIGN KEY (`author`)
            REFERENCES `project_management_db`.`users` (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`project_actions` (
    `action_id` INT NOT NULL,
    `project_id` INT NOT NULL,
    PRIMARY KEY (`action_id`, `project_id`),
    INDEX `fk_project_actions_projects` (`project_id` ASC) VISIBLE,
    CONSTRAINT `fk_project_actions_actions`
        FOREIGN KEY (`action_id`)
            REFERENCES `project_management_db`.`actions` (`action_id`),
    CONSTRAINT `fk_project_actions_projects`
        FOREIGN KEY (`project_id`)
            REFERENCES `project_management_db`.`projects` (`project_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `project_management_db`.`task_actions` (
    `action_id` INT NOT NULL,
    `task_id` INT NOT NULL,
    `assignee_id` INT NOT NULL,
    PRIMARY KEY (`action_id`, `task_id`),
    INDEX `fk_task_actions_tasks` (`task_id` ASC) VISIBLE,
    CONSTRAINT `fk_task_actions_actions`
        FOREIGN KEY (`action_id`)
            REFERENCES `project_management_db`.`actions` (`action_id`),
    CONSTRAINT `fk_task_actions_tasks`
        FOREIGN KEY (`task_id`)
            REFERENCES `project_management_db`.`tasks` (`task_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

USE `project_management_db`;

DELIMITER $$
USE `project_management_db`$$
CREATE
    DEFINER=`admin`@`localhost`
    TRIGGER `project_management_db`.`tasks_BEFORE_INSERT`
    BEFORE INSERT ON `project_management_db`.`tasks`
    FOR EACH ROW
BEGIN
    SET NEW.task_code = CONCAT(
            (SELECT project_code FROM `projects` WHERE `projects`.project_id = NEW.project_id), "-",
            ((SELECT count(*) FROM `tasks` WHERE `tasks`.project_id = NEW.project_id) + 1));
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
