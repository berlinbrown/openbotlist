"""
 Berlin Brown
 
 proc_logs.py
 
 Read log files
"""

import sys
import string
import time
import datetime

import cgi

#===========================================================

MAX_LINES_PROCESS = 4000

HTML_HEADER = "../conf/log_header.html"
HTML_FOOTER = "../conf/log_footer.html"

LOG_HTML_OUTPUT = "../output/logfile.html"

HTML_ERROR_BACKGROUND = "#edaeae"
HTML_INFO_BACKGROUND = "#ecf9ea"

#===========================================================

class LogReport:
	def __init__(self):
		self.line_no = -1
		self.time_run = -1
		
class GeneralRecord:
	def __init__(self):
		self.line_no = -1
		self.data = None
		
	def htmlEscapeData(self):
		return cgi.escape(self.data)
		
class ErrorRecord(GeneralRecord):
	pass

class InfoRecord(GeneralRecord):
	pass
	
class ScriptRecord(GeneralRecord):
	pass
	
procLogReport = LogReport()

#===========================================================
def readHtmlTemplate(filename, delim):
	f = open(filename)
	data = f.readlines()
	str_delim = "%s" % delim
	template = str_delim.join(data)
	f.close()
	return template

#===========================================================

def printSummary(logReportSummary):
	print "============================"
	print "  Number of Lines in Log File=%s" % logReportSummary.line_no
	print "  Time run in=%s" % logReportSummary.time_run
	print "============================"

#===========================================================

class LogObject:
	"""
		Log Object, main processing singleton object
	"""
	def __init__(self):
		self.log_rows = []
		self.html_filename = LOG_HTML_OUTPUT
	
	def writeLine(self, fout, log_rec):
		fout.write("  <tr>\n")
		fout.write("  <td>%s</td>\n" % log_rec.line_no)
		bg = ""
		if isinstance(log_rec, InfoRecord):
			bg = HTML_INFO_BACKGROUND
		elif isinstance(log_rec, ErrorRecord):
			bg = HTML_ERROR_BACKGROUND
		fout.write("     <td style='border: 1px solid #DDD; background-color: %s;'>%s</td>\n"
			   % (bg, log_rec.htmlEscapeData()))
		fout.write("  </tr>\n")
		
	def generateHtmlOutput(self):
		print "Generating Html Report"
		header = readHtmlTemplate(HTML_HEADER, "\n")
		footer = readHtmlTemplate(HTML_FOOTER, "\n")
		fout = open(self.html_filename, "w")
		fout.write(header)
		fout.write("<table cellspacing='0' cellpadding='0'>\n")
		for log_row in self.log_rows:
			if isinstance(log_row, GeneralRecord):
				# Output the different log type differently
				if isinstance(log_row, ErrorRecord):
					self.writeLine(fout, log_row)
				elif isinstance(log_row, InfoRecord):
					self.writeLine(fout, log_row)
				elif isinstance(log_row, ScriptRecord):
					self.writeLine(fout, log_row)
					
		fout.write("</table>\n")		
		fout.write(footer)
		fout.close()
		
	def processingLogFile(self, filename):
		global procLogReport
		print "INFO: processing file=%s" % filename
		try:
			f = open(filename, "r")
			line_no = 0
			start_run = time.time()
			for line in f:
				try:			
					if line:	
						log_format = line.split("|")
						if len(log_format) == 4:
							log_datetime = log_format[2].strip()
							log_data  = log_format[3].strip()
							record = None
							if log_data.find("INFO") >= 0:
								record = InfoRecord()
							elif log_data.find("ERROR") >= 0:
								record = ErrorRecord()
							elif log_data.find("<script>") >= 0:
								record = ScriptRecord()
							if record:
								record.data = log_data
								record.line_no = line_no								
								self.log_rows.append(record)
							
							if line_no > MAX_LINES_PROCESS:
								# Summary - after processing log file
								# Early exit, set summary data
								procLogReport.line_no = line_no
								procLogReport.time_run = end_run - start_run
								print("INFO: Max number of log lines reached=%s" % line_no)
								return
						else:
							raise Exception("Invalid Log File Format (column len=%s) at line=%s"
									% (len(log_format), line_no))
				except Exception, e:
					print "ERROR: error while processing log file line:"
					print e
				line_no = line_no + 1
				end_run = time.time()

			# Summary - after processing log file			
			procLogReport.line_no = line_no
			procLogReport.time_run = end_run - start_run

		except Exception, e:
			print "ERROR: error loading and processing file"
			print e

def main():
	if len(sys.argv) != 2:
		print "Usage: python %s <log_filename>" % sys.argv[0]
		sys.exit()
		return
	
	# Load the processing file
	filename = sys.argv[1]
	log = LogObject()
	log.processingLogFile(filename)
	log.generateHtmlOutput()
	global procLogReport
	printSummary(procLogReport)
	
if __name__ == '__main__':
	print "processing log files"
	main()
	print "done"