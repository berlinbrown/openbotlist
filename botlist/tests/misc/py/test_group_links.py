##
## Berlin Brown
##
import random
import all_tests
import junit
from java.lang import Long

from org.spirit.bean.impl import BotListGroupLinks

class testGroupLinks (junit.framework.TestCase):
    
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()
        
    def testAddNewGroupLink(self):
        randlinkid = random.randint(1, 90000)
        newrandlink = "%s%s.com" % ("http://www.new", randlinkid)
        
        c = applicationContext.getBean("radController")
        dao = c.linkGroupsDao
        daoGroupLinks = c.groupLinksDao
        
        self.assertNotNull(dao)        
        linkGroup = dao.readLinkGroup("aaf9dfb546f650d5fa614156000info")
        self.assertNotNull(linkGroup)

        linkGroupId = linkGroup.id
        sessionFactory = dao.getSessionFactory()
        hbm_session = sessionFactory.openSession()
        tx = hbm_session.beginTransaction()
        linkGroupBean = hbm_session.load("org.spirit.bean.impl.BotListLinkGroups", linkGroupId, None)
        self.assertNotNull(linkGroupBean)
        # Error with jython proxying, unable to save one-to-many
        
        link = BotListGroupLinks()        
        link.groupId = 4
        link.urlTitle = 'mytitle'
        link.mainUrl = newrandlink
        link.keywords = 'abc'

        daoGroupLinks.createGroupLink(link)
        tx.commit()
        hbm_session.close()
        
    def testGroupLinks(self):
        c = applicationContext.getBean("radController")
        dao = c.linkGroupsDao
        self.assertNotNull(dao)

        linkGroup = dao.readLinkGroup("aaf9dfb546f650d5fa614156000info")
        self.assertNotNull(linkGroup)
        self.assertEquals(linkGroup.generatedId, "aaf9dfb546f650d5fa614156000info")
        
def suite():
    testClassObject = testGroupLinks

    # Donot edit below.
    suite = junit.framework.TestSuite()
    # Edit the two class names
    for i in dir(testClassObject):
        if i.startswith("test"):
            p = testClassObject(i)
            suite.addTest(p)
            
    return suite

# End of Script
