###
### Author: Berlin Brown
### spec_helper.rb
### Date: 2/22/2008
### Description: RSpec, JRuby helper for setting up
### spring rspec tests for botlist
###
# -------------------------- COPYRIGHT_AND_LICENSE --
# Botlist contains an open source suite of software applications for 
# social bookmarking and collecting online news content for use on the web.  
# Multiple web front-ends exist for Django, Rails, and J2EE.  
# Users and remote agents are allowed to submit interesting articles.
#
# Copyright (c) 2007, Botnode.com (Berlin Brown)
# http://www.opensource.org/licenses/bsd-license.php
#
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without modification, 
# are permitted provided that the following conditions are met:
#
#	    * Redistributions of source code must retain the above copyright notice, 
#	    this list of conditions and the following disclaimer.
#	    * Redistributions in binary form must reproduce the above copyright notice, 
#	    this list of conditions and the following disclaimer in the documentation 
#	    and/or other materials provided with the distribution.
#	    * Neither the name of the Botnode.com (Berlin Brown) nor 
#	    the names of its contributors may be used to endorse or promote 
#	    products derived from this software without specific prior written permission.
#	
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
# "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
# LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
# A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
# -------------------------- END_COPYRIGHT_AND_LICENSE --
#

lib_path = File.expand_path("#{File.dirname(__FILE__)}/../../lib")
$LOAD_PATH.unshift lib_path unless $LOAD_PATH.include?(lib_path)

require 'spec'
require 'java'
include_class('java.lang.String') { 'JString' }
include_class('java.lang.System') { 'JSystem' }

#
# quick BUG-FIX: Helper method for working with windows.  Windows and java require
# the file URL path to have the following form.
#
# file:///usr/etc...
#
# Running this script under windows might use the following path, this is invalid:
#
# file://C://usr/etc..
def fix_windows_path(cur_spring_conf)
  if cur_spring_conf.match(/^(C|c|b|d|e):\//)
    return "/#{cur_spring_conf}"
  end  
  return cur_spring_conf
end

# Have to manually find the spring config files
spring_config = File.expand_path("#{File.dirname(__FILE__)}/../../../../WEB-INF/botlistings-servlet.xml")
spring_util_config = File.expand_path("#{File.dirname(__FILE__)}/../../../../WEB-INF/spring-botlist-util.xml")

# The logs will get output here
JSystem.getProperties().put("catalina.base", "util")

# Ensure correct java type String for spring config filename array
# spring_conf_arr = Java::JavaClass.for_name("java.lang.String").new_array(2)

string_class = Java::JavaClass.for_name("java.lang.String")
spring_conf_arr = string_class.new_array(2)
spring_conf_arr[0] = Java.primitive_to_java("file://" + fix_windows_path(spring_config))
spring_conf_arr[1] = Java.primitive_to_java("file://" + fix_windows_path(spring_util_config))

#
# Spring specific includes
include_class "org.springframework.context.support.FileSystemXmlApplicationContext"
# Set the application context.  Use this in the rspec tests.
$context = FileSystemXmlApplicationContext.new(spring_conf_arr)

# End of File
