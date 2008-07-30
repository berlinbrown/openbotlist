##
## Berlin Brown
##


import com.meterware.httpunit as http

import all_tests
import junit

URL_HOME = "http://localhost:8080/botlist/spring/mocktestslist"

class testHttpSections (junit.framework.TestCase):
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()

    def testLinkSections(self):
        sectpage = "mocktest_sections.html?city=1"
        requrl = "%s/%s" % (URL_HOME, sectpage)
        wc = http.WebConversation()
        resp = wc.getResponse(requrl)

        link = resp.getLinkWith( "Personals" )
        self.assertNotNull(link)

        link.click()
        genListingPage = wc.getCurrentPage()
        self.assertNotNull(genListingPage)

        links = genListingPage.getLinks()
        self.assertNotNull(links)

    def testSections(self):
        sectpage = "mocktest_sections.html?city=1"
        requrl = "%s/%s" % (URL_HOME, sectpage)
        wc = http.WebConversation()
        resp = wc.getResponse(requrl)
        self.assertNotNull(resp)
        hasValue = 0
        for n in resp.getLinks():
            if n.getText() == 'General Listings':
                hasValue = 1
        self.assertEquals(hasValue, 1)
        
def suite():

    # Change the following two class references
    suite = junit.framework.TestSuite()
    for i in dir(testHttpSections):
        if i.startswith("test"):
            p = testHttpSections(i)
            suite.addTest(p)
            
    return suite

# End of Script
