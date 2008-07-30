--
-- Berlin Brown
--
-- updated: 11/12/2006
--

CREATE TABLE doc_file (
	id			int(11) NOT NULL auto_increment,
	child_id	int(11),
	full_name	varchar(128) NOT NULL,
	title		varchar(255) NOT NULL,
	filename	varchar(255) NOT NULL,
	message		TEXT NOT NULL,
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);	

CREATE TABLE doc_file_metadata (
	id					int(11) NOT NULL auto_increment,
	document_id			int(11) NOT NULL, 
	doc_filename		varchar(255) NOT NULL UNIQUE,	
	doc_filesize		int(11),
	doc_originalname	varchar(255),
	created_on  		DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id),
	constraint fk_file_document
		foreign key (document_id) references doc_file(id)
);


-- End of file