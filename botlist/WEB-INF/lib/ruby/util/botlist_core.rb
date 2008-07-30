##
## Berlin Brown
## 04/07/2007
##

require 'java'

include_class 'org.spirit.util.BotListConsts' unless defined? BotListConsts
include_class 'org.spirit.form.BotListGenericPagingForm' unless defined? BotListGenericPagingForm

module BotListCore

  def BotListCore.createPagingForm(totalLinks, nextPage)
    totalPages = totalLinks / BotListConsts::MAX_RESULTS_PAGE
    pagingForm = BotListGenericPagingForm.new
    pagingForm.curPage = nextPage
    pagingForm.pageOffset = (nextPage * BotListConsts::MAX_RESULTS_PAGE)
    pagingForm.begin = nextPage - 1
    if pagingForm.begin < 0
      pagingForm.begin = 0
    end
    pagingForm.end = pagingForm.begin + 4
    pagingForm
  end

  # End of Module
end

