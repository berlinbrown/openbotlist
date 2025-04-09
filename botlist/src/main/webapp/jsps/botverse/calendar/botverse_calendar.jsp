<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse - Calendar View</title>
<meta name="DESCRIPTION" content="Botverse - Calendar View, popular links by date"></meta>
<meta name="keywords" content="date view, listing, bot, botlist, botlisting, bot's list, list, ads, advertising"></meta>

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<link href="<c:url value="/company/stylesheets/botlist_calendar.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>

<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>
</style>

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />" width="720" height="51" alt="Roof Logo" />
 </div>
<h1 class="bot_titlelogo">Botverse - Calendar View</h1>			
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

	<%-- Build the table for entering the new department information --%>
	<%@include file="/WEB-INF/jsps/general/botverse_navigation.jsp" %>	
	<%-- End of Table for Botverse Navigation Links --%>
	
	<div style="color: #888;">
		Calendar View - view top past articles
	</div>
	
	<%-- ==== Day - 6 (ie Current Day/Sunday) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats6'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats6']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>
	<%-- ==== (end) Day - 6 ==== --%>
	
	<%-- ==== Day - 5 (ie Sat) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats5'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats5']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>
	<%-- ==== (end) Day - 5 ==== --%>
			
	<%-- ==== Day - 4 (ie Fri) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats4'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats4']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>
	<%-- ==== (end) Day - 4 ==== --%>
	
	<%-- ==== Day - 3 (ie Thurs) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats3'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats3']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>
	<%-- ==== (end) Day - 3 ==== --%>
	
	<%-- ==== Day - 2 (ie Weds) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats2'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats2']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>	
	<%-- ==== (end) Day - 2 ==== --%>
		
	<%-- ==== Day - 1 (ie Tues) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats1'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats1']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>
	<%-- ==== (end) Day - 1 ==== --%>
	
	<%-- ==== Day - 0 (ie Mon) ==== --%>
	<h3 class="calendar_header" >
	 <fmt:formatDate pattern="EE dd, MMM yy" value="${command.linksDates['stats0'].time}" />
	</h3>
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.linksOnDates['stats0']}"  var="listing" varStatus="status">		
			<%@include file="/WEB-INF/jsps/general/botverse_calendar.jsp" %>				
		</c:forEach>
	</table>
	<%-- ==== (end) Day - 0 ==== --%>
		
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
