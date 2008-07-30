##
## Berlin Brown
##
import all_tests
import junit

from org.spirit.bean.impl import BotListPostListing

class testPostListing (junit.framework.TestCase):
    
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()
        
    def testCreatePostListing(self):
        # TODO add your test code below by replacing the default call to print.
        c = applicationContext.getBean("radController")
        dao = c.postListingDao
        self.assertNotNull(dao)

        listing = BotListPostListing()
        listing.cityId = 1
        listing.sectionId = 2
        listing.category = 'none'
        listing.email = 'a@a.com'
        listing.location = 'none'
        listing.title = 'mytitle'
        listing.mainUrl = 'http://www.url.com'
        listing.message = 'This is my message'
        dao.createPostListing(listing)
        
def suite():
    testClassObject = testPostListing
    suite = junit.framework.TestSuite()
    # Edit the two class names
    for i in dir(testClassObject):
        if i.startswith("test"):
            p = testClassObject(i)
            suite.addTest(p)
            
    return suite

# End of Script
