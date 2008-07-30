//
// Misc scalacheck lift tests
//

package org.spirit.check.tests

import org.spirit.lift.agents.model._

import org.scalacheck._
import org.scalacheck.Test._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._

object ExampleTests {
  def runTests() = {
	val prop_ConcatLists = property((l1: List[Int], l2: List[Int]) => 
	  l1.size + l2.size == (l1 ::: l2).size)

	Test.check(prop_ConcatLists)
  }
}
