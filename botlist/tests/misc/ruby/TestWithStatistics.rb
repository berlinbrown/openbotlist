##
## Berlin Brown
##
## Behaviour Testing for botlist
## Also see:
## http://www.javalobby.org/java/forums/t19807.html
## For information on hibernate statistics

require 'java'
require File.join(File.dirname(__FILE__), 'ruby', 'BaseStatisticTest') unless defined? TestWithStatistics

class LinkTestWithStatistics < BaseTestWithStatistics
  
  def test()	
    c = @ac.getBean("radController")    
    # Run the standard botverse, entity links query
    dao = c.entityLinksDao
    query = "from org.spirit.bean.impl.BotListEntityLinks as links order by links.createdOn desc"    
    links = dao.pageEntityLinks(query, 0, 4)
    links.each { |eLink|
		puts eLink.urlTitle
	}
    sessFact = dao.sessionFactory
    logStatistics(sessFact)
	logQueryStatistics(sessFact, query)
  end
  
  def to_s
    "TestWithStatistics"
  end
end

t = LinkTestWithStatistics.new($context)
t.test()
t

## End of Script
