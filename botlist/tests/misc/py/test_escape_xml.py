##
## Berlin Brown
##

import com.meterware.httpunit as http

import all_tests
import config as cfg
import junit

from org.spirit.util.xml import EscapeHTML

class testEscapeXML (junit.framework.TestCase):
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()

    def testEscapeHtmlLink(self):
        str = EscapeHTML.escapeURL("http://www.yahoo.com?hello=4&abc=5")
        self.assertEquals(str, "http%3A%2F%2Fwww.yahoo.com%3Fhello%3D4%26abc%3D5")
    
        
def suite():

    # Change the following two class references
    suite = junit.framework.TestSuite()
    for i in dir(testEscapeXML):
        if i.startswith("test"):
            p = testEscapeXML(i)
            suite.addTest(p)
            
    return suite

# End of Script
