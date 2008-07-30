//
// Misc scalacheck lift tests
//

package org.spirit.check.testsuite

import org.spirit.check.tests._
import org.scalacheck._
import org.scalacheck.Test._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._

object CheckTestSuite extends Application {
  println("TestSuite: starting")
  ExampleTests.runTests
  AgentMessageTest.runTests
  println("TestSuite: done")

}
