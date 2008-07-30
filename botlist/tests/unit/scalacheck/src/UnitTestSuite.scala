package org.botnode.botlist.tests

import org.scalacheck._
import org.scalacheck.Test._
import org.scalacheck.Gen._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._

object CheckUnitTestSuite extends Application {
  println("INFO: Unit Test Suite: running")
  //ExampleTests.runTests
  TestHtmlEncode.runTests
  println("INFO: TestSuite: done")
}
