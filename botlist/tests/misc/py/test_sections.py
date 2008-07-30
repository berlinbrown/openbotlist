##
## Berlin Brown
##
import all_tests
import junit

class testSections (junit.framework.TestCase):
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()
        
    def testSections(self):
        # TODO add your test code below by replacing the default call to print.
        c = applicationContext.getBean("radController")
        sectionsDao = c.postSectionsDao
        list = sectionsDao.listSections("from org.spirit.bean.impl.BotListPostSections")
        hasValue = 0
        for n in list:            
            if n.sectionName == 'General Listings':
                hasValue = 1
        self.assertEquals(hasValue, 1)
        
        hasValue = 0
        for n in list:            
            if n.sectionName == 'Personals':
                hasValue = 1                
        self.assertEquals(hasValue, 1)
        
        
def suite():
    suite = junit.framework.TestSuite()
    for i in dir(testSections):
        if i.startswith("test"):
            p = testSections(i)
            suite.addTest(p)
            
    return suite

# End of Script
