--
-- Berlin Brown
--
-- updated: 3/24/2006
--

--
-- Drop constraints first
--
ALTER TABLE users DROP FOREIGN KEY fk_authorities_users;

ALTER TABLE user_comments DROP FOREIGN KEY fk_forum_group_comments;
ALTER TABLE user_comments DROP FOREIGN KEY fk_post_listing_comments;
ALTER TABLE user_comments DROP FOREIGN KEY fk_link;

ALTER TABLE post_listing DROP FOREIGN KEY fk_post_listing;
ALTER TABLE post_listing DROP FOREIGN KEY fk_post_section;

ALTER TABLE doc_file_metadata DROP FOREIGN KEY fk_file_document;

ALTER TABLE post_image_metadata DROP FOREIGN KEY fk_image_adlist;

--
-- Drop foreign keys for core users
ALTER TABLE acl_manager		DROP FOREIGN KEY fk_acl_manager_acl;
ALTER TABLE acl_manager		DROP FOREIGN KEY fk_acl_manager_user;
ALTER TABLE group_manager 	DROP FOREIGN KEY fk_group_manager_group;
ALTER TABLE group_manager	DROP FOREIGN KEY fk_group_manager_user;

ALTER TABLE group_links			DROP FOREIGN KEY fk_group_links;
ALTER TABLE profile_settings	DROP FOREIGN KEY fk_profile_settings;

-- DROP ALL TABLES
DROP TABLE if exists acl_control;         
DROP TABLE if exists acl_manager;              
DROP TABLE if exists active_media_feeds;
DROP TABLE if exists admin_main_banner;         
DROP TABLE if exists authorities;               
DROP TABLE if exists cat_group_terms;
DROP TABLE if exists cat_link_groups;           
DROP TABLE if exists child_list_links;          
DROP TABLE if exists city_listing;              
DROP TABLE if exists core_users;                
DROP TABLE if exists developer_users;           
DROP TABLE if exists doc_file;                  
DROP TABLE if exists doc_file_metadata;         
DROP TABLE if exists entity_links;              
DROP TABLE if exists forum_group;               
DROP TABLE if exists group_control;             
DROP TABLE if exists group_links;               
DROP TABLE if exists group_manager;             
DROP TABLE if exists link_groups;               
DROP TABLE if exists media_feeds;               
DROP TABLE if exists post_image_metadata;       
DROP TABLE if exists post_listing;              
DROP TABLE if exists post_sections;             
DROP TABLE if exists profile_settings;          
DROP TABLE if exists search_query_filters;      
DROP TABLE if exists system_audit_log;          
DROP TABLE if exists system_feed_items;         
DROP TABLE if exists system_scan_feeds;         
DROP TABLE if exists system_web_files;          
DROP TABLE if exists user_comments;             
DROP TABLE if exists user_entity_links;
DROP TABLE if exists user_visit_audit;
DROP TABLE if exists user_visit_log;            
DROP TABLE if exists users;
--
-- New additions 3/14/2008
DROP TABLE if exists entity_type_foaf;

-- End of file