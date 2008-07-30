##
## Berlin Brown
##

import com.meterware.httpunit as http

import all_tests
import config as cfg
import junit

from org.spirit.util.xml import EscapeHTML
from org.spirit.util.text import KeywordProcessor

class testKeywords (junit.framework.TestCase):
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()

    def testCreateFilenameTitle(self):
        str = "This is the title I want to create 2233 999--3823**2323kj err"
        s = KeywordProcessor.createFilenameTitle(str)
        self.assertEquals(s, "this_is_the_title_i_want_to_create_2233_99938232323kj_err")

    def testTagViewKeywords(self):
        str = "g@@##%@#!@@$!@$ I am     \n\ttesting it @(#()!@#(*)"
        s = KeywordProcessor.tagViewKeywords(str, "style", "/spring/botverse_tags?defkeytagid=", "")
        self.assertEquals(s, '<a style="style" href="/spring/botverse_tags?defkeytagid=g">g</a>\n<a style="style" href="/spring/botverse_tags?defkeytagid=i">i</a>\n<a style="style" href="/spring/botverse_tags?defkeytagid=am">am</a>\n<a style="style" href="/spring/botverse_tags?defkeytagid=testing">testing</a>\n<a style="style" href="/spring/botverse_tags?defkeytagid=it">it</a>\n')

    def testCreateKeyword(self):
        str = "This is my tag@@##%@#!@@$!@$ I am testing it @(#()!@#(*)"
        s = KeywordProcessor.createKeywords(str)
        self.assertEquals(s, "this is my tag i am testing it")

        str = ""
        s = KeywordProcessor.createKeywords(str)
        self.assertEquals(s, "")
                    
def suite():

    # Change the following two class references
    suite = junit.framework.TestSuite()
    for i in dir(testKeywords):
        if i.startswith("test"):
            p = testKeywords(i)
            suite.addTest(p)
            
    return suite

# End of Script
