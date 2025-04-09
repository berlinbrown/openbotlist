'''
# Author: Berlin Brown
# Date: 3/10/2008
#
# Client to generate basic spring bean components
# used throughout this application
#
# Run with python(cpython)
# Copyright (c) 2007, Botnode.com (Berlin Brown)
# See LICENSE.BOTLIST for the most recent license updates.
#
# http://www.opensource.org/licenses/bsd-license.php
# 
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without modification, 
# are permitted provided that the following conditions are met:
#
#    * Redistributions of source code must retain the above copyright notice, 
#    this list of conditions and the following disclaimer.
#    * Redistributions in binary form must reproduce the above copyright notice, 
#    this list of conditions and the following disclaimer in the documentation 
#    and/or other materials provided with the distribution.
#    * Neither the name of the Newspiritcompany.com (Berlin Brown) nor 
#    the names of its contributors may be used to endorse or promote 
#    products derived from this software without specific prior written permission.
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
#
'''

__author__ = "Berlin Brown"
__version__ = "0.0.1"

'''
 Example:
 *****************************
  
 print "ini['Startup']['modemid'] =", ini['Startup']['modemid'] 
 print "ini.Startup =", ini.Startup
 print "ini.Startup.modemid =", ini.Startup.modemid

 print "ini['Startup']['modemid'] =", ini['Startup']['modemid']
 *****************************
'''
import sys
import os
import pprint

from pyparsing.pyparsing import \
     Literal, Word, ZeroOrMore, Group, Dict, Optional, \
     printables, ParseException, restOfLine

from beangen import BeanGen

APP_HEADER = '''Botlist - Beangen Code Generation Library.
Version: %s
--------------------''' % __version__

inibnf = None
def inifile_BNF():
    
    global inibnf    
    if not inibnf:
        
        # punctuation
        lbrack = Literal("[").suppress()
        rbrack = Literal("]").suppress()
        equals = Literal("=").suppress()
        semi   = Literal(";")
        
        comment = semi + Optional( restOfLine )
        
        nonrbrack = "".join( [ c for c in printables if c != "]" ] ) + " \t"
        nonequals = "".join( [ c for c in printables if c != "=" ] ) + " \t"
        
        sectionDef = lbrack + Word( nonrbrack ) + rbrack
        keyDef = ~lbrack + Word( nonequals ) + equals + restOfLine
        
        # using Dict will allow retrieval of named data fields as attributes of the parsed results
        inibnf = Dict( ZeroOrMore( Group( sectionDef + Dict( ZeroOrMore( Group( keyDef ) ) ) ) ) )
        
        inibnf.ignore( comment )
        
    return inibnf

def load_script( strng ):
    try:
        iniFile = file(strng)
        iniData = "".join( iniFile.readlines() )
        bnf = inifile_BNF()
        tokens = bnf.parseString( iniData )

    except ParseException, err:
        print "********* Err *************"
        print err.line
        print " "*(err.column-1) + "^"
        print err
    
    iniFile.close()
    print
    return tokens

def setup_output_dir():
    '''
    Check that the output directory exists
    '''
    if not os.path.exists('./output'):
        print "Warning: output directory does not exist, creating."
        os.mkdir("./output")
                            
def process_config(ini):
    '''
    Process the Configuration file 
     Default sections:
     System
     Table
     Fields
    '''
    if ini.System and ini.Table and ini.Fields:
        print "Valid configuration, continue"

        bean = ini.System['bean_package']
        dao = ini.System['dao_package']
        base = ini.System['base_package']
        table = ini.Table['table_name']

        dfields = {}
        for node in ini.Fields.keys():
            fkey = node.strip()
            dfields[fkey] = ini.Fields[node].strip()
            
        beangen = BeanGen()
        beangen.bean_package = bean.strip()
        beangen.dao_package = dao.strip()
        beangen.base_package = base.strip()
        beangen.table_name = table.strip()
        beangen.fields = dfields
        beangen.generateClassNames()
        beangen.generateClassFiles()
        beangen.toString()
        
    else:
        print "** ERR: Invalid configuration, current ini="
        print ini               
    
def main():
    import getopt
    print APP_HEADER
    
    sysargs = sys.argv
    if len(sysargs) != 3:
        print "usage: -f [INPUT FILE]"
        return
    
    optlist, args = getopt.getopt(sysargs[1:], "f:")
    print "Args=", optlist, args
    print "usage: -f [INPUT FILE]"
    
    for (option, value) in optlist:  
        if option == '-f':  
            inpath = value

    # Setup the output directory
    setup_output_dir()
    
    ini = load_script(inpath)
    process_config(ini)
            
    # Load the INI configuration file
    print "<INFO> ------------------------"
    print "<INFO> Code generation files sent to the 'output' directory"
    print "<INFO> ------------------------"
    print "Done."

if __name__ == '__main__':
    main()

# End of File


