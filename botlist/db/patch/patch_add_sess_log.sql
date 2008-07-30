-- 
-- 2/2/2008

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

-- End of File