INSERT INTO `Spotitube`.`users`
(`username`,`password`)
VALUES
("otisvdm","pass");

INSERT INTO `Spotitube`.`playlists`
(`name`, `owner`)
VALUES
("Heavy Metal", true), ("Pop", false);

INSERT INTO `Spotitube`.`tracks`
(`title`,`performer`,`duration`,`album`,`playcount`,`publicationDate`,`description`,`offlineAvailable`)
VALUES
("leukmuziekje","otis",210,"eerstemuziekjesvanmij",2,'1998-10-12',"MOEZIEEEEEK",false),
("gekkeTJUNES","Daan",354,"daansshizzle",NULL,'2001-10-05',"egwel van daan",true),
("leukmuziekjeDEELTWEE","otis",146,"eerstemuziekjesvanmij",3,'2005-4-17',"MOEZIEEEEEK... ENZO",false),
("dezegeluidjes",NULL,100,"album",7,'1903-5-07',"Kappa",false),
("nogmeerdingen?","iemand",210,"ALBOEM",5,'1967-9-14',"oke",false),
("muziekisdit",NULL,460,"hoi",NULL,'1988-6-23',"leesditbelangrijk",true);

INSERT INTO `Spotitube`.`trackInPlaylist`
(`playlistId`,`trackId`)
VALUES
(1,1),
(1,4),
(1,6),
(1,2),
(1,3),
(2,1),
(2,5),
(2,6);