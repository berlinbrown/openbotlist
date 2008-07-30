#
# Berlin Brown
# 11/10/2006
#
# To run, use:
# python beangen_client.py -f generate.ini
#
# Updates:
# ------------------
# 3/10/2008 - creating rspec test cases
#

import java_template as java
import rspec_test_template as rspec

from rspec_codegen import BeanGenRSpec
from ruby_biz_codegen import BeanGenRubyLogic

#
# BeanGen Class
class BeanGen:

    def __init__(self):
        self.bean_package = "org"
        self.dao_package = "org"
        self.base_package = "org"
        
        self.table_name = "table"
        self.fields = {}

        self.bean_class_name = None
        self.dao_interface_name = None
        self.dao_class_name = None
        
        #***********
        # Create helper beangen classes
        #***********
        self.rspec_bean = BeanGenRSpec(self)
        self.ruby_biz = BeanGenRubyLogic(self)
        
        # End of Constructor

    def writeBeanFieldMembers(self, f):
        f.write("\n")
        for node in self.fields.keys():
            f.write("\t\tprivate %s %s;\n" % (self.fields[node],
                                              self.mangleName(node)))
        f.write("\n")

    def writeDaoHandlerConfig(self, f):
        f.write("\n\n")
        f.write("BotListRubyDAOHandler.java:\n")
        field_val = "%sDao" % (self.mangleName(self.table_name))
        f.write("\tprivate %s %s = null;\n" % (self.dao_interface_name,
                                               field_val))
        
    def writeHbmConfig(self, f):
        f.write("\n")
        f.write("project.hbm.xml:\n")
        hbmclass = "%s.%s" % (self.bean_package, self.bean_class_name)
        f.write('\t<class name="%s" table="%s" dynamic-update="true">\n' % (hbmclass,
                                                      self.table_name))
        # Write the standard id field
        f.write(java.HBM_ID_FIELD)
        for node in self.fields.keys():
            f.write('\t\t<property name="%s" column="%s" not-null="false" />\n' %
                    (self.mangleName(node),
                     node))
            
        f.write("\n")
        f.write('\t</class>')

    #---------------------------------------------
    # Write various file definitions
    #---------------------------------------------

    def writeBeanClass(self, f, classname):
        f.write(java.JAVA_HEADER_COPYRIGHT)
        f.write("\npackage %s;\n" % self.bean_package)
        f.write("\nimport org.spirit.bean.impl.base.BotListBeanBase;\n")
        f.write(java.JAVA_HEADER_CLASS)
        f.write("\npublic class %s extends BotListBeanBase implements Serializable {\n" % classname)

    def writeFormClass(self, f, classname):
        f.write(java.JAVA_HEADER_COPYRIGHT)
        f.write("\npackage %s.form;\n" % self.base_package)
        f.write("\nimport org.spirit.form.base.BotListBaseForm;\n")
        f.write(java.JAVA_HEADER_CLASS)
        f.write("\npublic class %s extends BotListBaseForm {\n" % classname)
   
    def writeDAOImpl(self, f, classname):
        f.write(java.JAVA_HEADER_COPYRIGHT)
        f.write("\npackage %s.impl;\n" % self.dao_package)
        f.write("\nimport org.springframework.orm.hibernate3.support.HibernateDaoSupport;\n")
        f.write(java.JAVA_HEADER_CLASS)        
        f.write("\npublic class %s extends HibernateDaoSupport implements %s {\n" % (classname, self.dao_interface_name))

    def writeDAOInterface(self, f, classname):
        f.write(java.JAVA_HEADER_COPYRIGHT)
        f.write("\npackage %s;\n" % self.dao_package)
        f.write(java.JAVA_HEADER_CLASS)
        f.write("\npublic interface %s {\n" % classname)    
        
    def writeConfigFile(self, f):
        mangle_name = self.mangleName(self.table_name)
        f.write('\nspring-servlet.xml:\n\n')
        f.write('	<bean id="%sDaoBean" class="%s.impl.%s">\n' % (mangle_name, self.dao_package, self.dao_class_name))
        f.write(java.SPRING_CONFIG_DAO)

        f.write('\nspring-servlet.xml (radController):\n')
        f.write('\n\t<property name="%sDao">\n' % mangle_name)
		
        f.write('		<ref bean="%sDaoBean"/>\n' % mangle_name)
        f.write('\t</property>\n')

        # Write the hibernate config
        self.writeHbmConfig(f)

        # Write DAO Handler
        self.writeDaoHandlerConfig(f)
        
    #---------------------------------------------
    # Other Utilities
    #---------------------------------------------
        
    def mangleName(self, cur_name):
        ''' Mangle input string name to generate the hump casing '''
        name = cur_name.lower()
        name_arr = name.split('_')
        if len(name_arr) == 1:
            return name_arr[0]

        # Concatenate the mangled name
        s = []
        s.append(name_arr[0])
        for n in name_arr[1:]:
            s.append("%s%s" % (n[0].upper(), n[1:]))
            
        return "".join(s)
	
    def generateClassFiles(self):
        ''' Write the code generated files '''		
        filename = "./output/%s.java" % self.bean_class_name
        f = open(filename, "w")
        self.writeBeanClass(f, self.bean_class_name)
        self.writeBeanFieldMembers(f)
        f.write("\n}\n")
        f.close()

        filename = "./output/%s.java" % self.dao_interface_name
        f = open(filename, "w")
        self.writeDAOInterface(f, self.dao_interface_name)
        f.write("\n}\n")
        f.close()

        filename = "./output/%s.java" % self.dao_class_name
        f = open(filename, "w")
        self.writeDAOImpl(f, self.dao_class_name)
        f.write("\n}\n")
        f.close()

        filename = "./output/%s.java" % self.form_name
        f = open(filename, "w")
        self.writeFormClass(f, self.form_name)
        self.writeBeanFieldMembers(f)
        f.write("\n}\n")
        f.close()

        # Rspec test cases
        self.rspec_bean.generateRSpecTestCase()
        
        # Write the ruby business logic
        self.ruby_biz.generateRubyLogic()

        #*********************
        # Write the configurations
        #*********************
        filename = "./output/%sConfig.txt" % self.bean_class_name
        f = open(filename, "w")
        self.writeConfigFile(f)
        f.close()
         
    def generateClassNames(self):
        ''' Generate the Java Class Names,
        including: BeanImpl, DaoInterface, DaoImpl'''
        upper_first = "%s%s" % (
            self.mangleName(self.table_name)[0].upper(),
            self.mangleName(self.table_name)[1:])
        
        self.bean_class_name = "BotList%s" % upper_first        
        self.dao_interface_name = "BotList%sDAO" % upper_first
        self.dao_class_name = "BotList%sDAOImpl" % upper_first
        self.form_name = "BotList%sForm" % upper_first
        
    def toString(self):
        print "\nBeanGen Definition:"
        print "\tBean Package =", self.bean_package
        print "\tDAO Package =", self.dao_package
        print "\tBase Package =", self.base_package
        print "\tTable =", self.table_name
        
        print "\tField Definitions:"
        for node in self.fields.keys():
            print "\t", node, " =", self.fields[node]
            
        print "\tBean Class Name =", self.bean_class_name

# End of File
