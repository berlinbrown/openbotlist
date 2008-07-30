# bowling_spec.rb

require File.join(File.dirname(__FILE__), 'bowling_exampl')

describe Bowling do
  before(:each) do
    @bowling = Bowling.new
  end

  it "should score 0 for gutter game" do
    20.times { @bowling.hit(0) }
    @bowling.score.should == 0
  end
end
