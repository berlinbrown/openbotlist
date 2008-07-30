--*********************************************************
-- Author: Berlin Brown
--
-- Description:
-- Main sql script for creating the botlist database.
--
-- updated: 11/12/2006
--
-- file: create_tables.sql
-- see insert_tables.sql
--
-- Modifications: 
-- 2/2/2008 - sql clean-ups, made sure script is up to date.
--*********************************************************

---------------------------------
-- Create the user admin tables
---------------------------------
CREATE TABLE users (
	username VARCHAR(50) NOT NULL PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	enabled BIT NOT NULL
);

CREATE TABLE authorities (
	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL
);

ALTER TABLE authorities ADD CONSTRAINT fk_authorities_users foreign key (username) REFERENCES users(username);

-- End of Creating Admin Tables 
---------------------------------

--
-- User links is deprecated (not used, but deleting may cause issues)
CREATE TABLE user_links (
  id 				int(11) NOT NULL auto_increment,
  main_url			varchar(255) NOT NULL,
  url_title			varchar(128),
  url_description 	varchar(255),
  keywords			varchar(255),
  source			varchar(40),
  source_url		varchar(255),
  total_rating		int(11) DEFAULT 0,
  occurrence		int(11) DEFAULT 0,
  created_on		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id)
);

--
-- Keep a user log
CREATE TABLE user_visit_log (
	id 			int(11) NOT NULL auto_increment,	
	remote_host	varchar(30),
	host 		varchar(30),
	referer		varchar(255),
	user_agent	varchar(255),
	request_uri	varchar(255),
	request_page varchar(124),	
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);


--
-- City listings
CREATE TABLE city_listing (
  id            int(11) NOT NULL auto_increment,
  city_name     varchar(255) NOT NULL,
  created_on    datetime NOT NULL default '0000-00-00 00:00:00',
  city_category varchar(10) default 'MINOR',
  state_abbr    varchar(10) default NULL,
  PRIMARY KEY   (id),
  UNIQUE KEY    city_name (city_name),
  KEY city_listing_created_on_ndx (created_on)
);

--
-- PostSections
CREATE TABLE post_sections (
	id 				int(11) NOT NULL auto_increment,
	generated_id	varchar(255) NOT NULL UNIQUE,
	section_name	varchar(128) NOT NULL UNIQUE,
	created_on		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  	PRIMARY KEY (id)
);

--
-- Create a default post ad listing
-- A city has a post listing
CREATE TABLE post_listing (
  id 				int(11) NOT NULL auto_increment,
  city_id			int(11) NOT NULL,
  section_id		int(11) NOT NULL,
  category			varchar(128) NOT NULL,
  email				varchar(128) NOT NULL,  
  location			varchar(255),
  title				varchar(255) NOT NULL,
  main_url			varchar(255),
  keywords			varchar(255),
  message 			text NOT NULL,
  age				int(11) DEFAULT 0,
  created_on		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',  
  PRIMARY KEY (id),
  constraint fk_post_listing
			foreign key (city_id) references city_listing(id),
  constraint fk_post_section
  			foreign key (section_id) references post_sections(id)
);

--
-- Image Metadata associated with ad postings
-- typically, an ad listing could have 2 image uploads
CREATE TABLE post_image_metadata(
	id					int(11) NOT NULL auto_increment, 
	adlist_id			int(11) NOT NULL, 
	image_filename		varchar(255) NOT NULL UNIQUE,	
	image_filesize		int(11),
	image_originalname	varchar(255),
	created_on  		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_image_adlist
		foreign key (adlist_id) references post_listing(id)
);

