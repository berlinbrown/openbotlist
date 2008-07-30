--
-- Create the database (openbotlist_)
-- The openbotlist_test database is used with the testing server
create database openbotlist_development;
create database openbotlist_test;
create database openbotlist_production;

grant all on openbotlist_development.* to 'botlistfriend'@'localhost' identified by 'b80t1st';
grant all on openbotlist_test.* to 'botlistfriend'@'localhost' identified by 'b80t1st';
grant all on openbotlist_production.* to 'botlistfriend'@'localhost' identified by 'b80t1st';

grant all on openbotlist_development.* to 'botlistfriend'@'*' identified by 'b80t1st';
grant all on openbotlist_test.* to 'botlistfriend'@'*' identified by 'b80t1st';
grant all on openbotlist_production.* to 'botlistfriend'@'*' identified by 'b80t1st';

-- End of the File
