#
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
########################################
# Berlin Brown
# Date: 3/3/2008
# tests/integration/ruby/rspec
########################################

require 'rexml/document'
include REXML		

require File.join(File.dirname(__FILE__), '../../../../WEB-INF/lib/ruby/web/foaf', 'edit_foaf')

include_class 'java.text.SimpleDateFormat' unless defined? SimpleDateFormat
include_class "java.util.Calendar" unless defined? Calendar
include_class "org.spirit.bean.impl.BotListEntityTypeFoaf" unless defined? BotListEntityTypeFoaf

describe "For manipulating objects=" do  
  before(:each) do
    @ac = $context
    @rad_controller = @ac.getBean("radController")
    @cur_sess_id = rand(1000000)
  end
  
  it "Should manipulate the entity_type_foaf" do 
    dao = @rad_controller.entityLinksDao
    mock_obj = BotListEntityTypeFoaf.new
    mock_obj.foafInterestDescr = ""
    mock_obj.rating = ""
    mock_obj.fullName = ""
    mock_obj.foafMbox = ""
    mock_obj.dateOfBirth = ""
    mock_obj.friendSetUid = ""
    mock_obj.foafPageDocUrl = ""
    mock_obj.foafImg = ""
    mock_obj.processCount = ""
    mock_obj.nickname = ""
  end

  it "Should test simple unit <foaf xml>" do
    
    STR_XML = <<EOF
<?xml version="1.0"?>
<rdf:RDF 
	xml:lang="en" 
	xmlns:wot="http://xmlns.com/wot/0.1/" 
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
	xmlns:dct="http://purl.org/dc/terms/"
	xmlns:lang="http://purl.org/net/inkel/rdf/schemas/lang/1.1#"
	xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:rss="http://purl.org/rss/1.0/" xmlns="http://xmlns.com/foaf/0.1/" xmlns:foaf="http://xmlns.com/foaf/0.1/" xmlns:wn="http://xmlns.com/wordnet/1.6/" xmlns:air="http://www.megginson.com/exp/ns/airports#" xmlns:contact="http://www.w3.org/2000/10/swap/pim/contact#" xmlns:dc="http://purl.org/dc/elements/1.1/">

  <foaf:PersonalProfileDocument rdf:about="http://danbri.org/foaf.rdf">
        <wot:assurance rdf:resource="foaf.rdf.asc"/>
        <foaf:primaryTopic rdf:resource="#danbri"/>
  </foaf:PersonalProfileDocument>

  <Person rdf:ID="danbri">
    <foaf:name>Dan Brickley</foaf:name>

    <foaf:nick>danbri</foaf:nick>
    <foaf:weblog rdf:resource="http://danbri.livejournal.com/"/>
    <foaf:homepage rdf:resource="http://danbri.org/" dc:title=""/>
    <foaf:jabberID>danbri@jabber.org</foaf:jabberID>
    <foaf:aimChatID>danbri_2002</foaf:aimChatID>
    <foaf:msnChatID>danbri@hotmail.co.uk</foaf:msnChatID>

    <mbox rdf:resource="mailto:danbri@danbri.org"/>
    <mbox rdf:resource="mailto:danbri@porklips.org"/>

    <!-- Some retired mailboxes ...-->

    <mbox> 
      <rdf:Description rdf:about="mailto:danbri@w3.org"> 
        <dct:isReplacedBy rdf:resource="mailto:danbri@danbri.org"/>
      </rdf:Description>
    </mbox>

    <mbox_sha1sum>6e80d02de4cb3376605a34976e31188bb16180d0</mbox_sha1sum>
    <mbox_sha1sum xml:lang="">6e80d02de4cb3376605a34976e31188bb16180d0</mbox_sha1sum>

   <foaf:openid rdf:resource="http://danbri.org/"/>

    <lang:masters>en</lang:masters>
    <homepage rdf:resource="http://danbri.org/"/>
    <foaf:dateOfBirth>1972-01-09</foaf:dateOfBirth>
    <foaf:img rdf:resource="http://rdfweb.org/people/danbri/2000/01/01/Image1.gif"/>
    <wot:keyid>B573B63A</wot:keyid>

    <!-- connections to other peoples -->
    <rdfs:seeAlso rdf:resource="http://swordfish.rdfweb.org/discovery/2001/08/codepict/scutterplan.rdf"/>
    <rdfs:seeAlso rdf:resource="http://xmlns.com/foaf/0.1/"/>

    <!-- can do brief summary here, or longer/verbose, see below -->

    <knows>
      <Person>
        <foaf:nick>djgrw</foaf:nick>
        <mbox rdf:resource="mailto:libby.miller@bristol.ac.uk"/>
        <mbox rdf:resource="mailto:libby@asemantics.com"/>
      </Person>
    </knows>

    <knows>
      <Person rdf:about="http://www.w3.org/People/Berners-Lee/card#i">
       <name>Tim Berners-Lee</name>
       <isPrimaryTopicOf rdf:resource="http://en.wikipedia.org/wiki/Tim_Berners-Lee"/>

       <homepage rdf:resource="http://www.w3.org/People/Berners-Lee/"/>
       <mbox rdf:resource="mailto:timbl@w3.org"/>
      <rdfs:seeAlso rdf:resource="http://www.w3.org/People/Berners-Lee/card"/>
     </Person>
    </knows>

  </Person>
</rdf:RDF>
EOF
    mock_url = "http://danbri.org/foaf.rdf"
    url_content = STR_XML
    xml_doc = Document.new(url_content)
    foaf_doc = BotListFoaf::FoafDocument.new
    @foaf_type = foaf_doc.transformFoafType(mock_url, xml_doc)
    puts @foaf_type
  end
  
  it "Should test simple unit" do
    dao = @rad_controller.entityTypeFoafDao
    foaf = BotListFoaf::FoafHandler.new(dao, @log, "http://danbri.org/foaf.rdf")
    res = foaf.createFoaf
    sleep(1)
    # Launch another thread
    foaf_berlin = BotListFoaf::FoafHandler.new(dao, @log, "http://berlinbrown.livejournal.com/data/foaf")
    foaf_berlin.createFoaf
    # Delay so that the main thread does not exit
    sleep(7)
  end

  # End of module
end
# End of File
