/* Created on Nov 2, 2006 
 *** Copyright 2007 Berlin Brown
 *** Copyright 2006-2007 Newspiritcompany.com
 *** 
 *** This SOURCE FILE is licensed to NEWSPIRITCOMPANY.COM.  Unless
 *** otherwise stated, use or distribution of this program 
 *** for commercial purpose is prohibited.
 *** 
 *** See LICENSE.BOTLIST for more information.
 ***
 *** The SOFTWARE PRODUCT and CODE are protected by copyright and 
 *** other intellectual property laws and treaties. 
 ***  
 *** Unless required by applicable law or agreed to in writing, software
 *** distributed  under the  License is distributed on an "AS IS" BASIS,
 *** WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 *** implied.
 */

package org.spirit.spring;

import javax.servlet.http.HttpServletRequest;

import org.spirit.apps.foaf.BotListEntityTypeFoafDAO;
import org.spirit.bean.impl.BotListUserVisitLog;
import org.spirit.dao.BotListAclControlDAO;
import org.spirit.dao.BotListAclManagerDAO;
import org.spirit.dao.BotListActiveMediaFeedsDAO;
import org.spirit.dao.BotListAdminMainBannerDAO;
import org.spirit.dao.BotListCatGroupTermsDAO;
import org.spirit.dao.BotListCatLinkGroupsDAO;
import org.spirit.dao.BotListCityListingDAO;
import org.spirit.dao.BotListCoreSettings;
import org.spirit.dao.BotListCoreUsersDAO;
import org.spirit.dao.BotListDeveloperUsersDAO;
import org.spirit.dao.BotListEntityLinksDAO;
import org.spirit.dao.BotListForumGroupDAO;
import org.spirit.dao.BotListGroupControlDAO;
import org.spirit.dao.BotListGroupLinksDAO;
import org.spirit.dao.BotListGroupManagerDAO;
import org.spirit.dao.BotListLinkGroupsDAO;
import org.spirit.dao.BotListMediaFeedsDAO;
import org.spirit.dao.BotListPostImageMetadataDAO;
import org.spirit.dao.BotListPostListingDAO;
import org.spirit.dao.BotListPostSectionsDAO;
import org.spirit.dao.BotListProfileSettingsDAO;
import org.spirit.dao.BotListSearchQueryFiltersDAO;
import org.spirit.dao.BotListUserCommentsDAO;
import org.spirit.dao.BotListUserLinkDAO;
import org.spirit.dao.BotListUserLinksDAO;
import org.spirit.dao.BotListUserVisitLogDAO;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 * Spring Controller that interfaces between spring and jruby; reads the ruby script and invokes the
 * correct model and view.
 * 
 * @author Berlin Brown 
 */
