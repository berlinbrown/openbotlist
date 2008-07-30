--
-- Patch for admin tools
--

--
-- Created: 6/29/2007
CREATE TABLE admin_main_banner (
  id 			int(11) NOT NULL auto_increment,
  headline 		varchar(128) NOT NULL,
  enabled		char(1) NOT NULL DEFAULT 'N',
  app_section 	varchar(40),
  created_on	DATETIME NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id)
);

-- End of File