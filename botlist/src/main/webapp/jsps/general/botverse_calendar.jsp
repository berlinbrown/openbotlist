	<tr>
		<td colspan="3">							
					<a class="linklist_objlinks" href="<c:url value="${listing.mainUrl}" />" >
							<c:out value="${listing.urlTitle}" />
					</a>
					<%-- Add custom tag here, find hostname --%>
					<span class="linklist_comments_host">
					&nbsp;(<botlistutil:hostname value="${listing.mainUrl}" />)
					</span>
		</td>
	</tr>
	<tr>
		<td>
			<%-- Inner table for data/status information --%>
			<table>
				<tr>
					<td>
						<div style="margin-left: 10px;" class="linklist_comments_txt">
							<c:set var="curStatusCount" value="${status.count}" />
							<c:set var="curPageOffset" value="${command.pagingform.pageOffset}" />
							<c:out value="${curStatusCount + curPageOffset}" />)
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
					  <a class="linklist_comments" href="<c:url value="/spring/botverse/linkviewcomments.html?viewid=${listing.id}" />">details / comments (<c:out value="${listing.commentsCount}" />)</a> 					  
					  </span>						
					  | <span class="linklist_comments"><a class="linklist_comments" href="<c:url value="/spring/botverse/linkaddcomment.html?viewid=${listing.id}" />">add comment</a></span>						
					</td>
				</tr>			
			</table> <%-- End of inner table (loop) --%>					
		</td>
	</tr>