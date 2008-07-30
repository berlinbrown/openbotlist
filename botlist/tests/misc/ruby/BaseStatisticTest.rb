##
## Berlin Brown
##
## Behaviour Testing for botlist (base class)
## Also see:
## http://www.javalobby.org/java/forums/t19807.html
## For information on hibernate statistics

require 'java'
include_class "org.spirit.test.JRubyTestCase"

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class BaseTestWithStatistics < JRubyTestCase
  
  attr_accessor :ac
  def initialize(context)
    @ac = context
    @log = LogFactory::getLog("org.hibernate.SQL")
  end
 
  def logQueryStatistics(sessFact, strHbmQuery)
	if sessFact.nil? or @log.nil?
		raise "Invalid Session Factory"
	end
	  
	# Note: the hibernate/spring setting enable statistics must be turned on
	stats = sessFact.getStatistics()
	stats.setStatisticsEnabled(true)
	  
	queryStats = stats.getQueryStatistics(strHbmQuery)
	# total hits on cache by this query.
	cacheHitCount = queryStats.getCacheHitCount()
	@log.info("STAT: Cache Hit Count: #{cacheHitCount}")
	
	# total misses on cache by this query.
	missCount = queryStats.getCacheMissCount()
	@log.info("STAT: Miss Count: #{missCount}")
	
	# total number of objects put into cache by this query execution
	cachePutCount = queryStats.getCachePutCount()
	@log.info("STAT: Put Count: #{cachePutCount}")
	
	# Number of times this query has been invoked
	execCount = queryStats.getExecutionCount()
	@log.info("STAT: Times Invoked: #{execCount}")
	
	# average time for invoking this query.
	execAvTime = queryStats.getExecutionAvgTime()
	@log.info("STAT: Exec Invoke Query: #{execAvTime}")
	
	# maximum time incurred by query execution
	maxTime = queryStats.getExecutionMaxTime()
	@log.info("STAT: Max Time: #{maxTime}")
	
	# minimum time incurred by query execution
	minTime = queryStats.getExecutionMinTime()
	@log.info("STAT: Min Time: #{minTime}")
	
	#Number of rows returned over all invocations of this query
	rowCount = queryStats.getExecutionRowCount()
	@log.info("STAT: Row Count: #{rowCount}")

  end
 
  def logStatistics(sessFact)
	if sessFact.nil? or @log.nil?
		raise "Invalid Session Factory"
	end

	# Note: the hibernate/spring setting enable statistics must be turned on
	stats = sessFact.getStatistics()
	stats.setStatisticsEnabled(true)
	
	# Statistic code from
	# R.J. Lorimer (see article above)
	# Number of connection requests. Note that this number represents 
    # the number of times Hibernate asked for a connection, and 
    # NOT the number of connections (which is determined by your 
    # pooling mechanism).
    @log.info("========================")
    @log.info(" * Test Statistics for #{self}")
    @log.info("========================")
	connCount = stats.getConnectCount()	
	@log.info("STAT: Number of connection requests. #{connCount}")
	
    # Number of flushes done on the session (either by client code or 
    # by hibernate).
	flushCount = stats.getFlushCount()
	@log.info("STAT: Flush Count: #{flushCount}")
	
    # The number of completed transactions (failed and successful).
	transCount = stats.getTransactionCount()
	@log.info("STAT: Transaction Count: #{transCount}")
	
    # The number of transactions completed without failure
	sTransCount = stats.getSuccessfulTransactionCount()
	@log.info("STAT: Succ Transaction Count: #{sTransCount}")
	
    # The number of sessions your code has opened.
	openCount = stats.getSessionOpenCount()
	@log.info("STAT: Session Count: #{openCount}")
	
    # The number of sessions your code has closed.
	sessCloseCount = stats.getSessionCloseCount()
	@log.info("STAT: Session Close Count: #{sessCloseCount}")
	
    # All of the queries that have executed.
	#gQuery = stats.getQueries()
	#@log.info("STAT: Query: #{gQuery}")
	
    # Total number of queries executed.
	execCount = stats.getQueryExecutionCount()
	@log.info("STAT: Exec Count: #{execCount}")
	
    # Time of the slowest query executed.
	execMaxTime = stats.getQueryExecutionMaxTime()	
	@log.info("STAT: Max Time Slowest: #{execMaxTime}")
	
    # the number of collections fetched from the DB.
	fCount1 = stats.getCollectionFetchCount()
	@log.info("STAT: Fetch Collections: #{fCount1}")
	
    # The number of collections loaded from the DB.
	collLoadCount = stats.getCollectionLoadCount()
	@log.info("STAT: Collections Loaded: #{collLoadCount}")
	
    # The number of collections that were rebuilt
	recreateCount = stats.getCollectionRecreateCount()
	@log.info("STAT: Recreate Count: #{recreateCount}")
	
    # The number of collections that were 'deleted' batch.    
	colllRemoveCount = stats.getCollectionRemoveCount()
	@log.info("STAT: Remove Count: #{colllRemoveCount}")
	
    # The number of collections that were updated batch.
	collUpdateCount = stats.getCollectionUpdateCount()
	@log.info("STAT: Coll Update Count: #{collUpdateCount}")
 
    # The number of your objects deleted.
	eDeleteCount = stats.getEntityDeleteCount()
	@log.info("STAT: Delete Count: #{eDeleteCount}")
		
    # The number of your objects fetched.
	fCount = stats.getEntityFetchCount()
	@log.info("STAT: Fetch Count: #{fCount}")
	
    # The number of your objects actually loaded (fully populated).
	eLoadCount = stats.getEntityLoadCount()
	@log.info("STAT: Load Count: #{eLoadCount}")
	
    # The number of your objects inserted.
	insertCount = stats.getEntityInsertCount()
	@log.info("STAT: Insert Count: #{insertCount}")
	
    # The number of your object updated.
	eUpdateCt = stats.getEntityUpdateCount()
	@log.info("STAT: Update Count: #{eUpdateCt}")
	@log.info("========================")
	
	return stats
  end
  
  def to_s
    "Current Test"
  end
end

## End of Script
