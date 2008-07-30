-- 
-- 9/4/2007

CREATE TABLE user_entity_links (
	id 				int(11) NOT NULL auto_increment,
	user_id			int(11) NOT NULL,
	link_id			int(11) NOT NULL,
	created_on  	DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

-- Add the botrover users

insert into core_users(user_name, user_password, user_email, date_of_birth, account_number, active_code, last_login_success, last_login_failure, created_on, updated_on) VALUES(
    'botrover99', 'fa911d71b19af603c0f8a10455a670ef', 'botrover99@email.com', '1981-01-01', '55ebef6779f025f9174478e6abcd3874botrover99', 1, NOW(), NOW(), NOW(), NOW());

insert into core_users(user_name, user_password, user_email, date_of_birth, account_number, active_code, last_login_success, last_login_failure, created_on, updated_on) VALUES(
    'botbert99', 'fa911d71b19af603c0f8a10455a670ef', 'botbert99@email.com', '1981-01-01', 'b2acafa3c71be70c5aed5aedad365342botbert99', 1, NOW(), NOW(), NOW(), NOW());

-- Update the entity_links

update entity_links a
 	set user_id = (select id from core_users b 
 	where a.full_name = b.user_name);

-- Insert into user links

insert into user_entity_links(user_id, link_id, created_on) 
	select user_id, id, NOW() from entity_links limit 0, 200;

-- End of File