CREATE TABLE users (username VARCHAR(100) NOT NULL PRIMARY KEY, encoded_password VARCHAR(255));
INSERT INTO users(username, encoded_password) VALUES ('user1', ''
 /*demo*/ );
INSERT INTO users(username, encoded_password) VALUES ('user2', ''
 /*demo*/ );
ALTER TABLE customers ADD username VARCHAR(100) NOT NULL DEFAULT 'user1';
ALTER TABLE customers ADD CONSTRAINT FK_CUSTOMERS_USERNAME FOREIGN KEY (username) REFERENCES users;