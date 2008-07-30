-- 
-- 9/4/2007


-- link type can include a type for the particular link, eg, politics, finance, etc.
ALTER TABLE entity_links ADD COLUMN link_type varchar(20);

ALTER TABLE entity_links ADD COLUMN bot_rating DECIMAL(5,2) DEFAULT 0;

--
-- Also include creation of the different link categories and terms
-- where 'cat' equals category
CREATE TABLE cat_link_groups (
	category_name		varchar(20) NOT NULL UNIQUE,
	category_descr		varchar(80) NOT NULL,	
	category_color		varchar(10) NOT NULL,
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (category_name)
);

-- One to many relationship with link_group, one 'category_name' per group_terms
CREATE TABLE cat_group_terms (
	id					int(11) NOT NULL auto_increment,
	category_name		varchar(20) NOT NULL,
	category_term		varchar(40) NOT NULL,
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id, category_name)
);

--
-- Insert records for category and terms
insert into cat_link_groups(category_name, category_descr, category_color, created_on)
	VALUES('tech', 'tech', 'BFFFBF', NOW());

insert into cat_link_groups(category_name, category_descr, category_color, created_on)
	VALUES('politics', 'politics', 'FFE6BF', NOW());
	
insert into cat_link_groups(category_name, category_descr, category_color, created_on)
	VALUES('science', 'science', 'F2FFBF', NOW());

insert into cat_link_groups(category_name, category_descr, category_color, created_on)
	VALUES('international', 'international', 'C8C8FF', NOW());
	
insert into cat_link_groups(category_name, category_descr, category_color, created_on)
	VALUES('health', 'health', 'DABFFF', NOW());
	
insert into cat_link_groups(category_name, category_descr, category_color, created_on)
	VALUES('finance', 'finance', 'FFBFEF', NOW());

-- ==========================
-- Link groups to their terms
-- ==========================
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('tech', 'tech', NOW());
insert into cat_group_terms(category_name, category_term, created_on)		
	VALUES('tech', 'google', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('tech', 'apple', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('tech', 'itunes', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('tech', 'dell', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('tech', 'comcast', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('tech', 'facebook', NOW());
	
insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('finance', 'finance', NOW());	
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('finance', 'jobs', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('finance', 'wallstreet', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('finance', 'economy', NOW());
	
insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('health', 'health', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('health', 'heart', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('health', 'lungs', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('health', 'cancer', NOW());
		
insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'international', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'india', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'global', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'hiv', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'aids', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'russian', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'china', NOW());
insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('international', 'Afghanistan', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('international', 'german', NOW());
	
insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('science', 'science', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('science', 'discovermagazine', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('science', 'nasa', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('science', 'bacteria', NOW());

insert into cat_group_terms(category_name, category_term, created_on)
	VALUES('politics', 'politics', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'president', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'bush', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'people', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'senate', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'jury', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'iraq', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'america', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'cheney', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'clinton', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'washington', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'ronpaul', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'california', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'iran', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'foxnews', NOW());
insert into cat_group_terms(category_name, category_term, created_on)	
	VALUES('politics', 'cnn', NOW());

-- End of File