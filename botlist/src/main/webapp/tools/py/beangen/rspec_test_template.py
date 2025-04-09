'''
****************************************
 rspec test template
 Author: Berlin Brown
 Date: 3/10/2008
 License: New BSD License
****************************************
'''

RSPEC_HEADER='''########################################
# Berlin Brown
# Date: 3/3/2008
# tests/integration/ruby/rspec
########################################

include_class 'java.text.SimpleDateFormat' unless defined? SimpleDateFormat
include_class "java.util.Calendar" unless defined? Calendar
'''

RSPEC_FOOTER='''
end
# End of File
'''

def TEST_HEADER(header_text):
    CREATE_TEST_HEADER='''
describe "%s=" do  
  before(:each) do
    @ac = $context
    @rad_controller = @ac.getBean("radController")
    @cur_sess_id = rand(1000000)
  end

'''  % header_text
    return CREATE_TEST_HEADER

# End of File


