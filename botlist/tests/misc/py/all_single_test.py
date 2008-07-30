##
##

import sys
import junit
import os
import config as cfg

from java.lang import System

from org.springframework.context.support import FileSystemXmlApplicationContext

TOTAL_TESTS = 0
applicationContext = None

def walktree(dir, callback, result, select_file):
    '''recursively descend the directory rooted at dir,
    calling the callback function for each regular file'''    
    for f in os.listdir(dir):        
        pathname = '%s/%s' % (dir, f)
        if os.path.isdir(pathname):
            # It's a directory, recurse into it
            walktree(pathname, callback, result, select_file)
        elif os.path.isfile(pathname):
            # It's a file, call the callback function
            if f.endswith("py") and f == select_file:
                callback(pathname, f, result)
        else:
            # Unknown file type, print a message
            print 'Skipping %s' % pathname

def visitfile(path, file, result):
    global TOTAL_TESTS
    if file == "all_tests.py" or file == 'config.py' or file == 'all_tests_prod.py' or file == 'all_single_test.py':
        return    
    print "test file: %s" % file    
    execfile(path, globals())
    s = suite()
    tests = s.tests()
    while tests.hasMoreElements():
        et = tests.nextElement()
        result.addTest(et)
        TOTAL_TESTS = TOTAL_TESTS + 1
        
if __name__ == '__main__':

    # load the application context
    print "Application Context: %s" % sys.argv[2]

    # Load the multiple config files
    arr = [sys.argv[2], sys.argv[3]]
    select_filename = sys.argv[4]
    
    applicationContext = FileSystemXmlApplicationContext(arr)
    suite_result = junit.framework.TestSuite()
    walktree(os.path.join(os.getcwd(), "py"), visitfile, suite_result, select_filename)
    
    junit.textui.TestRunner.run(suite_result)    
    print "Total Tests: %s" % TOTAL_TESTS
    
## End of Script
