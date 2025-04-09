<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Global Search</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Global Search">
<META NAME="keywords" CONTENT="search, index, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/botverse/rss/botverse_rss.html" />">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

<script type="text/javascript">
	
	function submitForm(operation, linkid) {
		var theForm = document.forms[0];
		theForm.ratingId.value = linkid;
		theForm.operation.value = operation;
		theForm.submit();
	}
	
</script>

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">Botverse - Interesting things online | Post Popular Links, it is ok!</h1>
			
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>

<div style="margin: 10px;">

	<%-- Add Welcome user message and login content (above content border line) --%>
	<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
	<%-- End of Welcome Header --%>

<div style="border: 1px solid #DDD; padding: 10px; margin-right: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">			
	<p>		
	<!-- table just for search -->
	<table class="sample">
	<tr>
		<th>
			&nbsp;Search
		</th>						
	</tr>
	<tr>
	<td>
			<!-- Begin Search Form -->
			<form method="get" action="<c:url value="/spring/search/global_search.html" />">
				<table>
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
			<!-- End of Form -->			
	</td>
	</tr>
	</table>
	<!-- End of Table -->
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

 <%-- Debug, how long to process page --%>
 <div style="font-size: 10px; color: #888;text-align: right">
 <i>(process in <c:out value="${processingtime}" />s)</i>
 </div>
  
</body>
</html>
