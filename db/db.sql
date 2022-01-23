USE sql11465496;

CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
	isCompany TINYINT(1) NOT NULL,
	money DECIMAL(6, 2) NOT NULL
);

CREATE TABLE user_payments (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	amount DECIMAL(6, 2) NOT NULL,
	category VARCHAR(50) NOT NULL,
	date DATE NOT NULL,
	FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE is_friend (
	username1 VARCHAR (50) NOT NULL,
	username2 VARCHAR (50) NOT NULL,
	PRIMARY KEY (username1, username2),
	FOREIGN KEY (username1) REFERENCES users(username),
	FOREIGN KEY (username2) REFERENCES users(username)
);

CREATE TABLE user_categories (
	username VARCHAR(50) NOT NULL,
	category VARCHAR(50) NOT NULL,
    PRIMARY KEY(username, category),
	FOREIGN KEY (username) REFERENCES users(username) 
);

CREATE TABLE bill(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
	receiver VARCHAR(50) NOT NULL,
	payer VARCHAR(50) NOT NULL,
	amount DECIMAL(6, 2) NOT NULL,
	is_paid TINYINT(1) NOT NULL,
	FOREIGN KEY (receiver) REFERENCES users(username),
	FOREIGN KEY (payer) REFERENCES users(username)
);

CREATE TABLE _group(
	group_name VARCHAR(50) NOT NULL PRIMARY KEY,
	owner VARCHAR(50) NOT NULL,
	FOREIGN KEY (owner) REFERENCES users(username)
);

CREATE TABLE group_participants(
	group_name VARCHAR(50) NOT NULL,
    participant VARCHAR(50) NOT NULL,
    PRIMARY KEY(group_name, participant),
    FOREIGN KEY (group_name) REFERENCES _group(group_name),
    FOREIGN KEY (participant) REFERENCES users(username)
);

INSERT INTO users (username, password, isCompany, money) VALUES ('user1@user.com','pass', 0, 100.00);
INSERT INTO users (username, password, isCompany, money) VALUES ('user2@user.com','pass', 0, 100.00);
INSERT INTO users (username, password, isCompany, money) VALUES ('user3@user.com','pass', 0, 100.00);
INSERT INTO users (username, password, isCompany, money) VALUES ('user4@user.com','pass', 0, 100.00);
INSERT INTO users (username, password, isCompany, money) VALUES ('company@company.com','pass', 1, 10000.00);

INSERT INTO is_friend(username1, username2) VALUES ('user1@user.com', 'user2@user.com');
INSERT INTO is_friend(username1, username2) VALUES ('user1@user.com', 'user3@user.com');
INSERT INTO is_friend(username1, username2) VALUES ('user1@user.com', 'user4@user.com');
INSERT INTO is_friend(username1, username2) VALUES ('user2@user.com', 'user3@user.com');

INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user1@user.com', 20.00, 'Food', '2022-01-02');
INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user1@user.com', 10.00, 'Leisure', '2022-01-05');
INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user1@user.com', 80.00, 'Electricity', '2022-01-01');
INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user1@user.com', 28.50, 'Groceries', '2022-01-12');
INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user2@user.com', 15.75, 'Food', '2022-01-04');
INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user2@user.com', 20.00, 'Groceries', '2022-01-03');
INSERT INTO user_payments(id, username, amount, category, date) VALUES (uuid(), 'user2@user.com', 5.05, 'Bill', '2022-01-08');

INSERT INTO user_categories(username, category) VALUES ('user1@user.com', 'Food');
INSERT INTO user_categories(username, category) VALUES ('user1@user.com', 'Leisure');
INSERT INTO user_categories(username, category) VALUES ('user1@user.com', 'Electricity');
INSERT INTO user_categories(username, category) VALUES ('user1@user.com', 'Groceries');
INSERT INTO user_categories(username, category) VALUES ('user1@user.com', 'Water');

INSERT INTO bill(id, receiver, payer, amount, is_paid) VALUES (uuid(), 'user1@user.com', 'user2@user.com', 5.05, 1);
INSERT INTO bill(id, receiver, payer, amount, is_paid) VALUES (uuid(), 'user1@user.com', 'user3@user.com', 4.25, 0);
INSERT INTO bill(id, receiver, payer, amount, is_paid) VALUES (uuid(), 'user2@user.com', 'user1@user.com', 3.00, 0);
INSERT INTO bill(id, receiver, payer, amount, is_paid) VALUES (uuid(), 'user1@user.com', 'user4@user.com', 10.45, 1);
INSERT INTO bill(id, receiver, payer, amount, is_paid) VALUES (uuid(), 'user1@user.com', 'user2@user.com', 0.85, 0);

INSERT INTO _group(group_name, owner) VALUES ('group1', 'user1@user.com');
INSERT INTO _group(group_name, owner) VALUES ('group2', 'user1@user.com');
INSERT INTO _group(group_name, owner) VALUES ('group3', 'user2@user.com');

INSERT INTO group_participants(group_name, participant) VALUES ('group1', 'user2@user.com');
INSERT INTO group_participants(group_name, participant) VALUES ('group1', 'user3@user.com');
INSERT INTO group_participants(group_name, participant) VALUES ('group2', 'user4@user.com');
INSERT INTO group_participants(group_name, participant) VALUES ('group2', 'user2@user.com');
INSERT INTO group_participants(group_name, participant) VALUES ('group3', 'user1@user.com');
INSERT INTO group_participants(group_name, participant) VALUES ('group3', 'user4@user.com');


