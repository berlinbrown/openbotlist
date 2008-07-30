
import all_tests
import junit

class testMyTest (junit.framework.TestCase):
    def __init__(self, name):
        junit.framework.TestCase.__init__(self, name)
        self.theTestFunction = getattr(self, name)
        
    def setUp(self):
        pass
    
    def tearDown(self):
        pass
    
    def runTest(self):
        self.theTestFunction()
        
    def testExample(self):
        # TODO add your test code below by replacing the default call to print.
        self.assertEquals("A", "A")

    def testExample2(self):
        # TODO add your test code below by replacing the default call to print.
        c = applicationContext.getBean("radController")
        sectionsDao = c.postSectionsDao
        list = sectionsDao.listSections("from org.spirit.bean.impl.BotListPostSections")
        for n in list:
            n.sectionName
        
def suite():
    suite = junit.framework.TestSuite()
    for i in dir(testMyTest):
        if i.startswith("test"):
            p = testMyTest(i)
            suite.addTest(p)
            
    return suite

# End of Script
