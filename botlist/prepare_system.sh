#!/bin/sh
#
# prepare_system.sh
# Prepare the filesystem for botlist

DOCWRITE_HOME=/home/webadmin/botspiritcompany.com/html/docwritedocs

mkdir -p ${DOCWRITE_HOME} 
chown tomcat ${DOCWRITE_HOME}
chgrp tomcat ${DOCWRITE_HOME}


