--
-- Admin Settings

-- Insert example users
-- To encode the password, see the ViewMD5Encoding class

INSERT INTO users VALUES('berlinbrown','55bbf4f03036d6642f935c6a53795372', TRUE);

INSERT INTO authorities VALUES ('berlinbrown', 'ROLE_TELLER');
INSERT INTO authorities VALUES ('berlinbrown', 'ROLE_SUPERVISOR');

insert into core_users(user_name, user_password, user_email, date_of_birth, account_number, active_code, last_login_success, last_login_failure, created_on, updated_on) VALUES(
    'berlinbrown', 'c5084a613255f920e3be35e5366a94a8', 'berlinbrown_at_gmail.com', '1981-01-01', '68f150baab8c4439758bb33549ccd2a2berlinbrown', 1, NOW(), NOW(), NOW(), NOW());
insert into profile_settings(user_id, created_on) values(LAST_INSERT_ID(), NOW());

-- End of Insert Data