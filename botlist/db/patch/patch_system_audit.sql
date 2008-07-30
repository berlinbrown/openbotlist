--
-- Berlin Brown
-- Create system audit log for keeping track
-- of system and bot actions
--
-- 8/5/2007

--
-- Log Level values (SEVERE, MED, HIGH, ERR, INFO, DEBUG)
-- Need to check length in send_to
-- Message id format should use the following: '000000'
CREATE TABLE system_audit_log (
	id 					int(11) NOT NULL auto_increment,
	application_name 	varchar(60) NOT NULL,
	message_id			varchar(10) NOT NULL,	
	message				varchar(255),
	log_level			varchar(10) NOT NULL, 
	send_to				varchar(80),	
	created_on  DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (id)
);

-- End of File