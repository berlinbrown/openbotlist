//
// Misc scalacheck lift tests
// References:
// [1] http://code.google.com/p/scalacheck/wiki/UserGuide

package org.botnode.botlist.tests

import org.scalacheck._
import org.scalacheck.Test._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalacheck.ConsoleReporter.testStatsEx

object ExampleTests {
  def runTests() = {
	/**
	 * SmallInteger defines a generator that generates integers 
	 * between 0 and 100, inclusively. Generators will be 
	 * described closer in a later section. propSmallInteger 
	 * simply specifies that each integer generated should be 
	 * in the correct range. The forAll method is good 
	 * to use when you want to control the data generation 
	 * by specifying exactly which generator that should be used, 
	 * and not rely on a default generator for the given type.
	 * -- ScalaCheck User Guide.
	 */
	val smallInteger = Gen.choose(0, 10)
	val smallEvenInteger = Gen.choose(0,200) suchThat (_ % 2 == 0)
	val prop_SmallInteger = Prop.forAll(smallInteger)(n => {
	  (n >= 0)
	})
	
	val propReverseList = property( (l: List[String]) => l.reverse.reverse == l )
	val propConcatString = property( (s1: String, s2: String) => s1.concat(s2).endsWith(s2) )
		
	val prop_commutative = property((a: Int, b: Int) => {
	  a + b == b + a} )  
  
	testStatsEx("Commutativity", check(prop_commutative))
	Test.check(prop_SmallInteger)
	Test.check(propReverseList)
	Test.check(propConcatString)
  }
}
