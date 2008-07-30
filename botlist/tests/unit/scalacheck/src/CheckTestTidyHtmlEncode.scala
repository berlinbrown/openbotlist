//
// Misc scalacheck lift tests
// References:
// [1] http://code.google.com/p/scalacheck/wiki/UserGuide

package org.botnode.botlist.tests

import scala.Console.println
import org.scalacheck._
import org.scalacheck.Test._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalacheck.ConsoleReporter.testStatsEx

import org.spirit.loadtest.TidyUtilHtmlEncode.encode

/**
 * Also some common set operations:
 * [1] http://blog.lostlake.org/index.php?/archives/41-Scala-Idioms,-Step-1,-Lists-and-Maps.html
 * [2] http://scala.sygneca.com/common/string-manipulation
 *
 * List(-1,-2, 1,2,3) diff List(1,2,3) 
 * List(-1,-2, 1,2,3) intersect List(1,2,3) 
 * List(-1,-2, 1,2,3) union List(1,2,3, 77) 
 * List(-1,-2, 1,2,3, -1, -2, 1,8,3).removeDuplicates 
 */
object TestHtmlEncode {
  def runTests() = {
	
	val bad_entity = List.fromString("<>")
	val examples = "<html>" :: ">>>>> <<<<" :: "Dog &&&" :: "Normal Msg" :: Nil      
	val html_entity_gen = Gen.elementsFreq(
	  (1, examples(0)),
	  (1, examples(1)),
	  (1, examples(2)),
	  (2, examples(3))
	)
	val prop_encode = Prop.forAll(html_entity_gen) (n => {	  
	  val lst = List.fromString(encode(n))
	  val bad_set = lst intersect bad_entity
	  !(bad_set.length > 0)
	}) 
	Test.check(prop_encode)
  }
}
