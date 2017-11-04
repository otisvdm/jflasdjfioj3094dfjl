DROP DATABASE Spotitube;
CREATE DATABASE Spotitube;

CREATE TABLE `Spotitube`.`users` (
`username` VARCHAR(10) NOT NULL,
`password` VARCHAR(10) NOT NULL,
PRIMARY KEY (`username`));

CREATE TABLE `Spotitube`.`playlists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `owner` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `Spotitube`.`tracks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `performer` VARCHAR(255) NULL,
  `duration` INT(4) NOT NULL,
  `album` VARCHAR(255) NULL,
  `playcount` INT(4) NULL,
  `publicationDate` DATE NULL,
  `description` VARCHAR(510) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `Spotitube`.`trackInPlaylist` (
  `trackId` INT NOT NULL,
  `playlistId` INT NOT NULL,
  `offlineAvailable` BOOLEAN NOT NULL,
  PRIMARY KEY(`trackId`,`playlistId`),
  FOREIGN KEY(`playlistId`) REFERENCES `playlists`(`id`),
  FOREIGN KEY(`trackId`) REFERENCES `tracks`(`id`));
  