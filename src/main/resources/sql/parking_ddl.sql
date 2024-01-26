-- Creating parking_floor table query
CREATE TABLE `parking_management_db`.`parking_floor` (
  `floor_id` VARCHAR(256) NOT NULL,
  `lot_id` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`floor_id`),
  UNIQUE INDEX `floor_id_UNIQUE` (`floor_id` ASC) VISIBLE,
  CONSTRAINT `lot_id`
    FOREIGN KEY lot_id_fk (lot_id)
    REFERENCES `parking_management_db`.`parking_lot` (lot_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- parking_spot create table query
CREATE TABLE `parking_management_db`.`parking_spot` (
  `spot_id` VARCHAR(256) NOT NULL,
  `floor_id` VARCHAR(128) NOT NULL,
  `lot_id` VARCHAR(256) NOT NULL,
  `parking_spot_type` ENUM('HANDICAPPED', 'COMPACT', 'LARGE', 'MOTORBIKE', 'ELECTRIC') NOT NULL,
  `is_free` TINYINT NULL,
  PRIMARY KEY (`spot_id`),
  UNIQUE INDEX `spot_id_UNIQUE` (`spot_id` ASC) VISIBLE,
  INDEX `lot_id_idx` (`lot_id` ASC) VISIBLE,
  INDEX `floor_id_idx` (`floor_id` ASC) VISIBLE,
  CONSTRAINT `floor_id`
    FOREIGN KEY (`floor_id`)
    REFERENCES `parking_management_db`.`parking_floor` (`floor_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `lot_id`
    FOREIGN KEY (`lot_id`)
    REFERENCES `parking_management_db`.`parking_lot` (`lot_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

  --UPDATED QUERY ON 23JAN
   ALTER TABLE `parking_management_db`.`parking_spot`
   DROP FOREIGN KEY `floor_id`,
   DROP FOREIGN KEY `lot_id`;
   ALTER TABLE `parking_management_db`.`parking_spot`
   DROP COLUMN `isfree`,
   CHANGE COLUMN `parking_spot_type` `parking_spot_type` ENUM('HANDICAPPED', 'COMPACT', 'LARGE', 'MOTORBIKE', 'ELECTRIC') NOT NULL ,
   CHANGE COLUMN `is_free` `is_free` TINYINT NOT NULL ;
   ALTER TABLE `parking_management_db`.`parking_spot`
   ADD CONSTRAINT `floor_id`
     FOREIGN KEY (`floor_id`)
     REFERENCES `parking_management_db`.`parking_floor` (`floor_id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
   ADD CONSTRAINT `lot_id`
     FOREIGN KEY (`lot_id`)
     REFERENCES `parking_management_db`.`parking_lot` (`lot_id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE;

--Account query table
CREATE TABLE `parking_management_db`.`account` (
  `user_name` VARCHAR(250) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `account_status` ENUM('ACTIVE', 'CLOSED', 'CANCELED', 'BLACKLISTED', 'NONE') NOT NULL,
  PRIMARY KEY (`user_name`));

----Vehicle query table
CREATE TABLE `parking_management_db`.`vehicle` (
  `licence_number` VARCHAR(450) NOT NULL,
  `vehicle_type` ENUM('CAR', 'TRUCK', 'ELECTRIC', 'VAN', 'MOTORBIKE') NOT NULL,
  PRIMARY KEY (`licence_number`));


----TICKET query table
ALTER TABLE `parking_management_db`.`parking_ticket`
CHANGE COLUMN `end_time` `end_time` DATETIME(6) NOT NULL ,
CHANGE COLUMN `start_time` `start_time` DATETIME(6) NOT NULL ,
CHANGE COLUMN `ticket_status` `ticket_status` ENUM('ACTIVE', 'PAID', 'LOST') NOT NULL ;

----Parking TICKET query table V1
ALTER TABLE `parking_management_db`.`parking_ticket`
ADD COLUMN `vehicle_type` ENUM('CAR', 'TRUCK', 'ELECTRIC', 'VAN', 'MOTORBIKE') NOT NULL AFTER `ticket_status`;

-----Parking TICKET query table V2
ALTER TABLE `parking_management_db`.`parking_ticket`
CHANGE COLUMN `end_time` `end_time` DATETIME(6) NULL ;
ALTER TABLE `parking_management_db`.`parking_ticket`
CHANGE COLUMN `ticket_amount` `ticket_amount` DOUBLE NULL ;

-----Parking rate query table V1
ALTER TABLE `parking_management_db`.`parking_rate`
DROP COLUMN `hours`,
ADD COLUMN `parking_spot_type` ENUM('HANDICAPPED', 'COMPACT', 'LARGE', 'MOTORBIKE', 'ELECTRIC') NOT NULL AFTER `hourly_rate`;


----Parking ticket query v2
ALTER TABLE `parking_management_db`.`parking_ticket`
DROP COLUMN `vehicle_type`;


-- DDL update for parking_ticket to include spot_id and vehicle_number
ALTER TABLE `parking_management_db`.`parking_ticket`
ADD COLUMN `spot_id` VARCHAR(256) NOT NULL AFTER `ticket_status`,
ADD COLUMN `vehicle_number` VARCHAR(45) NOT NULL AFTER `spot_id`,
ADD INDEX `spot_id_idx` (`spot_id` ASC) VISIBLE;
;
ALTER TABLE `parking_management_db`.`parking_ticket`
ADD CONSTRAINT `spot_id`
  FOREIGN KEY (`spot_id`)
  REFERENCES `parking_management_db`.`parking_spot` (`spot_id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;
