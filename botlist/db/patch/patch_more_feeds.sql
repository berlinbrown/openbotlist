--
-- Patch that includes group links and user classes.
--

-- Insert the data for the group link sections

-- New Feeds

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.indystar.com/apps/pbcs.dll/section?Category=RSS&mime=XML','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://feeds.gawker.com/gizmodo/excerpts.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://feeds.feedburner.com/Techcrunch','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://feeds.gawker.com/lifehacker/excerpts.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.theregister.co.uk/headlines.rss','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.slate.com/rss/','Lambda','Lambda', 'http://lambda-the-ultimate.org');

-- New Set (2)

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	 	
 	VALUES('http://www.telegraph.co.uk/newsfeed/rss/news-breaking_news.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.timesonline.co.uk/tol/feeds/rss/worldnews.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.washingtonpost.com/wp-dyn/rss/linkset/2005/03/24/LI2005032400102.xml','Lambda','Lambda', 'http://lambda-the-ultimate.org');

insert into system_scan_feeds(main_url, url_title, url_description, url_source)	
 	VALUES('http://www.pheedo.com/f/newscientist_online-news','Lambda','Lambda', 'http://lambda-the-ultimate.org');

-- End of File