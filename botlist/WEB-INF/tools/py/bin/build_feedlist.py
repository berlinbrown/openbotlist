#!/usr/bin/python
"""
 Berlin Brown
 build_feedlist.py
  
 Tool to generate SQL system feed load script.
  
 Date: 6/20/2007
"""

import sys
import time
import datetime

FILE_HEADER = """--
-- Patch for inserting scan feeds
-- On %s
--
""" % datetime.date.today()

FILE_FOOTER = """
-- End of File
"""

INSERT_HEADER = "insert into system_scan_feeds(main_url, url_title, url_description, url_source)"
INSERT_FOOTER = "'NONE','NONE', 'http://www.none.org');"

class FeedListBOM:
	def __init__(self, filename):
		self.urllist = filename
		self.linedata = []
		self.patchname = "patch_system01.sql"
	
	def generate(self):
		fw = open(self.patchname, 'w')
		print "generating %s" % self.patchname
		fw.write(FILE_HEADER)
		for url in self.linedata:
			fw.write(INSERT_HEADER + '\n')
			fw.write("    VALUES('%s', " % url)
			fw.write(INSERT_FOOTER + '\n\n')
		fw.write(FILE_FOOTER)
		fw.close()
	
	def parse(self):
		print "* parsing url list=%s" % self.urllist
		f = open(self.urllist, 'r')
		readdata = f.readlines()
		for url in readdata:
			addurl = url.strip()
			print "adding url=%s" % addurl
			self.linedata.append(addurl)
		f.close()

if __name__ == '__main__':
	print "------------------------"
	print "running build_feedlist.py - system feed list generator"

	# script is included in arg list
	if len(sys.argv) != 4:
		print "Usage: %s -f <url_list> <output sql file>" % sys.argv[0]
		sys.exit(0)

	# Launch the feed list bom parser
	feedlistbom = FeedListBOM(sys.argv[2])
	feedlistbom.patchname = sys.argv[3]
	feedlistbom.parse()
	feedlistbom.generate()

	print "done"

# End of File
