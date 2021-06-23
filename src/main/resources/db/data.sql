-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('japarejo','super-secret',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'japarejo','admin');