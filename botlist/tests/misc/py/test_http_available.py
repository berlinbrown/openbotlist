##
## Berlin Brown
##

import com.meterware.httpunit as http

import all_tests
import config as cfg
import junit

class testHttpAvail (junit.framework.TestCase):
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()

    def testAvailCity(self):
        sectpage = "/botlist"
        requrl = "%s/%s" % (cfg.PUBLIC_URL_HOME, sectpage)
        wc = http.WebConversation()
        resp = wc.getResponse(requrl)
        self.assertNotNull(resp)

        link = resp.getLinkWith( "Atlanta" )
        link.click()
        nextPage = wc.getCurrentPage()
        self.assertNotNull(nextPage)

        link = resp.getLinkWith( "Other" )
        link.click()
        nextPage = wc.getCurrentPage()
        self.assertNotNull(nextPage)


    def testAvailIndex(self):
        sectpage = "/botlist"
        requrl = "%s/%s" % (cfg.PUBLIC_URL_HOME, sectpage)
        wc = http.WebConversation()
        resp = wc.getResponse(requrl)
        self.assertNotNull(resp)

        link = resp.getLinkWith( "enter botverse.home" )
        link.click()
        nextPage = wc.getCurrentPage()
        self.assertNotNull(nextPage)

        link = resp.getLinkWith( "enter forums.home" )
        link.click()
        nextPage = wc.getCurrentPage()
        self.assertNotNull(nextPage)
        
def suite():

    # Change the following two class references
    suite = junit.framework.TestSuite()
    for i in dir(testHttpAvail):
        if i.startswith("test"):
            p = testHttpAvail(i)
            suite.addTest(p)
            
    return suite

# End of Script
