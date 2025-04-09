<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse - Interesting things online</title>

<meta name="DESCRIPTION" content="Botverse - Popular links all in the same place.  Discover new links and articles on the web.  Like Reddit or Digg but for adults."></meta>
<meta name="keywords" content="botverse, listing, bot, botlist, botlisting, bot's list, list, ads, advertising, atlanta technology, read it, dig it, reddit, digg, stumbleupon"></meta>

<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/botverse/rss/botverse_rss.html" />" ></link>
<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<link href="<c:url value="/company/stylesheets/botlist_general2.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

<%-- Include jquery javascript library --%>
<script type="text/javascript" src="<c:url value="/company/js/jq/jquery-1.1.4.pack.js" />" ></script>

<script type="text/javascript">
	
	$(document).ready(function() {	
	});
	
	function submitForm(operation, linkid) {
		// deprecated	
	}
		
	function submitAjaxVote(oper_action, linkid) {
		// Submit vote information up/down vote based on linkid
		$(document).ready(function() {
			$.ajax({
			    url: '/botlist/spring/ajax/vote.html',
			    type: 'POST',
			    dataType: 'xml',
			    timeout: 5000,
			    data: {
			    	ratingId: linkid,
			    	operation: oper_action
			    }, // Post Data
			    error: function(xmlReqObj, errStr, expObj) {
			        alert('Error loading XML document');
			    },
			    success: function(xml) {
			        // set the bot message from the xml
			        setXmlMessage(xml);
			    }
			}); // End ajax call
		}); // End of call to "isReady"
		
	} // End of submit ajax 
	
	function setBotVoteMessage(msg, resp_status) {
	
		var css_msg_class = "ajx_msg_pass";
		if (resp_status == 'pass') {
			css_msg_class = "ajx_msg_pass"
		} else {
			css_msg_class = "ajx_msg_fail"
		}
	
		// set the message and green/red css class
		$('div#bot_msg_vote').html('! message: ' + msg)
			.addClass(css_msg_class);
		
	}	
	function setXmlMessage(xml) {
	
		var message_text = "invalid message";
		var resp_status;
		$(xml).find('message').each(function() {
			message_text = $(this).text();
    	}); // End of find call
    	
    	$(xml).find('status').each(function() {
			resp_status = $(this).text();
    	}); // End of find call    	
    	setBotVoteMessage(message_text, resp_status);
    }	
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

<div style="margin: 10px;">

	<%-- Add Welcome user message and login content (above content border line) --%>
	<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
	<%-- End of Welcome Header --%>

