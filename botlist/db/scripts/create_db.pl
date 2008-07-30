##
## Berlin Brown
## load_db.pl - run with perl 5 an greater
##
## setup the database
##

sub rtrim($);

$user_name = rtrim("root");
$user_access = rtrim(`sed -n "2{p;q;}" ./LOG.DATA`);

print "Creating database: \n";
system "mysql -f -u $user_name -p$user_access < ../create_database.sql";
print ".done\n";

sub rtrim($)
{
	my $string = shift;
	$string =~ s/\s+$//;
	return $string;
}
