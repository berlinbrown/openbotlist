##
## Berlin Brown
## load_db.pl - run with perl 5 an greater
##
## setup the database
##
sub rtrim($);

$user_name = rtrim(`sed -n "1{p;q;}" ./LOG.DATA`);
$user_access = rtrim(`sed -n "2{p;q;}" ./LOG.DATA`);
$db_name = "botlist_development";
$table_list = "acl_control acl_manager active_media_feeds admin_main_banner authorities child_list_links city_listing core_users developer_users doc_file doc_file_metadata entity_links forum_group group_control group_links group_manager link_groups media_feeds post_image_metadata post_listing post_sections profile_settings search_query_filters system_audit_log system_feed_items system_scan_feeds user_comments user_links user_visit_log users";

print "Creating database: \n";

system "mysqldump -p $db_name -u$user_name -p$user_access $table_list > ./backup/backup_botlist.sql";

print ".done\n";

sub rtrim($)
{
	my $string = shift;
	$string =~ s/\s+$//;
	return $string;
}
