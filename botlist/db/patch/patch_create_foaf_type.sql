--
-- Berlin Brown
-- Create object

drop table entity_type_foaf;

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

-- End of Script
