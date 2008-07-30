--
-- Patch that includes group links and user classes.
--

-- Insert the data for the group link sections

--
-- Create the system feed table to read
-- the feed list
-- Created: 5/3/2007
CREATE TABLE system_scan_feeds (
  id 				int(11) NOT NULL auto_increment,
  main_url			varchar(255) NOT NULL unique,
  url_title			varchar(128) NOT NULL,
  url_description 	varchar(255),
  url_source		varchar(255) NOT NULL,
  created_on		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id)
);
		
CREATE TABLE system_feed_items (
  id 				int(11) NOT NULL auto_increment,
  main_url			varchar(255) NOT NULL unique,
  url_title			varchar(128) NOT NULL,
  url_description 	text,
  url_source		varchar(255) NOT NULL,
  process_count		int(11) NOT NULL DEFAULT 0, 
  created_on		DATETIME NOT NULL DEFAULT '1901-01-01 00:00:00',
  PRIMARY KEY (id)
);

insert into system_scan_feeds(main_url, url_title, url_description, url_source)
	VALUES('http://lambda-the-ultimate.org/rss.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://rss.mnginteractive.com/live/sanjose/SanJose_1915296.xml', 'mnginteractive.com', 'mnginteractive.com', 'http://rss.mnginteractive.com/');

-- New Feeds

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.indystar.com/apps/pbcs.dll/section?Category=RSS&mime=XML','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://www.suntimes.com/rss/news/index.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://feeds.feedburner.com/schneier/excerpts','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://feeds.dailykos.com/dailykos/index.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://feeds.feedburner.com/AmericanChronicle','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
	VALUES('http://mediamatters.org/users/alert_rss?sid=001001012','Lambda','Lambda', 'http://lambda-the-ultimate.org');

--delete from system_feed_items
--drop table system_feed_items
--select * from system_feed_items

-- End of File