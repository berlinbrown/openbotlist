'''
 ***************************************
 Author: Berlin Brown
 Date: 3/10/2008
 File: ruby_biz_codgen.py - Ruby business logic code generation
 License: New BSD License
 ***************************************
'''
import java_template as java
import ruby_biz_template as biz

class BeanGenRubyLogic:
    
    def __init__(self, beangen):
        # Composition, associate the main beangen with this beangen object
        self.beangen = beangen
        
    def get_full_pojo_include(self):
        return "include_class \"%s.%s\" unless defined? %s\n" % (self.beangen.bean_package, self.beangen.bean_class_name, 
                                                               self.beangen.bean_class_name)
    
    def writeMockCreateFields(self, fout):
        ''' Write the field names with default values used during
        mock create. Default dynamic type is string'''
        for node in self.beangen.fields.keys():
            fout.write("    mock_obj.%s = \"\"\n" % (self.beangen.mangleName(node)))
    
    def writeBeanCreate(self, fout):
        clean_bean_name = self.beangen.table_name.lower()
        fout.write("  it \"Should create the %s\" do \n" % clean_bean_name)
        fout.write("    dao = @rad_controller.entityLinksDao\n")
        fout.write("    mock_obj = %s.new\n" % self.beangen.bean_class_name)
        self.writeMockCreateFields(fout)
        fout.write("  end\n")        

    def writeBeanRead(self, fout):
        clean_bean_name = self.beangen.table_name.lower()
        fout.write("  it \"Should manipulate the %s\" do \n" % clean_bean_name)
        fout.write("    dao = @rad_controller.entityLinksDao\n")
        fout.write("    mock_obj = %s.new\n" % self.beangen.bean_class_name)
        self.writeMockCreateFields(fout)
        fout.write("  end\n")        

    def generateRubyLogic(self):
        try:
            clean_bean_name = self.beangen.table_name.lower()
            filename = "./output/%s_jsps.jsp" % (clean_bean_name)
            f = open(filename, 'w')
            
            f.write(biz.JSP_HEADER)
            f.close()
            
            #*****************
            # Build the create test case for creating mock objects.
            #*****************
            filename = "./output/%s_jsps.rb" % (clean_bean_name)
            f = open(filename, 'w')

            f.write(java.RUBY_LIC_HEADER)
            #f.write(rspec.RSPEC_HEADER)
            #f.write(self.get_full_pojo_include())
            #f.write(rspec.TEST_HEADER('Should create test objects'))
            #self.writeBeanCreate(f)
            #f.write(rspec.RSPEC_FOOTER)
            f.close()
            
        except Exception, e:
            print "ERROR: generate ruby business, error creating class."
            print e

# End of File

