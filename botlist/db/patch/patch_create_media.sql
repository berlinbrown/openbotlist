--
-- Berlin Brown
--
-- patch_create_media.sql
--

--
-- Media Feed Object
CREATE TABLE media_feeds (
	id int(11) 		NOT NULL auto_increment,
	image_filename 	varchar(255) NOT NULL,
	media_url		varchar(255) NOT NULL UNIQUE,
	image_thumbnail varchar(255) NOT NULL,
	media_title 	varchar(255) NOT NULL,
	media_descr		TEXT NOT NULL,
	media_type		char(1) NOT NULL DEFAULT 'N',
	author			varchar(80) NOT NULL,
	rating			DECIMAL(11, 5),
	rating_count	int(11) default 0,
	views			int(11) default 0,
	keywords		varchar(128) NOT NULL,
	orginal_imgurl	varchar(255) NOT NULL,
	process_count	int(11) default 0,
	system_id 		int(11),
	validity 		int(11),
	created_on  	DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

CREATE TABLE active_media_feeds (
	id int(11) 		NOT NULL auto_increment,
	display_type	char(1) NOT NULL DEFAULT 'N',
	created_on  	DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

-- End of Script
