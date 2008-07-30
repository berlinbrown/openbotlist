#!/bin/sh
#
# Set the mimetype for media files to allow for HTML/CSS/JS files.
#
# Additional info:
# The metrics html content is created with http://www.statsvn.org/
# The following command was used:
#--------------------------------------
# java -jar ${HOME}/statsvn.jar -verbose -output-dir ${HOME}/output ${HOME}/output/svn.log $PROJ_HOME
#--------------------------------------

find . -name '*.html' -exec svn propset svn:mime-type text/html {} \;
find . -name '*.css' -exec svn propset svn:mime-type text/css {} \;

# End of Script
