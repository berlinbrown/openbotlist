<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>

										<table cellspacing="0" cellpadding="0">
										<c:forEach items="${linklistings}"
													var="listing" varStatus="status">
													
											<%-- ********************** --%>
											<%-- Begin row production for botverse links --%>
											<%-- ********************** --%>
											<tr>					
												<td colspan="3">
													<div class="home_for_url_group">
														<a class="linklist_objlinks" href="<c:url value="${listing.mainUrl}" />" >
															<c:out value='${botlistutil:getMaxWord(listing.urlTitle, 48)}' />
														</a>
														(<a class="linklist_comments_host" href="<c:url value="/spring/botverse/linkviewcomments.html?viewid=${listing.id}" />">#</a>)
														<%-- Next line, hostname information --%>
														<div style="font-size: 10px: color: #777; margin-left: 6px;">
														 <span class="home_source_label">source:</span> <c:out value="${listing.hostnameDisplay}" /> <a href="<c:out value="${listing.hostnameDisplayUrl}" />" class="linklist_comments_host">+</a>
														</div>
													</div>
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
													<div class="linklist_comments_txt">						 	
													 <span class="linklist_comments_date">						
														<botlistutil:timePast dateValue="${listing.createdOn.time}" />							
													 </span>
													</div>
												</td>
												<td>
												  <span style="margin-left: 0px; font-size: 10px">
														<span class="linklist_comments_date"><strong>by
															<c:out value="${listing.fullName}" /></strong>
														</span>
												  </span>
												</td>
												</tr>
												</table>
												<!-- End of inner table (loop) -->				
											</td>
											</tr>
											<%-- ********************** --%>
											<%-- End row production for botverse links --%>
											<%-- ********************** --%>															
										</c:forEach>							
										</table>
										<%-- ================= --%>
										<%-- End of table for botverse links --%>
										<%-- ================= --%>
										<div style="margin-top: 6px; background-color: #f3f3f3; padding: 6px; width: 30%">
											<a href="<c:url value="/spring/botverse/botverse.html?filterset=mostrecent" />" class="linklist_botnav">view more</a> 
										</div>
										