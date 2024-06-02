CREATE DATABASE `celebbussiness` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

-- celebbussiness.auction definition

CREATE TABLE `auction` (
  `auctionID` int(11) NOT NULL AUTO_INCREMENT,
  `auctionDate` date NOT NULL,
  `offeredProperties` int(11) NOT NULL,
  `sales` int(11) NOT NULL,
  `adminID` int(11) NOT NULL,
  PRIMARY KEY (`auctionID`),
  KEY `auction_user_FK` (`adminID`),
  CONSTRAINT `auction_user_FK` FOREIGN KEY (`adminID`) REFERENCES `user` (`clientID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.bank_account definition

CREATE TABLE `bank_account` (
  `accountNumber` int(11) NOT NULL,
  `personID` int(11) NOT NULL,
  `balance` double NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `isCancelled` tinyint(1) NOT NULL,
  `isEmbargoed` tinyint(1) NOT NULL,
  `embargoedValue` double DEFAULT NULL,
  `annualInterest` double NOT NULL,
  `openDate` date NOT NULL,
  `cancelationDate` date DEFAULT NULL,
  `accountType` varchar(30) NOT NULL,
  PRIMARY KEY (`accountNumber`),
  KEY `bank_account_person_FK` (`personID`),
  CONSTRAINT `bank_account_person_FK` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.bank_transfer definition

CREATE TABLE `bank_transfer` (
  `transferID` int(11) NOT NULL AUTO_INCREMENT,
  `emiterAccount` int(11) NOT NULL,
  `receptorAccount` int(11) NOT NULL,
  `transferAmount` double NOT NULL,
  `emiterInitialBalance` double NOT NULL,
  `emiterFinalBalance` double NOT NULL,
  `receptorInitialBalance` double NOT NULL,
  `receptorFinalBalance` double NOT NULL,
  `transferDate` datetime NOT NULL,
  PRIMARY KEY (`transferID`),
  KEY `bank_transfer_bank_account_FK` (`emiterAccount`),
  KEY `bank_transfer_bank_account_FK_1` (`receptorAccount`),
  CONSTRAINT `bank_transfer_bank_account_FK` FOREIGN KEY (`emiterAccount`) REFERENCES `bank_account` (`accountNumber`),
  CONSTRAINT `bank_transfer_bank_account_FK_1` FOREIGN KEY (`receptorAccount`) REFERENCES `bank_account` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.bill definition

CREATE TABLE `bill` (
  `billNumber` int(11) NOT NULL,
  `contractID` int(11) NOT NULL,
  `basePrice` double NOT NULL,
  `taxes` double NOT NULL,
  `comisions` double NOT NULL,
  `fines` double DEFAULT NULL,
  `billDate` date NOT NULL,
  PRIMARY KEY (`billNumber`),
  KEY `bill_sale_contract_FK` (`contractID`),
  CONSTRAINT `bill_sale_contract_FK` FOREIGN KEY (`contractID`) REFERENCES `sale_contract` (`contractID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.person definition

CREATE TABLE `person` (
  `personID` int(11) NOT NULL AUTO_INCREMENT,
  `document` varchar(20) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `secondName` varchar(30) DEFAULT NULL,
  `lastName1` varchar(30) NOT NULL,
  `lastName2` varchar(30) DEFAULT NULL,
  `birthPlace` varchar(30) NOT NULL,
  `hometown` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `cellPhoneNumber` varchar(20) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `registrationDate` date NOT NULL,
  PRIMARY KEY (`personID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.property definition

CREATE TABLE `property` (
  `propertyID` int(11) NOT NULL,
  `countryLocation` varchar(50) NOT NULL,
  `stateLocation` varchar(50) NOT NULL,
  `cityLocation` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `owner` int(11) NOT NULL,
  `onSale` tinyint(1) NOT NULL,
  `valuation` double NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`propertyID`),
  KEY `property_person_FK` (`owner`),
  CONSTRAINT `property_person_FK` FOREIGN KEY (`owner`) REFERENCES `person` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.sale_contract definition

CREATE TABLE `sale_contract` (
  `contractID` int(11) NOT NULL,
  `propertyID` int(11) NOT NULL,
  `sellPrice` double NOT NULL,
  `sellerID` int(11) NOT NULL,
  `buyerID` int(11) NOT NULL,
  `contractDate` date NOT NULL,
  `paymentDate` date,
  `auctionNumber` int(11) DEFAULT NULL,
  `contractState` varchar(3) NOT NULL,
  `fulfillmentDate` date NOT NULL,
  PRIMARY KEY (`contractID`),
  KEY `sale_contract_property_FK` (`propertyID`),
  KEY `sale_contract_person_FK` (`sellerID`),
  KEY `sale_contract_person_FK_1` (`buyerID`),
  KEY `sale_contract_auction_FK` (`auctionNumber`),
  CONSTRAINT `sale_contract_auction_FK` FOREIGN KEY (`auctionNumber`) REFERENCES `auction` (`auctionID`),
  CONSTRAINT `sale_contract_person_FK` FOREIGN KEY (`sellerID`) REFERENCES `person` (`personID`),
  CONSTRAINT `sale_contract_person_FK_1` FOREIGN KEY (`buyerID`) REFERENCES `person` (`personID`),
  CONSTRAINT `sale_contract_property_FK` FOREIGN KEY (`propertyID`) REFERENCES `property` (`propertyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


-- celebbussiness.`transaction` definition

CREATE TABLE `transaction` (
  `transactionID` int(11) NOT NULL AUTO_INCREMENT,
  `bankAccount` int(11) NOT NULL,
  `transactionDate` datetime NOT NULL,
  `transactionAmount` double NOT NULL,
  `initialBalance` double NOT NULL,
  `finalBalance` double NOT NULL,
  `transactionType` varchar(20) NOT NULL,
  PRIMARY KEY (`transactionID`),
  KEY `transaction_bank_account_FK` (`bankAccount`),
  CONSTRAINT `transaction_bank_account_FK` FOREIGN KEY (`bankAccount`) REFERENCES `bank_account` (`accountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.`user` definition

CREATE TABLE `user` (
  `clientID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `isCelebrity` tinyint(1) NOT NULL,
  `hasBankAccount` tinyint(1) NOT NULL,
  `personID` int(11) NOT NULL,
  `userType` varchar(30) NOT NULL,
  PRIMARY KEY (`clientID`),
  KEY `user_client_person_FK` (`personID`),
  CONSTRAINT `user_client_person_FK` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE `bid`(
  `bidID` int NOT NULL AUTO_INCREMENT,
  `auctionID` int NOT NULL,
  `offererID` int NOT NULL,
  `bidValue` double NOT NULL,
  `message` text,
  PRIMARY KEY (`bidID`),
  CONSTRAINT `bid_auction_FK`, FOREIGN KEY (`auctionID`) REFERENCES `auction` (`auctionID`),
  CONSTRAINT `bid_user_FK` , FOREIGN KEY (`offererID`) REFERENCES `user` (`clientID`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.portfolio definition

CREATE TABLE `portfolio` (
  `portfolioID` int(11) NOT NULL AUTO_INCREMENT,
  `unitValue` double NOT NULL,
  `commision` double NOT NULL,
  `portfolioName` varchar(50) NOT NULL,
  `riskGrade` int(11) NOT NULL,
  PRIMARY KEY (`portfolioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- celebbussiness.inversion_account definition

CREATE TABLE `inversion_account` (
  `inversionNumber` int(11) NOT NULL AUTO_INCREMENT,
  `inversionType` varchar(30) NOT NULL,
  `balance` double NOT NULL,
  `openDate` date NOT NULL,
  `dueDate` date DEFAULT NULL,
  `accumulatedProfit` double NOT NULL,
  `chargedProfitOnTaxes` double NOT NULL,
  `isEmbargoed` tinyint(1) NOT NULL,
  `embargoedValue` double DEFAULT NULL,
  `portfolioID` int(11) NOT NULL,
  `personID` int(11) NOT NULL,
  PRIMARY KEY (`inversionNumber`),
  KEY `inversion_account_portfolio_FK` (`portfolioID`),
  KEY `inversion_account_person_FK` (`personID`),
  CONSTRAINT `inversion_account_person_FK` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`),
  CONSTRAINT `inversion_account_portfolio_FK` FOREIGN KEY (`portfolioID`) REFERENCES `portfolio` (`portfolioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
 