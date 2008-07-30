##
## Description on running the Botlist Unit Tests
##

To run an individual test, use the 'run.single.test' target.
Edit the build.properties file and set the 'single.test' key.  Launch the target.
===================
 For example:
 
 single.test=test_group_links.py
 
Edit the particular python based unit test ie, test_group_links.py:
===================
        
def testGroupLinks(self): (! Add the test)
	c = applicationContext.getBean("radController")
	dao = c.linkGroupsDao
	self.assertNotNull(dao)

def suite():
    testClassObject = testGroupLinks (! edit the name of the class here)
===================

