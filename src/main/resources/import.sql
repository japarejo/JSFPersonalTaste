INSERT INTO users(username,password,enabled) VALUES ('japarejo','super-secret',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'japarejo','geek');
INSERT INTO geeks(id,email,user_username) VALUES (1,'japarejo@gmail.com','japarejo');


INSERT INTO users(username,password,enabled) VALUES ('prueba','super-secret',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'prueba','geek');
INSERT INTO geeks(id,email,user_username) VALUES (2,'japarejo@gmail.com','prueba');


INSERT INTO artistic_cathegories(id,name) VALUES (1,'Video Games');
INSERT INTO artistic_cathegories(id,name) VALUES (2,'Films');
INSERT INTO artistic_cathegories(id,name) VALUES (3,'Books');
INSERT INTO artistic_cathegories(id,name) VALUES (4,'Music');
INSERT INTO artistic_cathegories(id,name) VALUES (5,'Places');

												 
												 
INSERT INTO artworks(id,name,sponsor,year,type_id) VALUES (1,'Zelda Breath of the Wild', 'Nintendo',2017,1);		
INSERT INTO artworks(id,name,sponsor,year,type_id) VALUES (2,'The lord of the rings', 'J.R.R. Tolkien',1955,3);
INSERT INTO artworks(id,name,sponsor,year,type_id) VALUES (3,'The Empire Strikes Back','George Lucas',1980,2);
INSERT INTO artworks(id,name,sponsor,year,type_id) VALUES (4,'Entre dos aguas','Paco de Lucía',1976,4);
INSERT INTO artworks(id,name,sponsor,year,type_id) VALUES (5,'La Alhambra','Los omeyas',1314,5);
INSERT INTO artworks(id,name,sponsor,year,type_id) VALUES (6,'Super Mario Bros 3', 'Nintendo',1990,1);
    					
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (1,1);  
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (1,2);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (1,3);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (1,4);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (1,5);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (1,6);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (2,3);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (2,4);
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES (2,5);