<div id="link_content_group_botverse">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link">

	<%-- Build the table for entering the new department information --%>
	<%@include file="/WEB-INF/jsps/general/botverse_navigation.jsp" %>	
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
		</div><!-- End of Form -->		
	</td>
	</tr>	
	<tr>
		<td colspan="2">
					<%-- ============================== --%>
					<%-- Display Media Table if enabled --%>
					<%-- ============================== --%>
					<c:if test="${command.mediaListEnabled}">
					 <div style="margin-top: 0px;">
						<table cellspacing="0" cellpadding="2">
						<tr>
							<td width="100%">
								<span style="background-color: #e8e8e8; padding: 4px; width: 100%;">
								<b>related media feeds</b>
								</span>
							</td>
						</tr>
						<tr>
						<td >
						 <%-- Additional Row to Hold Table Horizontal Media Section --%>
						 <table style="width: 100%; height: 100%;" cellspacing="0" cellpadding="0" border="0"><tr><td>
							<c:forEach items="${command.mediaList}" var="curmedia" varStatus="status">							
									<td>
										<%-- Media Section is made up table (image, title) --%>
										<table cellspacing="0" cellpadding="12" width="100%" height="100%">
											<tr><td style="background-color: #e7f0f1;">
											   <a href="<c:out value="${curmedia.media.mediaUrl}" />" 
											   	   class="index_img">
												<img src="<c:out value="${curmedia.media.imageThumbnail}" />" border="0" width="130" />
											   </a>
											</td></tr>
											<tr><td style="background-color: #f1f1f1; width: 100%;">
												<b><c:out value='${botlistutil:getMaxWord(curmedia.media.mediaTitle, 22)}' /></b>
											</td></tr>
										</table>
										<%-- End of Media Table --%>
									</td>							
							</c:forEach>
						  </td></tr></table>
						  <%-- (end) Additional Row to Hold Table Horizontal Media Section --%>
						</td>
						</tr>
						</table>
					  </div>					
					</c:if>
					<%-- ============================== --%>
					<%-- End of Display Media table --%>
					<%-- ============================== --%>
		</td>
	</tr>	
	</table>
	<%-- End of Table search/banner --%>	
	<form:form method="post" name="botverse_form">
	<form:errors path="*" cssClass="general_field_error" />
	<%-- === Ajax vote message === --%>
	<div id="bot_msg_vote">
		&nbsp;		
	</div>
	<table class="linklist_data" cellspacing="2" cellpadding="0">
		<c:forEach items="${command.listings}"  var="listing" varStatus="status">
				<%-- Begin row production for botverse links --%>
				<tr>					
					<td colspan="3">
							<span class="rating_area">
							 <c:out value="${listing.rating}" /> pts
							</span>
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
								<c:if test="${not empty listing.urlDescription}">
									<%-- ======================== --%>
									<%-- == Updated: 2/2/2008; Add URL description text --%>
									<%-- ======================== --%>
									<tr>
										<td>
											<div class="home_descr_left">
												<span style="background-color: #ffcc66"><b>about:</b></span> <c:out value="${listing.urlDescription}" />
											</div>
										</td>
									</tr>
								</c:if>
				<tr>
				<td>
					<!-- Inner table for data/status information -->
					<table>
					<tr>
					<td>
						<div style="margin-left: 10px;" class="linklist_comments_txt">
							<c:set var="curStatusCount" value="${status.count}" />
							<c:set var="curPageOffset" value="${command.pagingform.pageOffset}" />
							<%-- ============== Vote Section ============= --%>
							<table cellspacing="0" cellpadding="0"><tr>
							  <td>
							     <b><c:out value="${curStatusCount + curPageOffset}" /># &nbsp;</b>
							  </td>
							  <td valign="top" style="padding-right: 4px;">
							     <%-- Temp location for ranking --%>							   
								 <b><span class="linklist_comments_date">mod</span></b> <span style="color: green; font-size: 10px"></span>
							  </td>
							  <td style="padding-right: 4px;" valign="bottom">
								 <a href="javascript: submitAjaxVote('upvote', <c:out value='${listing.id}' />)" class="linklist_sample">
									up
								 </a> /
							  </td>
							  <td style="border-right: 1px solid #e4e4e4; padding-right: 2px;" valign="bottom">
								  <a href="javascript: submitAjaxVote('downvote', <c:out value='${listing.id}' />)" class="linklist_sample">
								    dwn
								  </a>
							  </td></tr>
							</table>
							<%-- End Temp location for ranking --%>
							<%-- ============== Vote Section (end) ============= --%>
						</div>
					</td>
					<td>
						<div class="linklist_comments_txt">						 	
						 <span class="linklist_comments_date">						
							<botlistutil:timePast dateValue="${listing.createdOn.time}" />
							on <fmt:formatDate pattern="EE MMM, dd" value="${listing.createdOn.time}" />
						 </span>
						</div>
					</td>
					<td>
					  <span class="linklist_comments">
					     <a class="linklist_comments" href="<c:url value="/spring/botverse/linkviewcomments.html?viewid=${listing.id}&amp;commentsct=${fn:length(listing.listings)}" />">[!] comments (<c:out value="${fn:length(listing.listings)}" />)</a>
					  </span>						
					  | <span class="linklist_comments"><a class="linklist_comments" href="<c:url value="/spring/botverse/linkaddcomment.html?viewid=${listing.id}" />">add comment</a></span>
					  <%-- Print the UserName --%>
					  <span style="margin-left: 2px; font-size: 10px">
							<span class="linklist_comments_date"><strong>by
								<%-- ================================= --%>
								<%-- Display username if anonymous user %>
								<%-- Otherwise display 'link' to user's links --%>
								<%-- ================================= --%>								
								<c:choose>
   	 								<c:when test="${listing.coreUsername != null}" >
	  									<a href="<c:url value="/spring/profile/overview.html?username=${listing.coreUsername}" />" class="link_userprof">
	  										<c:out value="${listing.fullName}" />
	  									</a>
	 								</c:when>
	 								<c:otherwise>
	  									<c:out value="${listing.fullName}" />
     								</c:otherwise> 
								</c:choose>								
								<%-- ==== End of user links --%>
							</strong>							
							</span>
							<%-- ================================= --%>
							<%-- Display category name, eg 'politics' --%>
								<c:choose>
   	 								<c:when test="${listing.linkCategory != null}" >
							    		&nbsp;<span class="linklist_comments_date" style="color: black; background-color: <c:out value="${listing.linkCategory.categoryColor}" />">
											<c:out value="${listing.linkCategory.categoryName}" />
										</span>
									</c:when>
								</c:choose>
							<%--================================= --%>
					  </span>
					</td>
					</tr>
					</table>
					<!-- End of inner table (loop) -->				
				</td>
				</tr>
				<%-- End row production for botverse links --%>
		</c:forEach>
	</table>
	
	<form:hidden path="operation" />
	<form:hidden path="ratingId" />
	
	</form:form>
	
	<%-- ==== Section for paging ==== --%>
	<div id="linklist_paging"> 
		(view more) /
		<a href="<c:url value="/spring/botverse/botverse.html?offsetpage=0" />">first</a>
		<c:forEach begin="${command.pagingform.begin}" 
					end="${command.pagingform.end}" var="page" varStatus="status">
			&nbsp;/ [<span class="linklist_paging_pg">
				<a href="<c:url value="/spring/botverse/botverse.html?offsetpage=${page}" />">pg <c:out value="${page+1}" /></a> 
			</span>]
		</c:forEach> /
		<a href="<c:url value="/spring/botverse/botverse.html?offsetpage=${command.pagingform.begin+2}" />">next</a>
	</div>
	<%-- ==== End of Paging ==== --%>
	
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
 
<div>
 <script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
 </script>
 <script type="text/javascript">
 _uacct = "UA-286501-2";
 urchinTracker();
 </script>
</div>
  
</body>
</html>
