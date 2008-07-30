#!/bin/sh
#
# Get detailed source metrics on the application
# (run with cygwin)

APP_TYPE="java rb xml jsp scala"

for i in $APP_TYPE ; do
	echo "----- APP_TYPE: $i"
	find . -name \*.$i | xargs wc -l
done

svn update
svnversion
echo "done"