-- Create the simple user blog
-- Users have user-links
-- The foreign key is attached to the 'has-a'
--
-- entity_links is currently associated with
--  'child_list_links' and 'user_comments'
CREATE TABLE entity_links (
  id                     int(11) NOT NULL auto_increment,
  main_url               varchar(255) NOT NULL,
  url_title              varchar(128) NOT NULL,
  url_description        varchar(255) default NULL,
  keywords               varchar(255) default NULL,
  views int(11)          default '0',
  created_on datetime    NOT NULL default '0000-00-00 00:00:00',
  rating int(11)         NOT NULL default '0',
  user_id int(11)        default NULL,
  full_name              varchar(128) NOT NULL,
  hostname               varchar(128) default NULL,
  process_count          int(11) NOT NULL default '0',
  updated_on             datetime default '0000-00-00 00:00:00',
  link_type              varchar(20) default NULL,
  bot_rating             decimal(5,2) default '0.00',
  generated_obj_id       varchar(60) default NULL,
  user_up_votes          int(11) default '0',
  user_down_votes        int(11) default '0',
  links_ct               int(11) default '0',
  inbound_link_ct        int(11) default '0',
  outbound_links_ct      int(11) default '0',
  image_ct               int(11) default '0',
  meta_descr_len         int(11) default '0',
  meta_keywords_len      int(11) default '0',
  meta_descr_wct         int(11) default '0',
  meta_keywords_wct      int(11) default '0',
  geo_locations_ct       int(11) default '0',
  geo_locations_found    varchar(128) default NULL,
  document_size          int(11) default '0',
  request_time           int(11) default '0',
  object_id_status       tinyint(4) default '0',
  para_tag_ct            int(11) default '0',
  PRIMARY KEY  (id),
  UNIQUE KEY main_url (main_url),
  UNIQUE KEY generated_obj_id (generated_obj_id),
  KEY entity_links_created_on_ndx (created_on),
  KEY entity_links_rating_ndx (rating),
  KEY entity_links_views_ndx (views)
);

--
-- Entity Type Foaf
-- Date: 3/14/2008
CREATE TABLE entity_type_foaf (
	-- **********************
	-- Default Entity Type Fields
	-- **********************
	id int(11)             NOT NULL auto_increment,	
    main_url               varchar(255) NOT NULL UNIQUE,
    url_title              varchar(128) NOT NULL,
    url_description        varchar(255) default NULL,
    keywords               varchar(255) default NULL,
    generated_obj_id       varchar(60)  default NULL UNIQUE,
    created_on             DATETIME NOT NULL default '0000-00-00 00:00:00',
	updated_on             datetime default '0000-00-00 00:00:00',

	-- Full name and user id (information about who created the entry) 
    full_name              varchar(80) NOT NULL,
	user_id                int(11) default NULL,
    views                  int(11) default '0',	
	rating                 SMALLINT default '0',
    process_count          SMALLINT default '0',	

	nickname               varchar(50) NOT NULL,
	foaf_name              varchar(80) NOT NULL,
    foaf_interest_descr    TEXT NOT NULL,
	foaf_mbox              varchar(255) NOT NULL,		
	foaf_page_doc_url      varchar(255) NOT NULL,
	foaf_img               varchar(255),
    friend_set_uid         varchar(60),	

	request_time           SMALLINT,
	http_status_code       varchar(10),

	date_of_birth          DATE NOT NULL DEFAULT '0000-00-00',	
	PRIMARY KEY (id)
);

-- 
-- Sub links
-- created: 1/4/2007
CREATE TABLE child_list_links (
  	id 				int(11) NOT NULL auto_increment,
	link_id			int(11),   
  	main_url		varchar(255) NOT NULL unique,
  	url_title		varchar(128) NOT NULL,  
  	keywords		varchar(255),  
  	created_on		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_child_link
		foreign key (link_id) references entity_links(id)
);

--
-- Create the forum groups for the forum discussions
CREATE TABLE forum_group (
	id			int(11) NOT NULL auto_increment,
	city_id		int(11), 
	forum_name	varchar(255) NOT NULL UNIQUE,
	forum_descr	varchar(255),
	keywords	varchar(255),
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

CREATE TABLE user_comments(
	id			int(11) NOT NULL auto_increment,  
	link_id		int(11),  
	adlist_id	int(11),  
	forum_id	int(11), 
	comment_id  int(11),
	user_id		int(11), 
	full_name	varchar(128) NOT NULL,
	email		varchar(80),
	subject		varchar(255),
	zipcode		varchar(20),
	main_url	varchar(255),
	keywords	varchar(255),
	message		TEXT NOT NULL,
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_link
		foreign key (link_id) references entity_links(id),
	constraint fk_post_listing_comments
		foreign key (adlist_id) references post_listing(id),
	constraint fk_forum_group_comments
		foreign key (forum_id) references forum_group(id)
);

--
-- Group Links - Categorize links by group
-- Added: 4/7/2007
CREATE TABLE link_groups (
	id 				int(11) NOT NULL auto_increment,
	group_name		varchar(255) NOT NULL UNIQUE,
	generated_id	varchar(255) NOT NULL UNIQUE,
	created_on		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  	PRIMARY KEY (id)
);

CREATE TABLE group_links (
  id 				int(11) NOT NULL auto_increment,
  group_id			int(11) NOT NULL, 
  main_url			varchar(255) NOT NULL unique,
  url_title			varchar(128) NOT NULL,
  url_description 	varchar(255),
  keywords			varchar(255),
  views				int(11) DEFAULT 0,
  rating			int(11) DEFAULT 0,
  created_on	DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id),
  constraint fk_group_links
			foreign key (group_id) references link_groups(id)
);

