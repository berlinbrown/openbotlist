*******************************************************
** Copyright 2006-2007 Newspiritcompany.com
** 
** This SOURCE FILE is licensed to NEWSPIRITCOMPANY.COM.  Unless
** otherwise stated, use or distribution of this program 
** for commercial purpose is prohibited.
** 
** [License modified], See LICENSE.BOTLIST for more information.
** Updated: 11/10/2007
*******************************************************
 
Overview
===================

Botlist is an open source social bookmarking web application that contains a news aggregating/news/article submission site. There is also an ads listing section so that users can post personal/ad profiles. The application itself is created with the following libraries; JRuby and J2EE's Spring framework as a middleware piece, Hibernate for object relational mapping (ORM). It is designed to run with Tomcat but should work with other J2EE servers. Search functionality uses the Lucene API.
Other Features

News aggregation (some links will be included); over hundred and fifty thousands links thus far.

You can reply to comments, up or down vote the news articles, post new article links similar to other social bookmarking sites.

- Comma delimited output for easy, text based access (for developers)
- Numerous documentation on architecture
- Discussion Forums section
- User profiles
- Ad/Profile listing section, listed by city
- Sort search by relevancy, submit date
- Can also operate as a simple bug tracking system

Visit Botlist Home
===================

http://botspiritcompany.com/botlist/

View the Source
===================
http://openbotlist.googlecode.com/svn/
 
===================
 * Technology:
===================
 
 Java, Spring, JRuby, Hibernate, Tomcat, JSTL,  MySQL
 
 * Resources:

 * Container:
 
 Tested with Tomcat
 
 --------------------------------------
  Third-party libraries (major libraries and versions)
  Updated: 3/6/2008
 --------------------------------------
	- Spring 2.5.2 (http://www.springframework.org/)
	- Hibernate 3.2.6 (http://www.hibernate.org)
	- ANTLR 2.7.6 (http://www.antlr.org)
	- JRuby 1.0.3 (http://jruby.codehaus.org/)
 
===================
 URGENT!!! Major Security Issues
=================== 

 You may find some major security issues when working with this project.  These
 bad practices were followed for speed of development.  Before deploying this 
 project on a production server.  Make sure do the following and this list is incomplete:
 
 1. Currently, the botlist project (directory) can be copied as is to the J2EE servers
 webapps directory.  This is a bad practice and should be avoided.  Ideally, you would not
 copy the entire project to a webapps directory.  You should create a project separate from
 the deployment directory; this can be accomplished by changes to the ant scripts.
 
 (All of these issues will be resolved in future source releases)

===================
 * For Developers:
===================

 For developers that want to build the botlist project, visit the 'docs/QuickStart' directory and
 also see the other documents in the 'docs' directory.

===================
 Contact:
===================
 
 berlin.brown at gmail.com
 
 