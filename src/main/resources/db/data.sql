-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('japarejo','super-secret',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'japarejo','geek');
INSERT INTO geeks(id,email,user_username) VALUES (1,'japarejo@gmail.com','japarejo');


INSERT INTO users(username,password,enabled) VALUES ('prueba','super-secret',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'prueba','geek');
INSERT INTO geeks(id,email,user_username) VALUES (2,'japarejo@gmail.com','prueba');


INSERT INTO artistic_cathegories(id,name) VALUES (1,'Video Games'),
												 (2,'Films'),
												 (3,'Books'),
												 (4,'Music'),
												 (5,'Places');

												 
												 
INSERT INTO artworks(id,name,sponsor,year,type_id) 
				VALUES 	(1,'Zelda Breath of the Wild', 'Nintendo',2017,1),		
				    	(2,'The lord of the rings', 'J.R.R. Tolkien',1955,3),
				    	(3,'The Empire Strikes Back','George Lucas',1980,2),
				    	(4,'Entre dos aguas','Paco de Lucía',1976,4),
				    	(5,'La Alhambra','Los omeyas',1314,5),
    					(6,'Super Mario Bros 3', 'Nintendo',1990,1);
    					
INSERT INTO geeks_favoritos(geek_id,favoritos_id) VALUES 	(1,1),  
															(1,2),
															(1,3),
															(1,4),
															(1,5),
															(1,6),
															(2,3),
															(2,4),
															(2,5);