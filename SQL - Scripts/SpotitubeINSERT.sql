INSERT INTO `Spotitube`.`users`
(`username`,`password`)
VALUES
("otisvdm","pass");

INSERT INTO `Spotitube`.`playlists`
(`name`, `owner`)
VALUES
("Heavy Metal", true), ("Pop", false);

INSERT INTO `Spotitube`.`tracks`
(`title`,`performer`,`duration`,`album`,`playcount`,`publicationDate`,`description`)
VALUES
("leukmuziekje","otis",210,"eerstemuziekjesvanmij",2,'1998-10-12',"MOEZIEEEEEK"),
("gekkeTJUNES","Daan",354,"daansshizzle",NULL,'2001-10-05',"egwel van daan"),
("leukmuziekjeDEELTWEE","otis",146,"eerstemuziekjesvanmij",3,'2005-4-17',"MOEZIEEEEEK... ENZO"),
("dezegeluidjes",NULL,100,"album",7,'1903-5-07',"Kappa"),
("nogmeerdingen?","iemand",210,"ALBOEM",5,'1967-9-14',"oke"),
("muziekisdit",NULL,460,"hoi",NULL,'1988-6-23',"leesditbelangrijk");

INSERT INTO `Spotitube`.`trackInPlaylist`
(`playlistId`,`trackId`,`offlineAvailable`)
VALUES
(1,1,true),
(1,4,false),
(1,6,false),
(1,2,false),
(1,3,true),
(2,1,false),
(2,5,true),
(2,6,true);