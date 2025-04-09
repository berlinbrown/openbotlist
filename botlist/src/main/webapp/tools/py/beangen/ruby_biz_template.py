'''
****************************************
 Author: Berlin Brown
 Date: 3/10/2008
 License: New BSD License
****************************************
'''

RUBY_HEADER='''########################################
# Berlin Brown
# Date: 3/3/2008
# Botlist - Ruby Business Logic
########################################

include_class 'java.text.SimpleDateFormat' unless defined? SimpleDateFormat
include_class "java.util.Calendar" unless defined? Calendar
'''

RUBY_FOOTER='''
end
# End of File
'''

JSP_HEADER='''<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html>
<head>
<title>Botlist</title>

<%@include file="/WEB-INF/jsps/general/botverse_incl/botverse_default_meta.jsp" %>
<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

<%-- Include jquery javascript library --%>
<script type="text/javascript" src="<c:url value="/company/js/jq/jquery-1.1.4.pack.js" />" ></script>

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">Botverse - find interesting things online</h1>
			
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>

<div style="margin: 8px;">

<%-- Add Welcome user message and login content (above content border line) --%>
<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
<%-- End of Welcome Header --%>

<div id="link_content_group_botverse">

<!-- Display the error message -->
<div class="bot_profile_sect_add_link">

<%-- Build the table for entering the new department information --%>
<%@include file="/WEB-INF/jsps/general/foaf_quick_navigation.jsp" %>	
<%-- End of Botverse Navigation Links --%>
					
<%-- [!] End DIV (bot_profile_sect_add_link) --%>
</div>

<%-- [!] End link content group --%>
</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
<%-- End DIV (body_content_center) --%>
</div>

<%-- Debug, how long to process page --%>
<div style="font-size: 10px; color: #999;text-align: right">
<i>(page processed in <c:out value="${processingtime}" />s)</i>
</div>
  
</body>
</html>
'''

# End of File

