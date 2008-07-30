-- Berlin Brown
--
-- Simple changes related to add hostname to entity links
-- 8/20/2007

alter table entity_links ADD COLUMN hostname varchar(128);

alter table system_feed_items ADD COLUMN hostname varchar(128);

--
-- Also include 'search_query_filters' in this patch
-- This gives user the ability to save and watch popular searches
CREATE TABLE search_query_filters (
	id 					int(11) NOT NULL auto_increment,
	search_term 		varchar(60) NOT NULL,
	description			varchar(128) NOT NULL,
	rating				int(11) NOT NULL,
	views				int(11) NOT NULL,
	user_name 			varchar(50) NOT NULL,
	user_id				int(11) NOT NULL,
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

--
-- Developer users are allowed to 
-- use the private api for their own use
-- Created: 8/20/2007
CREATE TABLE developer_users (
	id   			int(11) NOT NULL auto_increment,
	user_name 		VARCHAR(50) NOT NULL UNIQUE,
	user_id			int(11) NOT NULL,
	key_id 			VARCHAR(128) NOT NULL,
	enabled 		CHAR(1) NOT NULL DEFAULT 'N',
	description		VARCHAR(255) NOT NULL,
	last_application	VARCHAR(30) NOT NULL,
	last_login_success DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	last_login_failure DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	updated_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);