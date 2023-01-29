CREATE TABLE user_identification( 
userID INT,
username VARCHAR (16) UNIQUE,
CONSTRAINT user_identification_pk PRIMARY KEY (userID)
);

CREATE TABLE login_credentials(
userID INT,
u_password VARCHAR (60),
FOREIGN KEY (userID) REFERENCES user_identification (userID) ON DELETE CASCADE 
);

CREATE TABLE application_errors (
error_count INT,
error_message VARCHAR(255),
CONSTRAINT application_errors_pk PRIMARY KEY (error_count)
);

CREATE TABLE user_information(
userID INT,
email VARCHAR(60),
birthdate VARCHAR(40),
picture_link VARCHAR (200),
user_desc VARCHAR(255),
FOREIGN KEY (userID) REFERENCES user_identification (userID) ON DELETE CASCADE
);

--SEQUENECES
CREATE SEQUENCE user_identification_seq START WITH 1;
--CREATE SEQUENCE login_credentials_seq START WITH 1: --login id inherted from user_identification.userID
--CREATE SEQUENCE user_information_seq START WITH 1; --login id inherted from user_identification.userID
CREATE SEQUENCE application_errors_seq START WITH 1;

--TRIGGER
CREATE OR REPLACE TRIGGER user_identification_trig 
BEFORE INSERT ON user_identification
FOR EACH ROW
	BEGIN
		SELECT user_identification_seq.NEXTVAL
				INTO :new.userID
		FROM dual;
	END;
/

CREATE OR REPLACE TRIGGER application_errors_trig 
BEFORE INSERT ON application_errors
FOR EACH ROW
	BEGIN
		SELECT application_errors_seq.NEXTVAL
		INTO :new.error_count
		FROM
		dual;
	END;
/

CREATE OR REPLACE FUNCTION get_userID (usernameb IN VARCHAR)
RETURN INT IS 
return_userID INT;
	BEGIN
		SELECT userID
		INTO return_userID
		FROM user_identification
		WHERE user_identification.username = usernameb;
		RETURN (return_userID);
	END;
/

--PROCEDURES
CREATE OR REPLACE PROCEDURE delete_user (user_name VARCHAR ) AS
	BEGIN
	-- delete specified user
	DELETE
	FROM user_identification
	WHERE user_identification.username = user_name;
	END;
/

--Views for low authorization users
CREATE VIEW user_identification_view AS
SELECT
	userID,
	username
FROM    
    user_identification WITH READ ONLY;
--
CREATE VIEW login_credentials_view AS 
SELECT
	userID,
	u_password
FROM   
    login_credentials WITH READ ONLY;
--
CREATE VIEW application_errors_view AS 
SELECT
	error_count,
	error_message
FROM
   application_errors WITH READ ONLY;
--
CREATE VIEW user_information_view AS
SELECT
	userID,
	email,
	birthdate,
	picture_link,
	user_desc
FROM
    user_information WITH READ ONLY;

--ROLE for low authorization users
CREATE ROLE view_user_information;
GRANT SELECT ON user_identification_view TO view_user_information; 
GRANT SELECT ON login_credentials_view TO view_user_information; 
GRANT SELECT ON application_errors_view TO view_user_information; 
GRANT SELECT ON user_information_view TO view_user_information;

--for user that owns these objects
--SELECT * FROM user_identification; 
--SELECT * FROM login_credentials;
--SELECT * FROM user_information; 
--SELECT * FROM application_errors;

--DROP ALL DATABASE INFORMATION  
/*
DROP TABLE login_credentials; 
DROP TABLE application_errors; 
DROP TABLE user_information;
DROP TABLE user_identification; 
DROP SEQUENCE user_identification_seq;  
--DROP SEQUENCE login_credentials_seq; 
--DROP SEQUENCE user_information_seq; 
DROP SEQUENCE application_errors_seq;
DROP FUNCTION get_userID;
DROP ROLE view_user_information;
DROP VIEW user_identification_view; 
DROP VIEW login_credentials_view; 
DROP VIEW application_errors_view; 
DROP VIEW user_information_view; */