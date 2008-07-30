-- Berlin Brown
--
-- 8/20/2007

alter table entity_links ADD COLUMN process_count int(11) NOT NULL DEFAULT 0;

alter table entity_links ADD COLUMN updated_on DATETIME DEFAULT '0000-00-00 00:00:00';

-- End of script