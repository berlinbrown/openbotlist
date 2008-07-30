##
## Berlin Brown
## load_db.pl - run with perl 5 an greater
##
## setup the database
##
sub rtrim($);

$user_name = rtrim(`sed -n "1{p;q;}" ./LOG.DATA`);
$user_access = rtrim(`sed -n "2{p;q;}" ./LOG.DATA`);

$numArgs = $#ARGV + 1;
if ($numArgs < 1 ) {
	print "ERR: Invalid number of arguments";
	exit(-1);
}
$patchVal = $ARGV[0];

print "Creating tables: $patchVal\n";
system "mysql -f -u$user_name -p$user_access < ../patch/$patchVal.sql";
print ".done\n";

sub rtrim($)
{
	my $string = shift;
	$string =~ s/\s+$//;
	return $string;
}
