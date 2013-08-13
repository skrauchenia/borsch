ALTER TABLE `borsch`.`dish` DROP FOREIGN KEY `order` ;
ALTER TABLE `borsch`.`dish` CHANGE COLUMN `order` `orderId` INT(11) DEFAULT '0'  , 
  ADD CONSTRAINT `orderId`
  FOREIGN KEY (`orderId` )
  REFERENCES `borsch`.`order` (`idOrder` );


ALTER TABLE borsch.`order` RENAME TO borsch.orders;

ALTER TABLE `borsch`.`dish` DROP FOREIGN KEY `menuItemId` ;
ALTER TABLE `borsch`.`dish` DROP COLUMN `menuItemId`
, DROP INDEX `menuItemId` ;
