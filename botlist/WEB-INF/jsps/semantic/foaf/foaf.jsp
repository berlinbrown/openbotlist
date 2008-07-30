<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Friends of a Friend - find friends online</title>

<%@include file="/WEB-INF/jsps/general/botverse_incl/botverse_default_meta.jsp" %>
<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

<%-- Include jquery javascript library --%>
<script type="text/javascript" src="<c:url value="/company/js/jq/jquery-1.1.4.pack.js" />" ></script>

<script type="text/javascript">
		
</script>
</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />" width="720" height="51" alt="Roof Logo" />
 </div>
<h1 class="bot_titlelogo">Botverse - find interesting things online</h1>
			
<%-- Navigation Header --%>
<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
<%-- End of Navigation Header --%>

<div style="margin: 4px;">
	<h3 class="bot_headerinfo">
			The Friend of a Friend project is a web of machine-readable pages describing people, 
			links between them and the things they create and do. 
				<a href="http://www.foaf-project.org/">(foaf)</a>
	</h3>
</div>


<div style="margin: 10px;">

	<%-- Add Welcome user message and login content (above content border line) --%>
	<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
	<%-- End of Welcome Header --%>

<div id="link_content_group_botverse">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link">

	<%-- Build the table for entering the new department information --%>
	<%@include file="/WEB-INF/jsps/general/foaf_quick_navigation.jsp" %>	
	<%-- End of Table for Botverse Navigation Links --%>
				
	<%-- Table Section including banner and search  --%>	
	<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
	<td>
		<%-- Banner Section --%>
		<c:choose>
			<c:when test="${command.headline != null}" >
				<div style="color: #444; font-size: 14pt; background-color: #e7f0f1; padding: 6px;">
				  <c:out value="${command.headline}" />
				</div>
			</c:when>
		</c:choose>
		<%-- End of Banner Section --%>
	</td>
	<td align="right" valign="top">
		<!-- Begin Search Form -->
		<div align="right">		  
			<form method="get" action="<c:url value="/spring/search/search.html" />" name="newsearch">
			<table class="botverse_search_wrapper">
			<tr>
			<td> 
				<input name="query" size="26" />
			</td>
			<td>
				<input type="submit" value=" Search " />
			</td>
			</tr>
			</table>
			<input type="hidden" name="querymode" value="enabled" />
			</form>
		  </div>
		</div>
		<!-- End of Form -->		
	</td>
	</tr>
	</table>
	<%-- End of Table search/banner --%>
	<table class="linklist_data" cellspacing="2" cellpadding="0">
		<c:forEach items="${command.listings}"  var="listing" varStatus="status">
				<%-- Begin row production for botverse links --%>
				<tr>					
					<td colspan="3">							
							<a class="linklist_objlinks" href="<c:url value="${listing.mainUrl}" />" >
								<c:out value="${listing.urlTitle}" />
							</a>
							<%-- Add custom tag here, find hostname --%>
							<span class="linklist_comments_host">
								<%-- &nbsp;(<botlistutil:hostname value="${listing.mainUrl}" />) --%>
								&nbsp;(<span class="home_source_label">source:</span> <c:out value="${listing.hostnameDisplay}" /> <a href="<c:out value="${listing.hostnameDisplayUrl}" />" class="linklist_comments_host">+</a>)
							</span>
					</td>
				</tr>
			<%-- End row production for botverse links --%>
		</c:forEach>
	</table>
	
<%-- End DIV (bot_profile_sect_add_link) --%>
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
<%-- End DIV (body_content_center) --%>
</div>

 <%-- Debug, how long to process page --%>
 <div style="font-size: 10px; color: #888;text-align: right">
 <i>(process in <c:out value="${processingtime}" />s)</i>
 </div>
  
</body>
</html>
