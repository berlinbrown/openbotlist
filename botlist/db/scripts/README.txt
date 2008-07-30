##
## When making new changes to the database,
## for development, the patch system is used.
## load a script a particular script to manipulate only
## a few tables.

 To run the script, use the following:
 (make sure to change to the scripts directory.
   
   For example:
   perl create_db.pl
   
   (when invoking the create_db script, make sure to check
   the username and password in 'create_database.sql'
   
   Create the tables:
   perl create_tables.pl
   
   Load the database:
   perl load_db.pl
   
   ----------------
   Note: after creating the database, make sure to
   change the username and password in 'botlistings-servlet.xml'
   ----------------