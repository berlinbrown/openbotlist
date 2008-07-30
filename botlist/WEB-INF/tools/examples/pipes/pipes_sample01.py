#!/bin/sh
"""
 Example Pipes Python Application

 Connect to the pipe access point, retrieve the data and pretty-print
 the output.

 Date: 6/29/2007
 Author: Berlin Brown
"""

import urllib2 
import time

PIPE_HOST = "http://www.botspiritcompany.com"
PIPE_ACCESS_POINT = "/botlist/spring/pipes/search_pipes.html?querymode=enabled&query=bush"

def connectPipes(url):
    request=urllib2.Request(url)
    request.add_header('User-Agent', 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT')
    opener = urllib2.build_opener()
    data = opener.open(request).read()
    return data

def parsePipeData(data):
    lines = data.split('\n')
    for line in lines:
        try:
            url, title, list_datetime = line.split("\t")
            print "******************"
            print "title=%s\n  url=%s" % (title, url)
        except:
            print "WARN: invalid format"

if __name__ == '__main__':
    print "running pipes connect"
    fullURL = "%s%s" % (PIPE_HOST, PIPE_ACCESS_POINT)
    start = time.time()
    data = connectPipes(fullURL)
    parsePipeData(data)
    end = time.time()
    diff_time = end - start
    print "executed in=%s" % diff_time
    print "done"

