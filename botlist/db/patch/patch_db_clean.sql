--
-- Patch that includes group links and user classes.
--

-- Insert the data for the group link sections

patch_users_groups.sql

-- Cleanup invalid titles.
delete from entity_links where url_title like '%&#39%';

delete from entity_links where url_title like '%??%';

delete from system_feed_items where url_title = ' ';

delete from entity_links where url_title = ' ';

-- End of File