--
-- Core User Table
-- Added: 4/1/2007
CREATE TABLE core_users (
  id int(11)        NOT NULL auto_increment,
  user_name          varchar(50) NOT NULL,
  user_password      varchar(128) NOT NULL,
  user_email         varchar(255) NOT NULL,
  user_url           varchar(255) default NULL,
  location           varchar(255) default NULL,
  date_of_birth      date NOT NULL default '0000-00-00',
  experience_points  int(11) default '0',
  karma int(11)      default '0',
  secretques_code    tinyint(4) NOT NULL default '0',
  secret_answer      varchar(128) default NULL,
  account_number     varchar(128) NOT NULL,
  active_code        tinyint(4) default '0',
  failed_attempts    int(11) default '0',
  last_login_success datetime NOT NULL default '0000-00-00 00:00:00',
  last_login_failure datetime NOT NULL default '0000-00-00 00:00:00',
  created_on datetime NOT NULL default '0000-00-00 00:00:00',
  updated_on datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  UNIQUE KEY user_name (user_name),
  KEY core_users_created_on_ndx (created_on)
);

--
-- Access Control List - List of access levels
CREATE TABLE acl_control (
  id                int(11) NOT NULL auto_increment,
  control_uid       varchar(128) NOT NULL,
  control_name      varchar(50) NOT NULL,
  short_descr       varchar(50) NOT NULL,
  long_descr        varchar(255) default NULL,
  created_on        datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

--
-- Group Control List - List of groups
CREATE TABLE group_control (
	id 			int(11) NOT NULL auto_increment,
	group_uid 	VARCHAR(128) NOT NULL,
	group_name 	VARCHAR(50) NOT NULL,
	short_descr VARCHAR(50) NOT NULL,
	long_descr	VARCHAR(255),
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

CREATE TABLE acl_manager (  
	id 			int(11) NOT NULL auto_increment,
	acl_id  	int(11) NOT NULL,
	user_id  	int(11) NOT NULL,       
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_acl_manager_acl
			foreign key (acl_id) references acl_control(id),
	constraint fk_acl_manager_user
			foreign key (user_id) references core_users(id)
);

CREATE TABLE group_manager (
	id 			int(11) NOT NULL auto_increment,
   	group_id  	int(11) NOT NULL,
	user_id  	int(11) NOT NULL,
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_group_manager_group
			foreign key (group_id) references group_control(id),
	constraint fk_group_manager_user
			foreign key (user_id) references core_users(id)
);

--
-- Profile Settings, profile settings associated with the core user
-- set link color to light blue: 3838E5
-- 4/16/2007
CREATE TABLE profile_settings (
	id 			int(11) NOT NULL auto_increment,
	user_id		int(11) NOT NULL UNIQUE,
	link_color	varchar(10) NOT NULL DEFAULT '3838E5',
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_profile_settings
			foreign key (user_id) references core_users(id)
);

-- End of User Tables

CREATE TABLE active_media_feeds (
  id           int(11) NOT NULL auto_increment,
  display_type char(1) NOT NULL default 'N',
  created_on   datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  KEY active_media_feeds_created_on_ndx (created_on)
);

CREATE TABLE media_feeds (
  id int(11)       NOT NULL auto_increment,
  image_filename   varchar(255) NOT NULL,
  media_url        varchar(255) NOT NULL,
  image_thumbnail  varchar(255) NOT NULL,
  media_title      varchar(255) NOT NULL,
  media_descr      text NOT NULL,
  media_type       char(1) NOT NULL default 'N',
  author           varchar(80) NOT NULL,
  rating           decimal(11,5) default NULL,
  rating_count     int(11) default '0',
  views            int(11) default '0',
  keywords         varchar(128) NOT NULL,
  orginal_imgurl   varchar(255) NOT NULL,
  process_count    int(11) default '0',
  system_id        int(11) default NULL,
  validity         int(11) default NULL,
  created_on       datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  UNIQUE KEY media_url (media_url),
  KEY media_feeds_created_on_ndx (created_on)
);

CREATE TABLE admin_main_banner (
  id           int(11) NOT NULL auto_increment,
  headline     varchar(128) NOT NULL,
  enabled      char(1) NOT NULL default 'N',
  app_section  varchar(40) default NULL,
  created_on   datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

CREATE TABLE cat_group_terms (
  id             int(11) NOT NULL auto_increment,
  category_name  varchar(20) NOT NULL,
  category_term  varchar(40) NOT NULL,
  created_on     datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id, category_name)
);

CREATE TABLE cat_link_groups (
  category_name    varchar(20) NOT NULL,
  category_descr   varchar(80) NOT NULL,
  category_color   varchar(10) NOT NULL,
  created_on       datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (category_name),
  UNIQUE KEY category_name (category_name)
);


CREATE TABLE developer_users (
  id               int(11) NOT NULL auto_increment,
  user_name        varchar(50) NOT NULL,
  user_id          int(11) NOT NULL,
  key_id           varchar(128) NOT NULL,
  enabled          char(1) NOT NULL default 'N',
  description      varchar(255) NOT NULL,
  last_application varchar(30) NOT NULL,
  last_login_success datetime NOT NULL default '0000-00-00 00:00:00',
  last_login_failure datetime NOT NULL default '0000-00-00 00:00:00',
  created_on datetime NOT NULL default '0000-00-00 00:00:00',
  updated_on datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  UNIQUE KEY user_name (user_name)
);

CREATE TABLE doc_file (
  id              int(11) NOT NULL auto_increment,
  child_id        int(11) default NULL,
  full_name       varchar(128) NOT NULL,
  title           varchar(255) NOT NULL,
  filename        varchar(255) NOT NULL,
  message         text NOT NULL,
  created_on      datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

CREATE TABLE doc_file_metadata (
  id int(11)      NOT NULL auto_increment,
  document_id     int(11) NOT NULL,
  doc_filename    varchar(255) NOT NULL,
  doc_filesize    int(11) default NULL,
  doc_originalname varchar(255) default NULL,
  created_on datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  UNIQUE KEY doc_filename (doc_filename),
  KEY fk_file_document (document_id)
);

CREATE TABLE search_query_filters (
  id            int(11) NOT NULL auto_increment,
  search_term   varchar(60) NOT NULL,
  description   varchar(128) NOT NULL,
  rating        int(11) NOT NULL,
  views         int(11) NOT NULL,
  user_name     varchar(50) NOT NULL,
  user_id       int(11) NOT NULL,
  created_on    datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

CREATE TABLE system_audit_log (
  id                int(11) NOT NULL auto_increment,
  application_name  varchar(60) NOT NULL,
  message_id        varchar(10) NOT NULL,
  message           varchar(255) default NULL,
  log_level         varchar(10) NOT NULL,
  send_to           varchar(80) default NULL,
  created_on        datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

CREATE TABLE system_feed_items (
  id               int(11) NOT NULL auto_increment,
  main_url         varchar(255) NOT NULL,
  url_title        varchar(128) NOT NULL,
  url_description  text,
  url_source       varchar(255) NOT NULL,
  process_count    int(11) NOT NULL default '0',
  created_on       datetime NOT NULL default '1901-01-01 00:00:00',
  hostname         varchar(128) default NULL,
  enum_proc_type   varchar(30),
  PRIMARY KEY  (id),
  UNIQUE KEY main_url (main_url)
);

CREATE TABLE system_scan_feeds (
  id              int(11) NOT NULL auto_increment,
  main_url        varchar(255) NOT NULL,
  url_title       varchar(128) NOT NULL,
  url_description varchar(255) default NULL,
  url_source      varchar(255) NOT NULL,
  created_on      datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  UNIQUE KEY main_url (main_url)
);

CREATE TABLE system_web_files (
  id             int(11) NOT NULL auto_increment,
  filename       varchar(255) NOT NULL,
  fsize          int(10) NOT NULL,
  fmtime         int(10) NOT NULL,
  fext           varchar(40) NOT NULL,
  projectname    varchar(80) NOT NULL,
  created_on     datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

CREATE TABLE user_entity_links (
  id             int(11) NOT NULL auto_increment,
  user_id        int(11) NOT NULL,
  link_id        int(11) NOT NULL,
  created_on     datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id)
);

CREATE TABLE user_visit_log (
  id             int(11) NOT NULL auto_increment,
  remote_host    varchar(30) default NULL,
  host           varchar(30) default NULL,
  referer        varchar(255) default NULL,
  user_agent     varchar(255) default NULL,
  request_uri    varchar(255) default NULL,
  request_page   varchar(124) default NULL,
  created_on     datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  KEY user_visit_log_created_on_ndx (created_on)
);

--*********************************************************
-- Request session log, session log for REST system
-- Updated: 2/2/2008
--*********************************************************
CREATE TABLE session_request_log (
	request_id  varchar(50) NOT NULL UNIQUE,
	remote_host varchar(20) NOT NULL,
	msg_value varchar(40) NOT NULL,
	msg_key varchar(40) NOT NULL,
	param_key varchar(40),
	param_value varchar(40),
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (request_id)
);

--*********************************************************
-- Insert initial data for botlist
-- Updated: 2/2/2008
--*********************************************************

INSERT INTO city_listing(city_name, created_on) VALUES('Atlanta', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Austin', NOW());

INSERT INTO city_listing(city_name, created_on) VALUES('Dallas', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Denver', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Chicago', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Houston', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Miami', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('NewYork', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Phoenix', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Las Vegas', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('San Diego', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('SF', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Seattle', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Portland', NOW());

INSERT INTO city_listing(city_name, created_on) VALUES('Bangalore', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Berlin', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('London', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Tokyo', NOW());
INSERT INTO city_listing(city_name, created_on) VALUES('Other', NOW());

-- Update city listings, setting current set to 'MAJOR'
update city_listing set city_category = 'MAJOR';

--
-- Insert minor cities
--

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Birmingham, Al', 'MINOR', 'AL', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Tuscaloosa, Al', 'MINOR', 'AL', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Arlington, Tx', 'MINOR', 'TX', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('San Antonio, Tx', 'MINOR', 'TX', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Biloxi, Ms', 'MINOR', 'MS', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('New Orleans, La', 'MINOR', 'LA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Portland, Or', 'MINOR', 'OR', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Athens, Ga', 'MINOR', 'GA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Cleveland, Oh', 'MINOR', 'OH', NOW());

---
--- Second set of city listings
---
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Los Angeles, Ca', 'MAJOR', NULL, NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Wash DC', 'MAJOR', NULL, NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Sacremento, Ca', 'MINOR', 'CA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Denver, Co', 'MINOR', 'CO', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Jacksonville, Fl', 'MINOR', 'FL', NOW());
INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Orlando, Fl', 'MINOR', 'FL', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Hawaii', 'MINOR', 'HAWAII', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Indianapolis, IN', 'MINOR', 'IN', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Maine - Minor', 'MINOR', 'MAINE', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Massachusetts', 'MINOR', 'MASS', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('New Jersey', 'MINOR', 'NJ', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Paris, France', 'MINOR', 'FRANCE', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Alberta, Canada', 'MINOR', 'CANADA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Beijing, China', 'MINOR', 'CHINA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Hong Kong, China', 'MINOR', 'CHINA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Shanghai, China', 'MINOR', 'CHINA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Madrid, Spain', 'MINOR', 'SPAIN', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Mumbai, India', 'MINOR', 'INDIA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Delhi, India', 'MINOR', 'INDIA', NOW());

INSERT INTO city_listing(city_name, city_category, state_abbr, created_on) VALUES('Australia', 'MINOR', 'AUSTRALIA', NOW());

---
--- Add new reviews section

INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('c8ec2847bdac35595ffba82aa0f65fcbreviews', 'Reviews', NOW());

INSERT INTO post_sections(generated_id, section_name, created_on) 
			VALUES('e6ba9e9ab139f9c0b090cf77beaccNews', 'Local News', NOW());

--
-- Create the forums
INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('1fb6674f66ff9e617ec1313978513096', 'General Listings', NOW());

INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('9ce00803181d4611895ad3e764b2adb2', 'Personals', NOW());
		
INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('7ebc9a603519b9cdc277c2fc2d68d1a9', 'Resumes', NOW());
		
INSERT INTO post_sections(generated_id, section_name, created_on) 		
		VALUES('0b578fbfe97b317ded5ad929c2210b7d', 'Jobs', NOW());
		
INSERT INTO post_sections(generated_id, section_name, created_on) 		
		VALUES('cab4d3bd8e28cb03f17c12e5b322d6fb', 'For Sale', NOW());
		
INSERT INTO post_sections(generated_id, section_name, created_on) 		
		VALUES('effb06927b7bea46709d5d21c2465e04', 'Technology', NOW());

INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('9e876afb5e45a7f1d670ceceec3352a8', 'Events', NOW());

INSERT INTO post_sections(generated_id, section_name, created_on) 
		VALUES('c8ec2847bdac35595ffba82aa0f65fcbreviews', 'Reviews', NOW());

--
-- Insert the discussion forums
INSERT INTO forum_group(forum_name,
	forum_descr, keywords, created_on) 
	VALUES ('General Forum', 'General Forum', 'general forum forums chat', NOW());
	
INSERT INTO forum_group(forum_name,
	forum_descr, keywords, created_on) 
	VALUES ('Bugs and Feature Requests', 'Bugs and Feature Requests', 'bugs chat forums', NOW());
	
INSERT INTO forum_group(forum_name,
	forum_descr, keywords, created_on) 
	VALUES ('Bot Chat', 'Bot Chat', 'bot chat forums', NOW());
	
--
-- Insert new users (apr pwd)

insert into core_users(user_name, user_password, user_email, date_of_birth, account_number, active_code, last_login_success, last_login_failure, created_on, updated_on) VALUES(
    'botbob', 'c5084a613255f920e3be35e5366a94a8', 'botbob@bot.com', '1981-01-01', 'c3c18d19b5887570e74ef6cdce4b6abbbotbob', 1, NOW(), NOW(), NOW(), NOW());

insert into profile_settings(user_id, created_on) values(LAST_INSERT_ID(), NOW());

insert into core_users(user_name, user_password, user_email, date_of_birth, account_number, active_code, last_login_success, last_login_failure, created_on, updated_on) VALUES(
    'botrover99', 'fa911d71b19af603c0f8a10455a670ef', 'botrover99@email.com', '1981-01-01', '55ebef6779f025f9174478e6abcd3874botrover99', 1, NOW(), NOW(), NOW(), NOW());
    
insert into profile_settings(user_id, created_on) values(LAST_INSERT_ID(), NOW());

insert into core_users(user_name, user_password, user_email, date_of_birth, account_number, active_code, last_login_success, last_login_failure, created_on, updated_on) VALUES(
    'botbert99', 'fa911d71b19af603c0f8a10455a670ef', 'botbert99@email.com', '1981-01-01', 'b2acafa3c71be70c5aed5aedad365342botbert99', 1, NOW(), NOW(), NOW(), NOW());

insert into profile_settings(user_id, created_on) values(LAST_INSERT_ID(), NOW());

--
-- Insert Default Link Groups

insert into link_groups(group_name, generated_id, created_on) values('Info Articles', 'aaf9dfb546f650d5fa614156000info', NOW());			
insert into link_groups(group_name, generated_id, created_on) values('Media', 'fd50091908d57ab8b15db358000media', NOW());
insert into link_groups(group_name, generated_id, created_on) values('NSFW', '65ebdbd0e6a0a67c029000nsfw', NOW());

-- Update the entity_links

--update entity_links a
-- 	set user_id = (select id from core_users b 
-- 	where a.full_name = b.user_name);

-- Insert into user links

--insert into user_entity_links(user_id, link_id, created_on) 
--	select user_id, id, NOW() from entity_links limit 0, 200;

-- End of file