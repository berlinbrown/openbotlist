#!/bin/sh
#
# Updated for botlist 3/10/2007 - berlin brown
#
# Nifty mysqlhotcopy script by masalaman from vbulletin.com
# with NAS modifications and error checking by DaiTengu.backupdir
# backs up mysql databases to a local directory, then copies it to a Network
# Attached Storage (NAS) mounted via NFS.  The NFS option can be turned off to
# just back up to a local directory
#
# Also will send out an e-mail if a previous backup has failed.
#
# Version 0.1
#
 
# List of databases to be backed up, separated by a space
dblist="botlist_development botlist_test botlist_production"
 
# Directory for local backups.  Ideally this should be a seperate disk or partition.
backupdir=./backup/mysql
 
# Enable NAS? (0/1)
nas=0
 
# Directory for NAS (not used if NAS is disabled)
nasdir=/backup/nas
 
# Number of versions to keep
numversions=4
 
# Full path for MySQL hotcopy command
hotcopycmd=/usr/bin/mysqlhotcopy
 
# MySQL Username and password
userpassword=" --user=root --password=PASSWORD"
 
# E-mail address to send errors to
email=someone@daitengu.com
 
# Hostname (This can probably be left alone)
host=$(hostname)
 
# Create directory if needed
mkdir -p ${backupdir}
if [ ! -d ${backupdir} ]
 then
 echo "Invalid directory: ${backupdir}"
 exit 1
fi
 
# Hotcopy begins here
echo "Hotcopying MySQL Databases..."
RC=0
for database in $dblist
do
if [ -d  ${backupdir}/${database} ]; then
 mv ${backupdir}/${database} ${backupdir}/${database}.FAIL
 echo "${database} seems to have failed during a previous backup." &gt; err.txt
 mail -s "Something Failed on $host" $email &lt; err.txt
 rm -rf err.txt
fi
echo "Hotcopying $database ..."
$hotcopycmd $userpassword $database ${backupdir}
RC=$?
if [ $RC -gt 0 ]
 then
 break;
fi
if [ $nas == 1 ]; then
 cp -r ${backupdir}/${database} ${nasdir}
 RC=$?
   if [ $RC -gt 0 ]
    then
    break;
  fi
fi
# Rollover the backup directories
i=$numversions
mv ${backupdir}/${database} ${backupdir}/${database}.0 2&gt; /dev/null
if [ $nas == 1 ]; then
 mv ${nasdir}/${database} ${nasdir}/${database}.0 2&gt; /dev/null
 rm -rf ${nasdir}/${database}.$i 2&gt; /dev/null
fi
rm -rf ${backupdir}/${database}.$i 2&gt; /dev/null
while [ $i -gt 0 ]
 do
   mv ${backupdir}/${database}.`expr $i - 1` ${backupdir}/${database}.$i 2&gt; /dev/null
   mv ${nasdir}/${database}.`expr $i - 1` ${nasdir}/${database}.$i 2&gt; /dev/null
   i=`expr $i - 1`
 done
done
 
if [ $RC -gt 0 ]
 then
 echo "MySQL Hotcopy failed!"
 exit $RC
else
 # Hotcopy is complete. List the backup versions!
 ls -l ${nasdir}
 echo "MySQL Hotcopy is complete!"
fi
exit 0
