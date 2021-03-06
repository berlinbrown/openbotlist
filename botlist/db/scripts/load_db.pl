##
## Berlin Brown
## load_db.pl - run with perl 5 an greater
##
## setup the database
##

sub rtrim($);

$user_name = rtrim(`sed -n "1{p;q;}" ./LOG.DATA`);
$user_access = rtrim(`sed -n "2{p;q;}" ./LOG.DATA`);

print "Running botlist sql loader: \n";
print "Dropping...\n";
system "mysql -f -u $user_name -p$user_access < ../drop_tables.sql";

print "Creating tables...\n";
system "mysql -f -u $user_name -p$user_access < ../create_tables.sql";
system "mysql -f -u $user_name -p$user_access < ../create_tables_doc.sql";

print "Inserting system data...\n";
system "mysql -f -u $user_name -p$user_access < ../insert_test_data.sql";

print ".done\n";

sub rtrim($)
{
	my $string = shift;
	$string =~ s/\s+$//;
	return $string;
}