public abstract class BotListRubyDAOHandler 
		extends AbstractFormController {

	/**
	 * User Link Data Access Object.
	 */
	private BotListUserLinkDAO userLinkDAO = null;

	/**
	 * User Visit Audit Object.
	 */
	private BotListUserVisitLogDAO userVisitAudit = null;

	/**
	 * Post Listing DAO.
	 */
	private BotListPostListingDAO postListingDAO = null;
	
	/**
	 * City Listing.
	 */
	private BotListCityListingDAO cityListingDao = null;
	
	private BotListEntityLinksDAO entityLinksDAO = null;
	
	private BotListUserCommentsDAO userCommentsDao = null;
	
	private BotListForumGroupDAO forumGroupDao = null;
	
	private BotListPostSectionsDAO postSectionsDao = null;
	
	private BotListPostImageMetadataDAO postImageMetadataDao = null;
	
	private BotListCoreUsersDAO coreUsersDao = null;
	
	private BotListGroupLinksDAO groupLinksDao = null;
	
	private BotListLinkGroupsDAO linkGroupsDao = null;
	
	private BotListAclControlDAO aclControlDao = null;
	
	private BotListAclManagerDAO aclManagerDao = null;
	
	private BotListGroupControlDAO groupControlDao = null;
	
	private BotListGroupManagerDAO groupManagerDao = null;
	
	private BotListProfileSettingsDAO profileSettingsDao = null;
	
	private BotListAdminMainBannerDAO adminMainBannerDao = null;
	
	private BotListMediaFeedsDAO mediaFeedsDao = null;
	
	private BotListActiveMediaFeedsDAO activeMediaFeedsDao = null;
	
	private BotListCoreSettings coreSettings = null;
	
	private BotListSearchQueryFiltersDAO searchQueryFiltersDao = null;
	
	private BotListDeveloperUsersDAO developerUsersDao = null;
	
	private BotListUserLinksDAO userLinksDao = null;
	
	private BotListCatLinkGroupsDAO catLinkGroupsDao = null;
	
	private BotListCatGroupTermsDAO catGroupTermsDao = null;
	
	private BotListEntityTypeFoafDAO entityTypeFoafDao = null;
	
	/******************************************************
	 * 
	 *  Audit Utilities
	 *  
	 ******************************************************/
	
	/**
	 * @return the aclControlDao
	 */
	public BotListAclControlDAO getAclControlDao() {
		return aclControlDao;
	}

	/**
	 * @param aclControlDao the aclControlDao to set
	 */
	public void setAclControlDao(BotListAclControlDAO aclControlDao) {
		this.aclControlDao = aclControlDao;
	}

	/**
	 * @return the aclManagerDao
	 */
	public BotListAclManagerDAO getAclManagerDao() {
		return aclManagerDao;
	}

	/**
	 * @param aclManagerDao the aclManagerDao to set
	 */
	public void setAclManagerDao(BotListAclManagerDAO aclManagerDao) {
		this.aclManagerDao = aclManagerDao;
	}

	/**
	 * @return the groupControlDao
	 */
	public BotListGroupControlDAO getGroupControlDao() {
		return groupControlDao;
	}

	/**
	 * @param groupControlDao the groupControlDao to set
	 */
	public void setGroupControlDao(BotListGroupControlDAO groupControlDao) {
		this.groupControlDao = groupControlDao;
	}

	/**
	 * @return the groupManagerDao
	 */
	public BotListGroupManagerDAO getGroupManagerDao() {
		return groupManagerDao;
	}

	/**
	 * @param groupManagerDao the groupManagerDao to set
	 */
	public void setGroupManagerDao(BotListGroupManagerDAO groupManagerDao) {
		this.groupManagerDao = groupManagerDao;
	}

	/**
	 * @return the coreUsersDao
	 */
	public BotListCoreUsersDAO getCoreUsersDao() {
		return coreUsersDao;
	}

	/**
	 * @param coreUsersDao the coreUsersDao to set
	 */
	public void setCoreUsersDao(BotListCoreUsersDAO coreUsersDao) {
		this.coreUsersDao = coreUsersDao;
	}

	/**
	 * @return the postSectionsDao
	 */
	public BotListPostSectionsDAO getPostSectionsDao() {
		return postSectionsDao;
	}

	/**
	 * @param postSectionsDao the postSectionsDao to set
	 */
	public void setPostSectionsDao(BotListPostSectionsDAO postSectionsDao) {
		this.postSectionsDao = postSectionsDao;
	}

	/**
	 * @return the userCommentsDao
	 */
	public BotListUserCommentsDAO getUserCommentsDao() {
		return userCommentsDao;
	}

	/**
	 * @param userCommentsDao the userCommentsDao to set
	 */
	public void setUserCommentsDao(BotListUserCommentsDAO userCommentsDao) {
		this.userCommentsDao = userCommentsDao;
	}

	/**
	 * To call, use:
	 * 
	 */
	public void auditLogPage(HttpServletRequest request, String curPage) {
		if (userVisitAudit != null) {
			BotListUserVisitLog link = new BotListUserVisitLog();
			link.setRequestUri(request.getRequestURI());
			link.setRequestPage(curPage);
			link.setHost(request.getHeader("host"));
			link.setReferer(request.getHeader("referer"));
			link.setRemoteHost(request.getRemoteAddr());
			link.setUserAgent(request.getHeader("user-agent"));
			userVisitAudit.createVisitLog(link);
		}
	}
	
	/******************************************************
	 * 
	 *  Set the Data Access Object
	 *  
	 ******************************************************/
	public void setUserLinkDao(BotListUserLinkDAO dao) {
		this.userLinkDAO = dao;
	}

	public BotListUserLinkDAO getUserLinkDao() {
		return this.userLinkDAO;
	}

	public void setUserVisitLogDao(BotListUserVisitLogDAO dao) {
		this.userVisitAudit = dao;
	}

	public BotListUserVisitLogDAO getUserVisitLogDao() {
		return this.userVisitAudit;
	}

	
	/**
	 * @return the postListingDAO
	 */
	public BotListPostListingDAO getPostListingDao() {
		return postListingDAO;
	}

	/**
	 * @param postListingDAO the postListingDAO to set
	 */
	public void setPostListingDao(BotListPostListingDAO postListingDAO) {
		this.postListingDAO = postListingDAO;
	}

	/**
	 * @return the cityListingDao
	 */
	public BotListCityListingDAO getCityListingDao() {
		return cityListingDao;
	}

	/**
	 * @param cityListingDao the cityListingDao to set
	 */
	public void setCityListingDao(BotListCityListingDAO cityListingDao) {
		this.cityListingDao = cityListingDao;
	}

	/**
	 * @return the entityLinksDAO
	 */
	public BotListEntityLinksDAO getEntityLinksDao() {
		return entityLinksDAO;
	}

	/**
	 * @param entityLinksDAO the entityLinksDAO to set
	 */
	public void setEntityLinksDao(BotListEntityLinksDAO entityLinksDAO) {
		this.entityLinksDAO = entityLinksDAO;
	}

	/**
	 * @return the forumGroupDao
	 */
	public BotListForumGroupDAO getForumGroupDao() {
		return forumGroupDao;
	}

	/**
	 * @param forumGroupDao the forumGroupDao to set
	 */
	public void setForumGroupDao(BotListForumGroupDAO forumGroupDao) {
		this.forumGroupDao = forumGroupDao;
	}

	/**
	 * @return the postImageMetadataDao
	 */
	public BotListPostImageMetadataDAO getPostImageMetadataDao() {
		return postImageMetadataDao;
	}

	/**
	 * @param postImageMetadataDao the postImageMetadataDao to set
	 */
	public void setPostImageMetadataDao(BotListPostImageMetadataDAO postImageMetadataDao) {
		this.postImageMetadataDao = postImageMetadataDao;
	}

	/**
	 * @return the groupLinksDao
	 */
	public BotListGroupLinksDAO getGroupLinksDao() {
		return groupLinksDao;
	}

	/**
	 * @param groupLinksDao the groupLinksDao to set
	 */
	public void setGroupLinksDao(BotListGroupLinksDAO groupLinksDao) {
		this.groupLinksDao = groupLinksDao;
	}

	/**
	 * @return the linkGroupsDao
	 */
	public BotListLinkGroupsDAO getLinkGroupsDao() {
		return linkGroupsDao;
	}

	/**
	 * @param linkGroupsDao the linkGroupsDao to set
	 */
	public void setLinkGroupsDao(BotListLinkGroupsDAO linkGroupsDao) {
		this.linkGroupsDao = linkGroupsDao;
	}

	/**
	 * @return the profileSettingsDao
	 */
	public BotListProfileSettingsDAO getProfileSettingsDao() {
		return profileSettingsDao;
	}

	/**
	 * @param profileSettingsDao the profileSettingsDao to set
	 */
	public void setProfileSettingsDao(BotListProfileSettingsDAO profileSettingsDao) {
		this.profileSettingsDao = profileSettingsDao;
	}

	public BotListAdminMainBannerDAO getAdminMainBannerDao() {
		return adminMainBannerDao;
	}

	public void setAdminMainBannerDao(BotListAdminMainBannerDAO adminMainBannerDao) {
		this.adminMainBannerDao = adminMainBannerDao;
	}

	public BotListMediaFeedsDAO getMediaFeedsDao() {
		return mediaFeedsDao;
	}

	public void setMediaFeedsDao(BotListMediaFeedsDAO mediaFeedsDao) {
		this.mediaFeedsDao = mediaFeedsDao;
	}

	public BotListActiveMediaFeedsDAO getActiveMediaFeedsDao() {
		return activeMediaFeedsDao;
	}

	public void setActiveMediaFeedsDao(
			BotListActiveMediaFeedsDAO activeMediaFeedsDao) {
		this.activeMediaFeedsDao = activeMediaFeedsDao;
	}

	public BotListCoreSettings getCoreSettings() {
		return coreSettings;
	}

	public void setCoreSettings(BotListCoreSettings coreSettings) {
		this.coreSettings = coreSettings;
	}

	public BotListSearchQueryFiltersDAO getSearchQueryFiltersDao() {
		return searchQueryFiltersDao;
	}

	public void setSearchQueryFiltersDao(BotListSearchQueryFiltersDAO searchQueryFiltersDao) {
		this.searchQueryFiltersDao = searchQueryFiltersDao;
	}

	public BotListDeveloperUsersDAO getDeveloperUsersDao() {
		return developerUsersDao;
	}

	public void setDeveloperUsersDao(BotListDeveloperUsersDAO developerUsersDao) {
		this.developerUsersDao = developerUsersDao;
	}

	public BotListUserLinksDAO getUserLinksDao() {
		return userLinksDao;
	}

	public void setUserLinksDao(BotListUserLinksDAO userLinksDao) {
		this.userLinksDao = userLinksDao;
	}

	public BotListCatGroupTermsDAO getCatGroupTermsDao() {
		return catGroupTermsDao;
	}

	public void setCatGroupTermsDao(BotListCatGroupTermsDAO catGroupTermsDao) {
		this.catGroupTermsDao = catGroupTermsDao;
	}

	public BotListCatLinkGroupsDAO getCatLinkGroupsDao() {
		return catLinkGroupsDao;
	}

	public void setCatLinkGroupsDao(BotListCatLinkGroupsDAO catLinkGroupsDao) {
		this.catLinkGroupsDao = catLinkGroupsDao;
	}

	public BotListEntityTypeFoafDAO getEntityTypeFoafDao() {
		return entityTypeFoafDao;
	}

	public void setEntityTypeFoafDao(BotListEntityTypeFoafDAO entityTypeFoafDao) {
		this.entityTypeFoafDao = entityTypeFoafDao;
	}

}