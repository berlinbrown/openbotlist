-- 
-- Simple patch, add indexes on created on

-- 4/20/2007

-- Create created_on indices on main tables

CREATE INDEX entity_links_created_on_ndx ON entity_links(created_on);

CREATE INDEX entity_links_rating_ndx ON entity_links(rating);
CREATE INDEX entity_links_views_ndx ON entity_links(views);

CREATE INDEX active_media_feeds_created_on_ndx ON active_media_feeds(created_on);
CREATE INDEX media_feeds_created_on_ndx ON media_feeds(created_on);
CREATE INDEX city_listing_created_on_ndx ON city_listing(created_on);
CREATE INDEX core_users_created_on_ndx ON core_users(created_on);
CREATE INDEX post_listing_created_on_ndx ON post_listing(created_on);
CREATE INDEX user_visit_log_created_on_ndx ON user_visit_log(created_on);
CREATE INDEX user_comments_created_on_ndx ON user_comments(created_on);

-- End of File