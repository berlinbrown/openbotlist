##
## Berlin Brown
##
## The following readme describes the order in which
## to create and load the database
##

 -------------------
 9/4/2007
 -------------------
 Note: on the 'patch' directory.  These sql scripts are 'changes'
 to the original database design.  'create_tables.sql' was used
 as the original script and then 'patches' were applied to the system.
 (WARNING: At present, there are going to be inconsistencies between the original 'create_tables.sql'
  script and the database that is needed to run the application.  Careful evaluation of the 
  patch directory will allow you to build the complete database that is required).
 
 -------------------
 To do a simple backup of the database, you can do
 the following.
 
 mysqldump -p botlist_development -uUSER_NAME -pPASSWORD table_list > ./backup/backup_botlist.sql
 
 -------------------

 As of 04/10/2007, current look at the database:
 (some tables used for testing)
 
+-------------------------------+
| Tables_in_botlist_development |
+-------------------------------+
| acl_control                   |
| acl_manager                   |
| authorities                   |
| child_list_links              |
| city_listing                  |
| core_users                    |
| doc_file                      |
| doc_file_metadata             |
| entity_links                  |
| forum_group                   |
| group_control                 |
| group_links                   |
| group_manager                 |
| link_groups                   |
| post_image_metadata           |
| post_listing                  |
| post_sections                 |
| system_web_files              |
| user_comments                 |
| user_links                    |
| user_listings                 |
| user_visit_log                |
| users                         |
+-------------------------------+
25 rows in set (0.00 sec)

 


