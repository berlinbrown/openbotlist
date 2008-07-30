<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title><spring:message code="system.html.title"/></title>
 	
	<meta name="verify-v1" content="5vYTM0GqfzX+H+qkXwFSztV8Y7vHygc6kxtGldxcc+8=" ></meta> 	
  	<meta name="DESCRIPTION" content="Botlist - Promote yourself or something else interesting. Like Reddit or Digg but for adults.  New and exciting article web submissions with user ratings. (built with Scala, JRuby, Spring, Liftweb, Haskell technology)"></meta>
 	<meta name="keywords" content="articles, wikileaks, secret information, dune, obama 2008, ron paul 2008, article, submissions, web submit, user ratings, listing, bot, botlist, botlisting, bot's list, list, ads, advertising, social bookmarking, networking, social networking, reddit, digg, scala, jruby, liftweb"></meta>
   
  	<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/rss/listings_rss.html" />"></link>
	<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
  	<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
  	<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" ></link>
  	<link href="<c:url value="/company/stylesheets/botlist_general2.css" />" media="screen" rel="Stylesheet" type="text/css" ></link> 
  	
  	<style type="text/css">
	 <%@include file="/WEB-INF/jsps/general/botverse_link_stat.jsp" %>	
	</style>
</head>
<body>
		<div id="body_content_center">
			<div style="border-bottom: 1px solid #816943;">
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" alt="Botlist header logo" />
 			</div>
			<h1 class="bot_titlelogo">
				[ Botlist Home ] (thenetwork at ghostbots.com)
			</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>
			
			<div style="margin: 8px;">
				<h3 class="bot_headerinfo">				
				All your favorite links in one place.  Bookmark popular items for you and friends.  Check out what other people are interested in.								
				</h3>
			</div>			
			<!-- Section with City Listing -->
			<div style="margin: 8px;">
				
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/default_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>
				
				<div id="link_content_group">
				
				<!-- Table of Data Grid and Image -->
				<table width="100%" >				
				<tr>
				<td valign="top" align="left">
					<%-- ==== Next Set of Data, Recent Links ==== --%>
					<div style="margin-left: 4px;">
						<table width="100%">
						<tr>
							<td>
								<span style="background-color: #e8e8e8; padding: 4px; text-align: left;">&nbsp;most recent updates:</span>							
								&nbsp;/ <a href="<c:url value="/spring/botverse/botverse.html?filterset=mostrecent" />" class="linklist_botnav">view more</a> 
									/ <a href="<c:url value="/spring/botverse/botverse_submit.html" />" class="linklist_botnav">submit</a>
								&nbsp;&nbsp;<span style="color: #555;"><i>(<c:out value="${linkCount}" /> entries)</i></span>								
								<%-- Banner Section --%>
								<c:choose>
									<c:when test="${headline != null}" >
									  <div style="width: 80%;">
										<div style="color: #444; font-size: 14pt; background-color: #e7f0f1; padding: 6px;">
										  <c:out value="${headline}" />
										</div>
									 </div>
									</c:when>
								</c:choose>
								<%-- End of Banner Section --%>																		
							</td>
						</tr>
						<tr>
							<td valign="top">
									<%-- ================= --%>
									<%-- == 2/2/2008; Two column system, first set of links on left == --%>
									<%-- == TODO: figure out cleaner approach with jsp/struts includes  --%>
									<%-- ================= --%>
									<div id="two_col_left_links">
										<%@include file="/WEB-INF/jsps/general/botverse_incl/botverse_index_sect.jsp" %>	
									</div>
									<div id="two_col_right_links">
										<%@include file="/WEB-INF/jsps/general/botverse_incl/botverse_index_sect_right.jsp" %>	
									</div>
									<%-- (end) ================= --%>
							</td>							
						</tr>						
						<%-- ==== Print Map Reduce Top Terms ====  --%>
						<%-- 
						<tr>
							<td>
								 <c:forEach items="${popularwordmap}" var="curWord" varStatus="status">
								 	<c:out value="${curWord}" />
								</c:forEach>
							</td>
						</tr>
						--%>
						<%-- ==== End of Top Terms ==== ---%>						
						</table>
					</div>
					<%-- ==== End Next Set of Data, Recent Links ==== --%>					
				</td>
				<td valign="top" align="right">										
					<!-- table just for search -->
					<div class="home_search_wrapper">
						<table class="sample" cellspacing="0" cellpadding="0">
						<tr>
							<th>
								&nbsp;Search
							</th>						
						</tr>
						<tr>
						<td>
								<!-- Begin Search Form -->
								<form method="get" action="<c:url value="/spring/search/search.html" />">
									<table>
									<tbody>
										<tr>
										<td>
											<input name="query" size="16" value="" />
										</td>
										<td>
											<input type="submit" value=" Search " />
										</td>
										</tr>
									</tbody>
									</table>
									<input type="hidden" name="querymode" value="enabled" />
								</form>
								<!-- End of Form -->
								<div style="text-align: right; margin-top: 4px;">								
									<a href="<c:url value="/spring/botverse/rss/botverse_rss.html" />" class="index_img">
										<img style="border: 0px solid #FFF;" src="<c:url value="/company/images/rss.gif"  />" width="36" height="15" alt="rss link" />
									</a>
								</div>
						</td>
						</tr>
						</table>
					</div>
					<!-- End of Table for search -->					
					<%-- ============================== --%>
					<%-- New table for botverse image / media feeds / hot topics --%>
					<%-- ============================== --%>
					<table border="0">
					<tr>
						<td style="width: 100%" rowspan="2" valign="top">
							<%-- ============================== --%>
							<%-- Hot Topic Section (deprecated) --%>
							<%-- ============================== --%>							
						</td>
					    <td align="right">
					    	<%-- == Botlist Logo Image == --%>
							<a href="<c:url value="/spring/botverse/botverse.html" />" class="index_img">
						 		<img src="<c:url value="/company/images/BoxLogoVertical3.jpg" />" border="0" alt="Logo Vertical" width="130" height="104" />
							</a>
						</td>
					</tr>						
					<tr>
						<td valign="top">
						<%-- ============================== --%>
						<%-- Display Media Table if enabled --%>
						<%-- ============================== --%>
						<c:if test="${mediaListEnabled}">
						 <div style="margin-top: 8px;">
							<table cellspacing="0" cellpadding="0">
							<tr>
								<td style="background-color: #e8e8e8; padding: 4px; width: 100%;" valign="top">
									<span style="background-color: #e8e8e8; padding: 4px; width: 100%;">
									<b>related media feeds</b>
									</span>
								</td>						
							</tr>
							<c:forEach items="${mediaList}" var="curmedia" varStatus="status">
								<tr>
									<td valign="top">
										<%-- Media Section is made up table (image, title) --%>
										<table cellspacing="0" cellpadding="12" width="100%">
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
								</tr>
							</c:forEach>
							</table> <%-- End of Table media feeds --%>
						  </div>					
						</c:if>
						<%-- ============================== --%>
						<%-- End of Display Media table --%>
						<%-- ============================== --%>					
						</td>
						</tr>
						<%-- Table containg media feeds / botlist image --%>
					</table>			
					<%-- Right Section for media feeds / rss link, etc --%>					
				</td>
				</tr>				
				<%-- Header Content --%>	
				<tr>
					<td colspan="2" align="right">
						<div style="text-align: right">
						<h2 class="bot_splashinfo">
							Botlist contains fresh, user driven content.
						</h2>
						</div>										
					</td>
				</tr> 				
				</table> <%-- End of Header Content --%>
				<!-- End Data/Image Table -->					
				</div>
			</div>								
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>  
<div>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript"></script>
<script type="text/javascript">
_uacct = "UA-286501-2";
urchinTracker();
</script>
</div>
		
</body>
</html>