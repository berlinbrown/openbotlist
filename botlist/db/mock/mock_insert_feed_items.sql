-- Berlin Brown
--
-- 2/2/2008

INSERT INTO system_feed_items(main_url, url_title, url_description, url_source, process_count, created_on) 
		VALUES('http://www.reddit.com', 'The Reddit', 'The Reddit is Cool', 'http://www.google.com', 0, NOW());

INSERT INTO system_feed_items(main_url, url_title, url_description, url_source, process_count, created_on) 
		VALUES('http://www.google.com', 'The Google', 'The google is awesome', 'http://www.google.com', 0, NOW());
		
INSERT INTO system_feed_items(main_url, url_title, url_description, url_source, process_count, created_on) 
		VALUES('http://www.yahoo.com', 'The Yahoo<b>Yea</b>', 'The yahoo uses tags <b>Testing</b><a href="err">err</a>', 'http://www.yahoo.com', 0, NOW());
		
-- End of the script

