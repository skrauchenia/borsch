CREATE SCHEMA `borsch` DEFAULT CHARACTER SET utf8 ;

USE `borsch`;

CREATE  TABLE `borsch`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(255) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `needEmailNotification` TINYINT(1) NOT NULL ,
  `accessRights` VARCHAR(255) NOT NULL ,
  `locale` VARCHAR(10) NOT NULL DEFAULT "ru_RU",
  PRIMARY KEY (`idUser`) ,
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) );

CREATE  TABLE `borsch`.`Orders` (
  `idOrder` INT NOT NULL AUTO_INCREMENT ,
  `startDate` DATETIME NOT NULL ,
  `endDate` DATETIME NOT NULL ,
  `owner` INT ,
  PRIMARY KEY (`idOrder`) ,
  UNIQUE INDEX `owner_idx` (`owner` ASC) ,
  CONSTRAINT `owner`
    FOREIGN KEY (`owner` )
    REFERENCES `borsch`.`user` (`idUser` ));

CREATE  TABLE `borsch`.`MenuItem` (
  `idMenuItem` INT NOT NULL AUTO_INCREMENT ,
  `date` DATETIME NOT NULL ,
  `isPaid` TINYINT(1) NOT NULL ,
  `idOrder` INT ,
  PRIMARY KEY (`idMenuItem`) ,
  UNIQUE INDEX `idMenuItem_UNIQUE` (`idMenuItem` ASC) ,
  CONSTRAINT `idOrder`
    FOREIGN KEY (`idOrder` )
    REFERENCES `borsch`.`orders` (`idOrder` ));

CREATE  TABLE `borsch`.`PriceList` (
  `idPriceList` INT NOT NULL AUTO_INCREMENT ,
  `creationTime` DATETIME NOT NULL ,
  `expirationTime` DATETIME NOT NULL ,
  PRIMARY KEY (`idPriceList`) ,
  UNIQUE INDEX `idPriceList_UNIQUE` (`idPriceList` ASC) );

CREATE  TABLE `borsch`.`Dish` (
  `idDish` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `photoUrl` VARCHAR(255) NOT NULL ,
  `price` INT NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `course` VARCHAR(255) NOT NULL ,
  `menuItemId` INT DEFAULT 0,
  `priceList` INT DEFAULT 0 ,
  PRIMARY KEY (`idDish`) ,
  UNIQUE INDEX `idDish_UNIQUE` (`idDish` ASC), 
  CONSTRAINT `order`
    FOREIGN KEY (`menuItemId` )
    REFERENCES `borsch`.`orders` (`idOrder` ),
  CONSTRAINT `priceList`
    FOREIGN KEY (`priceList` )
    REFERENCES `borsch`.`PriceList` (`idPriceList` ));

CREATE TABLE `borsch`.`OrderChanges` (
	`changeId` INT NOT NULL AUTO_INCREMENT ,
	`changedDishId` INT ,
	`actedUserId` INT ,
	`menuItemId` INT ,
	`dateOfChange` DATETIME NOT NULL ,	
	`startOfWeek` DATETIME NOT NULL ,
	`committedAction` VARCHAR(255) NOT NULL ,
	PRIMARY KEY (`changeId`) ,
	UNIQUE INDEX `changeId_UNIQUE` (`changeId` ASC) ,
	CONSTRAINT `changedDishId`
		FOREIGN KEY (`changedDishId`)
		REFERENCES `borsch`.`Dish`(`idDish`) ,
	CONSTRAINT `actedUserId`
		FOREIGN KEY (`actedUserId`)
		REFERENCES `borsch`.`User`(`idUser`) ,
	CONSTRAINT `menuItemId`
		FOREIGN KEY (`menuItemId`)
		REFERENCES `borsch`.`MenuItem`(`idMenuItem`)
);

INSERT INTO `borsch`.`User` (`login`,`name`,`email`,`needEmailNotification`,`accessRights`,`locale`) 
VALUES ("admin","Administrator The Great"
		,"borschmail@gmail.com"
		,true
		,"[ROLE_EDIT_MENU_SELF, ROLE_EDIT_MENU_OTHER, ROLE_EDIT_PRICE, ROLE_PRINT_ORDER, ROLE_EDIT_PROFILE]"
		,"en_US");
	